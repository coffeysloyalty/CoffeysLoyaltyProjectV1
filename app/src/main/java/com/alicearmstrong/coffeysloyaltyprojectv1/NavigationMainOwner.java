package com.alicearmstrong.coffeysloyaltyprojectv1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.logout.LogoutFragment;
import com.alicearmstrong.coffeysloyaltyprojectv1.uiOwner.barcodeScanerOwner.BarcodeScannerOwnerFragment;
import com.alicearmstrong.coffeysloyaltyprojectv1.uiOwner.chatOwner.ChatOwnerFragment;
import com.alicearmstrong.coffeysloyaltyprojectv1.uiOwner.customerData.CustomerDataFragment;
import com.alicearmstrong.coffeysloyaltyprojectv1.uiOwner.homeOwner.HomeOwnerFragment;


public class NavigationMainOwner extends AppCompatActivity
{
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_main_owner);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        toolbar.setTitle("Shop");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   // toolbar.setTitle("Home");
                    fragment = new HomeOwnerFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_barcode_scanner:
                   // toolbar.setTitle("Barcode Scanner");
                    fragment = new BarcodeScannerOwnerFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_chat:
                    //toolbar.setTitle("Chat");
                    fragment = new ChatOwnerFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_customer_data:
               //     toolbar.setTitle("Customer's Data");
                    fragment = new CustomerDataFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_logout:
                    //     toolbar.setTitle("Customer's Data");
                    fragment = new LogoutFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment)
    {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}