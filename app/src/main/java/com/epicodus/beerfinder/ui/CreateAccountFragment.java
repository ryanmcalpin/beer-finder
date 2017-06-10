package com.epicodus.beerfinder.ui;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        getDialog().setTitle("What is?");
        return inflater.inflate(R.layout.fragment_create_account, container, false);
    }

}
