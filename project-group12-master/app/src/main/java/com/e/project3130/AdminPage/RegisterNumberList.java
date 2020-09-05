package com.e.project3130.AdminPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.project3130.Model.Course;
import com.e.project3130.Model.Term;
import com.e.project3130.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * courseList2.java
 *
 * Author Jiaxiang Liu & Chao Zheng
 *
 * Create Date: 2019/3/23
 *
 * Description: an activity shows courses and part of details
 * as a list. Used for numberregister.
 **/

public class RegisterNumberList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore database;
    private FirestoreRecyclerAdapter adapter;
    private Intent intent;
    private Term term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_number_list);
        intent = getIntent();
        term = (Term)intent.getSerializableExtra("Term");
        recyclerView = findViewById(R.id.courseList);
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
        Query query = db.collection("Term").document(term.year+term.semester).collection("Course").orderBy("code").limit(50);
        FirestoreRecyclerOptions<Course> options = new FirestoreRecyclerOptions.Builder<Course>()
                .setQuery(query,Course.class)
                .build();

        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<Course,RegisterNumberListHolder>(options)
        {

            @Override
            public void onBindViewHolder(final RegisterNumberListHolder holder, int position, final Course model)
            {
                database.collection("Term").document(term.year+term.semester).
                        collection("Course").document(model.code).collection("Students")
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count = 0;
                            for (DocumentSnapshot document : task.getResult()) {
                                count++;
                            }
                            holder.number.setText(String.valueOf(count)+" students");
                        }
                    }
                });
                holder.code.setText(model.code);
                holder.title.setText(model.title);
            }

            @Override
            public RegisterNumberListHolder onCreateViewHolder(ViewGroup group, int i)
            {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.activity_register_number_list_entry,group,false);
                return new RegisterNumberListHolder(view);

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
