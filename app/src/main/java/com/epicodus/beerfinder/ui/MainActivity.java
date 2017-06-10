package com.epicodus.beerfinder.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.beerfinder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.searchBeerButton) Button mBeerButton;
    @Bind(R.id.searchBreweriesButton) Button mBreweriesButton;
    @Bind(R.id.titleView) TextView mTitleView;
    @Bind(R.id.favoritesButton) Button mFavoritesButton;
    @Bind(R.id.signOutView) TextView mSignOutView;
    @Bind(R.id.welcomeView) TextView mWelcomeView;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface titleFont = Typeface.createFromAsset(getAssets(), "fonts/LANEUP__.ttf");
        mTitleView.setTypeface(titleFont);
        mBeerButton.setTypeface(titleFont);
        mBreweriesButton.setTypeface(titleFont);
        mFavoritesButton.setTypeface(titleFont);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mUser = firebaseAuth.getCurrentUser();
                if (mUser != null) {
                    mWelcomeView.setText("Welcome, " + mUser.getDisplayName() + "!");
                    mSignOutView.setText("Log Out");
                } else {
                    mWelcomeView.setText("Welcome, Guest!");
                    mSignOutView.setText("Log In");
                }
            }
        };

        mBeerButton.setOnClickListener(this);
        mBreweriesButton.setOnClickListener(this);
        mFavoritesButton.setOnClickListener(this);
        mSignOutView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == mBeerButton) {
            if (mUser != null) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("endpoint", "beers");
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Please log in or create an account", Toast.LENGTH_SHORT).show();
            }
        }
        if (v == mBreweriesButton) {
            if (mUser != null) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("endpoint", "breweries");
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Please log in or create an account", Toast.LENGTH_SHORT).show();
            }
        }
        if (v == mFavoritesButton) {
            if (mUser != null) {
                Intent intent = new Intent(MainActivity.this, FavoriteBeersActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Please log in or create an account", Toast.LENGTH_SHORT).show();
            }
        }
        if (v == mSignOutView) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
