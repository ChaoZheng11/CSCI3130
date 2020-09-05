package com.e.project3130.StudentPage.TutionFee;
/**
 * @author Chao Zheng && JiaXiang Liu
 * @date ${15/03/2019}
 * @return tuition fees for auth student
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.project3130.Model.Term;
import com.e.project3130.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Tuition  extends AppCompatActivity {

    //initiate stuff
    ImageView image;
    private FirebaseFirestore database;
    private TextView TuitionFee;
    private TextView TotalFee;
    private TextView CSFee;
    private TextView Advice;
    private Intent intent;
    private Term term;


    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_tuition);
        //First get the current user and database, then create the textviews needed.
        intent = getIntent();
        term = (Term)intent.getSerializableExtra("Term");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        database=FirebaseFirestore.getInstance();
        TuitionFee=findViewById(R.id.tuitionfee);
        TotalFee=findViewById(R.id.total);
        Advice=findViewById(R.id.advice);
        CSFee = findViewById(R.id.CS);
        image = findViewById(R.id.imageView);

        database.collection("Student").document(user.getEmail()).collection("Term").document(term.year+term.semester).collection("Course").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int count = 0;
                    //Count is exactly the number of courses the student takes.
                    for (DocumentSnapshot document : task.getResult()) {
                        count++;
                    }
                    if (count == 0) {
                        TotalFee.setText("$0.0");
                        TuitionFee.setText("$0.0");
                        CSFee.setText("$0.0");
                        Advice.setText("Please add a course to see your tuition.");
                    } else {
                        //We assume each course in computer science society costs the student 800$.
                        //We assume the society fee for computer science society is 1000$ so total fee is gonna be 1000+tuition.
                        double totalTuition = count * 800;
                        TotalFee.setText("$" + String.valueOf(totalTuition + 1000));
                        TuitionFee.setText("$" + String.valueOf(totalTuition));
                        CSFee.setText("$1000.0");
                    }
                    //If count is 0, the student is not taking any course. Set the textviews to zero and tell him/her to add a course.
                }
            }
        });


    }
    //This is the method we used to do the UnitTest.
    public static double TuitionCalculator(int count){
        if (count == 0) {
            return 0;
        }
        else{
            return count*800+1000;
        }
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}

