package com.example.healthandfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.healthandfitnessapp.Controllers.CurrentListAdapter;
import com.example.healthandfitnessapp.Controllers.ExerciseListAdapter;
import com.example.healthandfitnessapp.Models.CurrentDayList;
import com.example.healthandfitnessapp.Util.CustomDialog;

import java.util.ArrayList;
import java.util.List;

public class CurrentDayActivity extends AppCompatActivity {

    private ImageView breakfastCameraBtn, lunchCameraBtn, dinnerCameraBtn, exerciseCameraBtn;
    private ImageView breakfastAddBtn, lunchAddBtn, dinnerAddBtn, exerciseAddBtn;
    private ListView breakfastListView, lunchListView, dinnerListView, exerciseListView;


    private CurrentListAdapter breakfastAdapter = null;
    private CurrentListAdapter lunchAdapter = null;
    private CurrentListAdapter dinnerAdapter = null;
    private ExerciseListAdapter exerciseAdapter = null;
    private String[] breakfastFoodTitles = {"Coffee, w/ Skim milk", "Banana, Medium", "Scrambled Eggs", "Yoghurt"};
    private String[] breakfastFoodQtys = {"8 fluid ounces", "2", "2", "1"};
    private String[] breakfastImageUrls = {"https://static01.nyt.com/images/2019/02/05/world/05egg/15xp-egg-promo-jumbo-v2.jpg?quality=75&auto=webp",
            "",
            "https://static01.nyt.com/images/2019/02/05/world/05egg/15xp-egg-promo-jumbo-v2.jpg?quality=75&auto=webp",
            ""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_day);
        breakfastCameraBtn = findViewById(R.id.cameraIcon2);
        breakfastAddBtn = findViewById(R.id.addIcon2);
        lunchCameraBtn = findViewById(R.id.cameraIcon3);
        lunchAddBtn = findViewById(R.id.addIcon3);
        dinnerCameraBtn = findViewById(R.id.cameraIcon4);
        dinnerAddBtn = findViewById(R.id.addIcon4);
        exerciseCameraBtn = findViewById(R.id.cameraIcon5);
        exerciseAddBtn = findViewById(R.id.addIcon5);
        breakfastListView = findViewById(R.id.breakfast_listView);
        lunchListView = findViewById(R.id.lunch_listView);
        dinnerListView = findViewById(R.id.dinner_listView);
        exerciseListView = findViewById(R.id.exercise_listView);

        // breakfast listView
        breakfastAdapter = new CurrentListAdapter(this, breakfastFoodTitles,
                breakfastFoodQtys, breakfastImageUrls);
        breakfastListView.setAdapter(breakfastAdapter);
        breakfastListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"item " + i,Toast.LENGTH_SHORT).show();
            }
        });

        // lunch listView
        lunchAdapter = new CurrentListAdapter(this, breakfastFoodTitles,
                breakfastFoodQtys, breakfastImageUrls);
        lunchListView.setAdapter(lunchAdapter);
        lunchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"item " + i,Toast.LENGTH_SHORT).show();
            }
        });

        // dinner listView
        dinnerAdapter = new CurrentListAdapter(this, breakfastFoodTitles,
                breakfastFoodQtys, breakfastImageUrls);
        dinnerListView.setAdapter(dinnerAdapter);
        dinnerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"item " + i,Toast.LENGTH_SHORT).show();
            }
        });

        // exercise listView
        exerciseListInit();


        // on click listeners
        breakfastCameraBtn.setOnClickListener(view -> {
            Toast.makeText(CurrentDayActivity.this, "Breakfast photo upload..", Toast.LENGTH_SHORT).show();
        });
        lunchCameraBtn.setOnClickListener(view -> {
            Toast.makeText(CurrentDayActivity.this, "Lunch photo upload..", Toast.LENGTH_SHORT).show();
        });
        dinnerCameraBtn.setOnClickListener(view -> {
            Toast.makeText(CurrentDayActivity.this, "Dinner photo upload..", Toast.LENGTH_SHORT).show();
        });
        exerciseCameraBtn.setOnClickListener(view -> {
            Toast.makeText(CurrentDayActivity.this, "Exercise photo upload..", Toast.LENGTH_SHORT).show();
        });

        breakfastAddBtn.setOnClickListener(view -> {
            //Toast.makeText(CurrentDayActivity.this, "Breakfast add", Toast.LENGTH_SHORT).show();

            CustomDialog dialog = new CustomDialog(CurrentDayActivity.this, "Add Breakfast Meal",
                    "Meal: ", "Qty: ");
            //exerciseTitle = dialog.getFirstValue();
            //duration = dialog.getSecondValue();
            dialog.show();
        });
        lunchAddBtn.setOnClickListener(view -> {
            //Toast.makeText(CurrentDayActivity.this, "Lunch add", Toast.LENGTH_SHORT).show();
            CustomDialog dialog = new CustomDialog(CurrentDayActivity.this, "Add Lunch Meal",
                    "Meal: ", "Qty: ");
            dialog.show();
        });
        dinnerAddBtn.setOnClickListener(view -> {
            //Toast.makeText(CurrentDayActivity.this, "Dinner add", Toast.LENGTH_SHORT).show();
            CustomDialog dialog = new CustomDialog(CurrentDayActivity.this, "Add Dinner Meal",
                    "Meal: ", "Qty: ");
            dialog.show();
        });
        exerciseAddBtn.setOnClickListener(view -> {
            //Toast.makeText(CurrentDayActivity.this, "Exercise add", Toast.LENGTH_SHORT).show();
            String exerciseTitle;
            String duration;
            String image = "";

            CustomDialog dialog = new CustomDialog(CurrentDayActivity.this, "Add Exercise",
                    "Exercise: ", "Duration: ");
            //exerciseTitle = dialog.getFirstValue();
            //duration = dialog.getSecondValue();
            dialog.show();


            //Toast.makeText(CurrentDayActivity.this, exerciseTitle + " : " + duration, Toast.LENGTH_SHORT).show();


            //exerciseAdapter.notifyDataSetChanged();
            //exerciseListView.notify();
        });
    }

    private void exerciseListInit() {
        List<String> exerciseNamesList = new ArrayList<>();
        exerciseNamesList.add("1k Run");
        exerciseNamesList.add("Swimming");

        List<String> exerciseDurations = new ArrayList<>();
        exerciseDurations.add("60 minutes");
        exerciseDurations.add("30 minutes");

        List<String> exercisePhotos = new ArrayList<>();
        exercisePhotos.add("");
        exercisePhotos.add("");


        exerciseAdapter = new ExerciseListAdapter(this, exerciseNamesList,
                exerciseDurations, exercisePhotos);
        exerciseListView.setAdapter(exerciseAdapter);
        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"item " + i,Toast.LENGTH_SHORT).show();
            }
        });
    }
}