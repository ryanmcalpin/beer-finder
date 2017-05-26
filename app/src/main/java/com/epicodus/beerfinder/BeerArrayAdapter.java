package com.epicodus.beerfinder;

import android.widget.ArrayAdapter;
import android.content.Context;

/**
 * Created by Guest on 5/26/17.
 */

public class BeerArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mBeers;

    public BeerArrayAdapter(Context mContext, int resource, String[] mBeers) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mBeers = mBeers;
    }

    @Override
    public Object getItem(int position) {
        String beer = mBeers[position];
        return String.format("Beer: %s", beer);
    }

    @Override
    public int getCount() {
        return mBeers.length;
    }
}
