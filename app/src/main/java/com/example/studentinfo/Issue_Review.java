package com.example.studentinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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
    Issue_memeber member;
    FirebaseDatabase database;
    DatabaseReference reference;
    int maxid = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue__review);
        issue_student_id = (EditText)findViewById(R.id.issue_student_id);
        issue_description = (EditText)findViewById(R.id.issue_description);
        issue_submit = (ImageView)findViewById(R.id.issue_submit);
        member = new Issue_memeber() ;
        reference = database.getInstance().getReference().child("Issue_review").push().child("Issue_Type");

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

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
                if (adapterView.getItemAtPosition(i).equals("Choose a issue type")){

                }else{

                }
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
            int  student_Id =  Integer.valueOf(issue_student_id.getText().toString());
            if(student_Id == 0){
            Toast.makeText(Issue_Review.this,"Student id can not be blank or 0!",Toast.LENGTH_SHORT).show();
            }else{
                database.getInstance().getReference().child("Issue_review").push().child("Student_ID").setValue(student_Id);
            }
            String description = issue_description.getText().toString() ;
            if(description.isEmpty()){
                Toast.makeText(Issue_Review.this,"Description can not be blank",Toast.LENGTH_SHORT).show();
            }else{
                database.getInstance().getReference().child("Issue_review").push().child("Issue_Description").setValue(description);
            }
        member.setSpinner(spinner.getSelectedItem().toString());
                Toast.makeText(Issue_Review.this,"Thank you for your response.",Toast.LENGTH_LONG).show();

                reference.child(String.valueOf(maxid+1)).setValue(member);
            }
        });

    }
}