package com.example.healthandfitnessapp.Models;

import androidx.annotation.NonNull;

public class SearchResultModel {
    private String imageUrl;
    private String mainText;
    private String infoText;
    private String extraText;

    public SearchResultModel(String imageUrl, String mainText, String infoText, String extraText) {
        this.imageUrl = imageUrl;
        this.mainText = mainText;
        this.infoText = infoText;
        this.extraText = extraText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public String getExtraText() {
        return extraText;
    }

    public void setExtraText(String extraText) {
        this.extraText = extraText;
    }

    @NonNull
    @Override
    public String toString() {
        return "SearchResultModel{" +
                "imageUrl='" + imageUrl + '\'' +
                ", mainText='" + mainText + '\'' +
                ", infoText='" + infoText + '\'' +
                ", extraText='" + extraText + '\'' +
                '}';
    }
}
