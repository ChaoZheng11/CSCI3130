package com.e.project3130.StudentPage.CourseTree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.e.project3130.R;

/**
 * Author Chao Zheng
 * Create Date: 2019/2/15
 *Description: This class is used to search for the coursetree.
 *
 **/
public class EnterCrn extends AppCompatActivity {
    private Button click;

    private TextView courseCrn;

    @Override
    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entercrn);

        click=findViewById(R.id.courseTree);
        courseCrn=findViewById(R.id.editCRN);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnterCrn.this, CourseTree.class);
                intent.putExtra("crn", courseCrn.getText().toString());
                startActivity(intent);
            }
        });




    }
}
