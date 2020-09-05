package com.e.project3130.AdminPage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.e.project3130.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * @author Chao Zheng && JiaXiang Liu
 * @date ${16/03/2019}
 * @return tuition fees for auth student
 */

public class numberRegister extends AppCompatActivity {

    private TextView c3101;
    private TextView c3110;
    private TextView c3120;
    private TextView c3130;
    private TextView c3136;
    private FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_registered);

        c3101=findViewById(R.id.c3101);
        c3110=findViewById(R.id.c3110);
        c3120=findViewById(R.id.c3120);
        c3130=findViewById(R.id.c3130);
        c3136=findViewById(R.id.c3136);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        database=FirebaseFirestore.getInstance();
        database.collection("Course").document("CSCI3120").collection("Students Information").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int count = 0;
                    for (DocumentSnapshot document : task.getResult()) {
                        count++;
                    }
                    c3120.setText(String.valueOf(count)+" students");
                }
            }
        });

        database.collection("Course").document("CSCI3130").collection("Students Information").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int count = 0;
                    for (DocumentSnapshot document : task.getResult()) {
                        count++;
                    }
                    c3130.setText(String.valueOf(count)+" students");
                }
            }
        });

        database.collection("Course").document("CSCI3110").collection("Students Information").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int count = 0;
                    for (DocumentSnapshot document : task.getResult()) {
                        count++;
                    }
                    c3110.setText(String.valueOf(count)+ " students");
                }
            }
        });

        database.collection("Course").document("CSCI3101").collection("Students Information").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int count = 0;
                    for (DocumentSnapshot document : task.getResult()) {
                        count++;
                    }
                    c3101.setText(String.valueOf(count)+" students");
                }
            }
        });

        database.collection("Course").document("CSCI3136").collection("Students Information").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int count = 0;
                    for (DocumentSnapshot document : task.getResult()) {
                        count++;
                    }
                    c3136.setText(String.valueOf(count)+" students");
                }
            }
        });
    }
}
