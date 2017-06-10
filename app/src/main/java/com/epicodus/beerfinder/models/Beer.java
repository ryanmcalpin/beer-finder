package com.epicodus.beerfinder.models;

import org.parceler.Parcel;

@Parcel
public class Beer {
    private int position;
    private String id;
    private String name;
    private String description;
    private String abv;
    private String glasswareId;
    private String style;
    private String srm;
//    private String label;

    private String breweryId;
    private String breweryName;
    private String breweryLocation;
    private String breweryUrl;

    public Beer(){}

    public Beer(int position, String id, String name, String description, String abv, String glasswareId, String style, String srm, String breweryId, String breweryName, String breweryLocation, String breweryUrl) {
        this.position = position;
        this.id = id;
        this.name = name;
        this.description = description;
        this.abv = abv;
        this.glasswareId = glasswareId;
        this.style = style;
        this.srm = srm;
        this.breweryId = breweryId;
        this.breweryName = breweryName;
        this.breweryLocation = breweryLocation;
        this.breweryUrl = breweryUrl;
    }

    public int getPosition() {
        return position;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getABV() {
        return abv;
    }

    public String getGlasswareId() {
        return glasswareId;
    }

    public String getStyle() {
        return style;
    }

    public String getSRM() {
        return srm;
    }

    public String getBreweryId() {
        return breweryId;
    }

    public String getBreweryName() {
        return breweryName;
    }

    public String getBreweryLocation() {
        return breweryLocation;
    }

    public String getBreweryUrl() {
        return breweryUrl;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public void setGlasswareId(String glasswareId) {
        this.glasswareId = glasswareId;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setSrm(String srm) {
        this.srm = srm;
    }

    public void setBreweryId(String breweryId) {
        this.breweryId = breweryId;
    }

    public void setBreweryName(String breweryName) {
        this.breweryName = breweryName;
    }

    public void setBreweryLocation(String breweryLocation) {
        this.breweryLocation = breweryLocation;
    }

    public void setBreweryUrl(String breweryUrl) {
        this.breweryUrl = breweryUrl;
    }
}
