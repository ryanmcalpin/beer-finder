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

    public static void findBeers(String name, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BDB_BEER_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.API_PARAM, Constants.API_KEY).addQueryParameter(Constants.BDB_NAME_PARAM, "*" + name + "*");
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
                for (int i = 0; i < bdbJSON.length(); i++) {
                    JSONObject beerJSON = beersJSON.getJSONObject(i);
                    String id = beerJSON.getString("id");
                    String name = beerJSON.getString("name");
                    String description = beerJSON.optString("description");
                    String abv = beerJSON.optString("abv");
                    String glasswareId = String.valueOf(beerJSON.optInt("glasswareId"));
                    String style = beerJSON.getJSONObject("style").optString("shortName");
                    Beer beer = new Beer(id, name, description, abv, glasswareId, style);
                    beers.add(beer);
                    for (Beer b : beers) {
                        Log.d("processResults: ", b.getName());
                        Log.d("processResults: ", b.getStyle());
                        Log.d("processResults: ", b.getDescription());
                    }
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
