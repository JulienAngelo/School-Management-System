package com.example.studentinfo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private EditText login_username, login_password;
    private ImageView login_submit, login_register;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private DatabaseReference mDataRef;
    private FirebaseAuth.AuthStateListener mAuth;
    private ProgressDialog loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        login_username = (EditText)findViewById(R.id.login_username);
        login_password = (EditText)findViewById(R.id.login_password);
        login_submit = (ImageView)findViewById(R.id.login_submit);
        loginProgress = new ProgressDialog(this);
        login_register = (ImageView)findViewById(R.id.login_register);
        mDataRef = FirebaseDatabase.getInstance().getReference().child("Test");

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            startActivity(new Intent(Login.this,Student_welcome_page.class));
            }
        };
        login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });

        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerPage = new Intent(Login.this, Create_Account.class);
                startActivity(registerPage);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListner);
    }

    private void checkLogin() {
        String username = login_username.getText().toString().trim();
        String password = login_password.getText().toString().trim();
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
        loginProgress.setMessage("Checking login...");
        loginProgress.show();
        firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    loginProgress.dismiss();
                    checkUserExist();
                }
                else {
                    loginProgress.dismiss();
                    Toast.makeText(Login.this,"Error Login",Toast.LENGTH_SHORT).show();
                }
            }
        });
        }
    }

    private void checkUserExist() {
        final String user_id = firebaseAuth.getCurrentUser().getUid();
        mDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(user_id)){
                    Intent loginIntent = new Intent(Login.this,Student_welcome_page.class);
                    startActivity(loginIntent);
                }
                Toast.makeText(Login.this,"You have to register first",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}