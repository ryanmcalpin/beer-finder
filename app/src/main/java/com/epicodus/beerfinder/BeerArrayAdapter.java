package com.epicodus.beerfinder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.View;

/**
 * Created by Guest on 5/26/17.
 */

public class BeerArrayAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mBeers;
    private String[] mDescriptions;
    private String[] mBreweries;

    public BeerArrayAdapter(Context mContext, int resource, String[] mBeers, String[] mDescriptions, String[] mBreweries) {
        this.mContext = mContext;
        this.mBeers = mBeers;
        this.mDescriptions = mDescriptions;
        this.mBreweries = mBreweries;
    }

    @Override
    public int getCount() {
        return mBeers.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.beer_list_item, null);
            TextView beer = (TextView) gridView.findViewById(R.id.beerListBeer);
            TextView description = (TextView) gridView.findViewById(R.id.beerListDescription);
            TextView brewery = (TextView) gridView.findViewById(R.id.beerListBrewery);
            beer.setText(mBeers[position]);
            description.setText(mDescriptions[position]);
            brewery.setText(mBreweries[position]);

        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }
}
