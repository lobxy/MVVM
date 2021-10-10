package com.lovish.mvvmapp.main.adapter;

import android.view.View;

public interface IUserAdapterClickEvents {

    void editClickAction(View view, int position);

    void deleteClickAction(View view, int position);

}
