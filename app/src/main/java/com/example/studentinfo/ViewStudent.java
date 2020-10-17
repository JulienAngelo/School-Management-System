package com.example.studentinfo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * ViewStudent
 *
 ************************************
 *  ###   Date         Batch            Group Id      Group     Author          Description     ITNumber
 *-------------------------------------------------------------------------------------------------------
 *    1   03-10-2020   Metro-Weekend    Metro_WE_05   Maniacs   Yoganathan.J.A    Created       IT19067902
 *
 ************************************
 */
public class ViewStudent extends AppCompatActivity {

    TextView admissionNoTxt,FullnameTxt,DateOfBirthTxt,parentNicTxt,parentNameTxt,parentContactTxt,addressTxt,admissionDateTxt,ageTxt;
    Button button_get,back_button;
    DatabaseReference reff;
    String studentID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        //Get student ID
        Intent intent = getIntent();
        studentID = intent.getStringExtra("st_id");

        admissionNoTxt = (TextView)findViewById(R.id.admission_number_txt);
        FullnameTxt = (TextView)findViewById(R.id.fullname_txt);
        DateOfBirthTxt = (TextView)findViewById(R.id.dateOfBirth_txt);
        parentNicTxt = (TextView)findViewById(R.id.parentNic_txt);
        parentNameTxt = (TextView)findViewById(R.id.parentName_txt);
        parentContactTxt = (TextView)findViewById(R.id.parentContact_txt);
        addressTxt = (TextView)findViewById(R.id.address_txt);
        admissionDateTxt = (TextView)findViewById(R.id.admissionDate_txt);

        ageTxt = (TextView)findViewById(R.id.age_txt);

        button_get = (Button)findViewById(R.id.button_get);
        back_button = (Button)findViewById(R.id.back_button);

        button_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff = FirebaseDatabase.getInstance().getReference().child("Student").child(studentID);
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String admissionNo = snapshot.child("studentId").getValue().toString();
                        String studentFullName = snapshot.child("firstName").getValue().toString();
                        String studentDOB = snapshot.child("createdDate").getValue().toString();
                        String parentNIC = snapshot.child("lastName").getValue().toString();
                        String parentName = snapshot.child("firstName").getValue().toString();
                        String parentContact = snapshot.child("mobileNumber").getValue().toString();
                        String permanentAddress = snapshot.child("lastName").getValue().toString();
                        String AddmissionDate = snapshot.child("createdDate").getValue().toString();

//                        int a = getAge(studentDOB);
//                        String age = String.valueOf(a);

                        admissionNoTxt.setText(admissionNo);
                        FullnameTxt.setText(studentFullName);
                        DateOfBirthTxt.setText(studentDOB);
                        parentNicTxt.setText(parentNIC);
                        parentNameTxt.setText(parentName);
                        parentContactTxt.setText(parentContact);
                        addressTxt.setText(permanentAddress);
                        admissionDateTxt.setText(AddmissionDate);
                        //ageTxt.setText(age);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });


        back_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent login = new Intent(ViewStudent.this ,Student_information.class);

                startActivity(login);

            }
        });


    }

    public int getAge(String date) {

        int age = 0;
        try {
            SimpleDateFormat dateFormat = null;
            Date date1 = dateFormat.parse(date);
            Calendar now = Calendar.getInstance();
            Calendar dob = Calendar.getInstance();
            dob.setTime(date1);
            if (dob.after(now)) {
                throw new IllegalArgumentException("Can't be born in the future");
            }
            int year1 = now.get(Calendar.YEAR);
            int year2 = dob.get(Calendar.YEAR);
            age = year1 - year2;
            int month1 = now.get(Calendar.MONTH);
            int month2 = dob.get(Calendar.MONTH);
            if (month2 > month1) {
                age--;
            } else if (month1 == month2) {
                int day1 = now.get(Calendar.DAY_OF_MONTH);
                int day2 = dob.get(Calendar.DAY_OF_MONTH);
                if (day2 > day1) {
                    age--;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return age ;
    }
}