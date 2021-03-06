package com.epicodus.beerfinder.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Brewery {
    private String id;
    private String name;
    private String shortName;
    private String description;
    private String url;
    private String established;
    private String imageIcon;
    private String imageMedium;
    private String imageLarge;
    private String imageSM;
    private String imageSL;

    public Brewery() {}

    public Brewery(String id, String name, String shortName, String description, String url, String established, String imageIcon, String imageMedium, String imageLarge, String imageSM, String imageSL) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.url = url;
        this.established = established;
        this.imageIcon = imageIcon;
        this.imageMedium = imageMedium;
        this.imageLarge = imageLarge;
        this.imageSM = imageSM;
        this.imageSL = imageSL;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getEstablished() {
        return established;
    }

    public String getImageIcon() {
        return imageIcon;
    }

    public String getImageMedium() {
        return imageMedium;
    }

    public String getImageLarge() {
        return imageLarge;
    }

    public String getImageSM() {
        return imageSM;
    }

    public String getImageSL() {
        return imageSL;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setEstablished(String established) {
        this.established = established;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }

    public void setImageMedium(String imageMedium) {
        this.imageMedium = imageMedium;
    }

    public void setImageLarge(String imageLarge) {
        this.imageLarge = imageLarge;
    }

    public void setImageSM(String imageSM) {
        this.imageSM = imageSM;
    }

    public void setImageSL(String imageSL) {
        this.imageSL = imageSL;
    }
}
