package com.alicearmstrong.coffeysloyaltyprojectv1.uiOwner.chatOwner;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alicearmstrong.coffeysloyaltyprojectv1.R;

import java.util.ArrayList;

public class ChatOwnerFragment extends Fragment {

    private ChatOwnerViewModel chatOwnerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        chatOwnerViewModel = ViewModelProviders.of(this).get(ChatOwnerViewModel.class);
        View root = inflater.inflate( R.layout.fragment_chat_owner, container, false);


        chatOwnerViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

}