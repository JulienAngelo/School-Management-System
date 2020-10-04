package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.studentinfo.constants.CommonConstants;
import com.example.studentinfo.domain.Admin;
import com.example.studentinfo.domain.PendingStudent;
import com.example.studentinfo.enums.CommonStatus;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PendingStudentAdd extends AppCompatActivity {

    EditText txtPenStdId, txtPenStdFirstName, txtPenStdLastName, txtPenStdMobileNumber;
    ImageView PenStdSubmitBtn, menuBtn;
    DatabaseReference dbRef;
    PendingStudent pendingStudent;

    private void clearControls() {
        txtPenStdId.setText("");
        txtPenStdFirstName.setText("");
        txtPenStdLastName.setText("");
        txtPenStdMobileNumber.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_student_add);

        txtPenStdId = findViewById(R.id.penStdId);
        txtPenStdFirstName = findViewById(R.id.penStdFirstNameId);
        txtPenStdLastName = findViewById(R.id.penStdLastNameId);
        PenStdSubmitBtn = findViewById(R.id.penStdSubmitId);
        txtPenStdMobileNumber = findViewById(R.id.penStdMobileNoId);

        pendingStudent = new PendingStudent();

        PenStdSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("PendingStudent");
                try {
                    if(TextUtils.isEmpty(txtPenStdId.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Student Id is required!", Toast.LENGTH_SHORT).show();
                    else if(!txtPenStdId.getText().toString().matches("^$|[0-9]+"))
                        Toast.makeText(getApplicationContext(), "Student Id should be a number!", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtPenStdFirstName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "First Name is required!", Toast.LENGTH_SHORT).show();
                    else if(!txtPenStdFirstName.getText().toString().matches("^$|^[a-zA-Z]+"))
                        Toast.makeText(getApplicationContext(), "First Name should be consist with simple or capital letters!", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtPenStdLastName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Last Name is required!", Toast.LENGTH_SHORT).show();
                    else if(!txtPenStdLastName.getText().toString().matches("^$|^[a-zA-Z]+"))
                        Toast.makeText(getApplicationContext(), "Last Name should be consist with simple or capital letters!", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtPenStdMobileNumber.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Mobile Number is required!", Toast.LENGTH_SHORT).show();
                    else if(!txtPenStdMobileNumber.getText().toString().matches("^$|^[0-9]{10}+"))
                        Toast.makeText(getApplicationContext(), "Invalid Mobile Number!", Toast.LENGTH_SHORT).show();
                    else {
                        pendingStudent.setStudentId(CommonConstants.STUDENT_ID_PREFIX+txtPenStdId.getText().toString().trim());
                        pendingStudent.setFirstName(txtPenStdFirstName.getText().toString().trim());
                        pendingStudent.setLastName(txtPenStdLastName.getText().toString().trim());
                        pendingStudent.setStatus(CommonStatus.PENDING.toString());
                        pendingStudent.setCreatedDate(formatDate());
                        pendingStudent.setPassword("(enter password)");
                        pendingStudent.setFirstTermAmount(BigDecimal.ZERO.toString());
                        pendingStudent.setSecondTermAmount(BigDecimal.ZERO.toString());
                        pendingStudent.setThirdTermAmount(BigDecimal.ZERO.toString());
                        pendingStudent.setFullAmount(BigDecimal.ZERO.toString());
                        pendingStudent.setMobileNumber(txtPenStdMobileNumber.getText().toString().trim());

                        dbRef.child(pendingStudent.getStudentId()).setValue(pendingStudent);

                        Toast.makeText(getApplicationContext(), "Student created successfully", Toast.LENGTH_SHORT).show();
                        clearControls();

                        Intent pendingStudentList = new Intent(PendingStudentAdd.this, PendingStudentList.class);
                        startActivity(pendingStudentList);
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String formatDate() {
        Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        return format.format(date);
    }
}