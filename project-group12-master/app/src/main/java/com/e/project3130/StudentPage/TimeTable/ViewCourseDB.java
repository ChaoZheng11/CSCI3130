package com.e.project3130.StudentPage.TimeTable;

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
import android.widget.Toast;

import com.e.project3130.Model.Course;
import com.e.project3130.Model.Term;
import com.e.project3130.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * Author Ziwei Wang & Guoyu
 * Create Date: 2019/2/26
 *Description: Student View the course for specific term. and view the spot left
 *
 **/

public class ViewCourseDB extends AppCompatActivity {

    private Intent intent;
    private Term term;
    private RecyclerView recyclerView;
    private FirebaseFirestore database;
    private FirestoreRecyclerAdapter adapter;
    private String left_spot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_view_course_detail);

        intent = getIntent();
        term = (Term)intent.getSerializableExtra("Term");
        recyclerView = findViewById(R.id.view_course_list);
        database= FirebaseFirestore.getInstance();
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
        Query query = db.collection("Term").document(term.year+term.semester)
                .collection("Course").orderBy("code").limit(50);

        FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder().setQuery(query, Course.class).build();

        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<Course, CourseListHolder>(options)
        {
            //For each item in the database connect it to the view
            @Override
            public void onBindViewHolder(@NonNull  final CourseListHolder holder, int position, final Course course)
            {
                holder.code.setText(course.code);
                holder.title.setText(course.title);
                database.collection("Term").document(term.year+term.semester).
                        collection("Course").document(course.code).collection("Students")
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    int count = 0;
                                    for (DocumentSnapshot document : task.getResult()) {
                                        count++;
                                    }
                                    if (count < Integer.parseInt(course.spot)) {
                                        left_spot = SpotCalculator(course.spot, count);
                                        holder.spot.setText("Available/Max: " + left_spot + "/" + course.spot);
                                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(ViewCourseDB.this, AddCourse.class);
                                                intent.putExtra("Course", course);
                                                intent.putExtra("Term", term);
                                                startActivity(intent);
                                            }
                                        });
                                    }
                                    else{
                                        holder.spot.setText("Course seat is full, Please contact Admin!");
                                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(ViewCourseDB.this, "Course seat is full, Please contact Admin!", Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }
                                }
                                else {
                                    Toast.makeText(ViewCourseDB.this, "get student failed", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
            @NonNull
            @Override
            public CourseListHolder onCreateViewHolder(@NonNull ViewGroup group, int i)
            {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.student_course_list_entry,group,false);
                return new CourseListHolder(view);
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

    public static String SpotCalculator(String spot,int count){
        if(count == 0){
            return spot;
        }
        else if(count > Integer.parseInt(spot)){
            return String.valueOf(0);
        }
        else{
            return String.valueOf(Integer.parseInt(spot) - count);
        }

    }

}
