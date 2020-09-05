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

import com.e.project3130.Model.Course;
import com.e.project3130.Model.Student;
import com.e.project3130.Model.Term;
import com.e.project3130.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import javax.annotation.Nonnull;

/**
 * studentCourseList.java
 *
 * Author Kinson Chu & Xin Deng
 *
 * Create Date: 2019/3/30
 *
 * Description: an activity shows courses that
 * the student has registered. in the selected term
 * as a list.
 **/

public class studentCourseList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore database;
    private FirestoreRecyclerAdapter adapter;
    private Intent intent;
    private Student student;
    private Term term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course_list);
        intent = getIntent();
        student = (Student)intent.getSerializableExtra("Student");
        term = (Term)intent.getSerializableExtra("Term");
        recyclerView = findViewById(R.id.courselist);
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
        Query query = db.collection("Student").document(student.email)
                .collection("Term").document(term.year+term.semester)
                .collection("Course").orderBy("code").limit(50);
        FirestoreRecyclerOptions<Course> options = new FirestoreRecyclerOptions.Builder<Course>()
                .setQuery(query,Course.class)
                .build();

        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<Course,CourseViewHolder>(options)
        {

            @Override
            public void onBindViewHolder(final CourseViewHolder holder, int position, final Course model)
            {
                holder.code.setText(model.code);
                holder.title.setText(model.title);
                holder.time.setText(model.time);
                holder.week.setText(model.week);

                holder.detailsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(studentCourseList.this,studentGradeDetail.class);
                        intent.putExtra("Student",student);
                        intent.putExtra("Term",term);
                        intent.putExtra("Course",model);
                        startActivity(intent);

                    }
                });

                DocumentReference docRef = database.collection("Student").document(student.email)
                        .collection("Term").document(term.year+term.semester)
                        .collection("Course").document(model.code);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String docGrade;
                                if(document.get("grade")!=null)
                                    docGrade = document.get("grade").toString();
                                else
                                    docGrade = "X";
                                holder.grade.setText(docGrade);

                            }
                        }
                    }
                });
            }

            @Override
            public CourseViewHolder onCreateViewHolder(ViewGroup group, int i)
            {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.student_course_entry,group,false);
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

