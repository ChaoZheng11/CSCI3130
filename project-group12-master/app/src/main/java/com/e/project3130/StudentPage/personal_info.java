
package com.e.project3130.StudentPage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.e.project3130.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

import javax.annotation.Nonnull;

/**
 * author: Guoyu and Ziwei
 * date: Apr 4, 2019
 * Tests for update personal information
 */

public class personal_info extends AppCompatActivity {
    private TextView stName;
    private TextView stEmail;
    private EditText stPersonalEmail;
    private EditText stEmergContact;
    private EditText stAddress;
    private FirebaseFirestore database;
    private Button UpdateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        stName = findViewById(R.id.stname);
        stEmail = findViewById(R.id.stemail);
        stPersonalEmail = findViewById(R.id.stPersonalemail);
        stEmergContact = findViewById(R.id.stEmergcontact);
        stAddress = findViewById(R.id.staddress);
        UpdateBtn = findViewById(R.id.Updatebtn);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        database= FirebaseFirestore.getInstance();
        DocumentReference doc = database.collection("Student").document(user.getEmail());
        doc.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@Nonnull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()) {
                            stName.setText(task.getResult().getString("name"));
                            stEmail.setText(task.getResult().getString("email"));
                            stPersonalEmail.setText(task.getResult().getString("personal_email"));
                            stEmergContact.setText(task.getResult().getString("emergency_contact"));
                            stAddress.setText(task.getResult().getString("address"));
                        }
                    }
                });
        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( EmailTest(stPersonalEmail.getText().toString()) == "pass" && Contact(stEmergContact.getText().toString()) == "pass" &&
                        Address(stAddress.getText().toString()) == "pass"){
                    database= FirebaseFirestore.getInstance();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    DocumentReference doc = database.collection("Student").document(user.getEmail());
                    DocumentReference doc1 = database.collection("User").document(user.getEmail());
                    HashMap<String, Object> docData = new HashMap<>();
                    docData.put("personal_email", stPersonalEmail.getText().toString());
                    docData.put("emergency_contact", stEmergContact.getText().toString());
                    docData.put("address", stAddress.getText().toString());
                    doc.set(docData, SetOptions.merge());
                    doc1.set(docData, SetOptions.merge());
                    Toast.makeText(personal_info.this, "Successfully Updated",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(personal_info.this, "Wrong Format Input", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public static String EmailTest(String personal_email) {
        if (personal_email.matches("^[\\s\\S]*\\@[\\s\\S]*$")) {
            return "pass";
        }
        else {
            return "not pass";
        }

    }



    public static String Contact(String personal_contact) {
        if (personal_contact.matches("^[0-9]{10}$")) {
            return "pass";
        } else {
            return "not pass";
        }
    }

    public static String Address(String address) {
        if (address.matches("^.[\\s\\S]*$")) {
            return "pass";
        } else {
            return "not pass";
        }
    }
}

