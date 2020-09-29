package com.example.studentinfo.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.studentinfo.R;
import com.example.studentinfo.domain.Admin;
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

public class AdminAdapter extends FirebaseRecyclerAdapter<Admin, AdminAdapter.MyViewHolder> {

    public AdminAdapter(@NonNull FirebaseRecyclerOptions<Admin> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final MyViewHolder holder, final int position, @NonNull final Admin model) {
        holder.adminId.setText(model.getAdminId());
        holder.firstName.setText(model.getFirstName());
        holder.lastName.setText(model.getLastName());

        Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext()).setContentHolder(new ViewHolder(R.layout.update_admin)).setExpanded(true, 600).create();

                View myView = dialogPlus.getHolderView();

                final EditText pURL = myView.findViewById(R.id.uiImgURL);
                final EditText pFirstName = myView.findViewById(R.id.uFirstName);
                final EditText pLastName = myView.findViewById(R.id.uLastName);
                ImageView submit = myView.findViewById(R.id.uSubmit);

                pURL.setText(model.getPurl());
                pFirstName.setText(model.getFirstName());
                pLastName.setText(model.getLastName());

                dialogPlus.show();

                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("purl", pURL.getText().toString());
                            map.put("firstName", pFirstName.getText().toString());
                            map.put("lastName", pLastName.getText().toString());

                            FirebaseDatabase.getInstance().getReference().child("Admin").child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    dialogPlus.dismiss();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    dialogPlus.dismiss();
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
                builder.setTitle("Delete Admin");
                builder.setMessage("Are sure you want to delete?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Admin").child(getRef(position).getKey()).removeValue();
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_admin, parent, false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img;
        TextView adminId, firstName, lastName;
        ImageView edit, delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView)itemView.findViewById(R.id.img1);
            adminId = (TextView)itemView.findViewById(R.id.adminIdText);
            firstName = (TextView)itemView.findViewById(R.id.adminFirstNameText);
            lastName = (TextView)itemView.findViewById(R.id.adminLastNameText);
            edit = (ImageView)itemView.findViewById(R.id.editIcon);
            delete = (ImageView)itemView.findViewById(R.id.deleteIcon);
        }
    }
}
