package com.example.studentinfo.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.studentinfo.PendingStudentList;
import com.example.studentinfo.R;
import com.example.studentinfo.domain.PendingStudent;
import com.example.studentinfo.domain.Student;
import com.example.studentinfo.enums.CommonStatus;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class PendingStudentAdapter extends FirebaseRecyclerAdapter<PendingStudent, PendingStudentAdapter.MyViewHolder> {
    public PendingStudentAdapter(@NonNull FirebaseRecyclerOptions<PendingStudent> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final MyViewHolder holder, final int position, @NonNull final PendingStudent model) {
        holder.studentId.setText(model.getStudentId());
        holder.firstName.setText(model.getFirstName());
        holder.lastName.setText(model.getLastName());

        Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext()).setContentHolder(new ViewHolder(R.layout.update_pending_student)).setExpanded(true, 940).create();

                View myView = dialogPlus.getHolderView();

                final EditText pURL = myView.findViewById(R.id.pSTDUiImgURLId);
                final EditText pFirstName = myView.findViewById(R.id.pSTDUFirstNameId);
                final EditText pLastName = myView.findViewById(R.id.pSTDULastNameId);
                final EditText pPassword = myView.findViewById(R.id.pSTDUPasswordId);
                final EditText pTerm1Amount = myView.findViewById(R.id.pSTDUTerm1AmtId);
                final EditText pTerm2Amount = myView.findViewById(R.id.pSTDUTerm2AmtId);
                final EditText pTerm3Amount = myView.findViewById(R.id.pSTDUTerm3AmtId);
                final EditText pMobileNumber = myView.findViewById(R.id.pSTDUMobileNoId);
                ImageView submit = myView.findViewById(R.id.pSTDUSubmitBtnId);

                pURL.setText(model.getPurl());
                pFirstName.setText(model.getFirstName());
                pLastName.setText(model.getLastName());
                pPassword.setText(model.getPassword());
                pTerm1Amount.setText(model.getFirstTermAmount().toString());
                pTerm2Amount.setText(model.getSecondTermAmount().toString());
                pTerm3Amount.setText(model.getThirdTermAmount().toString());
                pMobileNumber.setText(model.getMobileNumber().toString());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String pTotalAmount = calculateTotal(pTerm1Amount.getText().toString(), pTerm2Amount.getText().toString(), pTerm3Amount.getText().toString());

                        final PendingStudent pendingStudentResource = new PendingStudent();
                        pendingStudentResource.setStudentId(model.getStudentId().toString());
                        pendingStudentResource.setFirstName(pFirstName.getText().toString());
                        pendingStudentResource.setLastName(pLastName.getText().toString());
                        pendingStudentResource.setPurl(pURL.getText().toString());
                        pendingStudentResource.setStatus(CommonStatus.ACTVE.toString());
                        pendingStudentResource.setCreatedDate(formatDate());
                        pendingStudentResource.setFirstTermAmount(pTerm1Amount.getText().toString());
                        pendingStudentResource.setSecondTermAmount(pTerm2Amount.getText().toString());
                        pendingStudentResource.setThirdTermAmount(pTerm3Amount.getText().toString());
                        pendingStudentResource.setFullAmount(pTotalAmount);
                        pendingStudentResource.setMobileNumber(pMobileNumber.getText().toString());
                        pendingStudentResource.setPassword(pPassword.getText().toString());

                        Map<String, Object> map = new HashMap<>();
                        map.put("purl", pURL.getText().toString());
                        map.put("firstName", pFirstName.getText().toString());
                        map.put("lastName", pLastName.getText().toString());
                        map.put("password", pPassword.getText().toString());
                        map.put("firstTermAmount", pTerm1Amount.getText().toString());
                        map.put("secondTermAmount", pTerm2Amount.getText().toString());
                        map.put("thirdTermAmount", pTerm3Amount.getText().toString());
                        map.put("status", CommonStatus.APPROVED.toString());
                        map.put("fullAmount", pTotalAmount);
                        map.put("mobileNumber", pMobileNumber.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("PendingStudent").child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                dialogPlus.dismiss();
                                sendSMS(pMobileNumber.getText().toString(), CommonStatus.APPROVED.toString());
                                saveApprovedStudent(pendingStudentResource);
                                FirebaseDatabase.getInstance().getReference().child("PendingStudent").child(getRef(position).getKey()).removeValue();
                                Toast.makeText(holder.img.getContext(), "Successfully Updated.", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialogPlus.dismiss();
                                Toast.makeText(holder.img.getContext(), "Error when updating!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Reject Pending Student");
                builder.setMessage("Are sure you want to reject?");
                builder.setIcon(R.drawable.warning);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("PendingStudent").child(getRef(position).getKey()).removeValue();
                        sendSMS(model.getMobileNumber().toString(), CommonStatus.REJECTED.toString());
                        Toast.makeText(holder.img.getContext(), "Successfully Rejected.", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pending_student, parent, false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img;
        TextView studentId, firstName, lastName;
        ImageView edit, delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView)itemView.findViewById(R.id.pStdImgId);
            studentId = (TextView)itemView.findViewById(R.id.pStdIdTextId);
            firstName = (TextView)itemView.findViewById(R.id.pStdFirstNameTextId);
            lastName = (TextView)itemView.findViewById(R.id.pStdLastNameTextId);
            edit = (ImageView)itemView.findViewById(R.id.pStdEditIconId);
            delete = (ImageView)itemView.findViewById(R.id.pStdDeleteIconId);
        }
    }

    private String calculateTotal(String term1Amount, String term2Amount, String term3Amount) {
        BigDecimal firstTermAmount = BigDecimal.ZERO;
        BigDecimal secondTermAmount = BigDecimal.ZERO;
        BigDecimal thirdTermAmount = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;

        firstTermAmount = new BigDecimal(term1Amount);
        secondTermAmount = new BigDecimal(term2Amount);
        thirdTermAmount = new BigDecimal(term3Amount);
        totalAmount = firstTermAmount.add(secondTermAmount).add(thirdTermAmount);

        return totalAmount.toString();
    }

    private void sendSMS(String mobileNumber, String status) {
        String message = "";
        SmsManager smsManager = SmsManager.getDefault();

        if(status.equalsIgnoreCase(CommonStatus.APPROVED.toString())) {
            message = "You are successfully approved, please enter following details to login. \n user name : STD206 \n password : abc123 \n (change this password after you logged in.)";
        } else if(status.equalsIgnoreCase(CommonStatus.REJECTED.toString())) {
            message = "You are rejected by Administrator. Please contact Student Affairs.";
        }

        smsManager.sendTextMessage(mobileNumber, null, message, null, null);
    }

    private void saveApprovedStudent(PendingStudent pendingStudent) {

        DatabaseReference dbRef;

        Student student = new Student();
        student.setStudentId(pendingStudent.getStudentId());
        student.setFirstName(pendingStudent.getFirstName());
        student.setLastName(pendingStudent.getLastName());
        student.setPurl(pendingStudent.getPurl());
        student.setStatus(pendingStudent.getStatus());
        student.setCreatedDate(pendingStudent.getCreatedDate());
        student.setFirstTermAmount(pendingStudent.getFirstTermAmount());
        student.setSecondTermAmount(pendingStudent.getSecondTermAmount());
        student.setThirdTermAmount(pendingStudent.getThirdTermAmount());
        student.setFullAmount(pendingStudent.getFullAmount());
        student.setMobileNumber(pendingStudent.getMobileNumber());
        student.setPassword(pendingStudent.getPassword());

        dbRef = FirebaseDatabase.getInstance().getReference().child("Student");
        dbRef.child(student.getStudentId()).setValue(student);

    }

    private String formatDate() {
        Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        return format.format(date);
    }
}
