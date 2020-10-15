package com.example.studentinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Secondactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);


        TextView loginbtn,adminbutton;

        loginbtn = findViewById(R.id.loginbtn);
        adminbutton = findViewById(R.id.adminbutton);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(Secondactivity.this,Login.class);
                startActivity(login);
            }
        });

        adminbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent admin = new Intent(Secondactivity.this,AdminLogin.class);
                startActivity(admin);
            }
        });

    }
}