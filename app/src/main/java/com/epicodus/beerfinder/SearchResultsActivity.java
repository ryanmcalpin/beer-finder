package com.epicodus.beerfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchResultsActivity extends AppCompatActivity {
    @Bind(R.id.textView4) TextView mTextView;
    @Bind(R.id.searchResultsView) ListView mSearchResultsList;

    private String[] beers = new String[] {"Pils", "HUB Lager", "Na Zdraví Pils", "Pilsner", "Session Lager", "Kölsch", "Sterling Pils", "Omission Lager"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        String searchTitle = intent.getStringExtra("params");
        mTextView.setText("Search results for \"" + searchTitle +"\"");

        BeerArrayAdapter adapter = new BeerArrayAdapter(this, android.R.layout.simple_list_item_1, beers);
        mSearchResultsList.setAdapter(adapter);
    }
}
