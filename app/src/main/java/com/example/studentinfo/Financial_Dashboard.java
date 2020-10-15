package com.example.studentinfo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentinfo.adapter.PaymentAdapter;
import com.example.studentinfo.domain.Payment;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigDecimal;

public class Financial_Dashboard extends AppCompatActivity {

    private TextView Tot_payments;
    DatabaseReference reff;
    RecyclerView recyclerView;
    PaymentAdapter paymentAdapter;
    EditText searchBox;
    int pay = 0;
    float payment1 = (float) 0.00;

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial__dashboard);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Tot_payments = findViewById(R.id.tot_payments);

        reff = FirebaseDatabase.getInstance().getReference().child("Issue_Review");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot myDataSnapsot: snapshot.getChildren()){
                    Payment payment = myDataSnapsot.getValue(Payment.class);
                    BigDecimal tot = BigDecimal.ZERO;
                    BigDecimal  payment1 = new BigDecimal(String.valueOf(payment));
                    tot = tot.add(payment1);
           }}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        searchBox = (EditText)findViewById(R.id.paymentSearchBoxId);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().isEmpty()) {
                    processSearch(editable.toString());
                } else {
                    processSearch("");
                }
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.finrecview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Payment> options = new FirebaseRecyclerOptions.Builder<com.example.studentinfo.domain.Payment>().
                setQuery(FirebaseDatabase.getInstance().getReference().
                        child("Payment"), com.example.studentinfo.domain.Payment.class).build();
        paymentAdapter = new PaymentAdapter(options);
        recyclerView.setAdapter(paymentAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        paymentAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        paymentAdapter.stopListening();
    }
    private void processSearch(String s) {
        FirebaseRecyclerOptions<com.example.studentinfo.domain.Payment> options = new FirebaseRecyclerOptions.Builder<com.example.studentinfo.
                domain.Payment>().setQuery(FirebaseDatabase.
                getInstance().getReference().child("Payment").orderByChild("studentId").startAt(s).endAt(s+"\uf8ff"), Payment.class).build();
        paymentAdapter = new PaymentAdapter(options);
        paymentAdapter.startListening();
        recyclerView.setAdapter(paymentAdapter);
    }
}