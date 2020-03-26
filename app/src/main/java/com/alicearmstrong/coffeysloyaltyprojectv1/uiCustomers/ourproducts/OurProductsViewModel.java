package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.ourproducts;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class OurProductsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OurProductsViewModel() {

    }

    public LiveData<String> getText()
    {
        return mText;
    }
}