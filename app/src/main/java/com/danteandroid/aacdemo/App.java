package com.danteandroid.aacdemo;

import android.app.Application;

import com.danteandroid.aacdemo.database.DataRepository;
import com.danteandroid.aacdemo.database.UserDatabase;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public UserDatabase getDatabase(){
        return UserDatabase.getInstance(this);
    }

    public DataRepository getRepository() {
        return DataRepository.getsInstance(getDatabase());
    }
}
