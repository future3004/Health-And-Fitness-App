package com.example.healthandfitnessapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.os.Bundle;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.healthandfitnessapp.API.GooglePlacesAPI;
import com.example.healthandfitnessapp.Controllers.GroceryStoresRecycleViewAdapter;
import com.example.healthandfitnessapp.Models.StoreModel;

import android.location.LocationListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MacrosHelpActivity extends AppCompatActivity {

    Button macrosSearchBtn;
    EditText macrosEditText;
    ListView macrosListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macros_help);

        macrosSearchBtn = findViewById(R.id.macros_search_button);
        macrosEditText = findViewById(R.id.editTextMacros);
        macrosListView = findViewById(R.id.macros_recycleView);

        // set toolbar
        ActionBar actionBar = getSupportActionBar();
        try {
            actionBar.setTitle("Macronutrients");
            actionBar.setDisplayHomeAsUpEnabled(true);
        } catch (Exception e){ e.printStackTrace(); }

        macrosSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MacrosHelpActivity.this);
                String url ="https://api.calorieninjas.com/v1/nutrition?query=" + macrosEditText.getText();

                //NOT SURE IF JsonObjectRequest or JsonArrayRequest
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String gramsSugar = "";
                        try {
                            JSONObject foodItem = response.getJSONObject(0);
                            gramsSugar = foodItem.getString("sugar_g");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(MacrosHelpActivity.this, "Grams of Sugar = " + gramsSugar, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MacrosHelpActivity.this, "Something wrong.", Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(request);
//                // Request a string response from the provided URL.
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                Toast.makeText(MacrosHelpActivity.this, response, Toast.LENGTH_SHORT).show();
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MacrosHelpActivity.this, "Error occured", Toast.LENGTH_SHORT).show();
//                    }
//                });

                // Add the request to the RequestQueue.


                //Toast.makeText(MacrosHelpActivity.this, "Displaying Macronutrients for " + MacrosEditText.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}