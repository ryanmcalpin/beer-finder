package com.epicodus.beerfinder.models;

/**
 * Created by rygn on 6/2/17.
 */

public class Beer {
    private String mId;
    private String mName;
    private String mDescription;
    private String mABV;
    private String mGlasswareId;
    private String mStyle;
    private String mSRM;

    private String mBreweryId;
    private String mBreweryName;
    private String mBreweryLocation;
    private String mBreweryUrl;

    public Beer(String id, String name, String description, String abv, String glasswareId, String style, String srm, String breweryId, String breweryName, String breweryLocation, String breweryUrl) {
        this.mId = id;
        this.mName = name;
        this.mDescription = description;
        this.mABV = abv;
        this.mGlasswareId = glasswareId;
        this.mStyle = style;
        this.mSRM = srm;
        this.mBreweryId = breweryId;
        this.mBreweryName = breweryName;
        this.mBreweryLocation = breweryLocation;
        this.mBreweryUrl = breweryUrl;
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

    public String getABV() {
        return mABV;
    }

    public String getGlasswareId() {
        return mGlasswareId;
    }

    public String getStyle() {
        return mStyle;
    }

    public String getSRM() {
        return mSRM;
    }

    public String getBreweryId() {
        return mBreweryId;
    }

    public String getBreweryName() {
        return mBreweryName;
    }

    public String getBreweryLocation() {
        return mBreweryLocation;
    }

    public String getBreweryUrl() {
        return mBreweryUrl;
    }
}
