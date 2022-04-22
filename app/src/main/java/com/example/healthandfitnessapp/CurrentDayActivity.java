package com.example.healthandfitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.healthandfitnessapp.Controllers.CurrentListAdapter;
import com.example.healthandfitnessapp.Controllers.ExerciseListAdapter;
import com.example.healthandfitnessapp.Controllers.MealListAdapter;
import com.example.healthandfitnessapp.Models.CurrentDayModel;
import com.example.healthandfitnessapp.Util.CustomDialog;

import java.util.ArrayList;
import java.util.List;

public class CurrentDayActivity extends AppCompatActivity {

    private ImageView breakfastAddBtn, lunchAddBtn, dinnerAddBtn, exerciseAddBtn;
    private ListView breakfastListView, lunchListView, dinnerListView, exerciseListView;


    private ExerciseListAdapter exerciseAdapter = null;
    private ArrayList<CurrentDayModel> exerciseItems;
    private ArrayList<CurrentDayModel> lunchItems;
    private MealListAdapter lunchAdapter = null;
    private ArrayList<CurrentDayModel> dinnerItems;
    private MealListAdapter dinnerAdapter = null;
    private ArrayList<CurrentDayModel> breakfastItems;
    private MealListAdapter breakfastAdapter = null;

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

        // set toolbar
        ActionBar actionBar = getSupportActionBar();
        try {
            actionBar.setTitle("Current Day");
            actionBar.setDisplayHomeAsUpEnabled(true);
        } catch (Exception e){ e.printStackTrace(); }

        // breakfast listView
        breakfastItems = new ArrayList<>();
        breakfastItems.add(new CurrentDayModel("Coffee, w/ Skim milk", "8 fluid ounces",
                "https://static01.nyt.com/images/2019/02/05/world/05egg/15xp-egg-promo-jumbo-v2.jpg?quality=75&auto=webp"));
        breakfastItems.add(new CurrentDayModel("Scrambled Eggs", "2",
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

        // lunch listView
        lunchItems = new ArrayList<>();
        lunchItems.add(new CurrentDayModel("Stew", "quarter pound",
                "https://static01.nyt.com/images/2019/02/05/world/05egg/15xp-egg-promo-jumbo-v2.jpg?quality=75&auto=webp"));
        lunchItems.add(new CurrentDayModel("Stew", "quarter pound",
                "https://static01.nyt.com/images/2019/02/05/world/05egg/15xp-egg-promo-jumbo-v2.jpg?quality=75&auto=webp"));
        lunchItems.add(new CurrentDayModel("Stew", "quarter pound",
                "https://static01.nyt.com/images/2019/02/05/world/05egg/15xp-egg-promo-jumbo-v2.jpg?quality=75&auto=webp"));
        lunchAdapter = new MealListAdapter(getApplicationContext(), lunchItems);
        lunchListView.setAdapter(lunchAdapter);

        // dinner listView
        dinnerItems = new ArrayList<>();
        dinnerItems.add(new CurrentDayModel("Spaghetti", "small", ""));
        dinnerItems.add(new CurrentDayModel("Spaghetti", "small", ""));
        dinnerAdapter = new MealListAdapter(getApplicationContext(), dinnerItems);
        dinnerListView.setAdapter(dinnerAdapter);

        // exercise listView
        exerciseListInit();


        // on click listeners
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
                ""));
        exerciseItems.add(new CurrentDayModel("Cross Country Run", "120 minutes",
                ""));


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