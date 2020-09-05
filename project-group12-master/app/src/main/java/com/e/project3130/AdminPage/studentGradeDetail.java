package com.e.project3130.AdminPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.e.project3130.Model.Course;
import com.e.project3130.Model.Student;
import com.e.project3130.Model.Term;
import com.e.project3130.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

import javax.annotation.Nonnull;

/**
 * studentGradeDetail.java
 *
 * Author Kinson Chu & Xin Deng
 *
 * Create Date: 2019/3/30
 *
 * Description: an activity, which shows grade of the course
 * stored in the database and those detail can be updated by
 * administrator
 **/
public class studentGradeDetail extends AppCompatActivity {

    private TextView email;
    private TextView name;
    private TextView code;
    private TextView grade;
    private Button update;
    private FirebaseFirestore database;
    private Intent intent;
    private Student student;
    private Course course;
    private Term term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_grade_update);
        email = findViewById(R.id.emailView);
        name = findViewById(R.id.nameView);
        code = findViewById(R.id.codeView);
        grade = findViewById(R.id.editGrade);
        update = findViewById(R.id.updateBtn);
        database = FirebaseFirestore.getInstance();
        intent = getIntent();
        student = (Student)intent.getSerializableExtra("Student");
        term = (Term)intent.getSerializableExtra("Term");
        course = (Course)intent.getSerializableExtra("Course");
        email.setText(student.email);
        name.setText(student.student_name);
        code.setText(course.code);
        setStudentGrade();


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grade.getText().toString().matches("[A-C][+-]?|D|F"))
                {
                    updateGrade();
                    finish();
                }
                else
                {
                    Toast.makeText(studentGradeDetail.this, "Please enter a valid grade.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setStudentGrade(){
        DocumentReference docRef = database.collection("Student").document(student.email)
                .collection("Term").document(term.year+term.semester)
                .collection("Course").document(course.code);
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
                        grade.setText(docGrade);
                        if(studentGradeValidator.validate(grade.getText().toString())==true) {
                            updateGrade();
                        }
                    }
                }
            }
        });

    }

    private void updateGrade()
    {
        DocumentReference docRef = database.collection("Student").document(student.email)
                .collection("Term").document(term.year+term.semester)
                .collection("Course").document(course.code);
        HashMap<String, Object> finishedGrade = new HashMap<>();
        finishedGrade.put("grade", grade.getText().toString());
        docRef.set(finishedGrade, SetOptions.merge());

    }

}

//