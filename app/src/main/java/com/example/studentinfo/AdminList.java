package com.example.studentinfo;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentinfo.adapter.AdminAdapter;
import com.example.studentinfo.domain.Admin;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class AdminList extends AppCompatActivity {

    RecyclerView recyclerView;
    AdminAdapter myAdapter;
    ImageView addButton;
    EditText searchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);

        searchBox = (EditText)findViewById(R.id.adminSearchBoxId);

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

        addButton = findViewById(R.id.adminAddId);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminList.this, AdminAdd.class);
                startActivity(intent);
            }
        });

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

    private void processSearch(String s) {
        FirebaseRecyclerOptions<Admin> options = new FirebaseRecyclerOptions.Builder<Admin>().setQuery(FirebaseDatabase.getInstance().getReference().child("Admin").orderByChild("adminId").startAt(s).endAt(s+"\uf8ff"), Admin.class).build();
        myAdapter = new AdminAdapter(options);
        myAdapter.startListening();
        recyclerView.setAdapter(myAdapter);
    }

}