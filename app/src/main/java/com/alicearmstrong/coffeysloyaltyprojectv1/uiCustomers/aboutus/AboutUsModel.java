package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.aboutus;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class AboutUsModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AboutUsModel()
    {

    }

    public LiveData<String> getText() {
        return mText;
    }
}