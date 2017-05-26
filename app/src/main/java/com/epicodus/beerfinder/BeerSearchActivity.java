package com.epicodus.beerfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BeerSearchActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.searchButton) Button searchButton;
    @Bind(R.id.searchText) EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_search);
        ButterKnife.bind(this);

        searchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String params = searchText.getText().toString();
        if (params.trim().length() == 0) {
            Toast.makeText(BeerSearchActivity.this, "Beer name required!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(BeerSearchActivity.this, SearchResultsActivity.class);
            intent.putExtra("params", params);
            startActivity(intent);
        }
    }
}
