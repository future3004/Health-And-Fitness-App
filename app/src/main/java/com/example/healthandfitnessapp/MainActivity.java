package com.example.healthandfitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
    private TextView signUpLink, forgotPasswordToggle;

    private FirebaseAuth mAuth = null;
    private FirebaseUser user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailLogIn = findViewById(R.id.Email);
        passwordLogIn = findViewById(R.id.password);
        submitButton = findViewById(R.id.submitButtonLogInPage);
        signUpLink = findViewById(R.id.signUpLink);
        forgotPasswordToggle = findViewById(R.id.forgotPassword);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mAuth = FirebaseAuth.getInstance();

        //testing with email and password as "admin" and "admin"
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        //Sign Up Link
        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFirstTimeSignUpPage();
            }
        });

        // go to forgot password screen
        forgotPasswordToggle.setOnClickListener(view -> startActivity(new Intent(this, ResetPasswordActivity.class)));
    }
    private void loginUser(){
        String password = passwordLogIn.getText().toString();
        String email = emailLogIn.getText().toString();

        if (TextUtils.isEmpty(password)){
            passwordLogIn.setError("Password cannot be empty");
            passwordLogIn.requestFocus();
        }else if (TextUtils.isEmpty(email)){
            emailLogIn.setError("Email cannot be empty");
            emailLogIn.requestFocus();
        }else{

           mAuth.signInWithEmailAndPassword(email, password)
                   .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("MainActivity", "signInWithEmail:success");
                        user = mAuth.getCurrentUser();
                        openHomePage(user);
                    }else{
                        // If sign in fails, display a message to the user.
                        Log.w("MainActivity", "signInWithEmail:failure", task.getException());
                        Toast.makeText(MainActivity.this, "Account doesn't exist..",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
       //FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            startActivity(new Intent(MainActivity.this, HomePage.class));
        }
    }


    private void openHomePage(FirebaseUser user){
        if (user != null) {
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }
    }

    private void openFirstTimeSignUpPage(){
        Intent intent = new Intent(this, FirstTimeSignUp.class);
        startActivity(intent);
    }
}