package com.example.studentinfo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.studentinfo.Database.SignUpHelper;
import com.example.studentinfo.enums.CommonStatus;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SignUp extends AppCompatActivity {

    EditText AdmissionNo, StudentFullName, StudentDOB, ParentNIC, ParentName, ParentContact, Address, AdmissionDate;

    Button SignUp;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    SignUpHelper signUpHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        //Hooks
        AdmissionNo = findViewById(R.id.student_admission_no);
        StudentFullName = findViewById(R.id.student_fullname);
        StudentDOB = findViewById(R.id.student_dob);
        ParentNIC = findViewById(R.id.gaurdian_nic);
        ParentName = findViewById(R.id.gaurdian_name);
        ParentContact = findViewById(R.id.gaurdian_contact_no);
        Address = findViewById(R.id.permanent_address);
        AdmissionDate = findViewById(R.id.admission_date);
        SignUp = findViewById(R.id.signUp);

        signUpHelper = new SignUpHelper();

        //save data in firebase on button click
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("StudentPendingApproval");

                //get all the values
                String admissionNo = AdmissionNo.getText().toString();
                String studentFullName = StudentFullName.getText().toString();
                String studentDOB = StudentDOB.getText().toString();
                String parentNIC = ParentNIC.getText().toString();
                String parentName = ParentName.getText().toString();
                String parentContact = ParentContact.getText().toString();
                String address = Address.getText().toString();
                String admissionDate = AdmissionDate.getText().toString();
                signUpHelper.setStatus(CommonStatus.PENDING.toString());
                signUpHelper.setCreatedDate(formatDate());
                signUpHelper.setPassword("(enter password)");
                signUpHelper.setFirstTermAmount(BigDecimal.ZERO.toString());
                signUpHelper.setSecondTermAmount(BigDecimal.ZERO.toString());
                signUpHelper.setThirdTermAmount(BigDecimal.ZERO.toString());
                signUpHelper.setFullAmount(BigDecimal.ZERO.toString());
                signUpHelper.setPurl("https://www.iconfinder.com/data/icons/people-std-pack/512/indian-512.png");

                SignUpHelper signuphelper  = new SignUpHelper(admissionNo,studentFullName,studentDOB,parentNIC,parentName,parentContact,address,admissionDate, signUpHelper.getPurl(), signUpHelper.getStatus(), signUpHelper.getCreatedDate(), signUpHelper.getFirstTermAmount(), signUpHelper.getSecondTermAmount(), signUpHelper.getThirdTermAmount(), signUpHelper.getFullAmount(), signUpHelper.getPassword());

                reference.child(admissionNo).setValue(signuphelper);

                checkFields();

                //openDialog();
            }
        });

    }

                        public void imageClick(View view) {

                            Intent goToLogin = new Intent(this ,Login.class);

                            startActivity(goToLogin);
                    }

                        public void openDialog(){

                        SignUpDialog signupdialog = new SignUpDialog();
                        signupdialog.show(getSupportFragmentManager(), "Sign Up Dialog");


    }

    private void checkFields()
    {

        String admissionNo = AdmissionNo.getText().toString();
        String studentFullName = StudentFullName.getText().toString();
        String studentDOB = StudentDOB.getText().toString();
        String parentNIC = ParentNIC.getText().toString();
        String parentName = ParentName.getText().toString();
        String parentContact = ParentContact.getText().toString();
        String address = Address.getText().toString();
        String admissionDate = AdmissionDate.getText().toString();
        if(TextUtils.isEmpty(admissionNo)) {
            Toast.makeText(this, "Please enter the Admission Number", Toast.LENGTH_SHORT).show();
        }


        else if(TextUtils.isEmpty(studentFullName)){
            Toast.makeText(this,"Please enter the Fullname",Toast.LENGTH_SHORT).show();
        }


        else if(TextUtils.isEmpty(studentDOB)){
            Toast.makeText(this,"Please enter the Student Date of Birth",Toast.LENGTH_SHORT).show();
        }


        else if(TextUtils.isEmpty(parentName)){
            Toast.makeText(this,"Please enter the Parent NIC",Toast.LENGTH_SHORT).show();
        }


        else if(TextUtils.isEmpty(parentNIC)){
            Toast.makeText(this,"Please enter the Parent Name",Toast.LENGTH_SHORT).show();
        }


        else if(TextUtils.isEmpty(address)){
            Toast.makeText(this,"Please enter the Parent Contact Number",Toast.LENGTH_SHORT).show();
        }


        else if(TextUtils.isEmpty(parentContact)){
            Toast.makeText(this,"Please enter the Address",Toast.LENGTH_SHORT).show();
        }


        else if(TextUtils.isEmpty(admissionDate)){
            Toast.makeText(this,"Please enter the Admission Date",Toast.LENGTH_SHORT).show();
        }

        else{
                    openDialog();
        }


    }

    private String formatDate() {
        Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        return format.format(date);
    }

}