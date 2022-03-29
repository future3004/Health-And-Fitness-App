package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class FirstTimeSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_sign_up);

        //Can these be the same variables as in activity main (login) page ??????
        // TO DO: Need to set up regex to have validity for all of these.
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        TextView email = (TextView) findViewById(R.id.email);

        MaterialButton submitButtonSignUpPage = (MaterialButton) findViewById(R.id.submitButtonSignUpPage);
        submitButtonSignUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGettingStartedPage();
            }
        });
    }

    public void openGettingStartedPage(){
        Intent intent = new Intent(this, GettingStarted.class);
        startActivity(intent);
    }
}