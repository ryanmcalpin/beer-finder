package com.epicodus.beerfinder.models;

import org.parceler.Parcel;

@Parcel
public class Beer {
    private String id;
    private String name;
    private String description;
    private String abv;
    private String glasswareId;
    private String style;
    private String srm;

    private String breweryId;
    private String breweryName;
    private String breweryLocation;
    private String breweryUrl;

    public Beer(){}

    public Beer(String id, String name, String description, String abv, String glasswareid, String style, String srm, String breweryid, String breweryname, String breweryLocation, String breweryUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.abv = abv;
        this.glasswareId = glasswareid;
        this.style = style;
        this.srm = srm;
        this.breweryId = breweryid;
        this.breweryName = breweryname;
        this.breweryLocation = breweryLocation;
        this.breweryUrl = breweryUrl;
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
}
