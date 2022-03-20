package com.example.healthandfitnessapp.API;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.healthandfitnessapp.Controllers.VolleyQueueSingleton;
import com.example.healthandfitnessapp.Models.RecipeModel;
import com.example.healthandfitnessapp.Models.StoreModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GooglePlacesAPI {
    Context context;
    private static String API_KEY = "AIzaSyCq_l8CRNgCkyuSSkHMxBDv6f0x5AAHzik";
    private static String whatQuery = "supermarket";
    private static String cityQuery = "arlington";
    private static final String GOOGLE_PLACE_API_BASEURL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query="+ whatQuery + "%20in%20"
            + cityQuery + "&key="+ API_KEY;

    public GooglePlacesAPI(Context context) {
        this.context = context;
    }

    public interface FetchGroceryStoreCallback {
        void onResponse(List<StoreModel> storeModelList);

        void onError(String message);
    }

    public void fetchGroceryStores(String desiredCity, FetchGroceryStoreCallback callback) {
        String PLACE_API_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query="+ whatQuery + "%20in%20"
                + desiredCity + "&key="+ API_KEY;

        List<StoreModel> storesList = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                PLACE_API_URL,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    // get the array with recipe info
                    JSONArray jsonArray = response.getJSONArray("results");

                    //StoreModel storeModel = new StoreModel();

                    for(int i=0; i < jsonArray.length(); i++) {
                        JSONObject store = jsonArray.getJSONObject(i);

                        JSONArray photosArray = store.getJSONArray("photos");
                        storesList.add(new StoreModel(photosArray.getJSONObject(0).getString("photo_reference"),
                                store.getString("name"), store.getString("rating"),
                                store.getString("formatted_address"),
                                store.getJSONObject("opening_hours").getBoolean("open_now")));
                    }

/*                    JSONObject store = jsonArray.getJSONObject(0);

                    JSONArray photosArray = store.getJSONArray("photos");
                    storeModel.setPhotoReference(photosArray.getJSONObject(0).getString("photo_reference"));
                    storeModel.setStoreName(store.getString("name"));
                    storeModel.setStoreRating(store.getString("rating"));
                    storeModel.setStoreAddress(store.getString("formatted_address"));
                    storeModel.setStoreOpenClose(store.getJSONObject("opening_hours").getBoolean("open_now"));*/
                    Log.i("Places_API_test", storesList.get(0).toString());
                    //storesList.add(storeModel);



                    callback.onResponse(storesList);

                } catch (Exception e) {
                    callback.onError(e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                callback.onError(error.toString());
            }
        });

        // Add this request to RequestQueue
        VolleyQueueSingleton.getInstance(context).addToRequestQueue(request);
    }











    //String queryZ = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants%20in%20Sydney&key=AIzaSyCq_l8CRNgCkyuSSkHMxBDv6f0x5AAHzik"
    // https://maps.googleapis.com/maps/api/place/textsearch/json?query=supermarket%20in%20Arlington&key=AIzaSyCq_l8CRNgCkyuSSkHMxBDv6f0x5AAHzik

}
