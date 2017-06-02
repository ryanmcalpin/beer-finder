package com.epicodus.beerfinder.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.beerfinder.R;
import com.epicodus.beerfinder.services.BDBService;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.searchBeerButton) Button mBeerButton;
    @Bind(R.id.searchStyleButton) Button mStyleButton;
    @Bind(R.id.titleView) TextView mTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface titleFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        mTitleView.setTypeface(titleFont);

        mBeerButton.setOnClickListener(this);
        mStyleButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mBeerButton) {
            Intent intent = new Intent(MainActivity.this, BeerSearchActivity.class);
            startActivity(intent);
        } else if (v == mStyleButton) {
            Intent intent = new Intent(MainActivity.this, StyleSearchActivity.class);
            startActivity(intent);
        }
    }
}
