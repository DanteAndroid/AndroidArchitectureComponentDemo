package com.danteandroid.aacdemo.database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.danteandroid.aacdemo.entity.UserEntity;
import com.danteandroid.aacdemo.util.AppExecutors;

@Database(entities = {UserEntity.class}, version = 2, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "aac-db";
    private static final String TAG = "UserDatabase";
    private static UserDatabase sInstance;

    public static UserDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (UserDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static UserDatabase buildDatabase(final Context context) {
        sInstance = Room.databaseBuilder(context, UserDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        AppExecutors.getsInstance().getDiskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).userDao().insert(new UserEntity("test", 110));
                            }
                        });
                    }
                })
                .build();

        return sInstance;
    }

    public abstract UserDao userDao();
}
