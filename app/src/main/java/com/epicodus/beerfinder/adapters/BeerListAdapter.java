package com.epicodus.beerfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.beerfinder.models.Beer;
import com.epicodus.beerfinder.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BeerListAdapter extends RecyclerView.Adapter<BeerListAdapter.BeerViewHolder> {
    private ArrayList<Beer> mBeers = new ArrayList<>();
    private Context mContext;

    public BeerListAdapter(Context context, ArrayList<Beer> beers) {
        mContext = context;
        mBeers = beers;
    }

    public class BeerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.beerListBeer) TextView mNameView;
        @Bind(R.id.beerListStyle) TextView mStyleView;
        @Bind(R.id.beerListABV) TextView mABVView;
        @Bind(R.id.beerListBrewery) TextView mBreweryView;
        @Bind(R.id.beerListGlassImage) ImageView mGlassImage;
        @Bind(R.id.beerListDescription) TextView mDescriptionView;
        @Bind(R.id.hiddenUrl) TextView mUrlView;

        private Context mContext;

        Typeface font = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/LANENAR_.ttf");

        public BeerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            mNameView.setTypeface(font);
            mStyleView.setTypeface(font);
            mABVView.setTypeface(font);
            mBreweryView.setTypeface(font);
            mDescriptionView.setTypeface(font);

            mBreweryView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == mBreweryView) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrlView.getText().toString()));
                mContext.startActivity(webIntent);
            } else {
                //go to beer detail pager, showing other beers from that brewery
            }
        }

        public void bindBeer(Beer beer) {
            mNameView.setText(beer.getName());
            mStyleView.setText(beer.getStyle());
            if (beer.getABV().equals("")) {
                mABVView.setVisibility(View.GONE);
            } else {
                mABVView.setText(beer.getABV() + "% ABV");
            }
            mBreweryView.setText(beer.getBreweryName() + "\n" + beer.getBreweryLocation());
            if (beer.getDescription().equals("")) {
                mDescriptionView.setVisibility(View.GONE);
            } else {
                mDescriptionView.setText(beer.getDescription());
            }
            mUrlView.setText(beer.getBreweryUrl());

            //replace R.drawable.glass with specific glass image
            Picasso.with(mContext).load(R.drawable.glass).into(mGlassImage);

            if (!beer.getSRM().equals("")) {
                mGlassImage.setBackgroundColor(Color.parseColor("#" + beer.getSRM()));
            }
        }
    }

    @Override
    public BeerListAdapter.BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_list_item, parent, false);
        BeerViewHolder viewHolder = new BeerViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BeerListAdapter.BeerViewHolder holder, int position) {
        holder.bindBeer(mBeers.get(position));
    }

    @Override
    public int getItemCount() {
        return mBeers.size();
    }


}


