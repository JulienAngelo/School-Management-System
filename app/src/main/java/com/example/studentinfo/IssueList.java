package com.example.studentinfo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentinfo.adapter.IssueAdapter;
import com.example.studentinfo.domain.Issue;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class IssueList extends AppCompatActivity {

    RecyclerView recyclerView;
    IssueAdapter issueAdapter;
    EditText searchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_list);

        searchBox = findViewById(R.id.pSTDSearchBoxId);

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

        recyclerView = findViewById(R.id.pendingStdRecView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Issue> options = new FirebaseRecyclerOptions.Builder<Issue>().setQuery(FirebaseDatabase.getInstance().getReference().child("Issue"), Issue.class).build();

        issueAdapter = new IssueAdapter(options);
        recyclerView.setAdapter(issueAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        issueAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        issueAdapter.stopListening();
    }

    private void processSearch(String s) {
        FirebaseRecyclerOptions<Issue> options = new FirebaseRecyclerOptions.Builder<Issue>().setQuery(FirebaseDatabase.getInstance().getReference().child("Issue").orderByChild("studentId").startAt(s).endAt(s+"\uf8ff"), Issue.class).build();
        issueAdapter = new IssueAdapter(options);
        issueAdapter.startListening();
        recyclerView.setAdapter(issueAdapter);
    }
}