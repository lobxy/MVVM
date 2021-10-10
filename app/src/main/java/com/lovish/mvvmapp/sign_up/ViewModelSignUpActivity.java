package com.lovish.mvvmapp.sign_up;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lovish.mvvmapp.main.model.UserData;

public class ViewModelSignUpActivity extends ViewModel {

    public UserData userData = new UserData();

    public MutableLiveData<String> validationErrorString = new MutableLiveData<>();

    public MutableLiveData<String> validate() {
        return validationErrorString;
    }

    public void validateUserData(View view) {
        String s;
        if (userData.getName() == null || userData.getName().isEmpty()) {
            s = "Please enter first name";
        } else if (userData.getLastName() == null || userData.getLastName().isEmpty()) {
            s = "Please enter last name";
        } else {
            s = "";
        }

        validationErrorString.postValue(s);
    }

    public void prePopulateData(Intent data) {
        if (data != null && data.getSerializableExtra("data") != null) {
            userData = (UserData) data.getSerializableExtra("data");
        } else {
            userData.setId(data.getIntExtra("dataSize", -1));
        }
    }

}
