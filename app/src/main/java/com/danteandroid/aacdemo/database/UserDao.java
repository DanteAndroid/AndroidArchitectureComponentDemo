package com.danteandroid.aacdemo.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.danteandroid.aacdemo.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity entity);

    @Query("select * from UserEntity where name like :userName")
    LiveData<UserEntity> load(String userName);

    @Query("select * from UserEntity")
    LiveData<List<UserEntity>> loadAll();
}
