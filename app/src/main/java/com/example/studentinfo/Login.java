package com.example.studentinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText login_username, login_password;
    ImageView login_submit, login_register;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_username = (EditText)findViewById(R.id.login_username);
        login_password = (EditText)findViewById(R.id.login_password);
        login_submit = (ImageView)findViewById(R.id.login_submit);
        login_register = (ImageView)findViewById(R.id.login_register);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user!=null){
            finish();
            startActivity(new Intent(Login.this,Student_information.class));
        }

        login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(login_username.getText().toString(), login_password.getText().toString());
            }
        });

        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Create_Account.class));
            }
        });
    }
    private void validate(String userName, String userPassword){

        progressDialog.setMessage("Please wait a moment");
        progressDialog.show();

        if((userName =="admin") && (userPassword == "1234")){
            Intent intent = new Intent(Login.this,Student_welcome_page.class);
            startActivity(intent);
        }
        /*firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,Student_welcome_page.class));
                }else{
                    Toast.makeText(Login.this,"Login failed",Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

}