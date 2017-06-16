package com.epicodus.beerfinder.ui;

import android.app.ProgressDialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.epicodus.beerfinder.R;
import com.epicodus.beerfinder.adapters.BeerPagerAdapter;
import com.epicodus.beerfinder.models.Beer;
import com.epicodus.beerfinder.services.BDBService;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BeerDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    @Bind(R.id.breweryNameView) TextView mBreweryView;
    private BeerPagerAdapter adapterViewPager;
    private ProgressDialog mAPIProgressDialog;
    private String mBreweryName;
    ArrayList<Beer> mBeers = new ArrayList<>();
    String beerId;
    int startingPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_detail);
        ButterKnife.bind(this);

        String mBreweryId = getIntent().getStringExtra("breweryId");
        beerId = getIntent().getStringExtra("beerId");

        mBreweryName = getIntent().getStringExtra("breweryName");
        mBreweryView.setText(mBreweryName);

        createAPIProgressDialog();
        searchDB(mBreweryId);
    }

    private void searchDB(final String mBreweryId) {
        final BDBService bdbService = new BDBService();
        mAPIProgressDialog.show();
        bdbService.findResults(mBreweryId, "beersByBrewery", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                mAPIProgressDialog.dismiss();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mAPIProgressDialog.dismiss();
                mBeers = bdbService.processBeers(response);
                for (Beer beer : mBeers) {
                    if (beer.getId().equals(beerId)) {
                        startingPosition = beer.getPosition();
                        beer.setBreweryId(mBreweryId);
                    }
                }

                BeerDetailActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapterViewPager = new BeerPagerAdapter(getSupportFragmentManager(), mBeers);
                        mViewPager.setAdapter(adapterViewPager);
                        mViewPager.setCurrentItem(startingPosition);
                    }
                });
            }
        });
    }

    private void createAPIProgressDialog() {
        mAPIProgressDialog = new ProgressDialog(this);
        mAPIProgressDialog.setTitle("Finding beers...");
        mAPIProgressDialog.setMessage("Searching " + mBreweryName + "...");
        mAPIProgressDialog.setCancelable(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }
}
