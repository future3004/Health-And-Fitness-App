package com.example.healthandfitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    MaterialButton submitButton;
    EditText emailLogIn;
    EditText passwordLogIn;
    TextView signUpLink;

    FirebaseAuth mAuth = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the FirebaseAnalytics instance.
        //mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        emailLogIn = findViewById(R.id.Email);
        passwordLogIn = findViewById(R.id.password);
        submitButton = findViewById(R.id.submitButtonLogInPage);
        signUpLink = findViewById(R.id.signUpLink);

        //testing with email and password as "admin" and "admin"
        //MaterialButton submitButtonLogInPage = (MaterialButton) findViewById(R.id.submitButtonLogInPage);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emailLogIn.getText().toString().equals("admin") && passwordLogIn.getText().toString().equals("admin")){
                    //correct
                    Toast.makeText(MainActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    openHomePage();
                }else {
                    //incorrect
                    Toast.makeText(MainActivity.this, "INCORRECT LOGIN, PLEASE RE-ENTER", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //mAuth = FirebaseAuth.getInstance();


        //Sign Up Link

        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFirstTimeSignUpPage();
            }
        });
    }
    private void loginUser(){
        String password = passwordLogIn.getText().toString();
        String email = emailLogIn.getText().toString();

        if (TextUtils.isEmpty(password)){
            passwordLogIn.setError("Password cannot be empty");
            passwordLogIn.requestFocus();
        }else if (TextUtils.isEmpty(email)){
            emailLogIn.setError("Username cannot be empty");
            emailLogIn.requestFocus();
        }else{
            startActivity(new Intent(MainActivity.this, HomePage.class));
/*            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Log in successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, MainActivity.class)); //idk if second param is correct
                    }else{
                        Toast.makeText(MainActivity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });*/
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
/*        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, MainActivity.class)); //idk if second param is correct
        }*/
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