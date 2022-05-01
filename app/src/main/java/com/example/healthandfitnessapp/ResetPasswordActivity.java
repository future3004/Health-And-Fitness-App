package com.example.healthandfitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {
    private EditText emailEditText;
    private Button resetPasswordButton;

    private FirebaseAuth auth = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);
        emailEditText = findViewById(R.id.lostPassEmailEditText);

        ActionBar actionBar = getSupportActionBar();
        try {
            actionBar.setTitle("Reset Password");
            actionBar.setDisplayHomeAsUpEnabled(true);
        } catch (Exception e){ e.printStackTrace(); }

        auth = FirebaseAuth.getInstance();

        // button click
        resetPasswordButton.setOnClickListener(view -> sendNewPassword());
    }

    private void sendNewPassword() {

        String user_email = emailEditText.getText().toString().trim();

        if (user_email.matches("")) {
            // email field is empty
            Toast.makeText(ResetPasswordActivity.this, "Email is required to reset password!", Toast.LENGTH_SHORT).show();
        } else {
            // proceed to reset
            auth.sendPasswordResetEmail(user_email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("ResetPassActivity", "Email sent.");
                                Toast.makeText(ResetPasswordActivity.this, "Check your email for password reset instructions.", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("ResetPassActivity", "Password reset send email failure.");
                                try {
                                    Toast.makeText(ResetPasswordActivity.this, "Couldn't send email, please try again later. \n"
                                            + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                } catch (NullPointerException e){
                                    e.printStackTrace();
                                }

                            }
                        }
                    });
        }


    }
}