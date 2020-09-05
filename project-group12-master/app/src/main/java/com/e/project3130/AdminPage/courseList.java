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
import android.widget.Button;

import com.e.project3130.Model.Course;
import com.e.project3130.Model.Term;
import com.e.project3130.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * courseList.java
 *
 * Author Kinson Chu & Xin Deng
 *
 * Create Date: 2019/3/9
 *
 * Description: an activity shows courses and part of details
 * as a list.
 **/

public class courseList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore database;
    private FirestoreRecyclerAdapter adapter;
    private Button add;
    private Intent intent;
    private Term term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        intent = getIntent();
        term = (Term)intent.getSerializableExtra("Term");
        recyclerView = findViewById(R.id.courseList);
        database = FirebaseFirestore.getInstance();
        add = findViewById(R.id.addBtn);
        adapter = setUpAdapter(database);
        setUpRecyclerView(recyclerView,adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(courseList.this,addNewCourse.class);
                intent.putExtra("Term",term);
                startActivity(intent);
            }
        });

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

        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<Course,CourseViewHolder>(options)
        {

            @Override
            public void onBindViewHolder(CourseViewHolder holder, int position, final Course model)
            {
                holder.code.setText(model.code);
                holder.title.setText(model.title);
                holder.detailsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(courseList.this,courseDetail.class);
                        intent.putExtra("Course",model);
                        intent.putExtra("Term",term);
                        startActivity(intent);

                    }
                });
                holder.drop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DocumentReference ref = database.collection("Term").document(term.year+term.semester).collection("Course").document(model.code);
                        ref.delete();
                    }
                });
            }

            @Override
            public CourseViewHolder onCreateViewHolder(ViewGroup group, int i)
            {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.course_entry,group,false);
                return new CourseViewHolder(view);

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

