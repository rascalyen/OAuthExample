package com.sample.oauthexercise;

import android.app.Application;
import android.content.Context;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


public class OAuthApp extends Application {

    private Retrofit.Builder retroBuilder;

    @Override
    public void onCreate() {
        super.onCreate();

        retroBuilder = new Retrofit.Builder().client(new OkHttpClient());
    }

    public static Retrofit.Builder getRetroBuilder(Context context) {
        return ((OAuthApp) context.getApplicationContext()).retroBuilder;
    }

}