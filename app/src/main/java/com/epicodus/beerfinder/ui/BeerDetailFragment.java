package com.epicodus.beerfinder.ui;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
    @Bind(R.id.beerListGlassImage) ImageView mGlassImage;

    private Beer mBeer;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

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
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer_detail, container, false);
        ButterKnife.bind(this, view);

        mNameView.setText(mBeer.getName());
        mStyleVeiw.setText(mBeer.getStyle());
        mAbvView.setText(mBeer.getABV() + "% ABV");
        mDescriptionView.setText(mBeer.getDescription());

        if (!mBeer.getSRM().equals("")) {
            mGlassImage.setBackgroundColor(Color.parseColor("#" + mBeer.getSRM()));
        } else if (!mBeer.getStyleSrmMin().equals("") && !mBeer.getStyleSrmMax().equals("")){
            float styleSrmTotal = (float) Integer.parseInt(mBeer.getStyleSrmMin()) + Integer.parseInt(mBeer.getStyleSrmMax());
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

        mSaveButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveButton && mUser != null) {
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

        } else if (v == mSaveButton) {
            Toast.makeText(getContext(), "Please log in or create an account.", Toast.LENGTH_SHORT).show();
        }
    }
}
