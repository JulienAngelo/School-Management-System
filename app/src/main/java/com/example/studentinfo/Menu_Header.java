package com.example.studentinfo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class Menu_Header extends AppCompatActivity {

    private TextView nav_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__header);

        nav_name = findViewById(R.id.nav_name);


    }
}