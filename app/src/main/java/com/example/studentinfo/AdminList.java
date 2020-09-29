package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.studentinfo.domain.Admin;
import com.example.studentinfo.adapter.AdminAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class AdminList extends AppCompatActivity {

    RecyclerView recyclerView;
    AdminAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);
        recyclerView = (RecyclerView) findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Admin> options = new FirebaseRecyclerOptions.Builder<Admin>().setQuery(FirebaseDatabase.getInstance().getReference().child("Admin"), Admin.class).build();

        myAdapter = new AdminAdapter(options);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_search_menu, menu);

        MenuItem item = menu.findItem(R.id.searchMenu);

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
        FirebaseRecyclerOptions<Admin> options = new FirebaseRecyclerOptions.Builder<Admin>().setQuery(FirebaseDatabase.getInstance().getReference().child("Admin").orderByChild("adminId").startAt(s).endAt(s+"\uf8ff"), Admin.class).build();
        myAdapter = new AdminAdapter(options);
        myAdapter.startListening();
        recyclerView.setAdapter(myAdapter);
    }
}