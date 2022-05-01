package com.example.healthandfitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthandfitnessapp.API.GooglePlacesAPI;
import com.example.healthandfitnessapp.Controllers.GroceryStoresRecycleViewAdapter;
import com.example.healthandfitnessapp.Models.StoreModel;

import java.util.List;

public class GymParkActivity extends AppCompatActivity {
    private EditText searchEditText;
    private Button findGymBtn, findParkBtn;
    private TextView infoTextView;
    private RecyclerView recyclerView;

    private List<StoreModel> gymsList = null;
    private List<StoreModel> parksList = null;
    private GroceryStoresRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_park);
        searchEditText = findViewById(R.id.gym_editText);
        findGymBtn = findViewById(R.id.find_gym_button);
        findParkBtn = findViewById(R.id.find_park_button);
        infoTextView = findViewById(R.id.text_info);
        recyclerView = findViewById(R.id.nearby_services_recycleView);

        // set toolbar
        ActionBar actionBar = getSupportActionBar();
        try {
            actionBar.setTitle("Gyms And Parks");
            actionBar.setDisplayHomeAsUpEnabled(true);
        } catch (Exception e){ e.printStackTrace(); }


        // places api provider
        final GooglePlacesAPI googlePlacesAPI = new GooglePlacesAPI(GymParkActivity.this);

        adapter = new GroceryStoresRecycleViewAdapter(GymParkActivity.this, gymsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        // adding a divider/separator between each store
        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(this, R.drawable.horizontal_divider);
        assert horizontalDivider != null;
        horizontalDecoration.setDrawable(horizontalDivider);
        recyclerView.addItemDecoration(horizontalDecoration);

        // on page load
        googlePlacesAPI.nearbyGymSearch(new GooglePlacesAPI.FetchGymsParkCallback() {
            @Override
            public void onResponse(List<StoreModel> list) {
                gymsList = list;

                // update the adapter with new stores
                adapter.setItems(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getApplicationContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
            }
        });


        // onclick on find park button
        findParkBtn.setOnClickListener(view -> {
            infoTextView.setText("Parks : ");

            String city = searchEditText.getText().toString().trim();

            googlePlacesAPI.fetchForGymsAndParks("park", city, new GooglePlacesAPI.FetchGymsParkCallback() {
                @Override
                public void onResponse(List<StoreModel> list) {
                    parksList = list;

                    // update the adapter with new stores
                    adapter.setItems(list);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onError(String message) {
                    Toast.makeText(getApplicationContext(), "Error: " + message, Toast.LENGTH_SHORT).show();

                }
            });
        });

        // onclick on find gym button
        findGymBtn.setOnClickListener(view -> {
            infoTextView.setText("Your Gyms options: ");


            String city = searchEditText.getText().toString().trim();

            googlePlacesAPI.fetchForGymsAndParks("gym", city, new GooglePlacesAPI.FetchGymsParkCallback() {
                @Override
                public void onResponse(List<StoreModel> list) {
                    gymsList = list;

                    // update the adapter with new stores
                    adapter.setItems(list);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onError(String message) {
                    Toast.makeText(getApplicationContext(), "Error: " + message, Toast.LENGTH_SHORT).show();

                }
            });
        });

    }
}