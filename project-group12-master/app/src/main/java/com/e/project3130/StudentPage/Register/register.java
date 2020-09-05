package com.e.project3130.StudentPage.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.project3130.Model.Course;
import com.e.project3130.Model.Term;
import com.e.project3130.R;
import com.e.project3130.StudentPage.Student_Main;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
* Author Ziwei Wang & Guoyu
* Create Date: 2019/3/9
*Description: Allow Student add  course and drop course,
 * allow student view the course registration list
 *
**/

public class register extends AppCompatActivity {
    private Intent intent;
    private Term term;
    private RecyclerView recyclerView;
    private FirebaseFirestore database;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_registerlist);

        intent = getIntent();
        term = (Term)intent.getSerializableExtra("Term");
        database= FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.register_list);
        adapter = setUpAdapter(database);
        setUpRecyclerView(recyclerView, adapter);

    }

    private void setUpRecyclerView(RecyclerView rv, FirestoreRecyclerAdapter adapter)
    {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(manager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv.setAdapter(adapter);
    }

    private FirestoreRecyclerAdapter setUpAdapter(FirebaseFirestore db)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Query query = db.collection("Student").document(user.getEmail())
                .collection("Term").document(term.year+term.semester).collection("Course").orderBy("code").limit(10);

        FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder().setQuery(query,Course.class).build();

        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<Course,RegisterListHolder>(options)
        {
            //For each item in the database connect it to the view
            @Override
            public void onBindViewHolder(@NonNull RegisterListHolder holder, int position, final Course course)
            {
                holder.setTitle(course.getTitle());
                holder.setCode(course.getCode());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(register.this,CourseDetail.class);
                        intent.putExtra("Course",course);
                        intent.putExtra("Term",term);
                        startActivity(intent);
                    }
                });

            }
            @NonNull
            @Override
            public RegisterListHolder onCreateViewHolder(@NonNull ViewGroup group, int i)
            {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.registerlist_entry,group,false);
                return new RegisterListHolder(view);
            }
        };

        return adapter;

    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        adapter.startListening();
    }


    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.setClass(register.this,Student_Main.class);
        intent.putExtra("Term",term);
        startActivity(intent);
    }
}
