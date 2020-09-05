package com.e.project3130.AdminPage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.project3130.Model.Term;
import com.e.project3130.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.annotation.Nonnull;

/**
 * addNewTerm.java
 *
 * Author Jiaxiang Liu & Chao Zheng
 *
 * Create Date: 2019/3/23
 *
 * Description: Create a new term for students and admin to use.
 **/
public class AddNewTerm extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseFirestore database;
    private EditText year;
    private EditText semester;
    private Button create;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_term);
        year = findViewById(R.id.edityear);
        semester = findViewById(R.id.editsemester);
        create = findViewById(R.id.add);
        database = FirebaseFirestore.getInstance();
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Year = year.getText().toString();
                String Semester = semester.getText().toString();
                if( semester.getText() == null){
                    Toast.makeText(AddNewTerm.this, "Invalid semester", Toast.LENGTH_LONG).show();
                }
                else{
                    if (yearcheck(Year) == true && semestercheck(Semester) == true) {
                        database.collection("Term").document(Year + Semester).get()
                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                                        if (task.getResult().exists()) {
                                            Toast.makeText(AddNewTerm.this, "Term exist", Toast.LENGTH_LONG).show();
                                        } else {
                                            Term c = new Term(year.getText().toString(),
                                                    semester.getText().toString());
                                            DocumentReference ref = database.collection("Term").document(c.year + c.semester);
                                            ref.set(c);
                                            Toast.makeText(AddNewTerm.this, "Successfully Add", Toast.LENGTH_LONG).show();
                                            finish();
                                        }
                                    }
                                });
                    }
                }
            }

            public boolean yearcheck(String Year) {
                int year1;
                char[] year2 = Year.toCharArray();
                for( int i = 0; i < year2.length; i++){
                    if ( year2[i] >57 || year2[i] < 48){
                        Toast.makeText(AddNewTerm.this, "Invalid year", Toast.LENGTH_LONG).show();
                        return false;
                    }
                }
                if (Year != null && Year.length() > 0) {
                    year1 = Integer.parseInt(Year);
                    if (year1 <= 2018) {
                        Toast.makeText(AddNewTerm.this, "Invalid year", Toast.LENGTH_LONG).show();
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    Toast.makeText(AddNewTerm.this, "Invalid year", Toast.LENGTH_LONG).show();
                    return false;
                }
            }

            public boolean semestercheck(String Semester) {
                if (Semester.equalsIgnoreCase("Fall") || Semester.equalsIgnoreCase("Winter") || Semester.equalsIgnoreCase("Summer")) {
                    return true;
                } else {
                    Toast.makeText(AddNewTerm.this, "Invalid semester", Toast.LENGTH_LONG).show();
                    return false;
                }
            }
        });
    }
    public static boolean semestercheck(String Semester){
        if (Semester.equalsIgnoreCase("Fall") || Semester.equalsIgnoreCase("Winter") || Semester.equalsIgnoreCase("Summer")) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean yearcheck(String Year) {
        int year1;
        char[] year2 = Year.toCharArray();
        if (Year != null && Year.length() > 0) {
            for( int i = 0; i < Year.length(); i++){
                if ( year2[i] > 57 || year2[i] < 48){
                    return false;
                }
            }
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
}
