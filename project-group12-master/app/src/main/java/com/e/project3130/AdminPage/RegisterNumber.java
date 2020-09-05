package com.e.project3130.AdminPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.project3130.Model.Term;
import com.e.project3130.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * TermList2.java
 *
 * Author Jiaxiang Liu & Chao Zheng
 *
 * Create Date: 2019/3/27
 *
 * Description: an activity shows terms and returns number of
 * students registered in each course
 **/
public class RegisterNumber extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseFirestore database;
    private FirestoreRecyclerAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_number);
        recyclerView = findViewById(R.id.termlist);
        database = FirebaseFirestore.getInstance();
        adapter = setUpAdapter(database);
        setUpRecyclerView(recyclerView,adapter);
    }
    private void setUpRecyclerView(RecyclerView rv, FirestoreRecyclerAdapter adapter)
    {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(manager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
    }
    private FirestoreRecyclerAdapter setUpAdapter(FirebaseFirestore db)
    {
        Query query = db.collection("Term").orderBy("year").limit(50);
        FirestoreRecyclerOptions<Term> options = new FirestoreRecyclerOptions.Builder<Term>()
                .setQuery(query,Term.class)
                .build();

        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<Term, RegisterHolder>(options)
        {
            @Override
            public void onBindViewHolder(RegisterHolder holder, int position, final Term model)
            {
                holder.term.setText(model.year+model.semester);
                holder.enter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RegisterNumber.this,RegisterNumberList.class);
                        intent.putExtra("Term",model);
                        startActivity(intent);
                    }
                });

            }
            @Override
            public RegisterHolder onCreateViewHolder(ViewGroup group, int i)
            {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.activity_register_number_entry,group,false);
                return new RegisterHolder(view);

            }
        };

        return adapter;

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
