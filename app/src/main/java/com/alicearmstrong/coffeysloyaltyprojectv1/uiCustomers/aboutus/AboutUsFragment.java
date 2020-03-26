package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.aboutus;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alicearmstrong.coffeysloyaltyprojectv1.R;

public class AboutUsFragment extends Fragment {

    private AboutUsModel aboutUsModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aboutUsModel = ViewModelProviders.of(this).get(AboutUsModel.class);
        View root = inflater.inflate( R.layout.fragment_about_us_customer, container, false);

        // https://github.com/deano2390/FlowTextView

       // FlowTextView flowTextView = (FlowTextView) root.findViewById(R.id.ftv);
        String about_us = getString(R.string.about_us);
       // flowTextView.setText(about_us);
    //    flowTextView.setColor(R.color.red);


        return root;
    }
}