package com.example.studentinfo.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.studentinfo.domain.Payment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class PaymentAdapter extends FirebaseRecyclerAdapter<Payment, PaymentAdapter.MyViewHolder> {
    public PaymentAdapter(@NonNull FirebaseRecyclerOptions<Payment> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final MyViewHolder holder, final int position, @NonNull final Payment model) {
        holder.studentId.setText(model.getStudent_indexNo());
        holder.payment.setText(model.getPayment());
        holder.term.setText(model.getTerm());
        Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext()).
                        setContentHolder(new ViewHolder(R.layout.update_payment)).setExpanded(true, 600).create();

                View myView = dialogPlus.getHolderView();

                final EditText iStudentId = myView.findViewById(R.id.Student_id);
                final EditText ppayment = myView.findViewById(R.id.up_payment_payment);
                final EditText pterm = myView.findViewById(R.id.up_payment_term);
                ImageView submit = myView.findViewById(R.id.payment_submit);

                iStudentId.setText(model.getStudent_indexNo());
                ppayment.setText(model.getPayment());
                pterm.setText(model.getTerm());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();

                        map.put("payment", ppayment.getText().toString());
                        map.put("term", pterm.getText().toString());
                        map.put("studentId", iStudentId.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Payment").child(getRef(position).getKey()).
                                updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                dialogPlus.dismiss();
                                Toast.makeText(holder.img.getContext(), "Successfully Updated.", Toast.LENGTH_SHORT).show();
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
                builder.setTitle("Reject Payment");
                builder.setMessage("Are sure you want to reject?");
                builder.setIcon(R.drawable.warning);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Payment").child(getRef(position).getKey()).removeValue();
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_payment, parent, false);
        return new MyViewHolder(view);
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView studentId, payment, term;
        ImageView edit, delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView)itemView.findViewById(R.id.payment_img1);
            studentId = (TextView)itemView.findViewById(R.id.Student_id);
            term = (TextView)itemView.findViewById(R.id.payment_term);
            payment = (TextView)itemView.findViewById(R.id.payment_payment);
            edit = (ImageView)itemView.findViewById(R.id.payment_view);
            delete = (ImageView)itemView.findViewById(R.id.payment_Delete);
        }
    }
}
