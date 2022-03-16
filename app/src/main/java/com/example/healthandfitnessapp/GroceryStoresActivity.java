package com.example.healthandfitnessapp;

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
import android.widget.TextView;
import android.widget.Toast;


import com.example.healthandfitnessapp.API.GooglePlacesAPI;
import com.example.healthandfitnessapp.Controllers.GroceryStoresRecycleViewAdapter;
import com.example.healthandfitnessapp.Models.StoreModel;

import android.location.LocationListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class GroceryStoresActivity extends AppCompatActivity implements LocationListener {

    private List<StoreModel> groceryStoresList = null;
    private GroceryStoresRecycleViewAdapter adapter;

    private String latitude, longitude;

    private EditText editText;
    private Button searchBtn;
    private TextView textView;
    private RecyclerView recyclerView;

    private LocationManager locationManager;

    private String address, city, state, country, postalCode, knownName;


    // https://developers.google.com/maps/documentation/places/web-service/search-text#maps_http_places_textsearch-java

    // get grocery store url
    // https://maps.googleapis.com/maps/api/place/textsearch/json?query=supermarket%20in%20Arlington&key=AIzaSyCq_l8CRNgCkyuSSkHMxBDv6f0x5AAHzik

    // get photo url
    // https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photo_reference=Aap_uEA7vb0DDYVJWEaX3O-AtYp77AaswQKSGtDaimt3gt7QCNpdjp1BkdM6acJ96xTec3tsV_ZJNL_JP-lqsVxydG3nh739RE_hepOOL05tfJh2_ranjMadb3VoBYFvF0ma6S24qZ6QJUuV6sSRrhCskSBP5C1myCzsebztMfGvm7ij3gZT&key=AIzaSyCq_l8CRNgCkyuSSkHMxBDv6f0x5AAHzik
    // https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photo_reference=Aap_uEBCVTTIZ0bv3jTNtcllYaEmkCdFPVEivuEV6mbHihUv3N_UMSb6C7c9_pis679kb66S_gNmg4A0QxNiaybL3bgLwTqG9wmWyFmjKm-VJiiGi-22BBV6Wtch9dBfdAyKtzvu7FRKbFxrkkaSitVR1ynvKC-wjh-6O_7uvnjPlNRVee2A&key=AIzaSyCq_l8CRNgCkyuSSkHMxBDv6f0x5AAHzik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_stores);
        editText = findViewById(R.id.editText);
        searchBtn = findViewById(R.id.search_button);
        textView = findViewById(R.id.show_user_info);
        recyclerView = findViewById(R.id.nearby_stores_recycleView);

        // places api provider
        final GooglePlacesAPI googlePlacesAPI = new GooglePlacesAPI(GroceryStoresActivity.this);

        // ask for user to use their location
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(GroceryStoresActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }


/*        groceryStoresList = new ArrayList<>();
        groceryStoresList.add(new StoreModel("google", "Walmart", "4.4",
                "8550 Tom Landry Fwy", "Open"));
        groceryStoresList.add(new StoreModel("google", "ALDI", "4.9",
                "8550 Tom Landry Fwy", "Open"));
        groceryStoresList.add(new StoreModel("google", "Target Grocery", "4.2",
                "8550 Tom Landry Fwy", "Closed"));
        groceryStoresList.add(new StoreModel("google", "Costco", "5",
                "8550 Tom Landry Fwy", "Open"));
        groceryStoresList.add(new StoreModel("google", "Wincos", "4.8",
                "8550 Tom Landry Fwy", "Closed"));
                */
        // initial ui load
        //Toast.makeText(getApplicationContext(), "Grocery stores in " + userCity, Toast.LENGTH_SHORT).show();
        String city = "Arlington";
        googlePlacesAPI.fetchGroceryStores(city.toLowerCase(), new GooglePlacesAPI.FetchGroceryStoreCallback() {
            @Override
            public void onResponse(List<StoreModel> storeModelList) {

                groceryStoresList = storeModelList;

            }

            @Override
            public void onError(String message) {
                Toast.makeText(getApplicationContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
            }
        });

        adapter = new GroceryStoresRecycleViewAdapter(GroceryStoresActivity.this, groceryStoresList);

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


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userCity = editText.getText().toString().trim();
                //String[] split = s.split("\\s+");

                // getting current user location
                getLocation();

                //Toast.makeText(getApplicationContext(), "Grocery stores in " + userCity, Toast.LENGTH_SHORT).show();
                googlePlacesAPI.fetchGroceryStores(userCity.toLowerCase(), new GooglePlacesAPI.FetchGroceryStoreCallback() {
                    @Override
                    public void onResponse(List<StoreModel> storeModelList) {

                        // update the adapter with new stores
                        adapter.setItems(storeModelList);
                        adapter.notifyDataSetChanged();
                        //recyclerView.notify();
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(getApplicationContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, GroceryStoresActivity.this);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        Log.i("User curr location: ", "Your Location: " + "\n" + "Latitude: " + location.getLatitude() + "\n" + "Longitude: " + location.getLongitude() );
        //Toast.makeText(this, "Lat: "+location.getLatitude()+"  Longi: "+location.getLongitude(), Toast.LENGTH_LONG).show();
        latitude = String.valueOf(location.getLatitude());
        longitude = String.valueOf(location.getLongitude());
        try {
            Geocoder geocoder = new Geocoder(GroceryStoresActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            address = addresses.get(0).getAddressLine(0);
            city = addresses.get(0).getLocality();
            state = addresses.get(0).getAdminArea();
            country = addresses.get(0).getCountryName();
            postalCode = addresses.get(0).getPostalCode();
            knownName = addresses.get(0).getFeatureName();

            //textView.setText("city: " + city+ "state: " + state);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // getting current user location
        getLocation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // getting current user location
        getLocation();
    }
}