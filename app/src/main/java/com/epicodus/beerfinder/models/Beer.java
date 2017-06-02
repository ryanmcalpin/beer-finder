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
//    private String mBreweryId;

    public Beer(String id, String name, String description, String abv, String glasswareId, String style) {
        this.mId = id;
        this.mName = name;
        this.mDescription = description;
        this.mABV = abv;
        this.mGlasswareId = glasswareId;
        this.mStyle = style;
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
}
