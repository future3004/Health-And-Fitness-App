package com.example.healthandfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SearchResultActivity extends AppCompatActivity {
    private FloatingActionButton fab;
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
        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, R.layout.cooking_list_item, R.id.instruction_label, instructions);
        listView.setAdapter(adapter);



        fab.setOnClickListener(view -> Toast.makeText(SearchResultActivity.this, "Show more recipe info", Toast.LENGTH_SHORT).show());
    }
}