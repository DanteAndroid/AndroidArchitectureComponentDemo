package com.danteandroid.aacdemo.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class UserEntity {
    @SerializedName("login")
    @NonNull @PrimaryKey
    private String name;

    private int id;

    @Ignore
    public UserEntity(@NonNull String name) {
        this.name = name;
    }

    public UserEntity(@NonNull String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
