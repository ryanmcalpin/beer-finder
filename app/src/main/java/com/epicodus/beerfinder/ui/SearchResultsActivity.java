package com.epicodus.beerfinder.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.epicodus.beerfinder.R;
import com.epicodus.beerfinder.adapters.BeerListAdapter;
import com.epicodus.beerfinder.adapters.BreweryListAdapter;
import com.epicodus.beerfinder.models.Beer;
import com.epicodus.beerfinder.models.Brewery;
import com.epicodus.beerfinder.services.BDBService;
import java.io.IOException;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchResultsActivity extends AppCompatActivity {
    @Bind(R.id.beerListTitle) TextView mTextView;
    @Bind(R.id.searchResultsView) RecyclerView mRecyclerView;
    private BeerListAdapter mBeerAdapter;
    private BreweryListAdapter mBreweryAdapter;
    private String endpoint;
    private ProgressDialog mAPIProgressDialog;
    public ArrayList<Beer> mBeers = new ArrayList<>();
    public ArrayList<Brewery> mBreweries = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/LANENAR_.ttf");
        mTextView.setTypeface(font);

        Intent intent = getIntent();
        String searchTitle = intent.getStringExtra("params");
//        parentString = intent.getStringExtra("parent");
        endpoint = intent.getStringExtra("endpoint");

        mTextView.setText("Search results for \"" + searchTitle +"\"");

        createAPIProgressDialog();
        searchDB(searchTitle);
    }

    private void searchDB(String name) {
        final BDBService bdbService = new BDBService();
        mAPIProgressDialog.show();
        bdbService.findResults(name, endpoint, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                mAPIProgressDialog.dismiss();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mAPIProgressDialog.dismiss();
                if (endpoint.equals("beers")) {
                    mBeers = bdbService.processBeers(response);
                }
                if (endpoint.equals("breweries")) {
                    mBreweries = bdbService.processBreweries(response);
                }

                SearchResultsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (endpoint.equals("beers")) {
                            mBeerAdapter = new BeerListAdapter(getApplicationContext(), mBeers);
                            mRecyclerView.setAdapter(mBeerAdapter);
                        }
                        if (endpoint.equals("breweries")) {
                            mBreweryAdapter = new BreweryListAdapter(getApplicationContext(), mBreweries);
                            mRecyclerView.setAdapter(mBreweryAdapter);
                        }
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchResultsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }

    private void createAPIProgressDialog() {
        mAPIProgressDialog = new ProgressDialog(this);
        mAPIProgressDialog.setTitle("Finding " + endpoint + "...");
        mAPIProgressDialog.setMessage("Searching BreweryDB...");
        mAPIProgressDialog.setCancelable(false);
    }
}
