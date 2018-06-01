package com.danteandroid.aacdemo.database;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.danteandroid.aacdemo.entity.UserEntity;
import com.danteandroid.aacdemo.net.WebService;
import com.danteandroid.aacdemo.util.AppExecutors;

import java.io.IOException;

public class DataRepository {
    private static final String TAG = "DataRepository";
    private static DataRepository sInstance;
    private final UserDatabase userDatabase;


    private DataRepository(UserDatabase database) {
        userDatabase = database;
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
                AppExecutors.addDelay();
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

        return userDatabase.userDao().load(userName);
    }
}
