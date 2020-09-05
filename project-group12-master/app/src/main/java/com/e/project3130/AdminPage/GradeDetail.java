package com.e.project3130.AdminPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

import javax.annotation.Nonnull;

/**
 * GradeDetail.java
 *
 * Author Kinson Chu & Xin Deng
 *
 * Create Date: 2019/3/31
 *
 * Description: an activity which shows the grade of the course
 * stored in the database.
 **/
public class GradeDetail extends AppCompatActivity {

    private TextView email;
    private TextView code;
    private TextView grade;
    private FirebaseFirestore database;
    private Intent intent;
    private Course course;
    private Term term;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_grade_detail);
        email = findViewById(R.id.emailView);
        code = findViewById(R.id.codeView);
        grade = findViewById(R.id.gradeView);
        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseFirestore.getInstance();
        intent = getIntent();
        term = (Term)intent.getSerializableExtra("Term");
        course = (Course)intent.getSerializableExtra("Course");
        email.setText(user.getEmail());
        code.setText(course.code);
        setStudentGrade();




    }

    private void setStudentGrade(){
        DocumentReference docRef = database.collection("Student").document(user.getEmail())
                .collection("Term").document(term.year+term.semester)
                .collection("Course").document(course.code);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        if(document.get("grade")!=null)
                            grade.setText(document.get("grade").toString());
                        else
                            grade.setText("X");
                        updateGrade();
                    }
                }
            }
        });

    }

    private void updateGrade()
    {
        DocumentReference docRef = database.collection("Student").document(user.getEmail())
                .collection("Term").document(term.year+term.semester)
                .collection("Course").document(course.code);
        HashMap<String, Object> finishedGrade = new HashMap<>();
        finishedGrade.put("grade", grade.getText().toString());
        docRef.set(finishedGrade, SetOptions.merge());
    }

}

//