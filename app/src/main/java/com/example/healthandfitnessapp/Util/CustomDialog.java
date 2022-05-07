package com.example.healthandfitnessapp.Util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.healthandfitnessapp.CurrentDayActivity;
import com.example.healthandfitnessapp.Models.CurrentDayModel;
import com.example.healthandfitnessapp.R;

import java.util.ArrayList;

public class CustomDialog extends Dialog implements View.OnClickListener {
    Context mContext;
    public Button yes, no;

    private String dialogTitle;
    private TextView dialogTitleTxt, calorieTxt;
    private TextView titleTxt, subTxt;
    private String inputTitle, inputSub, calorieString;
    private EditText dialog_titleValue, dialog_subValue, calorieValue;
    //private String inputOneValue, inputTwoValue;
    private ArrayList<CurrentDayModel> list;

    public CustomDialog(@NonNull Context context, ArrayList<CurrentDayModel> list, String dialogTitle,
                        String inputTitle, String inputSub, String calorieTxt) {
        super(context);
        this.mContext = context;
        this.dialogTitle = dialogTitle;
        this.inputTitle = inputTitle;
        this.inputSub = inputSub;
        this.calorieString = calorieTxt;
        this.list = list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = findViewById(R.id.btn_yes);
        no = findViewById(R.id.btn_no);
        dialogTitleTxt = findViewById(R.id.txt_title);
        titleTxt = findViewById(R.id.titleTxt);
        dialog_titleValue = findViewById(R.id.dialog_titleValue);
        subTxt = findViewById(R.id.subTxt);
        dialog_subValue = findViewById(R.id.dialog_subValue);
        calorieTxt = findViewById(R.id.calorieTxt);
        calorieValue = findViewById(R.id.dialog_calorieValue);

        dialogTitleTxt.setText(dialogTitle);
        titleTxt.setText(inputTitle);
        subTxt.setText(inputSub);
        calorieTxt.setText(calorieString);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_yes) {
            String meal = dialog_titleValue.getText().toString().trim();
            String qty = dialog_subValue.getText().toString().trim();
            int calories = Integer.parseInt(calorieValue.getText().toString());
            if (!meal.matches("") || !qty.matches("")) {

                CurrentDayModel newItem;

                if (dialogTitle.matches("Add Breakfast Meal")) {
                    // meal is breakfast
                    newItem = new CurrentDayModel(meal,
                            qty, calories, "https://rkmsite.s3.us-east-2.amazonaws.com/assets/breakfast.jpg");
                    // save newItem to db
                    CurrentDayActivity.saveBreakfastMealToDb(newItem);

                } else if (dialogTitle.matches("Add Lunch Meal")) {
                    // meal is lunch
                    newItem = new CurrentDayModel(meal,
                            qty, calories, "https://rkmsite.s3.us-east-2.amazonaws.com/assets/lunch.png");
                    // save newItem to db
                    CurrentDayActivity.saveLunchMealToDb(newItem);

                } else if (dialogTitle.matches("Add Dinner Meal")) {
                    // meal is dinner
                    newItem = new CurrentDayModel(meal,
                            qty, calories, "https://rkmsite.s3.us-east-2.amazonaws.com/assets/dinner.png");
                    // save newItem to db
                    CurrentDayActivity.saveDinnerMealToDb(newItem);
                } else {
                    // its exercise item
                    newItem = new CurrentDayModel(meal, qty,
                            calories,"");
                    // save newItem to db
                    CurrentDayActivity.saveExerciseItem(newItem);
                }

                list.add(newItem);
            }

            dismiss();

        } if (view.getId() == R.id.btn_no) {
            dismiss();
        }
    }

    public ArrayList<CurrentDayModel> newDialogList() {
        return list;
    }

}

