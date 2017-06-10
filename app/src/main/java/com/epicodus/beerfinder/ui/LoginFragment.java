package com.epicodus.beerfinder.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.beerfinder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends DialogFragment {
    FirebaseAuth mAuth;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        getDialog().setTitle("Whyy");

        final EditText nameView = (EditText) rootView.findViewById(R.id.nameInput);
        final EditText emailView = (EditText) rootView.findViewById(R.id.emailInput);
        final EditText passwordView = (EditText) rootView.findViewById(R.id.passwordInput);
        final EditText passConfView = (EditText) rootView.findViewById(R.id.passConfInput);
        TextView signUpView = (TextView) rootView.findViewById(R.id.signUpLinkView);
        Button logInButton = (Button) rootView.findViewById(R.id.logInButton);

        mAuth = FirebaseAuth.getInstance();


        signUpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                CreateAccountFragment createAccountFragment = new CreateAccountFragment();
                createAccountFragment.show(fm, "What");
                dismiss();
            }
        });

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final String name = nameView.getText().toString().trim();
//                final String email = emailView.getText().toString().trim();
//                String password = passwordView.getText().toString().trim();
//                String passConf = passConfView.getText().toString().trim();
//
//                mAuth.createUserWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    Log.d("", "Authentication successful");
//                                } else {
//                                    Log.d("", "Authentication successful");
//
//                                }
//                            }
//                        });

            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }

        });
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}


