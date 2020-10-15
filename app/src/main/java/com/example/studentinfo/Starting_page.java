package com.example.studentinfo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Starting_page extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    ImageView img;
private static int SPLASH_TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_page);

new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {
        Intent intent = new Intent(Starting_page.this,Login.class);
        startActivity(intent);
        finish();
    }
},SPLASH_TIME_OUT);
    }
}