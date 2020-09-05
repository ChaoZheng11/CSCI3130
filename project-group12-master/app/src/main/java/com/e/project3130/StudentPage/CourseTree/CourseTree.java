package com.e.project3130.StudentPage.CourseTree;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.e.project3130.R;

/**
 * Author: Guoyu
 * Date: 2019/3/9
 * Description: Allow student view the course tree
 */

public class CourseTree extends AppCompatActivity {
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_trees);

        image = findViewById(R.id.imageView);

        Bundle extras = getIntent().getExtras();
        String crn = ""; //crn = id of the course

        if (extras != null) {
            crn = extras.getString("crn");
        }
        changeSource(crn);
    }

    private void changeSource(String crn){
        switch(crn){
            case "3101":
                image.setImageResource(R.drawable.tree3101);
                break;
            case "3130":
                image.setImageResource(R.drawable.tree3130);
                break;
            case "3136":
                image.setImageResource(R.drawable.tree3136);
                break;
            case "3110":
                image.setImageResource(R.drawable.tree3110);
                break;
            case "3120":
                image.setImageResource(R.drawable.tree3120);
                break;
            default:
                image.setImageResource(R.drawable.blanktree);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Waring").setIcon(R.mipmap.ic_launcher).setMessage("No course tree founded by this CRN").show();
        }
    }
}