package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.logout;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alicearmstrong.coffeysloyaltyprojectv1.LoginScreen;
import com.alicearmstrong.coffeysloyaltyprojectv1.R;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragment extends Fragment {

    private FirebaseAuth firebaseAuth;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        firebaseAuth = FirebaseAuth.getInstance();
        View root = inflater.inflate( R.layout.fragment_logout, container, false);

        firebaseAuth.signOut();
        Toast.makeText(getActivity(), "You have been sucessfully logged out.", Toast.LENGTH_SHORT).show();
        Intent logout = new Intent(getActivity(), LoginScreen.class);
        startActivity(logout);
        Log.d("Logout Fragment", "Successfully logged out.");

        return root;
    }
}