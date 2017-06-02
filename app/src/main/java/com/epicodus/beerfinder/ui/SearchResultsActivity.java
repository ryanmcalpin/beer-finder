package com.epicodus.beerfinder.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.beerfinder.adapters.BeerArrayAdapter;
import com.epicodus.beerfinder.R;
import com.epicodus.beerfinder.models.Beer;
import com.epicodus.beerfinder.services.BDBService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchResultsActivity extends AppCompatActivity {
    @Bind(R.id.textView4) TextView mTextView;
    @Bind(R.id.searchResultsView) ListView mSearchResultsList;

    public ArrayList<Beer> mBeers = new ArrayList<>();

    private String parentString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String searchTitle = intent.getStringExtra("params");
        parentString = intent.getStringExtra("parent");

        mTextView.setText("Search results for \"" + searchTitle +"\"");

        getBeers(searchTitle);
    }

    @Override
    public Intent getSupportParentActivityIntent() {
        return getParentActivityIntentImpl();
    }

    @Override
    public Intent getParentActivityIntent() {
        return getParentActivityIntentImpl();
    }

    private Intent getParentActivityIntentImpl() {
        Intent i = null;
        if (this.parentString.equals("beer") ) {
            i = new Intent(this, BeerSearchActivity.class);
        } else if (this.parentString.equals("style")) {
            i = new Intent(this, StyleSearchActivity.class);
        }
        return i;
    }

    private void getBeers(String name) {
        final BDBService bdbService = new BDBService();
        bdbService.findBeers(name, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mBeers = bdbService.processResults(response);

                SearchResultsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] beerNames = new String[mBeers.size()];
                        for (int i = 0; i < beerNames.length; i++) {
                            beerNames[i] = mBeers.get(i).getName();
                        }

                        String[] beerDescriptions = new String[mBeers.size()];
                        for (int i = 0; i < beerDescriptions.length; i++) {
                            beerDescriptions[i] = mBeers.get(i).getDescription();
                        }

                        String[] breweries = new String[mBeers.size()];
                        for (int i = 0; i < breweries.length; i++) {
                            breweries[i] = "brewery";
                        }

                        BeerArrayAdapter adapter = new BeerArrayAdapter(SearchResultsActivity.this, android.R.layout.simple_list_item_1, beerNames, beerDescriptions, breweries);
                        mSearchResultsList.setAdapter(adapter);
                    }
                });
            }
        });
    }
}
