package com.lovish.mvvmapp.main.view_models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelActivityMain extends ViewModel {

    public MutableLiveData<Integer> dataSize = new MutableLiveData<>();

    public void dataSetChanged(int data) {
        dataSize.postValue(data);
    }

}
