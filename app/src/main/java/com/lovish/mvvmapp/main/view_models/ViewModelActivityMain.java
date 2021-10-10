package com.lovish.mvvmapp.main.view_models;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lovish.mvvmapp.main.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class ViewModelActivityMain extends ViewModel {

    public MutableLiveData<List<UserData>> userDataList = new MutableLiveData<>();

    private List<UserData> userData = new ArrayList<>();

    public void initialiseData() {
        userData.clear();
        userData.add(new UserData(1, "Lovish", "Pandey", true, ""));
        userData.add(new UserData(2, "Lovish1", "Pandey1", false, ""));
        userData.add(new UserData(3, "Lovish2", "Pandey2", false, ""));
        userDataList.postValue(userData);
    }

    public UserData getUserData(int position) {
        return userData.get(position);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 121 && data != null) {
            UserData us = (UserData) data.getSerializableExtra("data");

            boolean foundThing = false;

            for (int i = 0; i < userData.size(); i++) {
                if (userData.get(i).getId() == us.getId()) {
                    userData.set(i, us);
                    foundThing = true;
                    break;
                }
            }

            if (!foundThing) userData.add(us);

            userDataList.postValue(userData);
        }
    }

    public void deleteUserData(int position) {
        userData.remove(position);
        userDataList.postValue(userData);
    }
}
