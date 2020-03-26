package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.location;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class LocationViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LocationViewModel() {
        mText = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }
}