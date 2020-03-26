package com.alicearmstrong.coffeysloyaltyprojectv1.uiOwner.homeOwner;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.alicearmstrong.coffeysloyaltyprojectv1.R;
import com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.home.HomeViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeOwnerFragment extends Fragment
{

    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private String userID;
    private TextView txtWelcomeBack;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate( R.layout.fragment_home_owner, container, false);
        txtWelcomeBack = root.findViewById(R.id.txtWelcomeBack);
        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Customers").child(userID);

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s)
            {
                txtWelcomeBack.setText("Hello, Philip" + "\n \n" + "Welcome back to Coffey's Loyalty! ");

            }

        });



        return root;
    }



}