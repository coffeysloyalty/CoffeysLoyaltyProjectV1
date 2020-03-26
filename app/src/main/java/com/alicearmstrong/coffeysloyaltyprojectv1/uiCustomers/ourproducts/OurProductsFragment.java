package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.ourproducts;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alicearmstrong.coffeysloyaltyprojectv1.R;


public class OurProductsFragment extends Fragment implements View.OnClickListener
{

    private OurProductsViewModel ourProductsViewModel;
    private Button btnPork, btnBeef, btnPoultry, btnLamb, btnSpecialityMeats, btnCookedProducts;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ourProductsViewModel = ViewModelProviders.of(this).get(OurProductsViewModel.class);
        View root = inflater.inflate( R.layout.fragment_our_products_customers, container, false);
        btnPork = root.findViewById(R.id.btPork);
        btnBeef = root.findViewById(R.id.btBeef);
        btnPoultry = root.findViewById(R.id.btPoultry);
        btnLamb = root.findViewById(R.id.btLamb);
        btnSpecialityMeats = root.findViewById(R.id.btSpecialityMeats);
        btnCookedProducts = root.findViewById(R.id.btCookedProducts);

        btnPork.setOnClickListener(this);
        btnBeef.setOnClickListener(this);
        btnPoultry.setOnClickListener(this);
        btnLamb.setOnClickListener(this);
        btnSpecialityMeats.setOnClickListener(this);
        btnCookedProducts.setOnClickListener(this);

        return root;
    }


    @Override
    public void onClick(View v)
    {
        Fragment fragment = null;

        switch (v.getId()) {
            case R.id.btPork:
                fragment = new PorkFragment();
                replaceFragment(fragment);
                break;

            case R.id.btBeef:
                fragment = new BeefFragment();
                replaceFragment(fragment);
                break;

            case R.id.btPoultry:
                fragment = new PoultryFragment();
                replaceFragment(fragment);
                break;

           case R.id.btLamb:
                fragment = new LambFragment();
                replaceFragment(fragment);
                break;

                case R.id.btSpecialityMeats:
                fragment = new SpecialityMeatsFragment();
                replaceFragment(fragment);
                break;

            case R.id.btCookedProducts:
                fragment = new CookedProductsFragment();
                replaceFragment(fragment);
                break;

        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}