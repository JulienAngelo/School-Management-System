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
import com.example.studentinfo.R;
import com.example.studentinfo.domain.IssueReview;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class IssueAdapter extends FirebaseRecyclerAdapter<IssueReview, IssueAdapter.MyViewHolder> {
    String msg;
    public IssueAdapter(@NonNull FirebaseRecyclerOptions<IssueReview> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final MyViewHolder holder, final int position, @NonNull final IssueReview model) {
        holder.studentId.setText(model.getStudentId());
        holder.issueType.setText(model.getSpinner());
        holder.issue_description.setText(model.getDescription());


        Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext()).
                        setContentHolder(new ViewHolder(R.layout.update_issue)).setExpanded(true, 600).create();

                View myView = dialogPlus.getHolderView();

                final EditText iStudentId = myView.findViewById(R.id.studentId);
                final EditText Idescription = myView.findViewById(R.id.issue_description);
                final EditText iIssueType = myView.findViewById(R.id.issue_type);
                ImageView submit = myView.findViewById(R.id.issue_approve);


                iStudentId.setText(model.getStudentId());
                Idescription.setText(model.getDescription());
                iIssueType.setText(model.getSpinner());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String, Object> map = new HashMap<>();
                        map.put("studentId", iStudentId.getText().toString());
                        map.put("description", Idescription.getText().toString());
                        map.put("spinner", iIssueType.getText().toString());
                        msg = Idescription.getText().toString();

                        FirebaseDatabase.getInstance().getReference().child("Issue_Review").child(getRef(position).getKey()).
                                updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                dialogPlus.dismiss();
                                FirebaseDatabase.getInstance().getReference().child("Issue_Review").child(getRef(position).getKey()).removeValue();
                                Toast.makeText(holder.img.getContext(), "Msg Sent", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialogPlus.dismiss();
                                Toast.makeText(holder.img.getContext(), "Error when updating!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        FirebaseDatabase.getInstance().getReference("Student").child("studentId").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String mobileNumber = snapshot.child("mobileNumber").getValue(String.class);
                                sendSMS(mobileNumber);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

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
                builder.setTitle("Delete selected Issue");
                builder.setMessage("Are sure you want to delete?");
                builder.setIcon(R.drawable.warning);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Issue_Review").child(getRef(position).getKey()).removeValue();
                        Toast.makeText(holder.img.getContext(), "Successfully Deleted.", Toast.LENGTH_SHORT).show();
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_issue, parent, false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img;
        TextView studentId, issueType,issue_description;
        ImageView edit, delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView)itemView.findViewById(R.id.issue_img1);
            studentId = (TextView)itemView.findViewById(R.id.issue_student_id);
            issueType = (TextView)itemView.findViewById(R.id.issue_description);
            issue_description = (TextView)itemView.findViewById(R.id.issue_description);
            edit = (ImageView)itemView.findViewById(R.id.issue_editIcon);
            delete = (ImageView)itemView.findViewById(R.id.issue_deleteIcon);

        }
    }
    private void sendSMS(String mobileNumber) {
        String message = "";
        SmsManager smsManager = SmsManager.getDefault();

            message = msg;

        smsManager.sendTextMessage(mobileNumber, null, message, null, null);
    }
}
