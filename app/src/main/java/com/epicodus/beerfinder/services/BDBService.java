package com.epicodus.beerfinder.services;

import android.util.Log;

import com.epicodus.beerfinder.Constants;
import com.epicodus.beerfinder.models.Beer;
import com.epicodus.beerfinder.models.Brewery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BDBService {

    public static void findResults(String params, String endpoint, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = new HttpUrl.Builder();

        if (endpoint.equals("beers")) {
            urlBuilder = HttpUrl.parse(Constants.BDB_BEER_URL).newBuilder();
            urlBuilder.addQueryParameter(Constants.API_PARAM, Constants.API_KEY).addQueryParameter(Constants.BDB_NAME_PARAM, "*" + params + "**").addQueryParameter(Constants.BDB_WITH_BREWERIES_PARAM, "y").addQueryParameter("order", "updateDate").addQueryParameter("sort", "desc");
        }
        if (endpoint.equals("breweries")) {
            urlBuilder = HttpUrl.parse(Constants.BDB_BREWERY_URL).newBuilder();
            urlBuilder.addQueryParameter(Constants.API_PARAM, Constants.API_KEY).addQueryParameter(Constants.BDB_NAME_PARAM, "*" + params + "**").addQueryParameter("order", "description").addQueryParameter("sort", "desc");
        }
        if (endpoint.equals("beersByBrewery")) {
            urlBuilder = HttpUrl.parse(Constants.BDB_BREWERY_BEERS_URL + "/" + params + "/beers").newBuilder();
            urlBuilder.addQueryParameter(Constants.API_PARAM, Constants.API_KEY);
        }

        String url = urlBuilder.build().toString();
        Log.d("LOGADOG: ", url);
        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Beer> processBeers(Response response) {
        ArrayList<Beer> beers = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject bdbJSON = new JSONObject(jsonData);
                JSONArray beersJSON = bdbJSON.getJSONArray("data");
                for (int i = 0; i < beersJSON.length(); i++) {
                    JSONObject beerJSON = beersJSON.getJSONObject(i);
                    int position = i;
                    String id = beerJSON.getString("id");
                    String name = beerJSON.getString("name");
                    String description = beerJSON.optString("description");
                    String abv = beerJSON.optString("abv");
                    String glasswareId = String.valueOf(beerJSON.optInt("glasswareId"));
                    JSONObject styleJSON = beerJSON.optJSONObject("style");
                    String style = "";
                    if (styleJSON != null) {
                        style = styleJSON.optString("shortName");
                    }
                    JSONObject srmJSON = beerJSON.optJSONObject("srm");
                    String srm = "";
                    if (srmJSON != null) {
                        srm = srmJSON.getString("hex");
                    }

                    JSONArray breweryArrJSON = beerJSON.optJSONArray("breweries");
                    String breweryId = "";
                    String breweryName = "";
                    String breweryUrl = "";
                    String breweryCity = "";
                    String breweryState = "";
                    String breweryLocation = "";
                    if (breweryArrJSON != null) {
                        JSONObject breweryJSON = breweryArrJSON.optJSONObject(0);
                        breweryId = breweryJSON.optString("id");
                        breweryName = breweryJSON.optString("name");
                        breweryUrl = breweryJSON.optString("website");
                        if (breweryJSON.optJSONArray("locations") != null) {
                            breweryCity = breweryJSON.optJSONArray("locations").optJSONObject(0).optString("locality");
                            breweryState = breweryJSON.optJSONArray("locations").optJSONObject(0).optString("region");
                        }
                        if (!breweryCity.equals("") && !breweryState.equals("")) {
                            breweryLocation = breweryCity + ", " + breweryState;
                        } else  {
                            breweryLocation = breweryCity + breweryState;
                        }
                    }

                    Beer beer = new Beer(position, id, name, description, abv, glasswareId, style, srm, breweryId, breweryName, breweryLocation, breweryUrl);
                    beers.add(beer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return beers;
    }

    public ArrayList<Brewery> processBreweries(Response response) {
        ArrayList<Brewery> breweries = new ArrayList<>();

        try {
            String jsonData = response.body().string();
                if (response.isSuccessful()) {
                    JSONObject bdbJSON = new JSONObject(jsonData);
                    JSONArray breweriesJSON = bdbJSON.getJSONArray("data");
                    for (int i = 0; i < breweriesJSON.length(); i++) {
                        JSONObject breweryJSON = breweriesJSON.getJSONObject(i);
                        String id = breweryJSON.getString("id");
                        String name = breweryJSON.getString("name");
                        String shortName = breweryJSON.optString("nameShortDisplay");
                        String description = breweryJSON.optString("description");
                        String url = breweryJSON.optString("website");
                        String established = breweryJSON.optString("established");
                        JSONObject imageUrlsObj = breweryJSON.optJSONObject("images");
                        String icon = "";
                        String medium = "";
                        String large = "";
                        String squareMedium = "";
                        String squareLarge = "";
                        if (imageUrlsObj != null) {
                            icon = imageUrlsObj.optString("icon");
                            medium = imageUrlsObj.optString("medium");
                            large = imageUrlsObj.optString("large");
                            squareMedium = imageUrlsObj.optString("squareMedium");
                            squareLarge = imageUrlsObj.optString("squareLarge");
                        }
                        Brewery brewery = new Brewery(id, name, shortName, description, url, established, icon, medium, large, squareMedium, squareLarge);
                        breweries.add(brewery);
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return breweries;
    }
}
