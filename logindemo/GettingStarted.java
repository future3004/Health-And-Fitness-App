package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class GettingStarted extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);

        //Spinners
        Spinner sexSpinner = (Spinner) findViewById(R.id.sexDropDown);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.sexChoices, android.R.layout.simple_spinner_dropdown_item);
        sexSpinner.setAdapter(adapter1);

        Spinner activitySpinner = (Spinner) findViewById(R.id.activityLevelDropDown);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.activityLevelChoices, android.R.layout.simple_spinner_dropdown_item);
        activitySpinner.setAdapter(adapter2);

        Spinner typeOfGoalSpinner = (Spinner) findViewById(R.id.typeOfGoalDropDown);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.typeOfGoalChoices, android.R.layout.simple_spinner_dropdown_item);
        typeOfGoalSpinner.setAdapter(adapter3);

        //Button
        MaterialButton submitButtonGettingStartedPage = (MaterialButton) findViewById(R.id.submitButtonGettingStartedPage);
        submitButtonGettingStartedPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });
    }

    public void openHomePage(){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}