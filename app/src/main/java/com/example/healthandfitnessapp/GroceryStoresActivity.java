package com.example.healthandfitnessapp;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
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


import com.example.healthandfitnessapp.Models.StoreModel;

import android.location.LocationListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class GroceryStoresActivity extends AppCompatActivity implements LocationListener {

    private List<StoreModel> storeModels;

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
        textView = findViewById(R.id.show_user_loc);
        recyclerView = findViewById(R.id.nearby_stores_recycleView);


        // ask for user to use their location
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(GroceryStoresActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }


        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String s = editText.getText().toString().trim();
                //String[] split = s.split("\\s+");

                getLocation();

                //Toast.makeText(getApplicationContext(), "Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude, Toast.LENGTH_LONG).show();


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
        Toast.makeText(this, "Lat: "+location.getLatitude()+"  Longi: "+location.getLongitude(), Toast.LENGTH_LONG).show();
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

            textView.setText(address + "\nCity: " + city+ "\nState: " + state);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}