package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.studentinfo.adapter.PendingStudentAdapter;
import com.example.studentinfo.domain.PendingStudent;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class PendingStudentList extends AppCompatActivity {

    RecyclerView recyclerView;
    PendingStudentAdapter pendingStudentAdapter;
    EditText searchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_student_list);

        searchBox = (EditText)findViewById(R.id.pSTDSearchBoxId);

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().isEmpty()) {
                    processSearch(editable.toString());
                } else {
                    processSearch("");
                }
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.pendingStdRecView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<PendingStudent> options = new FirebaseRecyclerOptions.Builder<PendingStudent>().setQuery(FirebaseDatabase.getInstance().getReference().child("PendingStudent"), PendingStudent.class).build();

        pendingStudentAdapter = new PendingStudentAdapter(options);
        recyclerView.setAdapter(pendingStudentAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        pendingStudentAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pendingStudentAdapter.stopListening();
    }

    private void processSearch(String s) {
        FirebaseRecyclerOptions<PendingStudent> options = new FirebaseRecyclerOptions.Builder<PendingStudent>().setQuery(FirebaseDatabase.getInstance().getReference().child("PendingStudent").orderByChild("studentId").startAt(s).endAt(s+"\uf8ff"), PendingStudent.class).build();
        pendingStudentAdapter = new PendingStudentAdapter(options);
        pendingStudentAdapter.startListening();
        recyclerView.setAdapter(pendingStudentAdapter);
    }
}