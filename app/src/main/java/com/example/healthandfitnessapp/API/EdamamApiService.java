package com.example.healthandfitnessapp.API;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.healthandfitnessapp.Controllers.VolleyQueueSingleton;
import com.example.healthandfitnessapp.Models.RecipeModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EdamamApiService {

    Context context;
    public static final String EDAMAM_API = "https://api.edamam.com/search?&app_id=4a5d81a2&app_key=379308ab9da9a8ee47f63563d2774ac4&from=0&to=9&q=";
    int from = 0;
    int to = 9;

    public EdamamApiService(Context context) {
        this.context = context;
    }

    // interface callback for data return
    public interface GetRecipeCallback {
        void onResponse(List<RecipeModel> recipe);

        void onError(String message);
    }

    public void getRecipeInfo(String userQuery, GetRecipeCallback callback) {
        List<RecipeModel> recipesArray = new ArrayList<>();

        String searchURL = EDAMAM_API + userQuery;
        //Toast.makeText(HealthOptionsActivity.this, "Searching..." + userQuery, Toast.LENGTH_SHORT).show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, searchURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    String query = response.getString("q");
                    // get the array with recipe info
                    JSONArray hits_array = response.getJSONArray("hits");

                    // get the individual recipes and save to recipesArray list
                    for (int i=0; i < hits_array.length(); i++) {

                        RecipeModel recipeModel = new RecipeModel();
                        JSONObject currentRecipeObject = hits_array.getJSONObject(i);
                        JSONObject recipe = currentRecipeObject.getJSONObject("recipe");

                        // get individual recipe info
                        recipeModel.setCalories(recipe.getLong("calories"));
                        recipeModel.setAllergyContain(recipe.getJSONArray("cautions"));
                        recipeModel.setCuisineType(recipe.getJSONArray("cuisineType"));
                        recipeModel.setDietLabels(recipe.getJSONArray("dietLabels"));
                        recipeModel.setDishType(recipe.getJSONArray("dishType"));
                        recipeModel.setHealthLabels(recipe.getJSONArray("healthLabels"));
                        recipeModel.setImageUrl(recipe.getString("image"));
                        recipeModel.setIngredientLines(recipe.getJSONArray("ingredientLines"));
                        recipeModel.setLabel(recipe.getString("label"));
                        recipeModel.setMealType(recipe.getJSONArray("mealType"));
                        recipeModel.setShareUrl(recipe.getString("shareAs"));
                        recipeModel.setTotalTime(recipe.getInt("totalTime"));
                        recipeModel.setRecipeCal(recipe.getLong("totalWeight"));
                        recipeModel.setViewOnWebUrl(recipe.getString("url"));

                        recipesArray.add(recipeModel);
                    }

                    //recipesArray.add(recipeModel);

                    callback.onResponse(recipesArray);

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

        // Add a request (in this example, called stringRequest) to your RequestQueue.
        VolleyQueueSingleton.getInstance(context).addToRequestQueue(request);
    }
}
