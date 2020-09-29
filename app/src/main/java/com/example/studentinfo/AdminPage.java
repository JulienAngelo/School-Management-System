package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminPage extends AppCompatActivity {

    private ImageView addAdminBtn, addStudentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        addAdminBtn = findViewById(R.id.addAdmin);

        addAdminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPage.this, AdminList.class);
                startActivity(intent);
            }
        });

        addStudentBtn = findViewById(R.id.addPSTDBtnId);

        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPage.this, PendingStudentList.class);
                startActivity(intent);
            }
        });
    }
}