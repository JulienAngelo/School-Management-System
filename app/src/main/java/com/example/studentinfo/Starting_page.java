package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

public class Starting_page extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    ImageView img;
private static int SPLASH_TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_page);

        /*img = findViewById(R.id.img);
        img.animate().alpha(4000).setDuration(0);
        handler = new Handler(Looper.myLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent dap = new Intent (Starting_page.this,Login.class);
                startActivity(dap);
                finish();
            }
        }, 4000);*/

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