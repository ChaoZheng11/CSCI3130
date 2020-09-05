package com.e.project3130.AdminPage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.e.project3130.R;

/**
 * Author Ziwei Wang
 * Create Date: 2019/2/26
 *Description: This is the main page for admin, user can select the activity he/she wants by clicking buttons.
 *
 **/
public class Admin_Main extends AppCompatActivity {

    private Button course;
    private Button check;
    private Button studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_main);

        course = findViewById(R.id.CourseDetail);
        check=findViewById(R.id.checkNumber);
        studentList = findViewById(R.id.studentList);

        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Main.this,TermList.class);
                startActivity(intent);
            }
        });

        check.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(Admin_Main.this, RegisterNumber.class);
                startActivity(intent);
            }
        });

        studentList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(Admin_Main.this, com.e.project3130.AdminPage.studentList.class);
                startActivity(intent);
            }
        });




    }
}

