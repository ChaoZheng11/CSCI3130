package com.e.project3130.StudentPage.WeeklySchedule;

/**
 * @author Chao Zheng && JiaXiang Liu
 * @date ${08/04/2019}
 * @return weekly schedule
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.e.project3130.Model.Course;
import com.e.project3130.Model.Term;
import com.e.project3130.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class weekschedule extends AppCompatActivity {

    private TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    private TextView t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,t23,t24,t25;

    private FirebaseAuth user;
    private FirebaseFirestore database;

    private Course course;
    private Term term;

    private Intent intent;

    private ArrayList<String> courselist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly);

        intent=getIntent();
        term = (Term)intent.getSerializableExtra("Term");
        course = (Course)intent.getSerializableExtra("Course");

        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        t5=findViewById(R.id.t5);

        t6=findViewById(R.id.t6);
        t7=findViewById(R.id.t7);
        t8=findViewById(R.id.t8);
        t9=findViewById(R.id.t9);
        t10=findViewById(R.id.t10);

        t11=findViewById(R.id.t11);
        t12=findViewById(R.id.t12);
        t13=findViewById(R.id.t13);
        t14=findViewById(R.id.t14);
        t15=findViewById(R.id.t15);

        t16=findViewById(R.id.t16);
        t17=findViewById(R.id.t17);
        t18=findViewById(R.id.t18);
        t19=findViewById(R.id.t19);
        t20=findViewById(R.id.t20);

        t21=findViewById(R.id.t21);
        t22=findViewById(R.id.t22);
        t23=findViewById(R.id.t23);
        t24=findViewById(R.id.t24);
        t25=findViewById(R.id.t25);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        database= FirebaseFirestore.getInstance();



       DocumentReference doc= database.collection("Student").document(user.getEmail())
               .collection("Term").document(term.year + term.semester);



        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.getResult().exists()){
                    DocumentSnapshot doc = task.getResult();
                    List<String> group = (List<String>) doc.get("courselist");
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(numberCourses(group.size()).equals("pass")) {

                        if(group.size()==1) {
                            database.collection("Student").document(user.getEmail()).collection("Term")
                                    .document(term.year + term.semester).collection("Course").document(group.get(0))
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                    if (task.getResult().exists()) {
                                        if (task.getResult().getString("week").contains("M")) {
                                            t1.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("T")) {
                                            t2.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("W")) {
                                            t3.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("R")) {
                                            t4.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("F")) {
                                            t5.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                    }
                                }
                            });
                        }


                        else if(group.size()==2) {
                            database.collection("Student").document(user.getEmail()).collection("Term")
                                    .document(term.year + term.semester).collection("Course").document(group.get(0))
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.getResult().exists()) {
                                        if (task.getResult().getString("week").contains("M")) {
                                            t1.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("T")) {
                                            t2.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("W")) {
                                            t3.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("R")) {
                                            t4.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("F")) {
                                            t5.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                    }
                                }
                            });

                            database.collection("Student").document(user.getEmail()).collection("Term")
                                    .document(term.year + term.semester).collection("Course").document(group.get(1))
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.getResult().exists()) {
                                        if (task.getResult().getString("week").contains("M")) {
                                            t6.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("T")) {
                                            t7.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("W")) {
                                            t8.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("R")) {
                                            t9.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("F")) {
                                            t10.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                    }
                                }
                            });
                        }

                        else if(group.size()==3) {
                            database.collection("Student").document(user.getEmail()).collection("Term")
                                    .document(term.year + term.semester).collection("Course").document(group.get(0))
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.getResult().exists()) {
                                        if (task.getResult().getString("week").contains("M")) {
                                            t1.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("T")) {
                                            t2.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("W")) {
                                            t3.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("R")) {
                                            t4.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("F")) {
                                            t5.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                    }
                                }
                            });

                            database.collection("Student").document(user.getEmail()).collection("Term")
                                    .document(term.year + term.semester).collection("Course").document(group.get(1))
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.getResult().exists()) {
                                        if (task.getResult().getString("week").contains("M")) {
                                            t6.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("T")) {
                                            t7.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("W")) {
                                            t8.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("R")) {
                                            t9.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("F")) {
                                            t10.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                    }
                                }
                            });

                            database.collection("Student").document(user.getEmail()).collection("Term")
                                    .document(term.year + term.semester).collection("Course").document(group.get(2))
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.getResult().exists()) {
                                        if (task.getResult().getString("week").contains("M")) {
                                            t11.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("T")) {
                                            t12.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("W")) {
                                            t13.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("R")) {
                                            t14.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("F")) {
                                            t15.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                    }
                                }
                            });
                        }

                        else if(group.size()==4) {
                            database.collection("Student").document(user.getEmail()).collection("Term")
                                    .document(term.year + term.semester).collection("Course").document(group.get(0))
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.getResult().exists()) {
                                        if (task.getResult().getString("week").contains("M")) {
                                            t1.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("T")) {
                                            t2.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("W")) {
                                            t3.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("R")) {
                                            t4.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("F")) {
                                            t5.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                    }
                                }
                            });

                            database.collection("Student").document(user.getEmail()).collection("Term")
                                    .document(term.year + term.semester).collection("Course").document(group.get(1))
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.getResult().exists()) {
                                        if (task.getResult().getString("week").contains("M")) {
                                            t6.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("T")) {
                                            t7.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("W")) {
                                            t8.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("R")) {
                                            t9.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("F")) {
                                            t10.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                    }
                                }
                            });

                            database.collection("Student").document(user.getEmail()).collection("Term")
                                    .document(term.year + term.semester).collection("Course").document(group.get(2))
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.getResult().exists()) {
                                        if (task.getResult().getString("week").contains("M")) {
                                            t11.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("T")) {
                                            t12.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("W")) {
                                            t13.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("R")) {
                                            t14.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("F")) {
                                            t15.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                    }
                                }
                            });
                            database.collection("Student").document(user.getEmail()).collection("Term")
                                    .document(term.year + term.semester).collection("Course").document(group.get(3))
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.getResult().exists()) {
                                        if (task.getResult().getString("week").contains("M")) {
                                            t16.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("T")) {
                                            t17.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("W")) {
                                            t18.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("R")) {
                                            t19.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("F")) {
                                            t20.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                    }
                                }
                            });
                        }

                        else if(group.size()==5) {
                            database.collection("Student").document(user.getEmail()).collection("Term")
                                    .document(term.year + term.semester).collection("Course").document(group.get(0))
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.getResult().exists()) {
                                        if (task.getResult().getString("week").contains("M")) {
                                            t1.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("T")) {
                                            t2.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("W")) {
                                            t3.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("R")) {
                                            t4.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("F")) {
                                            t5.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                    }
                                }
                            });

                            database.collection("Student").document(user.getEmail()).collection("Term")
                                    .document(term.year + term.semester).collection("Course").document(group.get(1))
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.getResult().exists()) {
                                        if (task.getResult().getString("week").contains("M")) {
                                            t6.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("T")) {
                                            t7.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("W")) {
                                            t8.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("R")) {
                                            t9.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("F")) {
                                            t10.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                    }
                                }
                            });

                            database.collection("Student").document(user.getEmail()).collection("Term")
                                    .document(term.year + term.semester).collection("Course").document(group.get(2))
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.getResult().exists()) {
                                        if (task.getResult().getString("week").contains("M")) {
                                            t11.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("T")) {
                                            t12.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("W")) {
                                            t13.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("R")) {
                                            t14.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("F")) {
                                            t15.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                    }
                                }
                            });
                            database.collection("Student").document(user.getEmail()).collection("Term")
                                    .document(term.year + term.semester).collection("Course").document(group.get(3))
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.getResult().exists()) {
                                        if (task.getResult().getString("week").contains("M")) {
                                            t16.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("T")) {
                                            t17.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("W")) {
                                            t18.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("R")) {
                                            t19.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("F")) {
                                            t20.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                    }
                                }
                            });
                            database.collection("Student").document(user.getEmail()).collection("Term")
                                    .document(term.year + term.semester).collection("Course").document(group.get(4))
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.getResult().exists()) {
                                        if (task.getResult().getString("week").contains("M")) {
                                            t21.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("T")) {
                                            t22.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("W")) {
                                            t23.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("R")) {
                                            t24.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                        if (task.getResult().getString("week").contains("F")) {
                                            t25.setText("" + task.getResult().getString("code") + "\n" + task.getResult().getString("time"));
                                        }
                                    }
                                }
                            });
                        }

                    }

                    else{
                        Toast.makeText(weekschedule.this, "No Courses", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }
    public static String numberCourses(int num){
        if(num>0 && num<=5){
            return "pass";
        }
        else{
            return "Not Pass";
        }
    }

}


