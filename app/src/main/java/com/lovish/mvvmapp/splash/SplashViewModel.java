package com.lovish.mvvmapp.splash;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SplashViewModel extends ViewModel {

    public static final int DELAY_TIME = 1000;

    public MutableLiveData<SplashViewModel> liveData = new MutableLiveData<>();

    public void startHandler() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                liveData.postValue(null);
            }
        }, DELAY_TIME);
    }

}

