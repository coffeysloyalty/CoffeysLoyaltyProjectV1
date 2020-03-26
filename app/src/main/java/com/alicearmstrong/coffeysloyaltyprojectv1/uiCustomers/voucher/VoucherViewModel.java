package com.alicearmstrong.coffeysloyaltyprojectv1.uiCustomers.voucher;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class VoucherViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VoucherViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}