package com.example.studentinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentinfo.SendNotification.APIService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Student_information extends AppCompatActivity {

    private ImageView logout;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mCurrentUser;
    private APIService apiService;
    private TextView st_name;
    String username,st_id;
    private ImageView studentPro,st_dashboard,st_issue_review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);


        studentPro = findViewById(R.id.studentPro);
        st_dashboard = findViewById(R.id.st_dashboard);
        st_issue_review = findViewById(R.id.st_issue_review);
        st_name = findViewById(R.id.st_name);
        logout = findViewById(R.id.student_logout);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        Intent intent = getIntent();
        username = intent.getStringExtra("UserId");
        st_id = intent.getStringExtra("st_id");

        st_name = findViewById(R.id.st_name);
        st_name.setText(username);

        studentPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Student_information.this,VeiwStudentProfile.class);
                i.putExtra("st_id",st_id);
                startActivity(i);
            }
        });

        st_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Student_information.this,Student_DashBoard.class);
                startActivity(i);
            }
        });

        st_issue_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Student_information.this,Issue_Review.class);
                i.putExtra("st_id",st_id);
                startActivity(i);
            }
        });
    }

    public void logout(View view) {
        Intent i = new Intent(Student_information.this,Secondactivity.class);
        startActivity(i);
    }
}