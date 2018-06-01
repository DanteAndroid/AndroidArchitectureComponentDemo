package com.danteandroid.aacdemo.net;

import com.danteandroid.aacdemo.entity.UserEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApi {
    String BASE_URL = "https://api.github.com/";

    @GET("users/{user}")
    Call<UserEntity> getUser(@Path("user") String userName);
}
