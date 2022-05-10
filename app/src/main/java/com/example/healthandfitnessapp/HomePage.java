package com.example.healthandfitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class HomePage extends AppCompatActivity {
    Button currentDayButton;
    Button macrosHelpButton;
    Button toDoTodayButton;
    Button findGymsOrParksButton;
    Button remindersButton;
    Button findHealthyOptionsButton;
    TextView userTxtView;

    private FirebaseAuth auth = null;
    private FirebaseUser user = null;
    private FirebaseDatabase firebaseDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // set toolbar
        ActionBar actionBar = getSupportActionBar();
        try {
            actionBar.setTitle("Home");
            //actionBar.setDisplayHomeAsUpEnabled(true);
        } catch (Exception e){ e.printStackTrace(); }

        currentDayButton = findViewById(R.id.currentDay);
        macrosHelpButton = (Button) findViewById(R.id.macrosHelp);
        toDoTodayButton = (Button) findViewById(R.id.toDoToday);
        findGymsOrParksButton = (Button) findViewById(R.id.findGymsOrParks);
        remindersButton = (Button) findViewById(R.id.reminders);
        findHealthyOptionsButton = (Button) findViewById(R.id.findHealthyOptions);
        userTxtView = findViewById(R.id.homePageHeader);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();


        userTxtView.setText("Hello, welcome");


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_logout) {
            // logout user
            signUserOut();
        }

        return super.onOptionsItemSelected(item);
    }

    private void signUserOut(){

        //Log.i("App Build", "Logged User out");
        if (auth != null){
            // sign user out and return to login screen
            auth.signOut();
            Intent intent = new Intent(HomePage.this, MainActivity.class);
            startActivity(intent);
        }


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