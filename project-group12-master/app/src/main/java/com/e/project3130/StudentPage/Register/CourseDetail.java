package com.e.project3130.StudentPage.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.e.project3130.Model.Course;
import com.e.project3130.Model.Term;
import com.e.project3130.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * Author Ziwei Wang & Guoyu
 * Create Date: 2019/2/26
 *Description: Student View the course detail and Add course
 *
 **/

public class CourseDetail extends AppCompatActivity {
        private TextView code;
        private TextView title;
        private TextView time;
        private TextView week;
        private TextView spot;

        private Button drop;

        private FirebaseFirestore database;
        private Intent intent;
        private Course course;
        private Term term;

        @Override
        protected void onCreate(Bundle saveInstanceState) {
            super.onCreate(saveInstanceState);
            setContentView(R.layout.course_detail_student);

            code = findViewById(R.id.code);
            title = findViewById(R.id.title);
            time = findViewById(R.id.time);
            week = findViewById(R.id.week);
            spot = findViewById(R.id.spot);

            drop = (Button)findViewById(R.id.drop);
            database = FirebaseFirestore.getInstance();
            intent = getIntent();
            term = (Term)intent.getSerializableExtra("Term");
            course = (Course)intent.getSerializableExtra("Course");
            code.setText("Course code: "+ course.code);
            title.setText("Course title: " + course.title);
            time.setText("Course time: " + course.time);
            week.setText("Course week: " + course.week);
            spot.setText("Course max spot" + course.spot);
            drop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DropCourse();
                }
            });
        }


        private void DropCourse(){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            database.collection("Student").document(user.getEmail()).collection("Term")
                    .document(term.year + term.semester).collection("Course").document(course.code).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                            if (task.getResult().exists()) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                DocumentReference ref = database.collection("Student").document(user.getEmail()).collection("Term")
                                        .document(term.year + term.semester).collection("Course").document(course.code);
                                ref.delete();

                                DocumentReference studentInfor = database.collection("Term").document(term.year + term.semester).
                                        collection("Course").document(course.code).collection("Students").document(user.getEmail());
                                studentInfor.delete();

                                DocumentReference doc = database.collection("Student").document(user.getEmail())
                                        .collection("Term").document(term.year+term.semester);


                                final DocumentReference userInstance = database.collection("Student/"+user.getEmail()+"/Term/").document(term.year+term.semester);
                                userInstance.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot document = task.getResult();
                                            //String array = document.toString();
                                            if (document.exists()) {
                                                Log.d("LOGGER", "DocumentSnapshot data: " + document.getData());
                                                Term termUpdate = document.toObject(Term.class);
                                                List<String> courses = termUpdate.returnCourseList();
                                                String courseCode = course.code;
                                                courses.remove(courseCode);
                                                userInstance.update("courselist",courses);

                                            }
                                        }
                                    }
                                });


                                Toast.makeText(CourseDetail.this, "Successfully drop", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(CourseDetail.this,register.class);
                                intent.putExtra("Term",term);
                                startActivity(intent);
                            }
                        }
                    });
            finish();
        }

}
