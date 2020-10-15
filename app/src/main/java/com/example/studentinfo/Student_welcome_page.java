package com.example.studentinfo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Student_welcome_page extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    private TextView user;
    private static int SPLASH_TIME_OUT = 3000;
    String username;
    String st_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_welcome_page);

        user = findViewById(R.id.welcomeId);
        Intent intent = getIntent();
        username = intent.getStringExtra("UserId");
        st_id = intent.getStringExtra("st_id");

        user = findViewById(R.id.user);
        user.setText(username);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Student_welcome_page.this,Student_information.class);
                intent.putExtra("UserId",username);
                intent.putExtra("st_id",st_id);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}