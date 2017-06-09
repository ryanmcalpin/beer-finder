package com.epicodus.beerfinder;

/**
 * Created by rygn on 6/2/17.
 */

public class Constants {
    public static final String BDB_BEER_URL = "http://api.brewerydb.com/v2/beers";
    public static final String BDB_BREWERY_URL = "http://api.brewerydb.com/v2/breweries";
    public static final String BDB_BREWERY_BEERS_URL = "http://api.brewerydb.com/v2/brewery";
    public static final String API_PARAM = "key";
    public static final String API_KEY = BuildConfig.API_KEY;
    public static final String BDB_NAME_PARAM = "name";
    public static final String BDB_WITH_BREWERIES_PARAM = "withBreweries";

    public static final String FIREBASE_CHILD_BEERS = "beers";
    public static final String FIREBASE_CHILD_BREWERIES = "breweries";

    public static final String PREFERENCES_BEER_SEARCHES_KEY = "beerQueries";
    public static final int PREF_MAX = 30;
}
