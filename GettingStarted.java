package com.example.healthandfitnessapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class GettingStarted extends AppCompatActivity {

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    TextView usernameEntry;

    private FirebaseAuth mAuth = null;
    EditText ageEntryChoice;
    EditText heightEntryChoice;
    EditText currentWeightEntryChoice;
    EditText goalWeightEntryChoice;
    MaterialButton submitButtonGettingStartedPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);

        mDisplayDate = (TextView) findViewById(R.id.tvDate);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                //month = month + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(GettingStarted.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        // set toolbar
        ActionBar actionBar = getSupportActionBar();
        try {
            actionBar.setTitle("Getting started");
            actionBar.setDisplayHomeAsUpEnabled(true);
        } catch (Exception e){ e.printStackTrace(); }

        String greetingMessage = "Welcome, " + username + ". Please enter the following information to get started:";
        TextView greetingTV = (TextView)findViewById(R.id.gettingStartedText);
        greetingTV.setText(greetingMessage);

        ageEntryChoice = (EditText) findViewById(R.id.ageEntry);
        heightEntryChoice = (EditText) findViewById(R.id.heightEntry);
        currentWeightEntryChoice = (EditText) findViewById(R.id.currentWeightEntry);
        goalWeightEntryChoice = (EditText) findViewById(R.id.goalWeightEntry);
        submitButtonGettingStartedPage = (MaterialButton) findViewById(R.id.submitButtonGettingStartedPage);

        mAuth = FirebaseAuth.getInstance();

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

        submitButtonGettingStartedPage.setOnClickListener(view -> {
            createUserStats();
        });

    }

//    private String getTodaysDate(){
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        month = month + 1;
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        return makeDateString(day, month, year);
//    }
//
//    private void initDatePicker() {
//        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                month = month + 1;
//                String date = makeDateString(day, month, year);
//                dateButton.setText(date);
//            }
//        };
//
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//
//        int style = AlertDialog.THEME_HOLO_LIGHT;
//
//        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
//        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
//    }
//
//    private String makeDateString(int day, int month, int year){
//        return getMonthFormat(month) + " " + day + " " + year;
//    }
//
//    private String getMonthFormat(int month){
//        if(month == 1)
//            return "JAN";
//        if(month == 2)
//            return "FEB";
//        if(month == 3)
//            return "MAR";
//        if(month == 4)
//            return "APR";
//        if(month == 5)
//            return "MAY";
//        if(month == 6)
//            return "JUN";
//        if(month == 7)
//            return "JUL";
//        if(month == 8)
//            return "AUG";
//        if(month == 9)
//            return "SEP";
//        if(month == 10)
//            return "OCT";
//        if(month == 11)
//            return "NOV";
//        if(month == 12)
//            return "DEC";
//        //default
//        return "JAN";
//    }
//
//    public void openDatePicker(View view) {
//        datePickerDialog.show();
//    }
    private void createUserStats() {
/*        String age = ageEntryChoice.getText().toString();
        int ageInt = Integer.parseInt(age);
        String height = heightEntryChoice.getText().toString();
        int heightInt = Integer.parseInt(height);
        String currentWeight = currentWeightEntryChoice.getText().toString();
        int currentWeightInt = Integer.parseInt(currentWeight);
        String goalWeight = goalWeightEntryChoice.getText().toString();
        int goalWeightInt = Integer.parseInt(goalWeight);*/

        openHomePage();

/*        if(TextUtils.isEmpty(age)){
            ageEntryChoice.setError("age cannot be empty");
            ageEntryChoice.requestFocus();
        }else if (TextUtils.isEmpty(height)){
            heightEntryChoice.setError("height cannot be empty");
            heightEntryChoice.requestFocus();
        }else if (TextUtils.isEmpty(currentWeight)) {
            currentWeightEntryChoice.setError("current weight cannot be empty");
            currentWeightEntryChoice.requestFocus();
        }else if (TextUtils.isEmpty(goalWeight)) {
            goalWeightEntryChoice.setError("goal weight cannot be empty");
            goalWeightEntryChoice.requestFocus();
        }else{
            openHomePage();
        }*/

    }


    public void openHomePage(){
        Intent intent = new Intent(GettingStarted.this, HomePage.class);
        startActivity(intent);
    }


}