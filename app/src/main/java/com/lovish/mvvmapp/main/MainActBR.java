package com.lovish.mvvmapp.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;

import com.lovish.mvvmapp.main.model.UserData;

import java.util.ArrayList;

public class MainActBR extends BroadcastReceiver {

    MutableLiveData<ArrayList<UserData>> userData = new MutableLiveData<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        userData.postValue(bundle.getParcelableArrayList("userData"));
    }

}
