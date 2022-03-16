package com.example.healthandfitnessapp.Models;

import androidx.annotation.NonNull;

public class StoreModel {

    private String photoReference;
    private String storeName;
    private String storeRating;
    private String storeAddress;
    private Boolean storeOpenClose;

    public StoreModel(String photoReference, String storeName, String storeRating, String storeAddress, Boolean storeOpenClose) {
        this.photoReference = photoReference;
        this.storeName = storeName;
        this.storeRating = storeRating;
        this.storeAddress = storeAddress;
        this.storeOpenClose = storeOpenClose;
    }

    public StoreModel(){}

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreRating() {
        return storeRating;
    }

    public void setStoreRating(String storeRating) {
        this.storeRating = storeRating;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public Boolean getStoreOpenClose() {
        return storeOpenClose;
    }

    public void setStoreOpenClose(Boolean storeOpenClose) {
        this.storeOpenClose = storeOpenClose;
    }

    @NonNull
    @Override
    public String toString() {
        return "StoreModel{" +
                "photoReference='" + photoReference + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeRating='" + storeRating + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", storeOpenClose='" + storeOpenClose + '\'' +
                '}';
    }
}
