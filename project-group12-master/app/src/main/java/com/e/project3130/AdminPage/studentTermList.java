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

import com.e.project3130.Model.Student;
import com.e.project3130.Model.Term;
import com.e.project3130.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * studentTermList.java
 *
 * Author Kinson Chu & Xin Deng
 *
 * Create Date: 2019/3/30
 *
 * Description: an activity shows terms that the student has registered.
 * as a list.
 **/

public class studentTermList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore database;
    private FirestoreRecyclerAdapter adapter;
    private Intent intent;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_term_list);
        intent = getIntent();
        student = (Student)intent.getSerializableExtra("Student");
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
        Query query = db.collection("Student").document(student.email).collection("Term").orderBy("semester").limit(50);
        FirestoreRecyclerOptions<Term> options = new FirestoreRecyclerOptions.Builder<Term>()
                .setQuery(query,Term.class)
                .build();

        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<Term,TermViewHolder>(options)
        {

            @Override
            public void onBindViewHolder(TermViewHolder holder, int position, final Term model)
            {
                holder.term.setText(model.year+model.semester);
                holder.enter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(studentTermList.this,studentCourseList.class);
                        intent.putExtra("Student",student);
                        intent.putExtra("Term",model);
                        startActivity(intent);

                    }
                });
            }

            @Override
            public TermViewHolder onCreateViewHolder(ViewGroup group, int i)
            {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.term3_entry,group,false);
                return new TermViewHolder(view);

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

//