package com.danteandroid.aacdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.danteandroid.aacdemo.App;
import com.danteandroid.aacdemo.database.DataRepository;
import com.danteandroid.aacdemo.entity.UserEntity;

public class UserViewModel extends AndroidViewModel {
    private static final String TAG = "UserViewModel";
    //    public ObservableField<UserEntity> user = new ObservableField<>();
    private String userName;
    private MutableLiveData<UserEntity> user = new MutableLiveData<>();
    private LiveData<UserEntity> userEntity;
    private DataRepository dataRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        dataRepository = ((App) application).getRepository();
    }


    public void setUserName(String name) {
        userName = name;
        userEntity = dataRepository.getUserEntity(name);
    }


    public LiveData<UserEntity> getUserEntity() {
        return userEntity;
    }

}
