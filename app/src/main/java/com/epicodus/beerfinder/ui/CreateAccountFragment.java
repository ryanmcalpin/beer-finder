package com.epicodus.beerfinder.ui;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.beerfinder.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountFragment extends DialogFragment {


    public CreateAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_account, container, false);
        getDialog().setTitle("What is?");

        TextView signInView = (TextView) rootView.findViewById(R.id.signInLinkView);

        signInView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                LoginFragment loginFragment = new LoginFragment();
                loginFragment.show(fm, "Huh?");
                dismiss();
            }
        });
        return rootView;
    }

}
