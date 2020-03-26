package com.alicearmstrong.coffeysloyaltyprojectv1.uiOwner.chatOwner;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ChatOwnerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ChatOwnerViewModel() {
        mText = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }
}