package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.studentinfo.adapter.PendingStudentAdapter;
import com.example.studentinfo.domain.PendingStudent;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class PendingStudentList extends AppCompatActivity {

    RecyclerView recyclerView;
    PendingStudentAdapter pendingStudentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_student_list);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pending_student_search_menu, menu);

        MenuItem item = menu.findItem(R.id.pSTDSearchMenuId);

        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processSearch(String s) {
        FirebaseRecyclerOptions<PendingStudent> options = new FirebaseRecyclerOptions.Builder<PendingStudent>().setQuery(FirebaseDatabase.getInstance().getReference().child("PendingStudent").orderByChild("studentId").startAt(s).endAt(s+"\uf8ff"), PendingStudent.class).build();
        pendingStudentAdapter = new PendingStudentAdapter(options);
        pendingStudentAdapter.startListening();
        recyclerView.setAdapter(pendingStudentAdapter);
    }
}