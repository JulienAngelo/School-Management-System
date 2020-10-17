package com.example.studentinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminPage extends AppCompatActivity {

    private ImageView addAdminBtn, addStudentBtn,issue_list,financial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        addAdminBtn = findViewById(R.id.addAdmin);
        issue_list = findViewById(R.id.issue_list);
        financial = findViewById(R.id.financial);

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

        issue_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminPage.this,IssueList.class);
                startActivity(i);
            }
        });
        financial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminPage.this,Financial_Dashboard.class);
                startActivity(i);
            }
        });
    }

}