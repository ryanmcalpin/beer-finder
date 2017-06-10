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
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    mWelcomeView.setText("Welcome, " + user.getDisplayName() + "!");
                } else {

                }
            }
        };

        mBeerButton.setOnClickListener(this);
        mBreweriesButton.setOnClickListener(this);
        mFavoritesButton.setOnClickListener(this);
        mSignOutView.setOnClickListener(this);

//        FragmentManager fm = getSupportFragmentManager();
//        LoginFragment loginFragment = new LoginFragment();
//        loginFragment.show(fm, "Whaaa");
    }

    @Override
    public void onClick(View v) {
        if (v == mBeerButton) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            intent.putExtra("endpoint", "beers");
            startActivity(intent);
        }
        if (v == mBreweriesButton) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            intent.putExtra("endpoint", "breweries");
            startActivity(intent);
        }
        if (v == mFavoritesButton) {
            Intent intent = new Intent(MainActivity.this, FavoriteBeersActivity.class);
            startActivity(intent);
        }
        if (v == mSignOutView) {
            FirebaseAuth.getInstance().signOut();
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

    //    public static void createNewUser(String name, String email, String password, String passConf) {
//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Log.d("onComplete: ", "YAAAA");
//                        } else {
//                            Log.d("onComplete: ", "NOOOOO");
//                        }
//                    }
//                });
//    }
}
