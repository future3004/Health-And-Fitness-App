package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //uncomment all when able to test

        //LISTENERS FOR THE 7 BOTTOM BUTTONS ON HOME PAGE
        /*

        TextView progressTimeLineLink = (TextView) findViewById(R.id.progressTimelineLink);
        progressTimeLineLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProgressTimeLinePage();
            }
        });

        Button currentDayButton = (Button) findViewById(R.id.currentDay);
        currentDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCurrentDayPage();
            }
        });

        Button macrosHelpButton = (Button) findViewById(R.id.macrosHelp);
        macrosHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMacrosHelpPage();
            }
        });

        Button toDoTodayButton = (Button) findViewById(R.id.toDoToday);
        toDoTodayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openToDoTodayPage();
            }
        });

        Button findGymsOrParksButton = (Button) findViewById(R.id.findGymsOrParks);
        findGymsOrParksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFindGymsOrParksPage();
            }
        });

        Button remindersButton = (Button) findViewById(R.id.reminders);
        remindersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRemindersPage();
            }
        });

        Button findHealthyOptionsButton = (Button) findViewById(R.id.findHealthyOptions);
        findHealthyOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFindHealthyOptionsPage();
            }
        });

         */
    }
    //METHODS FOR OPENING OTHER APP PAGES FROM BUTTONS
    //ALL STILL NEED THE C FILES FOR EACH PAGE... CAN COMMENT OUT
    /*

    public void openProgressTimeLinePage(){
        Intent intent = new Intent(this, ProgressTimeLine.class);
        startActivity(intent);
    }

    public void openCurrentDayPage() {
        Intent intent = new Intent(this, CurrentDayPage.class);
        startActivity(intent);
    }

    public void openMacrosHelpPage() {
        Intent intent = new Intent(this, MacrosHelpPage.class);
        startActivity(intent);
    }

    public void openToDoTodayPage() {
        Intent intent = new Intent(this, ToDoTodayPage.class);
        startActivity(intent);
    }

    public void openFindGymsOrParksPage() {
        Intent intent = new Intent(this, FindGymsOrParksPage.class);
        startActivity(intent);
    }

    public void openRemindersPage() {
        Intent intent = new Intent(this, RemindersPage.class);
        startActivity(intent);
    }

    public void openFindHealthyOptionsPage() {
        Intent intent = new Intent(this, FindHealthyOptionsPage.class);
        startActivity(intent);
    }
     */
}