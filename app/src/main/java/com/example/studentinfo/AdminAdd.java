package com.example.studentinfo;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.studentinfo.domain.Admin;
import com.example.studentinfo.enums.CommonStatus;
import com.example.studentinfo.constants.CommonConstants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.text.SimpleDateFormat;

public class AdminAdd extends AppCompatActivity {

    EditText txtAdminId, txtFirstName, txtLastName;
    ImageView submitBtn, menuBtn;
    DatabaseReference dbRef;
    Admin admin;

    private void clearControls() {
        txtAdminId.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add);

        txtAdminId = findViewById(R.id.adminId);
        txtFirstName = findViewById(R.id.adminFirstName);
        txtLastName = findViewById(R.id.adminLastName);

        submitBtn = findViewById(R.id.adminSubmit);

        admin = new Admin();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Admin");
                try {
                    if(TextUtils.isEmpty(txtAdminId.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Admin Id is required!", Toast.LENGTH_SHORT).show();
                    else if(!txtAdminId.getText().toString().matches("^$|[0-9]+"))
                        Toast.makeText(getApplicationContext(), "Admin Id should be a number!", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtFirstName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "First Name is required!", Toast.LENGTH_SHORT).show();
                    else if(!txtFirstName.getText().toString().matches("^$|^[a-zA-Z]+"))
                        Toast.makeText(getApplicationContext(), "First Name should be consist with simple or capital letters!", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtLastName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Last Name is required!", Toast.LENGTH_SHORT).show();
                    else if(!txtLastName.getText().toString().matches("^$|^[a-zA-Z]+"))
                        Toast.makeText(getApplicationContext(), "Last Name should be consist with simple or capital letters!", Toast.LENGTH_SHORT).show();
                    else {
                        admin.setAdminId(CommonConstants.ADMIN_ID_PREFIX+txtAdminId.getText().toString().trim());
                        admin.setFirstName(txtFirstName.getText().toString().trim());
                        admin.setLastName(txtLastName.getText().toString().trim());
                        admin.setStatus(CommonStatus.ACTVE.toString());
                        admin.setCreatedDate(formatDate());

                        dbRef.child(admin.getAdminId()).setValue(admin);

                        Toast.makeText(getApplicationContext(), "Admin created successfully", Toast.LENGTH_SHORT).show();
                        clearControls();

                        Intent adminList = new Intent(AdminAdd.this, AdminList.class);
                        startActivity(adminList);
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