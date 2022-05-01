package com.example.healthandfitnessapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthandfitnessapp.Controllers.CurrentListAdapter;
import com.example.healthandfitnessapp.Controllers.ExerciseListAdapter;
import com.example.healthandfitnessapp.Controllers.MealListAdapter;
import com.example.healthandfitnessapp.Models.CurrentDayModel;
import com.example.healthandfitnessapp.Util.CustomDialog;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CurrentDayActivity extends AppCompatActivity {

    private ImageView breakfastAddBtn, lunchAddBtn, dinnerAddBtn, exerciseAddBtn;
    private ListView breakfastListView, lunchListView, dinnerListView, exerciseListView;
    private TextView exerciseCaloriesTxt, goalCalorieTxt, caloriesCountTxt, calorieBankTxt, calorieStatusTxt;
    private TextView dateTextView;
    private TextView breakFastTxt, lunchTxt,  dinnerTxt;
    private LinearProgressIndicator progressIndicator;


    private ExerciseListAdapter exerciseAdapter = null;
    private ArrayList<CurrentDayModel> exerciseItems;
    private ArrayList<CurrentDayModel> lunchItems;
    private MealListAdapter lunchAdapter = null;
    private ArrayList<CurrentDayModel> dinnerItems;
    private MealListAdapter dinnerAdapter = null;
    private ArrayList<CurrentDayModel> breakfastItems;
    private MealListAdapter breakfastAdapter = null;

    public int breakFastCalories = 0;
    public int lunchCalories = 0;
    public int dinnerCalories = 0;
    public int exerciseCaloriesBurnt = 0;
    public int calorieGoal = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_day);
        breakfastAddBtn = findViewById(R.id.addIcon2);
        lunchAddBtn = findViewById(R.id.addIcon3);
        dinnerAddBtn = findViewById(R.id.addIcon4);
        exerciseAddBtn = findViewById(R.id.addIcon5);
        breakfastListView = findViewById(R.id.breakfast_listView);
        lunchListView = findViewById(R.id.lunch_listView);
        dinnerListView = findViewById(R.id.dinner_listView);
        exerciseListView = findViewById(R.id.exercise_listView);
        dateTextView = findViewById(R.id.date_textView);
        breakFastTxt = findViewById(R.id.breakfast_text);
        lunchTxt = findViewById(R.id.lunch_text);
        dinnerTxt = findViewById(R.id.dinner_text);
        exerciseCaloriesTxt = findViewById(R.id.calories_burn);
        goalCalorieTxt = findViewById(R.id.calories_goal);
        caloriesCountTxt = findViewById(R.id.calories_intake);
        calorieBankTxt = findViewById(R.id.calories_bank);
        calorieStatusTxt = findViewById(R.id.under_over);
        progressIndicator = findViewById(R.id.my_progressBar);

        // set toolbar
        ActionBar actionBar = getSupportActionBar();
        try {
            actionBar.setTitle("Current Day");
            actionBar.setDisplayHomeAsUpEnabled(true);
        } catch (Exception e){ e.printStackTrace(); }

        // set today date
        //"Today: Tues, Dec 31"
        //E.dd.LLL.yyyy HH:mm:ss aaa z
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, LLL dd HH:mm:ss aaa z");
        String dateTime = simpleDateFormat.format(calendar.getTime()).toString();
        dateTextView.setText("Today: " + dateTime);

        // breakfast listView
        breakfastItems = new ArrayList<>();
        breakfastItems.add(new CurrentDayModel("Coffee, w/ Skim milk", "8 fluid ounces",
                100,
                "https://static01.nyt.com/images/2019/02/05/world/05egg/15xp-egg-promo-jumbo-v2.jpg?quality=75&auto=webp"));
        breakfastItems.add(new CurrentDayModel("Scrambled Eggs", "2", 50,
                "https://static01.nyt.com/images/2019/02/05/world/05egg/15xp-egg-promo-jumbo-v2.jpg?quality=75&auto=webp"));
        breakfastAdapter = new MealListAdapter(getApplicationContext(), breakfastItems);
        breakfastListView.setAdapter(breakfastAdapter);
        breakfastListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),"Remove at: " + i,Toast.LENGTH_SHORT).show();
                removeItem(breakfastItems, breakfastAdapter, i);

                return false;
            }
        });
        // calculate calories
        for (CurrentDayModel i: breakfastItems) {
            breakFastCalories += i.getCaloriesPlusMinus();
        }
        breakFastTxt.setText("Breakfast: " + breakFastCalories);

        // lunch listView
        lunchItems = new ArrayList<>();
        lunchItems.add(new CurrentDayModel("Stew", "quarter pound", 300,
                "https://static01.nyt.com/images/2019/02/05/world/05egg/15xp-egg-promo-jumbo-v2.jpg?quality=75&auto=webp"));

        lunchAdapter = new MealListAdapter(getApplicationContext(), lunchItems);
        lunchListView.setAdapter(lunchAdapter);
        lunchListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),"Remove at: " + i,Toast.LENGTH_SHORT).show();
                removeItem(lunchItems, lunchAdapter, i);

                return false;
            }
        });
        // calculate calories
        for (CurrentDayModel i: lunchItems) {
            lunchCalories += i.getCaloriesPlusMinus();
        }
        lunchTxt.setText("Lunch: " + lunchCalories);

        // dinner listView
        dinnerItems = new ArrayList<>();
        dinnerItems.add(new CurrentDayModel("Spaghetti", "small", 200, ""));
        dinnerAdapter = new MealListAdapter(getApplicationContext(), dinnerItems);
        dinnerListView.setAdapter(dinnerAdapter);
        dinnerListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),"Remove at: " + i,Toast.LENGTH_SHORT).show();
                removeItem(dinnerItems, dinnerAdapter, i);

                return false;
            }
        });
        // calculate calories
        for (CurrentDayModel i: dinnerItems) {
            dinnerCalories += i.getCaloriesPlusMinus();
        }
        dinnerTxt.setText("Dinner: " + dinnerCalories);

        // exercise listView
        exerciseListInit();


        // on click listeners
        breakfastAddBtn.setOnClickListener(view -> {
            //Toast.makeText(CurrentDayActivity.this, "Breakfast add", Toast.LENGTH_SHORT).show();

            CustomDialog dialog = new CustomDialog(CurrentDayActivity.this, breakfastItems,
                    "Add Breakfast Meal",
                    "Meal: ", "Qty: ", "Calories(+): ");
            dialog.show();
            breakfastAdapter.setList(dialog.newDialogList());
            breakfastAdapter.notifyDataSetChanged();
        });
        lunchAddBtn.setOnClickListener(view -> {
            //Toast.makeText(CurrentDayActivity.this, "Lunch add", Toast.LENGTH_SHORT).show();
            CustomDialog dialog = new CustomDialog(CurrentDayActivity.this, lunchItems,
                    "Add Lunch Meal",
                    "Meal: ", "Qty: ", "Calories(+): ");
            dialog.show();
            lunchAdapter.setList(dialog.newDialogList());
            lunchAdapter.notifyDataSetChanged();
        });
        dinnerAddBtn.setOnClickListener(view -> {
            //Toast.makeText(CurrentDayActivity.this, "Dinner add", Toast.LENGTH_SHORT).show();
            CustomDialog dialog = new CustomDialog(CurrentDayActivity.this, dinnerItems,
                    "Add Dinner Meal",
                    "Meal: ", "Qty: ", "Calories(+): ");
            dialog.show();
            dinnerAdapter.setList(dialog.newDialogList());
            dinnerAdapter.notifyDataSetChanged();
        });
        exerciseAddBtn.setOnClickListener(view -> {
            //Toast.makeText(CurrentDayActivity.this, "Exercise add", Toast.LENGTH_SHORT).show();
            CustomDialog dialog = new CustomDialog(CurrentDayActivity.this, exerciseItems,
                    "Add Exercise",
                    "Exercise: ", "Duration: ", "Calories(-): ");
            dialog.show();

            exerciseAdapter.setList(dialog.newDialogList());
            exerciseAdapter.notifyDataSetChanged();
        });

        // initialize the calories card method
        caloriesCardInit();
    }

    private void caloriesCardInit() {

        goalCalorieTxt.setText("" + calorieGoal);
        int caloriesNow = breakFastCalories + lunchCalories + dinnerCalories;
        caloriesCountTxt.setText(""+ caloriesNow);
        exerciseCaloriesTxt.setText(""+ exerciseCaloriesBurnt);

        int minusExercise = caloriesNow - exerciseCaloriesBurnt;
        int overUnder = calorieGoal - minusExercise;

        if (overUnder > 0) {
            // user still under calories goal for day
            calorieStatusTxt.setText("UNDER");
            calorieStatusTxt.setTextColor(Color.GREEN);
        } else {
            // user exceeded calorie goal intake
            calorieStatusTxt.setText("OVER");
            calorieStatusTxt.setTextColor(Color.RED);
        }
        calorieBankTxt.setText(""+ overUnder);
        progressIndicator.setMax(calorieGoal);
        progressIndicator.setProgress(calorieGoal/minusExercise);
    }

    public static void removeItem(ArrayList<CurrentDayModel> list, MealListAdapter adapter,
                                  int indexToRemove) {
        list.remove(indexToRemove);
        adapter.notifyDataSetChanged();

    }

    public static void addItem(CurrentDayModel newItem, ArrayList<CurrentDayModel> list,
                               ListView listView) {
        //list.add(newItem);
        //listView.setAdapter(adapter);
    }

    private void exerciseListInit() {

        exerciseItems = new ArrayList<>();
        exerciseItems.add(new CurrentDayModel("Swimming", "25 minutes",
                250,""));
        exerciseItems.add(new CurrentDayModel("Cross Country Run", "120 minutes",
                300,""));

        // calculate calories
        for (CurrentDayModel i: exerciseItems) {
            exerciseCaloriesBurnt += i.getCaloriesPlusMinus();
        }
        //exerciseCaloriesTxt.setText(exerciseCaloriesBurnt);


        exerciseAdapter = new ExerciseListAdapter(CurrentDayActivity.this, exerciseItems);
        exerciseListView.setAdapter(exerciseAdapter);
        exerciseListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),"Remove at: " + i,Toast.LENGTH_SHORT).show();
                exerciseItems.remove(i);
                exerciseAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}