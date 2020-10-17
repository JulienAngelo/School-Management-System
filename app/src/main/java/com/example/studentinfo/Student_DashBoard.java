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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__dash_board);
        b1 =(TextView) findViewById(R.id.b1);
        b2 = (TextView) findViewById(R.id.b2);
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


                    if (term.equals("1st"))

                    {double p1=Integer.parseInt(b1.getText().toString());
                        p1=p1+Integer.parseInt(firstPayment);
                        double balance=(50000-p1);
                        fpa.setText(firstPayment);
                        b1.setText(String.valueOf((int) balance));
                    }
                    else if(term.equals("2nd"))
                    {  double p2=Integer.parseInt(b2.getText().toString());
                        p2=p2+Integer.parseInt(secondPayment);
                        double balance=(50000-p2);
                        spa.setText(secondPayment);
                        b2.setText(String.valueOf((int) balance));
                    }
                    else if(term.equals("3rd"))
                    {   double p3=Integer.parseInt(b3.getText().toString());
                        p3=p3+Integer.parseInt(thirdPayment);
                        double balance=(50000-p3);
                        tpa.setText(thirdPayment);
                        b3.setText(String.valueOf((int) balance));

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































