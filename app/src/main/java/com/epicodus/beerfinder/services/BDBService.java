package com.epicodus.beerfinder.services;

import android.util.Log;

import com.epicodus.beerfinder.Constants;
import com.epicodus.beerfinder.models.Beer;
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

/**
 * Created by rygn on 6/2/17.
 */

public class BDBService {

    public static void findResults(String name, String endpoint, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BDB_BEER_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.API_PARAM, Constants.API_KEY).addQueryParameter(Constants.BDB_NAME_PARAM, "*" + name + "**").addQueryParameter(Constants.BDB_WITH_BREWERIES_PARAM, "y");
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Beer> processResults(Response response) {
        ArrayList<Beer> beers = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject bdbJSON = new JSONObject(jsonData);
                JSONArray beersJSON = bdbJSON.getJSONArray("data");
                for (int i = 0; i < beersJSON.length(); i++) {
                    JSONObject beerJSON = beersJSON.getJSONObject(i);
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

                    Beer beer = new Beer(id, name, description, abv, glasswareId, style, srm, breweryId, breweryName, breweryLocation, breweryUrl);
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
}
