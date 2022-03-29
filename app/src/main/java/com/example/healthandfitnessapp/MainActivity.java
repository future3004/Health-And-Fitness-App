package com.example.healthandfitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private Button searchBtn;
    private SearchView searchView;
    private RecyclerView displayRecycleView;

    private List<SearchResultModel> searchResults = null;
    private DisplayResultsAdapter resultsAdapter;

    final int spanCount = 2; // 2 columns
    final int spacing = 25; // 25px for padding

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_bar);
        searchBtn = findViewById(R.id.search_btn);
        displayRecycleView = findViewById(R.id.display_results_recyclerView);


        // api provider
        final EdamamApiService apiService = new EdamamApiService(MainActivity.this);
        searchResults = new ArrayList<>();

        // populate the ui here
        resultsAdapter = new DisplayResultsAdapter(MainActivity.this, searchResults);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        displayRecycleView.setLayoutManager(gridLayoutManager);

        //AutoFitGridLayoutManager autoFitGridLayoutManager = new AutoFitGridLayoutManager(MainActivity.this, 500);
        //displayRecycleView.setLayoutManager(autoFitGridLayoutManager);

        displayRecycleView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));
        displayRecycleView.setAdapter(resultsAdapter);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get user search query
                String userQuery = searchView.getQuery().toString();

                // check if we have a query
                if (userQuery.matches("")) {
                    Toast.makeText(getApplicationContext(), "Nothing to search..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // get query object
                apiService.getRecipeInfo(userQuery, new EdamamApiService.GetRecipeCallback() {
                    @Override
                    public void onResponse(List<RecipeModel> recipe) {
                        Log.i("Edamam Recipe found: ", recipe.toString());
                        // Toast.makeText(MainActivity.this, recipe.get(0).toString(), Toast.LENGTH_LONG).show();
                        // set this list to the adapter
                        resultsAdapter.setRecipeModelList(recipe);

                        List<SearchResultModel> searchResults = new ArrayList<>();

                        for (int i=0; i < recipe.size(); i++) {

                            int totalIngredients = recipe.get(i).getIngredientLines().length();
                            String infoText = totalIngredients + " ingredients";

                            int totalTime = recipe.get(i).getTotalTime();
                            String extraText = totalTime + " min";

                            searchResults.add(new SearchResultModel(recipe.get(i).getImageUrl(),
                                    recipe.get(i).getLabel(), infoText, extraText));
                        }

                        // update the adapter with new stores
                        resultsAdapter.setItems(searchResults);
                        resultsAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // connect the artists menu in menu res
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.healthy_store_menu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_groceryStores:
                // go to grocery store page
                startActivity(new Intent(MainActivity.this, GroceryStoresActivity.class));
                return true;
            case R.id.action_gymsAndParks:
                // go to gym and park page
                startActivity(new Intent(MainActivity.this, GymParkActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}