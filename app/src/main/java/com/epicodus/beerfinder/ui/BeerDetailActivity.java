package com.epicodus.beerfinder.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    private BeerPagerAdapter adapterViewPager;
    ArrayList<Beer> mBeers = new ArrayList<>();
    String beerId;
    int startingPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_detail);
        ButterKnife.bind(this);

//        mBeers = Parcels.unwrap(getIntent().getParcelableExtra("beers"));
        String mBreweryId = getIntent().getStringExtra("breweryId");
        beerId = getIntent().getStringExtra("beerId");
//        String position = getIntent().getStringExtra("listPosition");  WRONG
//        startingPosition = Integer.parseInt(position);                WRONG

        searchDB(mBreweryId);
    }

    private void searchDB(String mBreweryId) {
        final BDBService bdbService = new BDBService();
        bdbService.findResults(mBreweryId, "beersByBrewery", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mBeers = bdbService.processBeers(response);
                for (Beer beer : mBeers) {
                    if (beer.getId().equals(beerId)) {
                        startingPosition = beer.getPosition();
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
}
