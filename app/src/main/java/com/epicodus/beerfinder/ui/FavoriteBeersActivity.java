package com.epicodus.beerfinder.ui;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FavoriteBeersActivity extends AppCompatActivity {
    private DatabaseReference mBeersReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.searchResultsView) RecyclerView mRecyclerView;
    @Bind(R.id.beerListTitle) TextView mTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);

        mTitleView.setText("My Favorites");

        mBeersReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BEERS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Beer, FirebaseBeerViewHolder>(Beer.class, R.layout.beer_list_item, FirebaseBeerViewHolder.class, mBeersReference) {
            @Override
            protected void populateViewHolder(FirebaseBeerViewHolder viewHolder, Beer model, int position) {
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
}
