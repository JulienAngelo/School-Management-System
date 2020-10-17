package com.example.studentinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Secondactivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);

    }

    public void adminbutton(View view) {
        Intent admin = new Intent(Secondactivity.this,AdminLogin.class);
        startActivity(admin);
    }

    public void loginbtn(View view) {
        Intent login = new Intent(Secondactivity.this,Login.class);
        startActivity(login);
    }
}