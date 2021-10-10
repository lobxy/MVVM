package com.lovish.mvvmapp.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lovish.mvvmapp.BR;
import com.lovish.mvvmapp.R;
import com.lovish.mvvmapp.databinding.InnerUserDataBinding;
import com.lovish.mvvmapp.main.model.UserData;

import java.util.List;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.ViewHolder> {

    private List<UserData> userDataList;
    private IUserAdapterClickEvents adapterClickEvents;

    public UserDataAdapter(List<UserData> userDataList, IUserAdapterClickEvents adapterClickEvents) {
        this.userDataList = userDataList;
        this.adapterClickEvents = adapterClickEvents;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InnerUserDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.inner_user_data, parent, false);
        return new ViewHolder(binding, adapterClickEvents);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(userDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    public void updateList(List<UserData> userDataList) {
        this.userDataList = userDataList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private IUserAdapterClickEvents adapterClickEvents;
        private InnerUserDataBinding binding;

        public ViewHolder(InnerUserDataBinding binding, IUserAdapterClickEvents adapterClickEvents) {
            super(binding.getRoot());
            this.adapterClickEvents = adapterClickEvents;
            this.binding = binding;

            setClickEvents();
        }

        private void setClickEvents() {
            binding.setClickEvent(new ClickAction());
        }

        public void bind(UserData userData) {
            binding.setVariable(BR.userData, userData);
            binding.executePendingBindings();
        }

        public class ClickAction {

            public void editClickAction(View view) {
                if (adapterClickEvents != null) adapterClickEvents.editClickAction(view, getAdapterPosition());
            }

            public void deleteClickAction(View view) {
                if (adapterClickEvents != null) adapterClickEvents.deleteClickAction(view, getAdapterPosition());
            }

        }

    }

}
