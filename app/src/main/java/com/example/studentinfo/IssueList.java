package com.example.studentinfo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentinfo.adapter.IssueAdapter;
import com.example.studentinfo.domain.IssueReview;
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

        searchBox = (EditText)findViewById(R.id.issueSearchBoxId);

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

        recyclerView = (RecyclerView) findViewById(R.id.issue_recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<IssueReview> options = new FirebaseRecyclerOptions.Builder<IssueReview>().setQuery(FirebaseDatabase.getInstance().getReference().child("Issue_Review"), IssueReview.class).build();

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
        FirebaseRecyclerOptions<IssueReview> options = new FirebaseRecyclerOptions.Builder<IssueReview>().setQuery(FirebaseDatabase.getInstance().getReference().child("Issue_Review").orderByChild("studentId").startAt(s).endAt(s+"\uf8ff"), IssueReview.class).build();
        issueAdapter = new IssueAdapter(options);
        issueAdapter.startListening();
        recyclerView.setAdapter(issueAdapter);
    }
}