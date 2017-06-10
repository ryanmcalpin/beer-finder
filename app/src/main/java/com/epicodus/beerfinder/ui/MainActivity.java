package com.epicodus.beerfinder.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.beerfinder.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.searchBeerButton) Button mBeerButton;
    @Bind(R.id.searchBreweriesButton) Button mBreweriesButton;
    @Bind(R.id.titleView) TextView mTitleView;
    @Bind(R.id.favoritesButton) Button mFavoritesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface titleFont = Typeface.createFromAsset(getAssets(), "fonts/LANEUP__.ttf");
        mTitleView.setTypeface(titleFont);
        mBeerButton.setTypeface(titleFont);
        mBreweriesButton.setTypeface(titleFont);
        mFavoritesButton.setTypeface(titleFont);

        mBeerButton.setOnClickListener(this);
        mBreweriesButton.setOnClickListener(this);
        mFavoritesButton.setOnClickListener(this);

        FragmentManager fm = getSupportFragmentManager();
//        CreateAccountFragment createAccountFragment = new CreateAccountFragment();
//        createAccountFragment.show(fm, "What");
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.show(fm, "Whaaa");

    }

    @Override
    public void onClick(View v) {
        if (v == mBeerButton) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            intent.putExtra("endpoint", "beers");
            startActivity(intent);
//            overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
        }
        if (v == mBreweriesButton) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            intent.putExtra("endpoint", "breweries");
            startActivity(intent);
//            overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
        }
        if (v == mFavoritesButton) {
            Intent intent = new Intent(MainActivity.this, FavoriteBeersActivity.class);
            startActivity(intent);
        }

        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }
}
