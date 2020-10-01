package com.example.studentinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.studentinfo.SendNotification.APIService;
import com.example.studentinfo.SendNotification.Client;
import com.example.studentinfo.SendNotification.Data;
import com.example.studentinfo.SendNotification.MyResponse;
import com.example.studentinfo.SendNotification.NotificationSender;
import com.example.studentinfo.SendNotification.Token;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student_information extends AppCompatActivity {

    private ImageView logout;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthlistener;
    private FirebaseUser mCurrentUser;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);

        logout = (ImageView)findViewById(R.id.student_logout);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);
        mAuthlistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null) {
                    Intent logout = new Intent(Student_information.this, Login.class);
                    startActivity(logout);
                }
            }
        };
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.signOut();
            }
        });

        //UpdateToken();
    }

    /*private void UpdateToken(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Token token = new Token(refreshToken);
        FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
    }

public void sendNotifications(String usertoken, String title, String message) {
    Data data = new Data(title, message);
    NotificationSender sender = new NotificationSender(data, usertoken);
    apiService.sendNotification(sender).enqueue(new Callback<MyResponse>() {
        @Override
        public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
            if (response.code() == 1) {
                if (response.body().success != 1) {
                    Toast.makeText(Student_information.this, "Failed", Toast.LENGTH_LONG);
                }
            }
        }

        @Override
        public void onFailure(Call<MyResponse> call, Throwable t) {

        }
    });
}

        @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthlistener);
    }*/
}