package com.e.project3130.AdminPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.project3130.Model.Course;
import com.e.project3130.Model.Term;
import com.e.project3130.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * addNewCourse.java
 *
 * Author Kinson Chu & Xin Deng
 *
 * Create Date: 2019/3/12
 *
 * Description: Allow administrator to add new courses,
 * and their details to the database.
 **/

public class addNewCourse extends AppCompatActivity {

    private EditText code;
    private EditText title;
    private EditText time;
    private EditText week;
    private EditText spot;
    private Button create;
    private Intent intent;
    private Term term;

    FirebaseFirestore database;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_course);
        intent = getIntent();
        term = (Term)intent.getSerializableExtra("Term");
        code = findViewById(R.id.editCode);
        title = findViewById(R.id.editTitle);
        time = findViewById(R.id.editTime);
        week = findViewById(R.id.editWeek);
        spot = findViewById(R.id.spot);
        create = findViewById(R.id.addBtn);

        database = FirebaseFirestore.getInstance();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( codecheck(code.getText().toString())== true && weekcheck(week.getText().toString())==true && timecheck(time.getText().toString()) == true) {
                    Course c = new Course(code.getText().toString(),
                            title.getText().toString(), time.getText().toString(),
                            week.getText().toString(), term.year + term.semester, spot.getText().toString());
                    DocumentReference ref = database.collection("Term").document(c.term);
                    ref.set(term);
                    DocumentReference ref1 = database.collection("Term").document(c.term).collection("Course").document(c.code);
                    ref1.set(c);
                    finish();
                }
                else{
                    Toast.makeText(addNewCourse.this, "Wrong format", Toast.LENGTH_LONG).show();
                }
            }
        });



    }
    public static boolean codecheck( String codetxt){
        if ( codetxt.matches("[A-Z][A-Z][A-Z][A-Z][0-9][0-9][0-9][0-9]")){
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