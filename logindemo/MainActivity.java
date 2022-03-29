package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);


        //testing with username and password as "admin" and "admin"
        MaterialButton submitButtonLogInPage = (MaterialButton) findViewById(R.id.submitButtonLogInPage);
        submitButtonLogInPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    //correct
                    Toast.makeText(MainActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    openHomePage();
                }else
                    //incorrect
                    Toast.makeText(MainActivity.this, "INCORRECT LOGIN, PLEASE RE-ENTER", Toast.LENGTH_SHORT).show();

            }
        });

        //Sign Up Link
        TextView signUpLink = (TextView) findViewById(R.id.signUpLink);
        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFirstTimeSignUpPage();
            }
        });
    }

//Can i use "intent" for all of these???
    public void openHomePage(){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void openFirstTimeSignUpPage(){
        Intent intent = new Intent(this, FirstTimeSignUp.class);
        startActivity(intent);
    }
}