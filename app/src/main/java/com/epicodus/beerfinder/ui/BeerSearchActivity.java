package com.epicodus.beerfinder.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.beerfinder.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BeerSearchActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.searchText) EditText mSearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_search);
        ButterKnife.bind(this);

        final InputMethodManager inputMethodManager = (InputMethodManager) this
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(mSearchText, InputMethodManager.SHOW_IMPLICIT);

        mSearchText.requestFocus();
        mSearchText.setFocusable(true);
        mSearchText.setFocusableInTouchMode(true);

        mSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String params = mSearchText.getText().toString();
        if (params.trim().length() == 0) {
            mSearchText.setError("Required");
            mSearchText.setText("");
        } else {
            Intent intent = new Intent(BeerSearchActivity.this, SearchResultsActivity.class);
            intent.putExtra("params", params);
            intent.putExtra("parent", "beer");
            startActivity(intent);
            overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
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
