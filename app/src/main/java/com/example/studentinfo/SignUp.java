package com.example.studentinfo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.basgeekball.awesomevalidation.utility.custom.SimpleCustomValidation;
import com.example.studentinfo.Database.SignUpHelper;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * SignUp
 *
 ************************************
 *  ###   Date         Batch            Group Id      Group     Author          Description     ITNumber
 *-------------------------------------------------------------------------------------------------------
 *    1   03-10-2020   Metro-Weekend    Metro_WE_05   Maniacs   Yoganathan.J.A    Created       IT19067902
 *
 ************************************
 */

public class SignUp extends AppCompatActivity {

    EditText AdmissionNo, StudentFullName, StudentDOB, ParentNIC, ParentName, ParentContact, Address, AdmissionDate;
    Button SignUp,back_button,DOB_button,Admission_Date_button;
    private DatePickerDialog.OnDateSetListener mDateSetListener,mDateSetListener_one;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hooks
        AdmissionNo = findViewById(R.id.student_admission_no);
        StudentFullName = findViewById(R.id.student_fullname);
        StudentDOB = findViewById(R.id.student_dob);
        ParentNIC = findViewById(R.id.gaurdian_nic);
        ParentName = findViewById(R.id.gaurdian_name);
        ParentContact = findViewById(R.id.gaurdian_contact_no);
        Address = findViewById(R.id.permanent_address);
        AdmissionDate = findViewById(R.id.admission_date);
        SignUp = findViewById(R.id.signUp);

        //validations
        awesomeValidation  = new AwesomeValidation(ValidationStyle.BASIC);

        //Fullname, Address , Admission,Parent Name, NIC Number Validation
        awesomeValidation.addValidation(this,R.id.student_fullname,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);

        awesomeValidation.addValidation(this,R.id.student_admission_no,
                RegexTemplate.NOT_EMPTY,R.string.invalid_addmission_no);

        //Contact Number Validation
        awesomeValidation.addValidation(this,R.id.gaurdian_contact_no,
                "[0]{1}[0-9]{9}$",R.string.invalid_phone);

        //Date of Birth Validation
        awesomeValidation.addValidation(this,R.id.student_dob, new SimpleCustomValidation() {
                    @Override
                    public boolean compare(String input) {
                        // check if the age is >= 6
                        try {
                            Calendar calendarBirthday = Calendar.getInstance();
                            Calendar calendarToday = Calendar.getInstance();
                            calendarBirthday.setTime(new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(input));
                            int yearOfToday = calendarToday.get(Calendar.YEAR);
                            int yearOfBirthday = calendarBirthday.get(Calendar.YEAR);
                            if (yearOfToday - yearOfBirthday > 6) {
                                return true;
                            } else if (yearOfToday - yearOfBirthday == 6) {
                                int monthOfToday = calendarToday.get(Calendar.MONTH);
                                int monthOfBirthday = calendarBirthday.get(Calendar.MONTH);
                                if (monthOfToday > monthOfBirthday) {
                                    return true;
                                } else if (monthOfToday == monthOfBirthday) {
                                    if (calendarToday.get(Calendar.DAY_OF_MONTH) >= calendarBirthday.get(Calendar.DAY_OF_MONTH)) {
                                        return true;
                                    }
                                }
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
        },R.string.invalid_date_of_birth);
        //last change : exchange of return types / true/ false


        back_button = (Button) findViewById(R.id.back_button);
        DOB_button = (Button)findViewById(R.id.dob_button);
        Admission_Date_button = (Button)findViewById(R.id.admission_date_button);

        StudentDOB.setFocusable(false);
        StudentDOB.setClickable(false);
        StudentDOB.setLongClickable(false);

        AdmissionDate.setFocusable(false);
        AdmissionDate.setClickable(false);
        AdmissionDate.setLongClickable(false);

        //save data in firebase on button click
        SignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(awesomeValidation.validate()) {
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("StudentPendingApproval");

                    //get all the values
                    String admissionNo = AdmissionNo.getText().toString();
                    String studentFullName = StudentFullName.getText().toString();
                    String studentDOB = StudentDOB.getText().toString();
                    String parentNIC = ParentNIC.getText().toString();
                    String parentName = ParentName.getText().toString();
                    String parentContact = ParentContact.getText().toString();
                    String address = Address.getText().toString();
                    String admissionDate = AdmissionDate.getText().toString();

                    SignUpHelper signuphelper = new SignUpHelper(admissionNo, studentFullName, studentDOB, parentNIC, parentName, parentContact, address, admissionDate);

                    reference.child(admissionNo).setValue(signuphelper);

                    openDialog();

                }else{
                    Toast.makeText(getApplicationContext(),
                            "Validation Failed.Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
                }

        });


        back_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent login = new Intent(SignUp.this ,Login.class);

                startActivity(login);

            }
        });


        DOB_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(SignUp.this,
                        R.style.Theme_AppCompat_DayNight_Dialog,
                        mDateSetListener,
                        year,month,day);

                dialog.show();

            }
        });



        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = day + "." + month + "." + year;
                StudentDOB.setText(date);

            }
        };

        Admission_Date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(SignUp.this,
                        R.style.Theme_AppCompat_DayNight_Dialog,
                        mDateSetListener_one,
                        year,month,day);

                dialog.show();

            }
        });



        mDateSetListener_one = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = day + "." + month + "." + year;
                AdmissionDate.setText(date);

            }
        };

    }


    public void openDialog(){
        SignUpDialog signupdialog = new SignUpDialog();
        signupdialog.show(getSupportFragmentManager(), "Sign Up Dialog");
    }




}