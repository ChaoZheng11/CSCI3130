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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Pattern;

/**
 * Author Ziwei Wang & Guoyu
 * Create Date: 2019/3/9
 *Description: Sort and list the courses and list information of the courses.
 *
 **/
public class Create extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText role;

    private FirebaseAuth Auth;
    private Button addNewUser;

    FirebaseFirestore database;
    private static final String Tag = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);

        name = findViewById(R.id.nameEdit);
        email = findViewById(R.id.emailEdit);
        password = findViewById(R.id.LoginPassword);
        role = findViewById(R.id.roleEdit);

        addNewUser = findViewById(R.id.addNewBtn);

        database = FirebaseFirestore.getInstance();
        Auth = FirebaseAuth.getInstance();



        addNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailTest(email.getText().toString()).equals("pass") && NameTest(name.getText().toString())
                        .equals("pass") && RoleTest(role.getText().toString()).equals("pass")
                        && isStrongPassword(password.getText().toString()) == 5) {

                    User u = new User(name.getText().toString(), email.getText().toString(),
                            role.getText().toString(), password.getText().toString(), "Not registered", "Not registered", "Not registered");
                    CreateAccount(email.getText().toString(), password.getText().toString());
                    DocumentReference doc = database.collection("User").document(u.email);
                    u.id = doc.getId();
                    doc.set(u);

                    if (role.getText().toString().equals("0")) {
                        if(!email.getText().toString().contains("admin")) {
                            database.collection("User").document(u.email).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    User user = task.getResult().toObject(User.class);
                                    database.collection("Student").document(user.email).set(user);
                                }
                            });
                            Toast.makeText(Create.this, "Student account created", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Create.this, NewAccount.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Create.this,"email is not match an admin account",Toast.LENGTH_LONG).show();
                        }
                    }
                    else if (role.getText().toString().equals("1") ) {
                        if(email.getText().toString().contains("admin")){
                            database.collection("User").document(u.email).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    User user = task.getResult().toObject(User.class);
                                    database.collection("Admin").document(user.email).set(user);
                                }
                            });
                            Toast.makeText(Create.this,"Admin account created",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Create.this,NewAccount.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Create.this,"email is not match an admin account",Toast.LENGTH_LONG).show();
                        }

                    }

                }
                else{
                    AlertDialog alertDialog = new AlertDialog.Builder(Create.this)
                            .setTitle("Wrong format problems")
                            .setMessage("Email should contain @, "
                                    + "Password should contain at least 1 number, 1 uppercase and 1 lowercase letter, length between 6 to 16"
                                    + "Role only can be 0 or 1")
                            .setIcon(R.mipmap.ic_launcher)
                            .create();
                    alertDialog.show();
                }
            }
        });
    }

    private void CreateAccount(String email,String password) {
        Auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(Tag, "createUserWithEmail:success");
                            Toast.makeText(Create.this, "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                            //FirebaseUser user = Auth.getCurrentUser();

                        }
                        else{
                            Log.w(Tag, "createUserWithEmail:success");
                            Toast.makeText(Create.this, "Authentication success.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


    public static String emailTest(String personal_email) {
        if (personal_email.matches("^[\\s\\S]*\\@[\\s\\S]*$")) {
            return "pass";
        }
        else {
            return "not pass";
        }

    }

    public static String RoleTest(String role) {
        if (role.equals("0") || role.equals("1")) {
            return "pass";
        } else {
            return "not pass";
        }
    }

    public static String NameTest(String name) {
        if (name.matches("^.[\\s\\S]*$")) {
            return "pass";
        } else {
            return "not pass";
        }
    }

    public static int isStrongPassword(String password) {
        int n = 0;
        if (!password.toLowerCase().equals("password")) {
            n++;
        }
        if (password.length() >= 6) {
            n++;
        }
        if (password.length() <= 16) {
            n++;
        }

        if (Pattern.matches(".*[a-z]{1,}.*", password) && Pattern.matches(".*[A-Z]{1,}.*", password)) {
            n++;
        }

        if (Pattern.matches(".*[0-9]{1,}.*", password)) {
            n++;
        }
        return n;
    }
}

