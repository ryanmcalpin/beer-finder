package com.epicodus.beerfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.beerfinder.models.Brewery;
import com.epicodus.beerfinder.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BreweryListAdapter extends RecyclerView.Adapter<BreweryListAdapter.BreweryViewHolder> {
    private ArrayList<Brewery> mBreweries = new ArrayList<>();
    private Context mContext;

    public BreweryListAdapter(Context context, ArrayList<Brewery> breweries) {
        mContext = context;
        mBreweries = breweries;
    }

    public class BreweryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.breweryListName) TextView mNameView;
        @Bind(R.id.breweryListEst) TextView mEstView;
        @Bind(R.id.breweryListBeerLink) TextView mLinkView;
        @Bind(R.id.breweryListDescription) TextView mDescriptionView;
        @Bind(R.id.breweryListImage) ImageView mImageView;
//        @Bind(R.id.hiddenUrl) TextView mHiddenUrl;

        private Context mContext;

        Typeface font = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/LANENAR_.ttf");

        public BreweryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            mNameView.setTypeface(font);
            mEstView.setTypeface(font);
            mLinkView.setTypeface(font);
            mDescriptionView.setTypeface(font);
            mLinkView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == mLinkView) {
//                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mHiddenUrl.getText().toString()));
            }
        }

        public void bindBrewery(Brewery brewery) {
            mNameView.setText(brewery.getName());
            String est = brewery.getEstablished();
            if (est.equals("")) {
                mEstView.setVisibility(View.GONE);
            } else {
                mEstView.setText("est. " + brewery.getEstablished());
            }
            String url = brewery.getUrl();
            if (!url.equals("")) {
                mLinkView.setText(url.toString());
            } else {
                mLinkView.setVisibility(View.GONE);
            }
            mDescriptionView.setText(brewery.getDescription());
            if (brewery.getImageSM() != null) {
//                Picasso.with(mContext).load(brewery.getImageSM()).into(mImageView);
            }
        }
    }

    @Override
    public BreweryListAdapter.BreweryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brewery_list_item, parent, false);
        BreweryViewHolder viewHolder = new BreweryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BreweryListAdapter.BreweryViewHolder holder, int position) {
        holder.bindBrewery(mBreweries.get(position));
    }

    @Override
    public int getItemCount() {
        return mBreweries.size();
    }
}
