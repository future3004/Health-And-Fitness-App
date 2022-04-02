package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirstTimeSignUp extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView emailEntry;
    TextView passwordEntry;
    TextView usernameEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_sign_up);

        //Can these be the same variables as in activity main (login) page ??????
        // TO DO: Need to set up regex to have validity for all of these.
        usernameEntry = (TextView) findViewById(R.id.username);
        passwordEntry = (TextView) findViewById(R.id.password);
        emailEntry = (TextView) findViewById(R.id.email);

        mAuth = FirebaseAuth.getInstance();

        MaterialButton submitButtonSignUpPage = (MaterialButton) findViewById(R.id.submitButtonSignUpPage);

        submitButtonSignUpPage.setOnClickListener(view -> {
            createUser();
        });

        submitButtonSignUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGettingStartedPage();
            }
        });
    }

    private void createUser(){
        String email = emailEntry.getText().toString();
        String password = passwordEntry.getText().toString();
        String username = usernameEntry.getText().toString();

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
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(FirstTimeSignUp.this, "User registered.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(FirstTimeSignUp.this, MainActivity.class)); //don't know if second param is correct
                    }else{
                        Toast.makeText(FirstTimeSignUp.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void openGettingStartedPage(){
        Intent intent = new Intent(this, GettingStarted.class);
        startActivity(intent);
    }
}