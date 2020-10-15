package com.example.studentinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentinfo.domain.IssueReview;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Issue_Review extends AppCompatActivity {

    private EditText issue_student_id;
    private Spinner spinner;
    private EditText issue_description;
    private ImageView issue_submit;
    FirebaseDatabase database;
    DatabaseReference reference;
    int maxid = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue__review);


        issue_student_id = findViewById(R.id.issue_student_id);
        issue_description = findViewById(R.id.issue_description);
        issue_submit = findViewById(R.id.issue_submit);
        reference = FirebaseDatabase.getInstance().getReference("Issue_Review");

        final Spinner spinner = findViewById(R.id.spinner);

        final List<String> IssueType = new ArrayList<>();
        IssueType.add(0,"Choose a issue type");
        IssueType.add("Payment");
        IssueType.add("Attendance");
        IssueType.add("Other");



       final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Issue_Review.this, android.R.layout.simple_list_item_1,
               IssueType);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    reference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if(snapshot.exists()){
                maxid = (int) snapshot.getChildrenCount();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });

        issue_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String student_Id =  issue_student_id.getText().toString();
                String description = issue_description.getText().toString();
                String spinner1 = spinner.getSelectedItem().toString();

                database = FirebaseDatabase.getInstance();
                reference = FirebaseDatabase.getInstance().getReference("Issue_Review");

                if(student_Id == null){
                    issue_student_id.setError("Please enter your student Id");
                    issue_student_id.requestFocus();
                }
                else if (description.isEmpty()){
                    issue_description.setError("Please enter your password");
                    issue_description.requestFocus();
                }
                else if(student_Id.isEmpty() && description.isEmpty()){
                    Toast.makeText(Issue_Review.this,"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else if(!(student_Id.isEmpty() && description.isEmpty())) {
                    IssueReview issueReview = new IssueReview(student_Id, description, spinner1);
                    reference.child(String.valueOf(student_Id)).setValue(issueReview);
                    Toast.makeText(Issue_Review.this, "Thank you for your response.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}