package com.example.studentinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.studentinfo.Database.SignUpHelper;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class ViewStudent extends AppCompatActivity {

    private TextView addmissionNoTextVeiw,fullnameTextVeiw,ParentNameTextVeiw;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    String admissionNo;
    private static final String StudentPendingApproval = "StudentPendingApproval";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiw_student);

        Intent intent = getIntent();
        admissionNo = intent.getStringExtra("admissionNo");

        addmissionNoTextVeiw = findViewById(R.id.admission_no_textveiw);
        fullnameTextVeiw = findViewById(R.id.fullname_textveiw);
        ParentNameTextVeiw = findViewById(R.id.parent_name_textveiw);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference(StudentPendingApproval);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if (ds.child("admissionNo").getValue().equals(admissionNo)){

                        addmissionNoTextVeiw.setText(ds.child("admissionNo").getValue(String.class));
                        fullnameTextVeiw.setText(ds.child("studentFullName").getValue(String.class));
                        ParentNameTextVeiw.setText(ds.child("parentName").getValue(String.class));

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}