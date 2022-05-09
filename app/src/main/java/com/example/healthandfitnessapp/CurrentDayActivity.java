package com.example.healthandfitnessapp;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.healthandfitnessapp.Controllers.CurrentListAdapter;
import com.example.healthandfitnessapp.Controllers.ExerciseListAdapter;
import com.example.healthandfitnessapp.Controllers.MealListAdapter;
import com.example.healthandfitnessapp.Models.CurrentDayModel;
import com.example.healthandfitnessapp.Util.CustomDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    int breakFastCalories = 0;
    int lunchCalories = 0;
    int dinnerCalories = 0;
    int exerciseCaloriesBurnt = 0;
    int calorieGoal = 2500;

    private FirebaseAuth auth = null;
    private static FirebaseUser user = null;
    private static FirebaseDatabase firebaseDatabase = null;

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

        // initialize firebase
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();


        // set today date
        //"Today: Tues, Dec 31"
        //E.dd.LLL.yyyy HH:mm:ss aaa z
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, LLL dd HH:mm:ss aaa z");
        String dateTime = simpleDateFormat.format(calendar.getTime()).toString();
        dateTextView.setText("Today: " + dateTime);


        // initialize listViews
        breakfastListInit();
        lunchListInit();
        dinnerListInit();
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

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }


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

    private void breakfastListInit() {
        // breakfast listView
        breakfastItems = new ArrayList<>();
/*        breakfastItems.add(new CurrentDayModel("Coffee, w/ Skim milk", "8 fluid ounces",
                100,
                "https://rkmsite.s3.us-east-2.amazonaws.com/assets/breakfast.jpg"));*/

        // read data from firebase
        DatabaseReference mDatabase = firebaseDatabase.getReference();
        if (user != null ) {
            String userId = user.getUid();
            //String key = mDatabase.child("users").child(userId).child("breakfast").getKey();
            try {
                mDatabase.child("users").child(userId).child("breakfast")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if (task.isSuccessful()) {
                                    Log.d("CurrentDay_firebase", String.valueOf(task.getResult().getValue()));

                                    for (DataSnapshot dataSnapshot: task.getResult().getChildren()) {
                                        CurrentDayModel meal = dataSnapshot.getValue(CurrentDayModel.class);
                                        String title = meal.getTitle();
                                        String extraInfo = meal.getExtraInfo();
                                        int caloriesPlusMinus = meal.getCaloriesPlusMinus();
                                        String imageUrl = meal.getImageUrl();

                                        breakFastCalories = breakFastCalories + caloriesPlusMinus;

                                        breakfastItems.add(new CurrentDayModel(title, extraInfo, caloriesPlusMinus, imageUrl));
                                    }

                                } else {
                                    Log.e("firebase", "Error getting data", task.getException());
                                }
                            }
                        });
            } catch (Exception e) {e.printStackTrace();}

        }

        // set up adapter
        breakfastAdapter = new MealListAdapter(getApplicationContext(), breakfastItems);
        breakfastListView.setAdapter(breakfastAdapter);

        breakFastTxt.setText("Breakfast: " + breakFastCalories);

        breakfastListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),"Remove at: " + i,Toast.LENGTH_SHORT).show();
                removeItem(breakfastItems, breakfastAdapter, i);

                return false;
            }
        });

    }

    private void lunchListInit() {
        // lunch listView
        lunchItems = new ArrayList<>();

        // read data from firebase
        DatabaseReference mDatabase = firebaseDatabase.getReference();
        if (user != null ) {
            String userId = user.getUid();
            //String key = mDatabase.child("users").child(userId).child("breakfast").getKey();
            try {
                mDatabase.child("users").child(userId).child("lunch")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if (task.isSuccessful()) {
                                    Log.d("CurrentDay_firebase", String.valueOf(task.getResult().getValue()));

                                    for (DataSnapshot dataSnapshot: task.getResult().getChildren()) {
                                        CurrentDayModel meal = dataSnapshot.getValue(CurrentDayModel.class);
                                        String title = meal.getTitle();
                                        String extraInfo = meal.getExtraInfo();
                                        int caloriesPlusMinus = meal.getCaloriesPlusMinus();
                                        String imageUrl = meal.getImageUrl();

                                        lunchItems.add(new CurrentDayModel(title, extraInfo, caloriesPlusMinus, imageUrl));
                                    }

                                } else {
                                    Log.e("firebase", "Error getting data", task.getException());
                                }
                            }
                        });
            } catch (Exception e) {e.printStackTrace();}

        }

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

    }

    private void dinnerListInit() {
        // dinner listView
        dinnerItems = new ArrayList<>();

        // read data from firebase
        DatabaseReference mDatabase = firebaseDatabase.getReference();
        if (user != null ) {
            String userId = user.getUid();
            //String key = mDatabase.child("users").child(userId).child("breakfast").getKey();
            try {
                mDatabase.child("users").child(userId).child("dinner")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if (task.isSuccessful()) {
                                    Log.d("CurrentDay_firebase", String.valueOf(task.getResult().getValue()));

                                    for (DataSnapshot dataSnapshot: task.getResult().getChildren()) {
                                        CurrentDayModel meal = dataSnapshot.getValue(CurrentDayModel.class);
                                        String title = meal.getTitle();
                                        String extraInfo = meal.getExtraInfo();
                                        int caloriesPlusMinus = meal.getCaloriesPlusMinus();
                                        String imageUrl = meal.getImageUrl();

                                        dinnerItems.add(new CurrentDayModel(title, extraInfo, caloriesPlusMinus, imageUrl));
                                    }

                                } else {
                                    Log.e("firebase", "Error getting data", task.getException());
                                }
                            }
                        });
            } catch (Exception e) {e.printStackTrace();}

        }

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
    }

    private void exerciseListInit() {

        exerciseItems = new ArrayList<>();
/*        exerciseItems.add(new CurrentDayModel("Swimming", "25 minutes",
                250,""));
        exerciseItems.add(new CurrentDayModel("Cross Country Run", "120 minutes",
                300,""));*/


        //exerciseCaloriesTxt.setText(exerciseCaloriesBurnt);

        // read data from firebase
        DatabaseReference mDatabase = firebaseDatabase.getReference();
        if (user != null ) {
            String userId = user.getUid();
            //String key = mDatabase.child("users").child(userId).child("breakfast").getKey();
            try {
                mDatabase.child("users").child(userId).child("exercise")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if (task.isSuccessful()) {
                                    Log.d("CurrentDay_firebase", String.valueOf(task.getResult().getValue()));

                                    for (DataSnapshot dataSnapshot: task.getResult().getChildren()) {
                                        CurrentDayModel meal = dataSnapshot.getValue(CurrentDayModel.class);
                                        String title = meal.getTitle();
                                        String extraInfo = meal.getExtraInfo();
                                        int caloriesPlusMinus = meal.getCaloriesPlusMinus();
                                        String imageUrl = meal.getImageUrl();

                                        exerciseItems.add(new CurrentDayModel(title, extraInfo, caloriesPlusMinus, imageUrl));
                                    }

                                } else {
                                    Log.e("firebase", "Error getting data", task.getException());
                                }
                            }
                        });
            } catch (Exception e) {e.printStackTrace();}

        }


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

        // calculate calories
        for (CurrentDayModel i: exerciseItems) {
            exerciseCaloriesBurnt += i.getCaloriesPlusMinus();
        }
    }

    public static void saveBreakfastMealToDb(CurrentDayModel newMeal) {
        DatabaseReference mDatabase = firebaseDatabase.getReference();

        if (user != null) {
            String userId = user.getUid();

            //String key = mDatabase.child("users").child(userId).child("breakfast").push().getKey();
            mDatabase.child("users").child(userId).child("breakfast").push().setValue(newMeal);

            //mDatabase.child("users").child(userId).child("breakfast").push().setValue(newMeal);
            //mDatabase.child("users").child(userId).child("breakfast").setValue(newMeal);
        }
    }

    public static void saveLunchMealToDb(CurrentDayModel newMeal) {
        DatabaseReference mDatabase = firebaseDatabase.getReference();

        if (user != null) {
            String userId = user.getUid();

            //String key = mDatabase.child("users").child(userId).child("lunch").push().getKey();
            mDatabase.child("users").child(userId).child("lunch").push().setValue(newMeal);
        }
    }

    public static void saveDinnerMealToDb(CurrentDayModel newMeal) {
        DatabaseReference mDatabase = firebaseDatabase.getReference();

        if (user != null) {
            String userId = user.getUid();

            //String key = mDatabase.child("users").child(userId).child("dinner").push().getKey();
            mDatabase.child("users").child(userId).child("dinner").push().setValue(newMeal);
        }
    }

    public static void saveExerciseItem(CurrentDayModel newExercise) {
        DatabaseReference mDatabase = firebaseDatabase.getReference();

        if (user != null) {
            String userId = user.getUid();

            //String key = mDatabase.child("users").child(userId).child("exercise").push().getKey();
            mDatabase.child("users").child(userId).child("exercise").push().setValue(newExercise);
        }
    }

    private void getBreakfastMeal() {
        DatabaseReference mDatabase = firebaseDatabase.getReference();
        if (user != null ) {
            String userId = user.getUid();
            mDatabase.child("users").child(userId).child("breakfast").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.d("CurrentDay_firebase", String.valueOf(task.getResult().getValue()));

                    } else {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                }
            });
        }
    }


}