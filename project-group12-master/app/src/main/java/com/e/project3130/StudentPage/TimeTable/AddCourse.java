package com.e.project3130.StudentPage.TimeTable;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.e.project3130.Model.Course;
import com.e.project3130.Model.Student;
import com.e.project3130.Model.Term;
import com.e.project3130.R;
import com.e.project3130.StudentPage.Student_Main;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * Author Ziwei Wang & Guoyu
 * Create Date: 2019/2/26
 *Description: Student can add course which showed .
 *
 **/
public class AddCourse extends AppCompatActivity {
    private TextView code;
    private TextView title;
    private TextView time;
    private TextView week;
    private TextView spot;

    private Button add;

    private FirebaseFirestore database;
    private Intent intent;
    private Course course;
    private Term term;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.add_course_student);

        code = findViewById(R.id.code);
        title = findViewById(R.id.title);
        time = findViewById(R.id.time);
        week = findViewById(R.id.week);
        spot = findViewById(R.id.spot);

        add = (Button)findViewById(R.id.student_add);
        database = FirebaseFirestore.getInstance();
        intent = getIntent();
        term = (Term)intent.getSerializableExtra("Term");
        course = (Course)intent.getSerializableExtra("Course");
        code.setText("Course code: "+ course.code);
        title.setText("Course title: " + course.title);
        time.setText("Course time: " + course.time);
        week.setText("Course week: " + course.week);
        spot.setText("Course max spot" + course.spot);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DocumentReference userInstance = database.collection("Student/"+user.getEmail()+"/Term/").document(term.year+term.semester);
        userInstance.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        Term termUpdate = document.toObject(Term.class);
                        List<String> courses = termUpdate.returnCourseList();
                        add.setClickable(false);
                        addCourse();
                        Toast.makeText(AddCourse.this, "Time Checking...", Toast.LENGTH_SHORT).show();
                        if(courses.size()>=5){
                            add.setVisibility(View.GONE);
                            Toast.makeText(AddCourse.this, "You are already take 5 courses!!! ", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student_AddCourse();
            }
        });
    }


    private void Student_AddCourse() {
        database.collection("Term").document(term.year + term.semester).collection("Course")
                .document(course.code).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@Nonnull Task<DocumentSnapshot> task) {

                if (task.getResult().exists()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    final Course course = task.getResult().toObject(Course.class);
                    DocumentReference ref = database.collection("Student")
                            .document(user.getEmail()).collection("Term")
                            .document(term.year+term.semester);
                    ref.set(term);
                    database.collection("Student").document(user.getEmail()).collection("Term")
                            .document(term.year + term.semester).collection("Course")
                            .document(course.code).set(course);

                    DocumentReference studentInfor = database.collection("Student").document(user.getEmail());
                    studentInfor.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            Student student = task.getResult().toObject(Student.class);

                            database.collection("Term").document(term.year + term.semester).
                                    collection("Course").document(course.code).collection("Students")
                                    .document(user.getEmail()).set(student);
                        }
                    });

                    final String email = user.getEmail();
                    final String year = term.year;
                    final String semester = term.semester;
                    CollectionReference docRef = database.collection("Student").document(user.getEmail()).collection("Term")
                            .document(term.year + term.semester).collection("Course");

                    docRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            //String code = String.valueOf(task.getResult().get("code"));
                            Term term = new Term();
                            term.setSemester(semester);
                            term.setYear(year);
                            for (DocumentSnapshot doc1 : task.getResult()) {
                                term.courselist.add(String.valueOf(doc1.getString("code")));
                                DocumentReference doc = database.collection("Student").document(email)
                                        .collection("Term").document(year + semester);
                                doc.set(term);
                            }
                        }
                    });



                    Toast.makeText(AddCourse.this, "Successfully add", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddCourse.this,ViewCourseDB.class);
                    intent.putExtra("Term",term);
                    startActivity(intent);
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddCourse.this);
                    builder.setTitle("Waring").setIcon(R.mipmap.ic_launcher).setMessage("Course do not exist!").show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.setClass(AddCourse.this, Student_Main.class);
        intent.putExtra("Term",term);
        startActivity(intent);
    }

    public void addCourse(){
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();
        final String authUid = auth.getUid();
        database = FirebaseFirestore.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    URLConnection connection = url.openConnection();
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is, "utf-8");
                    BufferedReader bufferedReader = new BufferedReader(isr);
                    String jsonData = "";
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        jsonData = jsonData + line;
                    }
                    bufferedReader.close();
                    try {
                        JSONObject jsonObject1 = new JSONObject(jsonData);
                        final String stateus = jsonObject1.getString("status");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if(stateus.equals("OK")){
                                    add.setClickable(true);
                                    Toast.makeText(AddCourse.this, "No Time Conflict.", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(AddCourse.this, "Time Conflict.", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });


                    }
                    catch (JSONException e) {
                        Log.i("json：", "???" );
                        e.printStackTrace();
                    }
                    isr.close();
                    is.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute("https://web.cs.dal.ca/~ziwei/api/?email="+ user.getEmail() +"&term="+term.year+term.semester+"&date="+course.week+"&time="+course.time);
    }
    
}
