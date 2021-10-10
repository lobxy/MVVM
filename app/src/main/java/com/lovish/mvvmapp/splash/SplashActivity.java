package com.lovish.mvvmapp.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lovish.mvvmapp.R;
import com.lovish.mvvmapp.databinding.ActivitySplashBinding;
import com.lovish.mvvmapp.main.view.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    private SplashViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        initViewModel();
        initObservers();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(SplashViewModel.class);
        viewModel.startHandler();
    }

    private void initObservers() {
        Observer<SplashViewModel> observer = new Observer<SplashViewModel>() {
            @Override
            public void onChanged(SplashViewModel splashViewModel) {
                startHomeActivity();
            }
        };
        viewModel.liveData.observe(this, observer);
    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}