package com.example.healthandfitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.healthandfitnessapp.Controllers.VolleyQueueSingleton;
import com.example.healthandfitnessapp.Models.RecipeModel;
import com.example.healthandfitnessapp.Util.EdamamApiService;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button findRecipesBtn, getArrayBtn;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_bar);
        findRecipesBtn = findViewById(R.id.get_recipe_btn);
        getArrayBtn = findViewById(R.id.get_array_btn);

        // api provider
        final EdamamApiService apiService = new EdamamApiService(MainActivity.this);

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

    }

    public static void getResults(String queryUrl) {
        //RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        // Instantiate the cache
        //Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        //Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        //requestQueue = new RequestQueue(cache, network);

        // Start the queue
        // requestQueue.start();
    }
}