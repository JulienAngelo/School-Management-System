package com.example.studentinfo.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentinfo.R;
import com.example.studentinfo.domain.Issue;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class IssueAdapter extends FirebaseRecyclerAdapter<Issue, IssueAdapter.MyViewHolder> {
    public IssueAdapter(@NonNull FirebaseRecyclerOptions<Issue> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final MyViewHolder holder, final int position, @NonNull final Issue model) {
        holder.studentId.setText(model.getStudentId());
        holder.description.setText(model.getDescription());


        /*holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext()).setContentHolder(new ViewHolder(R.layout.update_issue)).setExpanded(true, 600).create();

                View myView = dialogPlus.getHolderView();

                final EditText pdescription = myView.findViewById(R.id.issue_description);
                final Spinner pspinner = myView.findViewById(R.id.issue_type);
                ImageView submit = myView.findViewById(R.id.pSTDUSubmitBtnId);

                pdescription.setText(model.getDescription());
                pspinner.getSelectedItem();
                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("description", pdescription.getText().toString());
                        map.put("issueType", pspinner.getSelectedItem().toString());

                        FirebaseDatabase.getInstance().getReference().child("Issue").child(Objects.requireNonNull(getRef(position).getKey())).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
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
        });*/

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Delete Issue");
                builder.setMessage("Are sure you want to delete?");
                builder.setIcon(R.drawable.warning);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Issue").child(Objects.requireNonNull(getRef(position).getKey())).removeValue();
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

    static class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img;
        Spinner spinner;
        TextView studentId, description;
        ImageView  delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.pStdImgId);
            studentId = itemView.findViewById(R.id.pStdIdTextId);
            description = itemView.findViewById(R.id.pStdFirstNameTextId);
            spinner =itemView.findViewById(R.id.spinner);
            delete = itemView.findViewById(R.id.pStdDeleteIconId);
        }
    }
}
