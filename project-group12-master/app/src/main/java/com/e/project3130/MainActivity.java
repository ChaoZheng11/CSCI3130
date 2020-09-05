package com.e.project3130;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.e.project3130.R;

/**
 * Author: Chao Zheng
 * Date: 2019/2/16
 *
 * The main page for App, A button for jumping to create/login interface
 */

public class MainActivity extends AppCompatActivity {

    private Button enter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enter = findViewById(R.id.enterBtn);
        enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,NewAccount.class);
                startActivity(intent);
            }
        });



    }


}
