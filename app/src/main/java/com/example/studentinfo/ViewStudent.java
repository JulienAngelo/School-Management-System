package com.example.studentinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewStudent extends AppCompatActivity {

    TextView admissionNoTxt,FullnameTxt,DateOfBirthTxt,parentNicTxt,parentNameTxt,parentContactTxt,addressTxt,admissionDateTxt;
    Button button_get;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);


        admissionNoTxt = (TextView)findViewById(R.id.admission_number_txt);
        FullnameTxt = (TextView)findViewById(R.id.fullname_txt);
        DateOfBirthTxt = (TextView)findViewById(R.id.dateOfBirth_txt);
        parentNicTxt = (TextView)findViewById(R.id.parentNic_txt);
        parentNameTxt = (TextView)findViewById(R.id.parentName_txt);
        parentContactTxt = (TextView)findViewById(R.id.parentContact_txt);
        addressTxt = (TextView)findViewById(R.id.address_txt);
        admissionDateTxt = (TextView)findViewById(R.id.admissionDate_txt);



        button_get = (Button)findViewById(R.id.button_get);

        button_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff = FirebaseDatabase.getInstance().getReference().child("StudentPendingApproval").child("Student502");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String admissionNo = snapshot.child("admissionNo").getValue().toString();
                        String studentFullName = snapshot.child("studentFullName").getValue().toString();
                        String studentDOB = snapshot.child("studentDOB").getValue().toString();
                        String parentNIC = snapshot.child("parentNIC").getValue().toString();
                        String parentName = snapshot.child("parentName").getValue().toString();
                        String parentContact = snapshot.child("parentContact").getValue().toString();
                        String permanentAddress = snapshot.child("address").getValue().toString();
                        String AddmissionDate = snapshot.child("admissionDate").getValue().toString();

                        admissionNoTxt.setText(admissionNo);
                        FullnameTxt.setText(studentFullName);
                        DateOfBirthTxt.setText(studentDOB);
                        parentNicTxt.setText(parentNIC);
                        parentNameTxt.setText(parentName);
                        parentContactTxt.setText(parentContact);
                        addressTxt.setText(permanentAddress);
                        admissionDateTxt.setText(AddmissionDate);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}