package com.danteandroid.aacdemo.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danteandroid.aacdemo.R;
import com.danteandroid.aacdemo.databinding.FragmentUserBinding;
import com.danteandroid.aacdemo.entity.UserEntity;
import com.danteandroid.aacdemo.viewmodel.UserViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {
    public static final String USER_NAME = "name";
    private static final String TAG = "UserFragment";
    private UserViewModel viewModel;
    private FragmentUserBinding binding;

    public static UserFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString(USER_NAME, name);
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            String userName = getArguments().getString(USER_NAME);
            viewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
            viewModel.setUserName(userName);

            binding.setUserViewModel(viewModel);
            viewModel.getMediatorLiveData().observe(getActivity(), new Observer<UserEntity>() {
                @Override
                public void onChanged(@Nullable UserEntity userEntity) {
                    if (userEntity != null) {
                        viewModel.setUser(userEntity);
                    }
                }
            });
        }
    }
}
