package com.danteandroid.aacdemo.net;

import com.danteandroid.aacdemo.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {
    private static GithubApi sApi;

    public static GithubApi getGithubApi() {
        if (sApi == null) {
            sApi = createService(GithubApi.BASE_URL, GithubApi.class);
        }
        return sApi;
    }

    private static <T> T createService(String baseUrl, Class<T> serviceClass) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BASIC : HttpLoggingInterceptor.Level.NONE);
        Retrofit.Builder builder = new Retrofit.Builder()
                .client(new OkHttpClient().newBuilder().addInterceptor(logging).build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit sRetrofit = builder.build();
        return sRetrofit.create(serviceClass);
    }

}
