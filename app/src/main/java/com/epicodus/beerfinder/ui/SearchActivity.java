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
    private String endpoint;
    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.searchText) EditText mSearchText;

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
        if (endpoint.equals("beers")) {
            mSearchText.setHint("Enter Beer Name");
        }
        if (endpoint.equals("breweries")) {
            mSearchText.setHint("Enter Brewery Name");
        }

        mSearchButton.setOnClickListener(this);

//        testing
        String[] searchList = TextUtils.split(mSharedPreferences.getString(Constants.PREFERENCES_BEER_SEARCHES_KEY, null), ",_,");
        savedSearches = new ArrayList<>(Arrays.asList(searchList));
        for (String s : savedSearches) {
            Log.d("LOGADOG onCreate: ", s);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == mSearchButton) {
            String params = mSearchText.getText().toString();
            if (params.trim().length() == 0) {
                mSearchText.setError("Required");
                mSearchText.setText("");
            } else {
                savedSearches.add(params);
                addToSharedPreferences(savedSearches);
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
        mEditor.putString(Constants.PREFERENCES_BEER_SEARCHES_KEY, TextUtils.join(",_,", searchList)).apply();
        for (String s : savedSearches) {
            Log.d("LOGADOG addTo: ", s);
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
