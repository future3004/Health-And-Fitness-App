package com.example.healthandfitnessapp.Models;

public class CurrentDayModel {
    private String title;
    private String extraInfo;
    private int caloriesPlusMinus;
    private String imageUrl;

    public CurrentDayModel() {
        // Default constructor required for calls to DataSnapshot.getValue(CurrentDayModel.class)}
    }

    public CurrentDayModel(String title, String extraInfo,
                           int caloriesPlusMinus, String imageUrl) {
        this.title = title;
        this.extraInfo = extraInfo;
        this.caloriesPlusMinus = caloriesPlusMinus;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public int getCaloriesPlusMinus() {
        return caloriesPlusMinus;
    }

    public void setCaloriesPlusMinus(int caloriesPlusMinus) {
        this.caloriesPlusMinus = caloriesPlusMinus;
    }

    @Override
    public String toString() {
        return "CurrentDayModel{" +
                "title='" + title + '\'' +
                ", extraInfo='" + extraInfo + '\'' +
                ", caloriesPlusMinus=" + caloriesPlusMinus +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
