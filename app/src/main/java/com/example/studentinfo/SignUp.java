package com.example.studentinfo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.studentinfo.Database.SignUpHelper;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class SignUp extends AppCompatActivity {

    EditText AdmissionNo, StudentFullName, StudentDOB, ParentNIC, ParentName, ParentContact, Address, AdmissionDate;

    Button SignUp;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


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

        //save data in firebase on button click
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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



                //SignUpHelper signuphelper = new SignUpHelper();

                SignUpHelper signuphelper  = new SignUpHelper(admissionNo,studentFullName,studentDOB,parentNIC,parentName,parentContact,address,admissionDate);

                reference.child(admissionNo).setValue(signuphelper);


                openDialog();
            }
        });

    }

                 public void openDialog(){

                        SignUpDialog signupdialog = new SignUpDialog();
                        signupdialog.show(getSupportFragmentManager(), "Sign Up Dialog");


    }



}