package com.e.project3130.StudentPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.e.project3130.Model.Term;
import com.e.project3130.NewAccount;
import com.e.project3130.R;
import com.e.project3130.StudentPage.Register.register;
import com.e.project3130.StudentPage.TimeTable.ViewCourseDB;
import com.e.project3130.StudentPage.TutionFee.Tuition;
import com.e.project3130.StudentPage.WeeklySchedule.weekschedule;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * Author Ziwei Wang & Chao Zheng
 * Create Date: 2019/2/16
 *Description: This is the main page for student.
 *
 **/
public class Student_Main extends AppCompatActivity {

    private Button reg;
    private Button weekly;
    private Button tution;
    private Button term;
    private Button courseDB;
    private Intent intent;
    private Button PrivateInfo;
    private Button grade;
    private Button logout;
    private Term termmodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        intent = getIntent();
        termmodel = (Term)intent.getSerializableExtra("Term");
        reg=findViewById(R.id.register);
        weekly=findViewById(R.id.weekly_schedule);
        tution = findViewById(R.id.tuition);
        term = findViewById(R.id.termselection);
        courseDB = findViewById(R.id.CourseDB);
        PrivateInfo = findViewById(R.id.personal_info);
        grade = findViewById(R.id.grades);
        logout = findViewById(R.id.LogOut);

        reg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent();
                intent.setClass(Student_Main.this, register.class);
                intent.putExtra("Term",termmodel);
                startActivity(intent);
            }
        });

        weekly.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent=new Intent();
                intent.setClass(Student_Main.this, weekschedule.class);
                intent.putExtra("Term",termmodel);
                startActivity(intent);
            }
        });

        tution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Student_Main.this, Tuition.class);
                intent.putExtra("Term",termmodel);
                startActivity(intent);
            }
        });

        term.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent();
                intent.setClass(Student_Main.this, TermSelect.class);
                startActivity(intent);
            }
        });

        courseDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Student_Main.this, ViewCourseDB.class);
                intent.putExtra("Term", termmodel);
                startActivity(intent);
            }
        });

        PrivateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Student_Main.this, personal_info.class);
                startActivity(intent);
            }
        });

        grade.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(termmodel!=null) {
                    Intent intent = new Intent();
                    intent.setClass(Student_Main.this, com.e.project3130.StudentPage.AcademicReport.GradeReport.class);
                    intent.putExtra("Term", termmodel);
                    startActivity(intent);
                }else {
                    Toast.makeText(Student_Main.this, "Please select a valid term first.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance().signOut(Student_Main.this).addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent();
                        intent.setClass(Student_Main.this, NewAccount.class);
                        startActivity(intent);
                    }
                });
            }
        });



    }


}
