package com.epicodus.beerfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.beerfinder.Constants;
import com.epicodus.beerfinder.R;
import com.epicodus.beerfinder.models.Beer;
import com.epicodus.beerfinder.ui.BeerDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by rygn on 6/9/17.
 */

public class FirebaseBeerViewHolder extends RecyclerView.ViewHolder{
    View mView;
    Context mContext;

    public FirebaseBeerViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindBeer(final Beer beer) {
        TextView mNameView = (TextView) mView.findViewById(R.id.beerListBeer);
        TextView mStyleView = (TextView) mView.findViewById(R.id.beerListStyle);
        TextView mAbvView = (TextView) mView.findViewById(R.id.beerListABV);
        TextView mBreweryView = (TextView) mView.findViewById(R.id.beerListBrewery);
        ImageView mGlassImage = (ImageView) mView.findViewById(R.id.beerListGlassImage);
        TextView mDescriptionView = (TextView) mView.findViewById(R.id.beerListDescription);
//        final TextView mBreweryUrlView = (TextView) mView.findViewById(R.id.hiddenUrl);

        mBreweryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(beer.getBreweryUrl()));
                mContext.startActivity(webIntent);
            }
        });

        mNameView.setText(beer.getName());
        mStyleView.setText(beer.getStyle());
        mAbvView.setText(beer.getABV() + "% ABV");
        mBreweryView.setText(beer.getBreweryName());
//        mBreweryUrlView.setText(beer.getBreweryUrl());
        mDescriptionView.setText(beer.getDescription());
        Picasso.with(mContext).load(R.drawable.glass).into(mGlassImage);
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
        } else {
            mGlassImage.setBackgroundColor(Color.parseColor("#fffda0"));
        }


    }
}
