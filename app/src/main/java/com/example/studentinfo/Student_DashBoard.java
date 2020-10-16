package com.example.studentinfo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class Student_DashBoard extends AppCompatActivity {
    TextView fpa,spa,tpa,b1,b2,b3;
     Button button_get;
    DatabaseReference reff;
    String stid;
    int p1=0;
    int p2=0;
    int p3=0;
    int ffp=50000;
    int balance=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__dash_board);
        b1 = (TextView)findViewById(R.id.b1);
        b2 = (TextView)findViewById(R.id.b2);
        b3 = (TextView)findViewById(R.id.b3);
        fpa = (TextView)findViewById(R.id.fpa);
        spa = (TextView)findViewById(R.id.spa);
        tpa = (TextView)findViewById(R.id.tpa);
        Intent intent = getIntent();
        stid = intent.getStringExtra("st_id");

           button_get = (Button)findViewById(R.id.button_get);
            button_get.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            reff = FirebaseDatabase.getInstance().getReference().child("Payment").child(stid);
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String firstPayment = snapshot.child("payment").getValue().toString();
                    String secondPayment = snapshot.child("payment").getValue().toString();
                    String thirdPayment = snapshot.child("payment").getValue().toString();
                    String term = snapshot.child("term").getValue().toString();

//                    fpa.setText(firstPayment);
//                    spa.setText(secondPayment);
//                    tpa.setText(thirdPayment);
                    if (term.equals("1st"))

                    {

                        p1=p1+Integer.parseInt(firstPayment);
                        balance=ffp-p1;
                        fpa.setText(firstPayment);
                        b1.setText(balance);
                    }
                    else if(term.equals("2nd"))
                    {   p2=p2+Integer.parseInt(secondPayment);
                        balance=ffp-p2;
                        spa.setText(secondPayment);
                        b2.setText(balance);
                    }
                    else if(term.equals("3rd"))
                    {   p3=p3+Integer.parseInt(thirdPayment);
                        balance=ffp-p3;
                        tpa.setText(thirdPayment);
                        b3.setText(balance);
                    }
                    else
                    {

                    }


                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
                });
            }
        });
    }

    }































