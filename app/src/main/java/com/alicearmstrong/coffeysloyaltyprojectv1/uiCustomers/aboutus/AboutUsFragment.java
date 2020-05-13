package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.aboutus;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alicearmstrong.coffeysloyaltyprojectv1.R;

import uk.co.deanwild.flowtextview.FlowTextView;

public class AboutUsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate( R.layout.fragment_about_us_customer, container, false);


        FlowTextView flowTextView = (FlowTextView) root.findViewById(R.id.ftv);
        String about_us = getString(R.string.about_us);
        flowTextView.setText(about_us);
        flowTextView.setColor(Color.WHITE);


        return root;
    }
}