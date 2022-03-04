package com.example.healthandfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button findGroceryStoresBtn, findRecipesBtn;
    private EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findGroceryStoresBtn = findViewById(R.id.get_store_btn);
        findRecipesBtn = findViewById(R.id.get_recipe_btn);

        findGroceryStoresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Find Grocery Stores", Toast.LENGTH_SHORT).show();
            }
        });

        findRecipesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Find Recipes", Toast.LENGTH_SHORT).show();
            }
        });

    }
}