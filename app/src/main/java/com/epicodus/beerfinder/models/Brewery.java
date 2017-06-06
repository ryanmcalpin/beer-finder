package com.epicodus.beerfinder.models;

import java.util.ArrayList;

public class Brewery {
    private String mId;
    private String mName;
    private String mDescription;
    private String mUrl;
    private ArrayList<String> mImageUrls;

    public Brewery(String id, String name, String description, String url, ArrayList<String> imageUrls) {
        this.mId = id;
        this.mName = name;
        this.mDescription = description;
        this.mUrl = url;
        this.mImageUrls = imageUrls;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUrl() {
        return mUrl;
    }

    public ArrayList<String> getImageUrls() {
        return mImageUrls;
    }
}
