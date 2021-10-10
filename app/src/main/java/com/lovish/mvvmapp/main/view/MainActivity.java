package com.lovish.mvvmapp.main.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lovish.mvvmapp.R;
import com.lovish.mvvmapp.databinding.ActivityMainBinding;
import com.lovish.mvvmapp.main.adapter.IUserAdapterClickEvents;
import com.lovish.mvvmapp.main.adapter.UserDataAdapter;
import com.lovish.mvvmapp.main.model.UserData;
import com.lovish.mvvmapp.main.view_models.ViewModelActivityMain;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IUserAdapterClickEvents {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private ViewModelActivityMain viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setOnClicks();
        setViewModels();
    }

    private void setOnClicks() {
        binding.setClickEvents(new ClickAction());
    }

    private void setViewModels() {
        viewModel = new ViewModelProvider(this).get(ViewModelActivityMain.class);
        viewModel.dataSize.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 0) {
                    showNoDataFound(true);
                } else {
                    showNoDataFound(false);
                    initRecyclerView();
                }
            }
        });
    }

    private void showNoDataFound(boolean showData) {
        if (showData) {
            binding.idAddData.setVisibility(View.VISIBLE);
            binding.textView2.setVisibility(View.VISIBLE);
            binding.idRecyclerView.setVisibility(View.GONE);
        } else {
            binding.idAddData.setVisibility(View.GONE);
            binding.textView2.setVisibility(View.GONE);
            binding.idRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void initRecyclerView() {
        List<UserData> userData = new ArrayList<>();
        userData.add(new UserData(1, "Lovish", ""));
        userData.add(new UserData(2, "Lovish1", ""));
        userData.add(new UserData(3, "Lovish2", ""));
        userData.add(new UserData(4, "Lovish3", ""));
        userData.add(new UserData(5, "Lovish4", ""));
        userData.add(new UserData(6, "Lovish5", ""));

        UserDataAdapter userDataAdapter = new UserDataAdapter(userData, this);

        binding.idRecyclerView.setAdapter(userDataAdapter);
    }

    @Override
    public void editClickAction(int position) {
        Toast.makeText(this, "edit clicked: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteClickAction(int position) {
        Toast.makeText(this, "delete clicked: " + position, Toast.LENGTH_SHORT).show();
    }

    public class ClickAction {

        public void addUserButtonClicked(View view) {
            viewModel.dataSize.postValue(0);
//            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
//            startActivity(intent);
        }

        public void userDataChanged(View view) {
            viewModel.dataSize.postValue(1);
        }

    }

}