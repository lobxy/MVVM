package com.lovish.mvvmapp.sign_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lovish.mvvmapp.R;
import com.lovish.mvvmapp.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private ViewModelSignUpActivity viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        initViewModels();
        initObservers();
        initClickAction();
    }

    private void initViewModels() {
        viewModel = new ViewModelProvider(this).get(ViewModelSignUpActivity.class);
        viewModel.prePopulateData(getIntent());
    }

    private void initObservers() {
        viewModel.validate().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.isEmpty()) {
                    registerUser();
                } else {
                    showToast(s);
                }
            }
        });
    }

    private void registerUser() {
        Intent intent = new Intent();
        intent.putExtra("data", viewModel.userData);
        setResult(121, intent);
        finish();
    }

    private void initClickAction() {
        binding.setViewModel(viewModel);
        binding.setClickAction(new ClickAction());
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public class ClickAction {

        public void userFormSubmit(View view) {
            viewModel.validateUserData(view);
        }
    }
}