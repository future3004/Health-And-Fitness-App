package com.example.healthandfitnessapp.Models;

public class CurrentDayList {
    private String imageUrl;
    private String title;
    private String extraInfo;

    public CurrentDayList(String imageUrl, String title, String extraInfo) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.extraInfo = extraInfo;
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
}
