package com.danteandroid.aacdemo.util;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {
    private static AppExecutors sInstance;
    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor mainThread;

    private AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    private AppExecutors() {
        this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3), new MainThreadExecutor());
    }

    public static AppExecutors getsInstance() {
        if (sInstance == null) {
            sInstance = new AppExecutors();
        }
        return sInstance;
    }

    public static void addDelay() {
        try {
            Thread.sleep(1000 + 1000 * new Random().nextInt(3));
        } catch (InterruptedException ignored) {

        }
    }

    public Executor getDiskIO() {
        return diskIO;
    }

    public Executor getNetworkIO() {
        return networkIO;
    }

    public Executor getMainThread() {
        return mainThread;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler main = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            main.post(command);
        }
    }
}
