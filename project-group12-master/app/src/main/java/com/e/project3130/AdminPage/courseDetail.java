package com.e.project3130.AdminPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.e.project3130.Model.Course;
import com.e.project3130.Model.Term;
import com.e.project3130.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * courseDetail.java
 *
 * Author Kinson Chu & Xin Deng
 *
 * Create Date: 2019/3/12
 *
 * Description: an activity, which shows details of the courses
 * stored in the database and those detail can be updated by
 * administrator
 **/
public class courseDetail extends AppCompatActivity {

    private TextView code;
    private TextView title;
    private TextView time;
    private TextView week;
    private TextView spot;
    private Button update;
    private FirebaseFirestore database;
    private Intent intent;
    private Course course;
    private Term term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        code = findViewById(R.id.editCode);
        title = findViewById(R.id.editTitle);
        time = findViewById(R.id.editTime);
        week = findViewById(R.id.editWeek);
        spot = findViewById(R.id.editSpot);
        update = findViewById(R.id.updateBtn);
        database = FirebaseFirestore.getInstance();
        intent = getIntent();
        term = (Term)intent.getSerializableExtra("Term");
        course = (Course)intent.getSerializableExtra("Course");
        code.setText(course.code);
        title.setText(course.title);
        time.setText(course.time);
        week.setText(course.week);
        spot.setText(course.spot);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                updateCourse();


                if(!timecheck(time.getText().toString()))
                {
                    Toast.makeText(courseDetail.this, "Time format wrong, sample range: 04:30-05:00", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void updateCourse()
    {
        if ( addNewCourse.codecheck(code.getText().toString())== true && addNewCourse.weekcheck(week.getText().toString())==true && timecheck(time.getText().toString()))
        {
            DocumentReference ref = database.collection("Term").document(term.year + term.semester).collection("Course").document(course.code);
            ref.delete();
            Course c = new Course(code.getText().toString(),
                    title.getText().toString(), time.getText().toString(),
                    week.getText().toString(), term.year + term.semester, spot.getText().toString());
            DocumentReference ref1 = database.collection("Term").document(term.year + term.semester).collection("Course").document(code.getText().toString());
            ref1.set(c);
            finish();


        }
        else{
                Toast.makeText(courseDetail.this, "Wrong format", Toast.LENGTH_LONG).show();
            }
        }


    public static boolean timecheck( String weektxt)
    {
        if ( weektxt.matches("(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]-(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]"))
        {
            return true;
        }
        else
        {
            return  false;
        }
    }
}


