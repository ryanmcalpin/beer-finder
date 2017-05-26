package com.epicodus.beerfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchResultsActivity extends AppCompatActivity {
    @Bind(R.id.textView4) TextView mTextView;
    @Bind(R.id.searchResultsView) ListView mSearchResultsList;

    private String[] beers = new String[] {"Pils", "HUB Lager", "Na Zdraví Pils", "Pilsner", "Session Lager", "Kölsch", "Sterling Pils", "Omission Lager"};
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

        BeerArrayAdapter adapter = new BeerArrayAdapter(this, android.R.layout.simple_list_item_1, beers);
        mSearchResultsList.setAdapter(adapter);
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
        } else {
            i = new Intent(this, StyleSearchActivity.class);
        }
        return i;
    }
}
