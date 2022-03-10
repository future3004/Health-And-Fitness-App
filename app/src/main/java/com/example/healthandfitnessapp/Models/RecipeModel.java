package com.example.healthandfitnessapp.Models;

import org.json.JSONArray;

import java.util.ArrayList;

public class RecipeModel {
    private float calories;
    private JSONArray allergyContain;
    private JSONArray cuisineType;
    private JSONArray dietLabels;
    private JSONArray dishType;
    private JSONArray healthLabels;
    private String imageUrl;
    private JSONArray ingredientLines;
    private String label;
    private JSONArray mealType;
    private String shareUrl;
    private int totalTime;
    private float recipeCal;
    private String viewOnWebUrl;

    public RecipeModel(float calories, JSONArray allergyContain, JSONArray cuisineType, JSONArray dietLabels,
                       JSONArray dishType, JSONArray healthLabels, String imageUrl, JSONArray ingredientLines,
                       String label, JSONArray mealType, String shareUrl, int totalTime, float recipeCal, String viewOnWebUrl) {
        this.calories = calories;
        this.allergyContain = allergyContain;
        this.cuisineType = cuisineType;
        this.dietLabels = dietLabels;
        this.dishType = dishType;
        this.healthLabels = healthLabels;
        this.imageUrl = imageUrl;
        this.ingredientLines = ingredientLines;
        this.label = label;
        this.mealType = mealType;
        this.shareUrl = shareUrl;
        this.totalTime = totalTime;
        this.recipeCal = recipeCal;
        this.viewOnWebUrl = viewOnWebUrl;
    }

    public RecipeModel() {
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public JSONArray getAllergyContain() {
        return allergyContain;
    }

    public void setAllergyContain(JSONArray allergyContain) {
        this.allergyContain = allergyContain;
    }

    public JSONArray getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(JSONArray cuisineType) {
        this.cuisineType = cuisineType;
    }

    public JSONArray getDietLabels() {
        return dietLabels;
    }

    public void setDietLabels(JSONArray dietLabels) {
        this.dietLabels = dietLabels;
    }

    public JSONArray getDishType() {
        return dishType;
    }

    public void setDishType(JSONArray dishType) {
        this.dishType = dishType;
    }

    public JSONArray getHealthLabels() {
        return healthLabels;
    }

    public void setHealthLabels(JSONArray healthLabels) {
        this.healthLabels = healthLabels;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public JSONArray getIngredientLines() {
        return ingredientLines;
    }

    public void setIngredientLines(JSONArray ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public JSONArray getMealType() {
        return mealType;
    }

    public void setMealType(JSONArray mealType) {
        this.mealType = mealType;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public float getRecipeCal() {
        return recipeCal;
    }

    public void setRecipeCal(float recipeCal) {
        this.recipeCal = recipeCal;
    }

    public String getViewOnWebUrl() {
        return viewOnWebUrl;
    }

    public void setViewOnWebUrl(String viewOnWebUrl) {
        this.viewOnWebUrl = viewOnWebUrl;
    }

    @Override
    public String toString() {
        return "RecipeModel{" +
                "calories=" + calories +
                ", allergyContain=" + allergyContain +
                ", cuisineType=" + cuisineType +
                ", dietLabels=" + dietLabels +
                ", dishType=" + dishType +
                ", healthLabels=" + healthLabels +
                ", imageUrl='" + imageUrl + '\'' +
                ", ingredientLines=" + ingredientLines +
                ", label='" + label + '\'' +
                ", mealType=" + mealType +
                ", shareUrl='" + shareUrl + '\'' +
                ", totalTime=" + totalTime +
                ", recipeCal=" + recipeCal +
                ", viewOnWebUrl='" + viewOnWebUrl + '\'' +
                '}';
    }
}
