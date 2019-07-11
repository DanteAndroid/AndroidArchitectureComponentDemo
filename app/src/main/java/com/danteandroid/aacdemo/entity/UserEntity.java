package com.danteandroid.aacdemo.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity
public class UserEntity {
    @SerializedName("login")
    @NonNull
    @PrimaryKey
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

    @Override
    public String toString() {
        return "UserEntity{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
