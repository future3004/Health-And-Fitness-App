package com.example.healthandfitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirstTimeSignUp extends AppCompatActivity {

    FirebaseAuth mAuth = null;
    EditText emailEntry;
    EditText passwordEntry;
    EditText usernameEntry;
    MaterialButton submitButtonSignUpPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_sign_up);

        //Can these be the same variables as in activity main (login) page ??????
        // TO DO: Need to set up regex to have validity for all of these.
        usernameEntry = findViewById(R.id.username);
        passwordEntry = findViewById(R.id.password);
        emailEntry = findViewById(R.id.email);
        submitButtonSignUpPage = findViewById(R.id.submitButtonSignUpPage);

        //mAuth = FirebaseAuth.getInstance();
        // set toolbar
        ActionBar actionBar = getSupportActionBar();
        try {
            actionBar.setTitle("Signup");
            actionBar.setDisplayHomeAsUpEnabled(true);
        } catch (Exception e){ e.printStackTrace(); }

        submitButtonSignUpPage.setOnClickListener(view -> {
            createUser();

        });

    }
    private void createUser(){
        String email = emailEntry.getText().toString().trim();
        String password = passwordEntry.getText().toString().trim();
        String username = usernameEntry.getText().toString().trim();

        Intent intent = new Intent(FirstTimeSignUp.this, GettingStarted.class);
        intent.putExtra("username", username);
        startActivity(intent);

        //openGettingStartedPage();
/*
        if(TextUtils.isEmpty(email)){
            emailEntry.setError("Email cannot be empty");
            emailEntry.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            passwordEntry.setError("Password cannot be empty");
            passwordEntry.requestFocus();
        }else if (TextUtils.isEmpty(username)){
            usernameEntry.setError("Username cannot be empty");
            usernameEntry.requestFocus();
        }else{
           // startActivity(new Intent(FirstTimeSignUp.this, HomePage.class));
            openGettingStartedPage();
*//*            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(FirstTimeSignUp.this, "User registered.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(FirstTimeSignUp.this, MainActivity.class)); //don't know if second param is correct
                    }else{
                        Toast.makeText(FirstTimeSignUp.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });*//*
        }*/
    }

    public void openGettingStartedPage(){
        Intent intent = new Intent(FirstTimeSignUp.this, GettingStarted.class);
        startActivity(intent);
    }
}