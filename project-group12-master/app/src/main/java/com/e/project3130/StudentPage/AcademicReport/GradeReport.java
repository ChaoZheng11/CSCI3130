package com.e.project3130.StudentPage.AcademicReport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.e.project3130.Model.Term;
import com.e.project3130.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * Author: Kinson
 * Date: 2019/4/3
 * Description: An activity which shows the academic result of every courses
 * that the student has registered as a table.
 */

public class GradeReport extends AppCompatActivity {

    private FirebaseFirestore database;
    private Intent intent;
    private Term termModel;
    private TableLayout table;
    private FirebaseUser user;
    private TextView courseCode;
    private TextView term;
    private TextView grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_report);
        database = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        intent = getIntent();
        termModel = (Term)intent.getSerializableExtra("Term");
        table = findViewById(R.id.tableLayout);

        setupTable();

    }

    private void setupTable(){
        database.collection("Student").document(user.getEmail())
                .collection("Term").document(termModel.year+termModel.semester)
                .collection("Course")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i=1;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                TableRow row= new TableRow(GradeReport.this);
                                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                                row.setLayoutParams(lp);

                                courseCode = new TextView(GradeReport.this);
                                courseCode.setText(document.get("code").toString());
                                courseCode.setTextSize(22);
                                courseCode.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                                term = new TextView(GradeReport.this);
                                term.setText(termModel.year+termModel.semester);
                                term.setTextSize(22);
                                term.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                                grade = new TextView(GradeReport.this);
                                if(document.contains("grade"))
                                    grade.setText(document.get("grade").toString());
                                else
                                    grade.setText("IP");

                                grade.setTextSize(22);
                                grade.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                                row.addView(courseCode);
                                row.addView(term);
                                row.addView(grade);
                                table.addView(row,i);
                                i++;
                            }
                        }

                    }
                });
    }

}
