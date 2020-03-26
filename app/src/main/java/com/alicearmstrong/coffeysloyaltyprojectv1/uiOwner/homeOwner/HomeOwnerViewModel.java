package com.alicearmstrong.coffeysloyaltyprojectv1.uiOwner.homeOwner;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class HomeOwnerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeOwnerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}