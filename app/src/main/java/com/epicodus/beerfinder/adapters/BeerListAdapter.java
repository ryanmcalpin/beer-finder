package com.epicodus.beerfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.beerfinder.Constants;
import com.epicodus.beerfinder.models.Beer;
import com.epicodus.beerfinder.R;
import com.epicodus.beerfinder.ui.BeerDetailActivity;
import com.epicodus.beerfinder.ui.SearchResultsActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

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
        @Bind(R.id.hiddenBreweryId) TextView mBreweryIdView;
        @Bind(R.id.hiddenListPosition) TextView mIdView;

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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == mBreweryView && !mUrlView.getText().toString().equals("")) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrlView.getText().toString()));
                mContext.startActivity(webIntent);
            } else {
                Intent intent = new Intent(mContext, BeerDetailActivity.class);
                intent.putExtra("breweryId", mBreweryIdView.getText().toString());
                intent.putExtra("beerId", mIdView.getText().toString());
                intent.putExtra("breweryName", mBreweryView.getText().toString());

                mContext.startActivity(intent);
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
            mBreweryView.setText(beer.getBreweryName());
            if (beer.getDescription().equals("")) {
                mDescriptionView.setVisibility(View.GONE);
            } else {
                mDescriptionView.setText(beer.getDescription());
            }
            mUrlView.setText(beer.getBreweryUrl());
            mBreweryIdView.setText(beer.getBreweryId());
            mIdView.setText(String.valueOf(beer.getId()));

            if (!beer.getSRM().equals("")) {
                mGlassImage.setBackgroundColor(Color.parseColor("#" + beer.getSRM()));
            } else if (!beer.getStyleSrmMin().equals("") && !beer.getStyleSrmMax().equals("")){
                float styleSrmTotal = (float) Integer.parseInt(beer.getStyleSrmMin()) + Integer.parseInt(beer.getStyleSrmMax());
                int styleSrmAvg = Math.round(styleSrmTotal / 2);
                String hexColor = "#00ffffff";
                switch (styleSrmAvg) {
                       case 1:
                        hexColor = "#f0efb5";
                        break;
                       case 2:
                        hexColor = "#e9d76c";
                        break;
                       case 3:
                        hexColor = "#e1c336";
                        break;
                       case 4:
                        hexColor = "#dab700";
                        break;
                       case 5:
                        hexColor = "#d4ac00";
                        break;
                       case 6:
                        hexColor = "#cfa200";
                        break;
                       case 7:
                        hexColor = "#c99800";
                        break;
                       case 8:
                        hexColor = "#c38e0d";
                        break;
                       case 9:
                        hexColor = "#bd841a";
                        break;
                       case 10:
                        hexColor = "#b87b1c";
                        break;
                       case 11:
                        hexColor = "#b2731e";
                        break;
                       case 12:
                        hexColor = "#ad6a20";
                        break;
                       case 13:
                        hexColor = "#a86222";
                        break;
                       case 14:
                        hexColor = "#a35b20";
                        break;
                       case 15:
                        hexColor = "#9d531f";
                        break;
                       case 16:
                        hexColor = "#984c1d";
                        break;
                       case 17:
                        hexColor = "#94461c";
                        break;
                       case 18:
                        hexColor = "#8f3f1c";
                        break;
                       case 19:
                        hexColor = "#8a391d";
                        break;
                       case 20:
                        hexColor = "#85341d";
                        break;
                       case 21:
                        hexColor = "#812f1e";
                        break;
                       case 22:
                        hexColor = "#7c2a1f";
                        break;
                       case 23:
                        hexColor = "#78251c";
                        break;
                       case 24:
                        hexColor = "#74211a";
                        break;
                       case 25:
                        hexColor = "#701e18";
                        break;
                       case 26:
                        hexColor = "#6b1a16";
                        break;
                       case 27:
                        hexColor = "#671714";
                        break;
                       case 28:
                        hexColor = "#641413";
                        break;
                       case 29:
                        hexColor = "#601213";
                        break;
                       case 30:
                        hexColor = "#5c1012";
                        break;
                       case 31:
                        hexColor = "#580e12";
                        break;
                       case 32:
                        hexColor = "#550d11";
                        break;
                       case 33:
                        hexColor = "#510c11";
                        break;
                       case 34:
                        hexColor = "#4e0c11";
                        break;
                       case 35:
                        hexColor = "#4b0c11";
                        break;
                       case 36:
                        hexColor = "#470c11";
                        break;
                       case 37:
                        hexColor = "#440c11";
                        break;
                       case 38:
                        hexColor = "#410c11";
                        break;
                       case 39:
                        hexColor = "#3e0c11";
                        break;
                }
                if (styleSrmAvg >= 40) {
                    hexColor = "#000000";
                }
                mGlassImage.setBackgroundColor(Color.parseColor(hexColor));
            }

            //replace R.drawable.glass with specific glass image
            Picasso.with(mContext).load(R.drawable.glass).into(mGlassImage);

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


