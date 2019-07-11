package com.danteandroid.aacdemo.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.danteandroid.aacdemo.App;
import com.danteandroid.aacdemo.database.DataRepository;
import com.danteandroid.aacdemo.entity.UserEntity;

public class UserViewModel extends AndroidViewModel {
    private static final String TAG = "UserViewModel";
    private LiveData<UserEntity> userEntity;
    private DataRepository dataRepository;
    public ObservableField<UserEntity> user;//用于 databinding
    private MediatorLiveData<UserEntity> mMediatorLiveData;//用于观测别的 LiveData

    public MediatorLiveData<UserEntity> getMediatorLiveData() {
        return mMediatorLiveData;
    }

    public UserViewModel(@NonNull Application application) {
        super(application);
        dataRepository = ((App) application).getRepository();
        user = new ObservableField<>();
        mMediatorLiveData = new MediatorLiveData<>();
    }

    public void setUser(UserEntity user) {
        this.user.set(user);
    }

    public void setUserName(String name) {
        if (userEntity != null) {
            mMediatorLiveData.removeSource(userEntity);//不再观测旧的 LiveData
        }
        userEntity = dataRepository.getUserEntity(name);//根据名字获取对应的 LiveData
        mMediatorLiveData.addSource(userEntity, new Observer<UserEntity>() {
            @Override
            public void onChanged(@Nullable UserEntity userEntity) {
                mMediatorLiveData.postValue(userEntity);
            }
        });
    }

}
