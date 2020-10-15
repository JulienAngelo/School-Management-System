package com.example.studentinfo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private EditText login_username, login_password;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private DatabaseReference reference;
    private ProgressDialog loginProgress;
    private ImageView login_submit,login_register;
    String studentId,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        login_username = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);
        //login_submit = findViewById(R.id.login_submit);
        loginProgress = new ProgressDialog(this);
        login_register =  findViewById(R.id.login_register);
        reference = FirebaseDatabase.getInstance().getReference("Student");

        mAuthListner = new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
                if(mFirebaseUser!= null) {
                     Toast.makeText(Login.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, Create_Account.class));
                }
                else{
                    Toast.makeText(Login.this,"Please login",Toast.LENGTH_SHORT).show();
                }
            }
        };
        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(Login.this,Create_Account.class);
                startActivity(reg);
            }
        });

    }
    public void login(View view) {
        studentId = login_username.getText().toString();//.toLowerCase();
        password = login_password.getText().toString();

        if(studentId.isEmpty()){
            login_username.setError("Please enter your username");
            login_username.requestFocus();
        }
         else if (!studentId.isEmpty()) {

            reference.child(studentId).addListenerForSingleValueEvent(listener);
        }
        else if (password.isEmpty()){
            login_password.setError("Please enter your password");
            login_password.requestFocus();
        }
        else if(studentId.isEmpty() && password.isEmpty()){
            Toast.makeText(Login.this,"Fields are empty",Toast.LENGTH_SHORT).show();
        }
    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if(snapshot.exists()){
               String pass = snapshot.child("password").getValue(String.class);
                String username = snapshot.child("firstName").getValue(String.class);
                String st_id = snapshot.child("studentId").getValue(String.class);
                if(pass.equals(password)){
                    Intent i = new Intent(Login.this, Student_welcome_page.class);
                    i.putExtra("UserId",username);
                    i.putExtra("st_id",st_id);
                    startActivity(i);

                }
                else{
                    login_password.setError("Please enter your password");
                    login_password.requestFocus();
                }
            }
            else{
                Toast.makeText(Login.this, "Record not found", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}