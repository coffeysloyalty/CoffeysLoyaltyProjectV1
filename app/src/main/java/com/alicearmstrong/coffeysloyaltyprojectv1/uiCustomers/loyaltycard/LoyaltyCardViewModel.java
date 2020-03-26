package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.loyaltycard;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class LoyaltyCardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LoyaltyCardViewModel()
    {
        mText = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }
}