package com.e.project3130.AdminPage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.project3130.Model.Course;
import com.e.project3130.Model.Term;
import com.e.project3130.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.annotation.Nonnull;

/**
 * addNewCourse_new.java
 *
 * Author Jiaxiang Liu & Chao Zheng
 *
 * Create Date: 2019/3/23
 *
 * Description: Updated version of addNewcourse.java.
 * Allow administrator to add new courses,
 * and their details to the database.
 **/
public class addNewCourse_new extends AppCompatActivity {

    private EditText code;
    private EditText title;
    private EditText time;
    private EditText week;
    private EditText year;
    private EditText semester;
    private EditText spot;
    private Button create;

    FirebaseFirestore database;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_course_new);
        code = findViewById(R.id.editCode);
        title = findViewById(R.id.editTitle);
        time = findViewById(R.id.editTime);
        week = findViewById(R.id.editWeek);
        year = findViewById(R.id.editYear);
        semester = findViewById(R.id.editSemester);
        spot = findViewById(R.id.spot);
        create = findViewById(R.id.addBtn);

        database = FirebaseFirestore.getInstance();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Year = year.getText().toString();
                String Semester = semester.getText().toString();
                if (yearcheck(Year) == true && semestercheck(Semester) == true&& codecheck(code.getText().toString())==true && weekcheck(week.getText().toString())==true && timecheck(time.getText().toString()) == true) {
                    database.collection("Term").document(year.getText().toString() + semester.getText().toString()).get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                    if (task.getResult().exists()) {
                                        Course c = new Course(code.getText().toString(),
                                                title.getText().toString(), time.getText().toString(),
                                                week.getText().toString(), year.getText().toString() + semester.getText().toString() ,
                                                spot.getText().toString());
                                        DocumentReference ref = database.collection("Term").document(c.term).collection("Course").document(c.code);
                                        ref.set(c);
                                        finish();
                                    } else {
                                        Term c = new Term(year.getText().toString(),
                                                semester.getText().toString());
                                        DocumentReference ref1 = database.collection("Term").document(c.year + c.semester);
                                        ref1.set(c);
                                        Course d = new Course(code.getText().toString(),
                                                title.getText().toString(), time.getText().toString(),
                                                week.getText().toString(), year.getText().toString() + semester.getText().toString()
                                                ,spot.getText().toString());
                                        DocumentReference ref2 = database.collection("Term").document(year.getText().toString() + semester.getText().toString()).collection("Course").document(code.getText().toString());
                                        ref2.set(d);
                                        finish();
                                    }

                                }
                            });

                }
                else{
                    Toast.makeText(addNewCourse_new.this, "Wrong format", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    public boolean yearcheck( String Year){
        int year1;
        if (Year != null && Year.length() > 0) {
            year1 = Integer.parseInt(Year);
            if (year1 <= 2018) {
                Toast.makeText(addNewCourse_new.this, "Invalid year", Toast.LENGTH_LONG).show();
                return false;
            } else {
                return true;
            }
        } else {
            Toast.makeText(addNewCourse_new.this, "Invalid year", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean semestercheck(String Semester) {
        if (Semester.equalsIgnoreCase("Fall") || Semester.equalsIgnoreCase("Winter") || Semester.equalsIgnoreCase("Summer")) {
            return true;
        } else {
            Toast.makeText(addNewCourse_new.this, "Invalid semester", Toast.LENGTH_LONG).show();
            return false;
        }
    }
    public static boolean semestercheck1(String Semester){
        if (Semester.equalsIgnoreCase("Fall") || Semester.equalsIgnoreCase("Winter") || Semester.equalsIgnoreCase("Summer")) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean yearcheck1(String Year) {
        int year1;
        if (Year != null && Year.length() > 0) {
            year1 = Integer.parseInt(Year);
            if (year1 <= 2018) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public boolean codecheck( String codetxt){
        if ( codetxt.matches("[A-Z]{4}][0-9]{4}")){
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean weekcheck( String weektxt)
    {
        if ( weektxt.matches("[MTWRF]|[MTWRF]{2}|[MTWRF]{3}|[MTWRF]{4}|[MTWRF]{5}"))
        {
            return true;
        }
        else
        {
            return  false;
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
