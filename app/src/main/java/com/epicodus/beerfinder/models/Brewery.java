package com.epicodus.beerfinder.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Brewery {
    private String mId;
    private String mName;
    private String mShortName;
    private String mDescription;
    private String mUrl;
    private String mEstablished;
    private String mImageIcon;
    private String mImageMedium;
    private String mImageLarge;
    private String mImageSM;
    private String mImageSL;

    public Brewery() {}

    public Brewery(String id, String name, String shortName, String description, String url, String established, String imageIcon, String imageMedium, String imageLarge, String imageSM, String imageSL) {
        this.mId = id;
        this.mName = name;
        this.mShortName = shortName;
        this.mDescription = description;
        this.mUrl = url;
        this.mEstablished = established;
        this.mImageIcon = imageIcon;
        this.mImageMedium = imageMedium;
        this.mImageLarge = imageLarge;
        this.mImageSM = imageSM;
        this.mImageSL = imageSL;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getmShortName() {
        return mShortName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getEstablished() {
        return mEstablished;
    }

    public String getImageIcon() {
        return mImageIcon;
    }

    public String getImageMedium() {
        return mImageMedium;
    }

    public String getImageLarge() {
        return mImageLarge;
    }

    public String getImageSM() {
        return mImageSM;
    }

    public String getmImageSL() {
        return mImageSL;
    }
}
