package com.epicodus.beerfinder.adapters;

import android.content.Context;
import android.content.Intent;
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

public class FirebaseBeerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 120;
    private static final int MAX_HEIGHT = 120;
    View mView;
    Context mContext;

    public FirebaseBeerViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindBeer(Beer beer) {
        TextView mNameView = (TextView) mView.findViewById(R.id.beerListBeer);
        TextView mStyleView = (TextView) mView.findViewById(R.id.beerListStyle);
        TextView mAbvView = (TextView) mView.findViewById(R.id.beerListABV);
        TextView mBreweryView = (TextView) mView.findViewById(R.id.beerListBrewery);
        ImageView mGlassImage = (ImageView) mView.findViewById(R.id.beerListGlassImage);
        TextView mDescriptionView = (TextView) mView.findViewById(R.id.beerListDescription);

        mNameView.setText(beer.getName());
        mStyleView.setText(beer.getStyle());
        mAbvView.setText(beer.getABV() + "% ABV");
        mBreweryView.setText(beer.getBreweryName());
        Picasso.with(mContext).load(R.drawable.glass).resize(MAX_WIDTH, MAX_HEIGHT).centerCrop().into(mGlassImage);
        mDescriptionView.setText(beer.getDescription());
    }

    @Override
    public void onClick(View v) {
//        final ArrayList<Beer> beers = new ArrayList<>();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BEERS);
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snap : dataSnapshot.getChildren()) {
//                    beers.add(snap.getValue(Beer.class));
//                }
//                int itemPosition = getLayoutPosition();
//
//                Intent intent = new Intent(mContext, BeerDetailActivity.class);
//                intent.putExtra("position", itemPosition + "");
//                intent.putExtra("beers", Parcels.wrap(beers));
//
//                mContext.startActivity(intent);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }
}
