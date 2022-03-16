package com.example.healthandfitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.healthandfitnessapp.Controllers.DisplayResultsAdapter;
import com.example.healthandfitnessapp.Models.RecipeModel;
import com.example.healthandfitnessapp.Models.SearchResultModel;
import com.example.healthandfitnessapp.API.EdamamApiService;
import com.example.healthandfitnessapp.Util.GridSpacingItemDecoration;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button findRecipesBtn, getArrayBtn, getFruitsBtn, findGroceryStoresBtn;
    private SearchView searchView;
    private RecyclerView displayRecycleView;

    private List<SearchResultModel> searchResults;
    private DisplayResultsAdapter resultsAdapter;
    final int spanCount = 2; // 2 columns
    final int spacing = 25; // 25px for padding

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_bar);
        findRecipesBtn = findViewById(R.id.get_recipe_btn);
        getArrayBtn = findViewById(R.id.search_btn);
        getFruitsBtn = findViewById(R.id.get_fruits_btn);
        findGroceryStoresBtn = findViewById(R.id.get_stores_btn);
        displayRecycleView = findViewById(R.id.display_results_recyclerView);

        // api provider
        final EdamamApiService apiService = new EdamamApiService(MainActivity.this);

        findGroceryStoresBtn.setOnClickListener(view -> {startActivity(new Intent(MainActivity.this, GroceryStoresActivity.class));});

        getFruitsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "searching fruits...", Toast.LENGTH_SHORT).show();
            }
        });

        findRecipesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get user search query
                String userQuery = searchView.getQuery().toString();

                // get query object
                apiService.getQueryObject(userQuery, new EdamamApiService.ApiCallback() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        getArrayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get user search query
                String userQuery = searchView.getQuery().toString();

                // get query object
                apiService.getRecipeInfo(userQuery, new EdamamApiService.GetRecipeCallback() {
                    @Override
                    public void onResponse(ArrayList<RecipeModel> recipe) {
                        Log.i("Edamam Recipe found: ", recipe.get(0).toString());
                        Toast.makeText(MainActivity.this, recipe.get(0).toString(), Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        searchResults = new ArrayList<>();
        searchResults.add(new SearchResultModel("google.com", "Greek Salad", "10 ingredients", "20 min"));
        searchResults.add(new SearchResultModel("google.com", "Greek Salad", "8 ingredients", "30 min"));
        searchResults.add(new SearchResultModel("google.com", "Greek Salad", "10 ingredients", "20 min"));
        searchResults.add(new SearchResultModel("google.com", "Greek Salad", "7 ingredients", "40 min"));
        searchResults.add(new SearchResultModel("google.com", "Greek Salad", "10 ingredients", "10 min"));
        searchResults.add(new SearchResultModel("google.com", "Greek Salad", "15 ingredients", "60 min"));
        searchResults.add(new SearchResultModel("google.com", "Greek Salad", "25 ingredients", "210 min"));
        searchResults.add(new SearchResultModel("google.com", "Greek Salad", "3 ingredients", "5 min"));

        resultsAdapter = new DisplayResultsAdapter(MainActivity.this, searchResults);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        displayRecycleView.setLayoutManager(gridLayoutManager);

        //AutoFitGridLayoutManager autoFitGridLayoutManager = new AutoFitGridLayoutManager(MainActivity.this, 500);
        //displayRecycleView.setLayoutManager(autoFitGridLayoutManager);

        displayRecycleView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));
        displayRecycleView.setAdapter(resultsAdapter);



    }

}