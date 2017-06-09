package com.epicodus.beerfinder.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.beerfinder.models.Beer;
import com.epicodus.beerfinder.ui.BeerDetailFragment;

import java.util.ArrayList;

/**
 * Created by Guest on 6/7/17.
 */

public class BeerPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Beer> mBeers;

    public BeerPagerAdapter(FragmentManager fm, ArrayList<Beer> beers) {
        super(fm);
        mBeers = beers;
    }

    @Override
    public Fragment getItem(int position) {
        return BeerDetailFragment.newInstance(mBeers.get(position));
    }

    @Override
    public int getCount() {
        return mBeers.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBeers.get(position).getName();
    }
}
