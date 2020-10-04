package com.example.studentinfo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
    TextView ftFullPayment,ftPaidAmount,ftBalance,stFullPayment,stPaidAmount,stBalance,ttFullPayment,ttPaidAmount,ttBalance;
     Button button_get;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__dash_board);
        ftFullPayment = (TextView)findViewById(R.id.ffp);
        ftPaidAmount = (TextView)findViewById(R.id.fpa);
        ftBalance = (TextView)findViewById(R.id.fb);
        stFullPayment = (TextView)findViewById(R.id.sfp);
        stPaidAmount = (TextView)findViewById(R.id.spa);
        stBalance = (TextView)findViewById(R.id.sb);
        ttFullPayment = (TextView)findViewById(R.id.tfp);
        ttPaidAmount = (TextView)findViewById(R.id.tpa);
        ttBalance = (TextView)findViewById(R.id.tb);
           button_get = (Button)findViewById(R.id.button_get);
            button_get.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            reff = FirebaseDatabase.getInstance().getReference().child("StudentPendingApproval").child("Student107");
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String ftFullPaymenttxt = snapshot.child("ftFullPaymenttxt").getValue().toString();
                    String ftPaidAmounttxt = snapshot.child("ftPaidAmounttxt").getValue().toString();
                    String ftBalancetxt = snapshot.child("ftBalancetxt").getValue().toString();
                    String stFullPaymenttxt = snapshot.child("stFullPaymenttxt").getValue().toString();
                    String stPaidAmounttxt = snapshot.child(" stPaidAmounttxt").getValue().toString();
                    String stBalancetxt = snapshot.child("stBalancetxt").getValue().toString();
                    String ttFullPaymenttxt = snapshot.child("ttFullPaymenttxt").getValue().toString();
                    String ttPaidAmounttxt = snapshot.child("ttPaidAmounttxt").getValue().toString();
                    String ttBalancetxt = snapshot.child("ttBalancetxt").getValue().toString();
                    ftFullPayment.setText(ftFullPaymenttxt);
                    ftPaidAmount.setText(ftPaidAmounttxt);
                    ftBalance.setText( ftBalancetxt);
                    stFullPayment.setText(stFullPaymenttxt);
                    stPaidAmount.setText(stPaidAmounttxt);
                    stBalance.setText(stBalancetxt);
                    ttFullPayment.setText(ttFullPaymenttxt);
                    ttPaidAmount.setText(ttPaidAmounttxt);
                    ttBalance.setText(ttBalancetxt);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
                });
            }
        });
    }

    }































