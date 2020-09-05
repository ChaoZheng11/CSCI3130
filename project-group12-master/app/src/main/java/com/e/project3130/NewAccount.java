package com.e.project3130;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.project3130.AdminPage.Admin_Main;
import com.e.project3130.StudentPage.TermSelect;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Author Ziwei Wang & Chao Zheng
 * Create Date: 2019/2/11
 *Description: This class allow the user to create a new account and login with this new account.
 *
 **/
public class NewAccount extends AppCompatActivity {

    private EditText email;
    private EditText password;

    private Button login;
    private Button createNew;
    private Button reset;

    private FirebaseAuth Auth;
    private FirebaseFirestore database;
    private static final String Tag = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        email = findViewById(R.id.LoginEmail);
        password = findViewById(R.id.LoginPassword);
        database = FirebaseFirestore.getInstance();

        login = findViewById(R.id.LoginBtn);
        createNew = findViewById(R.id.NewAccount);
        reset = findViewById(R.id.passwordReset);




        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewAccount.this,Create.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int i = v.getId();
                if(email.getText().toString().matches("^[\\s\\S]*\\@[\\s\\S]*$") && password.getText().toString().matches("^.[\\s\\S]*$")){
                    if(i == R.id.LoginBtn ){
                        LoginAccount(email.getText().toString(),password.getText().toString());
                    }
                }

                else{
                    Toast.makeText(NewAccount.this,"Email and Password Might be Wrong",Toast.LENGTH_LONG).show();
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(email.getText().toString().matches("^[\\s\\S]*\\@[\\s\\S]*$")) {
                    //AuthUI.getInstance().signOut(NewAccount.this);
                    resetPassword(email.getText().toString());
                }
                else {
                   Toast.makeText(NewAccount.this,"Wrong Email format!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void LoginAccount(String email,String password){
        Auth = FirebaseAuth.getInstance();
        Auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(Tag, "signInWithEmail:success");
                            FirebaseUser user = Auth.getCurrentUser();

                            if( user.getEmail().contains("admin")) {

                                Intent intent = new Intent(NewAccount.this, Admin_Main.class);
                                startActivity(intent);
                            }else{
                                Intent intent = new Intent(NewAccount.this, TermSelect.class);
                                startActivity(intent);
                            }

                        }
                        else {
                            Log.w(Tag, "signInWithEmail:failure", task.getException());
                            Toast.makeText(NewAccount.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }


    private void resetPassword(final String email) {
        database.collection("User").document(email).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.getResult().exists()){
                    Auth = FirebaseAuth.getInstance();
                    Auth.sendPasswordResetEmail(email);
                    AlertDialog alertDialog1 = new AlertDialog.Builder(NewAccount.this)
                            .setTitle("Change Password Infor")
                            .setMessage("An email has bee sent to you")
                            .setIcon(R.mipmap.ic_launcher)
                            .create();
                    alertDialog1.show();
                }
                else{
                    Toast.makeText(NewAccount.this,"Email does not exist.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}