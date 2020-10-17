package com.example.studentinfo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Menu_Header extends AppCompatActivity {

    private TextView nav_name;
    private DatabaseReference databaseReference;
    DatabaseReference user;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__header);

        nav_name = findViewById(R.id.nav_name);
        user = FirebaseDatabase.getInstance().getReference("Student");


        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String user = snapshot.child(uid).child("firstName").getValue(String.class);
                nav_name.setText(user);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}