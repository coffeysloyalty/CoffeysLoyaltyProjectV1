package com.alicearmstrong.coffeysloyaltyprojectv1.uiOwner.barcodeScanerOwner;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class BarcodeScannerOwnerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BarcodeScannerOwnerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}