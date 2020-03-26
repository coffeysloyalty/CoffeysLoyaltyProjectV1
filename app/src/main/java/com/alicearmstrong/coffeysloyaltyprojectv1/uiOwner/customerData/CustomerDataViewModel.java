package com.alicearmstrong.coffeysloyaltyprojectv1.uiOwner.customerData;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


public class CustomerDataViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CustomerDataViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}