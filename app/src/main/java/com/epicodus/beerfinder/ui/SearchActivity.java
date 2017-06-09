package com.epicodus.beerfinder.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.beerfinder.Constants;
import com.epicodus.beerfinder.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private ArrayList<String> savedSearches;
    private String[] mSearchList;
    private String endpoint;
    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.searchText) AutoCompleteTextView mSearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_search);
        ButterKnife.bind(this);

//        mSharedPreferences = getSharedPreferences(Constants.PREFERENCES_BEER_SEARCHES_KEY, Context.MODE_PRIVATE);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/LANENAR_.ttf");
        mSearchButton.setTypeface(font);
        mSearchText.setTypeface(font);

        Intent intent = getIntent();
        endpoint = intent.getStringExtra("endpoint");
        savedSearches = new ArrayList<String>();
        mSearchList = new String[] {};
        if (endpoint.equals("beers")) {
            mSearchText.setHint("Enter Beer Name");
            if (mSharedPreferences.getString(Constants.PREFERENCES_BEER_SEARCHES_KEY, null) != null) {
                mSearchList = TextUtils.split(mSharedPreferences.getString(Constants.PREFERENCES_BEER_SEARCHES_KEY, null), ",_,");
            }
        }
        if (endpoint.equals("breweries")) {
            mSearchText.setHint("Enter Brewery Name");
            if (mSharedPreferences.getString(Constants.PREFERENCES_BREWERY_SEARCHES_KEY, null) != null) {
                mSearchList = TextUtils.split(mSharedPreferences.getString(Constants.PREFERENCES_BREWERY_SEARCHES_KEY, null), ",_,");
            }
        }
        if (savedSearches != null) {
            savedSearches = new ArrayList<>(Arrays.asList(mSearchList));
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mSearchList);
            mSearchText.setAdapter(adapter);
        }

        mSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSearchButton) {
            String params = mSearchText.getText().toString();
            if (params.trim().length() == 0) {
                mSearchText.setError("Required");
                mSearchText.setText("");
            } else {
                if (!savedSearches.contains(params.toLowerCase())) {
                    while (savedSearches.size() >= Constants.PREF_MAX) {
                        savedSearches.remove(0);
                    }
                    savedSearches.add(params.toLowerCase());
                    addToSharedPreferences(savedSearches);
                }

                Intent intent = new Intent(SearchActivity.this, SearchResultsActivity.class);
                intent.putExtra("endpoint", endpoint);
                intent.putExtra("params", params);
//            intent.putExtra("parent", "beer");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        }
    }

    private void addToSharedPreferences(ArrayList<String> savedSearches) {
        String[] searchList = savedSearches.toArray(new String[savedSearches.size()]);

        if (endpoint.equals("beers")) {
            mEditor.putString(Constants.PREFERENCES_BEER_SEARCHES_KEY, TextUtils.join(",_,", searchList)).apply();
        } else if (endpoint.equals("breweries")) {
            mEditor.putString(Constants.PREFERENCES_BREWERY_SEARCHES_KEY, TextUtils.join(",_,", searchList)).apply();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
        return (super.onOptionsItemSelected(item));
    }
}
