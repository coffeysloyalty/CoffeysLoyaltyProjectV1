package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.faq;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class FAQViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FAQViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}