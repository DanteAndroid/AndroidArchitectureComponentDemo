package com.danteandroid.aacdemo.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.util.Log;

import com.danteandroid.aacdemo.entity.UserEntity;
import com.danteandroid.aacdemo.net.WebService;
import com.danteandroid.aacdemo.util.AppExecutors;

import java.io.IOException;
import java.util.List;

public class DataRepository {
    private static final String TAG = "DataRepository";
    private static DataRepository sInstance;
    private final UserDatabase userDatabase;
    public MediatorLiveData<List<UserEntity>> mMediatorLiveData;

    private DataRepository(UserDatabase database) {
        userDatabase = database;
        mMediatorLiveData = new MediatorLiveData<>();

    }

    public static DataRepository getsInstance(final UserDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) sInstance = new DataRepository(database);
            }
        }

        return sInstance;
    }

    public LiveData<UserEntity> getUserEntity(final String userName) {
        AppExecutors.getsInstance().getNetworkIO().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    UserEntity entity = WebService.getGithubApi().getUser(userName).execute().body();
                    if (entity == null) {
                        entity = new UserEntity(userName, (int) System.currentTimeMillis());
                    }
                    Log.d(TAG, "run: insert " + entity.getName() + entity.getId());
                    userDatabase.userDao().insert(entity);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return loadUser(userName);
    }

    public LiveData<UserEntity> loadUser(String name) {
        return userDatabase.userDao().load(name);
    }
}
