package com.epicodus.beerfinder.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.beerfinder.Constants;
import com.epicodus.beerfinder.R;
import com.epicodus.beerfinder.models.Beer;
import com.epicodus.beerfinder.models.Brewery;
import com.epicodus.beerfinder.services.BDBService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeerDetailFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.beerListBeer) TextView mNameView;
    @Bind(R.id.beerListStyle) TextView mStyleVeiw;
    @Bind(R.id.beerListABV) TextView mAbvView;
    @Bind(R.id.beerListDescription) TextView mDescriptionView;
    @Bind(R.id.saveBeerButton) Button mSaveButton;

    private Beer mBeer;

    public static BeerDetailFragment newInstance(Beer beer) {
        BeerDetailFragment beerDetailFragment = new BeerDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("beer", Parcels.wrap(beer));
        beerDetailFragment.setArguments(args);
        return beerDetailFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBeer = Parcels.unwrap(getArguments().getParcelable("beer"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer_detail, container, false);
        ButterKnife.bind(this, view);

        mNameView.setText(mBeer.getName());
        mStyleVeiw.setText(mBeer.getStyle());
        mAbvView.setText(mBeer.getABV() + "% ABV");
        mDescriptionView.setText(mBeer.getDescription());

        mSaveButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveButton) {
            final BDBService bdbService = new BDBService();
            bdbService.findResults(mBeer.getBreweryId(), "breweryById", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Brewery brewery = bdbService.processBrewery(response);
//                    mBeer.setBreweryId();
//                    mBeer.setBreweryLocation(brewery.getLocation());
                    mBeer.setBreweryName(brewery.getName());
                    mBeer.setBreweryUrl(brewery.getUrl());
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = user.getUid();
                    DatabaseReference beerRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BEERS).child(uid);
                    DatabaseReference pushRef = beerRef.push();
                    String pushId = pushRef.getKey();
                    mBeer.setPushId(pushId);
                    pushRef.setValue(mBeer);
//                    Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}
