package com.epicodus.beerfinder.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.epicodus.beerfinder.Constants;
import com.epicodus.beerfinder.R;
import com.epicodus.beerfinder.adapters.FirebaseBeerViewHolder;
import com.epicodus.beerfinder.models.Beer;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FavoriteBeersActivity extends AppCompatActivity {
    private DatabaseReference mBeersReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private ProgressDialog mFirebaseProgressDialog;
    @Bind(R.id.searchResultsView) RecyclerView mRecyclerView;
    @Bind(R.id.beerListTitle) TextView mTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);

        mTitleView.setText("My Favorites");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        createFirebaseProgressDialog();
        mBeersReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BEERS).child(uid);
        setUpFirebaseAdapter();
    }


    private void setUpFirebaseAdapter() {
        mFirebaseProgressDialog.show();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Beer, FirebaseBeerViewHolder>(Beer.class, R.layout.beer_list_item, FirebaseBeerViewHolder.class, mBeersReference) {
            @Override
            protected void populateViewHolder(FirebaseBeerViewHolder viewHolder, Beer model, int position) {
                mFirebaseProgressDialog.dismiss();
                viewHolder.bindBeer(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    private void createFirebaseProgressDialog() {
        mFirebaseProgressDialog = new ProgressDialog(this);
        mFirebaseProgressDialog.setTitle("Fetching beers...");
        mFirebaseProgressDialog.setMessage("Gathering your favorites from Firebase...");
        mFirebaseProgressDialog.setCancelable(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }
}
