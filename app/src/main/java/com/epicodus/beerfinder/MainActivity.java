package com.epicodus.beerfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.searchBeerButton) Button beerButton;
    @Bind(R.id.searchStyleButton) Button styleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        beerButton.setOnClickListener(this);
        styleButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == beerButton) {
            Intent intent = new Intent(MainActivity.this, BeerSearchActivity.class);
            startActivity(intent);
        } else if (v == styleButton) {
            Intent intent = new Intent(MainActivity.this, StyleSearchActivity.class);
            startActivity(intent);
        }
    }
}
