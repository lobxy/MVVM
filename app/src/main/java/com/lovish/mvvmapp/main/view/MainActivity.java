package com.lovish.mvvmapp.main.view;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.lovish.mvvmapp.R;
import com.lovish.mvvmapp.databinding.ActivityMainBinding;
import com.lovish.mvvmapp.main.MainActBR;
import com.lovish.mvvmapp.main.adapter.IUserAdapterClickEvents;
import com.lovish.mvvmapp.main.adapter.UserDataAdapter;
import com.lovish.mvvmapp.main.model.UserData;
import com.lovish.mvvmapp.main.view_models.UserDataFileOperationsService;
import com.lovish.mvvmapp.main.view_models.ViewModelActivityMain;
import com.lovish.mvvmapp.sign_up.SignUpActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IUserAdapterClickEvents {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private ViewModelActivityMain viewModel;
    private UserDataAdapter userDataAdapter;
    private MainActBR mainActBR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActBR = new MainActBR();

        Intent intent = new Intent(this, UserDataFileOperationsService.class);
        intent.putExtra("operationType",1);
        startService(intent);

        setOnClicks();
        setViewModels();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(mainActBR, new IntentFilter("userDataBR"));
    }

    private void setOnClicks() {
        binding.setClickEvents(new ClickAction());
    }

    private void setViewModels() {
        viewModel = new ViewModelProvider(this).get(ViewModelActivityMain.class);

        viewModel.userDataList.observe(this, new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> userData) {
                if (userData.isEmpty()) {
                    showNoDataFound(true);
                } else {
                    showNoDataFound(false);
                    initRecyclerView(userData);
                }
            }
        });

        viewModel.initialiseData();
    }

    private void showNoDataFound(boolean showData) {
        if (showData) {
            binding.idAddData.setVisibility(View.VISIBLE);
            binding.textView2.setVisibility(View.VISIBLE);
            binding.floatingActionButton.hide();
            binding.idRecyclerView.setVisibility(View.GONE);
        } else {
            binding.idAddData.setVisibility(View.GONE);
            binding.textView2.setVisibility(View.GONE);
            binding.floatingActionButton.show();
            binding.idRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void initRecyclerView(List<UserData> userData) {
        if (userDataAdapter == null) {
            userDataAdapter = new UserDataAdapter(userData, this);
            binding.idRecyclerView.setAdapter(userDataAdapter);
        } else {
            userDataAdapter.updateList(userData);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mainActBR);
    }

    @Override
    public void editClickAction(View view, int position) {
        Intent intent = new Intent(view.getContext(), SignUpActivity.class);
        if (position != -1) {
            intent.putExtra("data", viewModel.getUserData(position));
        }
        intent.putExtra("dataSize", userDataAdapter.getItemCount() + 1);
        startActivityForResult(intent, 121);
    }

    @Override
    public void deleteClickAction(View view, int position) {
        viewModel.deleteUserData(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModel.onActivityResult(requestCode, resultCode, data);
    }

    public class ClickAction {

        public void addUserButtonClicked(View view) {
            editClickAction(view, -1);
        }

    }

}