package com.example.healthandfitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private AppCompatImageView imageView;
    private TextView labelTxt, totalIngredientsTxt, timeTxt, calorieTxt;
    private ListView listView;


    String[] instructions = {"Add 1/2 cup of water", "Add 20 teaspoons of butter",
            "Add 1/2 cup of water", "Add 20 teaspoons of butter",
            "Add 1/2 cup of water", "Add 20 teaspoons of butter",
            "Add 1/2 cup of water", "Add 20 teaspoons of butter"};
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        fab = findViewById(R.id.my_fab);
        imageView = findViewById(R.id.thumb_image);
        labelTxt = findViewById(R.id.label_textView);
        totalIngredientsTxt = findViewById(R.id.ingredients_total_textView);
        timeTxt = findViewById(R.id.time_textView);
        calorieTxt = findViewById(R.id.cal_textView);
        listView = findViewById(R.id.listView);

        // set the ui
        // get the passed in data from the intent
        Intent intent = getIntent();
        String thumbUrl = intent.getStringExtra("thumbNail");
        String label = intent.getStringExtra("label");
        String ingredientsCount = intent.getIntExtra("totalIngredients", 0) + " ingredients";
        String totalTime = intent.getIntExtra("time", 0) + " min";
        String calories = intent.getFloatExtra("calories", 0) + " Cal";
        String webUrl = intent.getStringExtra("moreInfo");
        ArrayList<String> cookingInstructions = intent.getStringArrayListExtra("instructions");

        // load the main image
        try {
            Glide.with(this)
                    .load(thumbUrl)
                    .into(imageView);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // set the textViews
        labelTxt.setText(label);
        totalIngredientsTxt.setText(ingredientsCount);
        timeTxt.setText(totalTime);
        calorieTxt.setText(calories);


        adapter = new ArrayAdapter<>(this, R.layout.cooking_list_item, R.id.instruction_label, cookingInstructions);
        listView.setAdapter(adapter);

        fab.setOnClickListener(view -> {
            //Toast.makeText(SearchResultActivity.this, "visit: " + webUrl, Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(SearchResultActivity.this, WebViewActivity.class);
            intent1.putExtra("ToUrl", webUrl);
            startActivity(intent1);
        });
    }
}