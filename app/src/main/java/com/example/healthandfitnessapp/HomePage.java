package com.example.healthandfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
    Button currentDayButton;
    Button macrosHelpButton;
    Button toDoTodayButton;
    Button findGymsOrParksButton;
    Button remindersButton;
    Button findHealthyOptionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        currentDayButton = findViewById(R.id.currentDay);
        macrosHelpButton = (Button) findViewById(R.id.macrosHelp);
        toDoTodayButton = (Button) findViewById(R.id.toDoToday);
        findGymsOrParksButton = (Button) findViewById(R.id.findGymsOrParks);
        remindersButton = (Button) findViewById(R.id.reminders);
        findHealthyOptionsButton = (Button) findViewById(R.id.findHealthyOptions);


        //LISTENERS FOR THE 7 BOTTOM BUTTONS ON HOME PAGE
        currentDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCurrentDayPage();
            }
        });

        macrosHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMacrosHelpPage();
            }
        });

        toDoTodayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openToDoTodayPage();
            }
        });

        findGymsOrParksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFindGymsOrParksPage();
            }
        });

        remindersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRemindersPage();
            }
        });

        findHealthyOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFindHealthyOptionsPage();
            }
        });
    }

    //METHODS FOR OPENING OTHER APP PAGES FROM BUTTONS
    //ALL STILL NEED THE C FILES FOR EACH PAGE... CAN COMMENT OUT

    public void openCurrentDayPage() {
        Intent intent = new Intent(this, CurrentDayActivity.class);
        startActivity(intent);
    }
    public void openMacrosHelpPage() {
        Intent intent = new Intent(this, MacrosHelpActivity.class);
        startActivity(intent);
    }
    public void openToDoTodayPage() {
        Intent intent = new Intent(this, TodayTasksActivity.class);
        startActivity(intent);
    }
    public void openFindGymsOrParksPage() {
        Intent intent = new Intent(this, GymParkActivity.class);
        startActivity(intent);
    }
    public void openRemindersPage() {
        Intent intent = new Intent(this, RemindersActivity.class);
        startActivity(intent);
    }
    public void openFindHealthyOptionsPage() {
        Intent intent = new Intent(this, HealthOptionsActivity.class);
        startActivity(intent);
    }

}