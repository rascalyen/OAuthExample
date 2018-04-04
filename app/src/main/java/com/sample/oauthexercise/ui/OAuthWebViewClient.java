package com.sample.oauthexercise.ui;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.sample.oauthexercise.BuildConfig;
import com.sample.oauthexercise.OAuthApp;
import com.sample.oauthexercise.api.AuthAPI;
import com.sample.oauthexercise.api.ProfileAPI;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OAuthWebViewClient extends WebViewClient {

    private AuthAPI authAPI;
    private ProfileAPI profileAPI;


    OAuthWebViewClient(Context context) {
        authAPI = OAuthApp.getRetroBuilder(context).baseUrl(BuildConfig.AUTH_URL)
                .build().create(AuthAPI.class);
        profileAPI = OAuthApp.getRetroBuilder(context).baseUrl(BuildConfig.PROFILE_URL)
                .build().create(ProfileAPI.class);
    }


    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        if (url.contains("?code=")) {

            String authCode = Uri.parse(url).getQueryParameter("code");
            Log.i("##########", "Code : " + authCode);

            authAPI.getToken(BuildConfig.KEY, BuildConfig.SECRET, BuildConfig.REDIRECT,
                    authCode, BuildConfig.GRANT_TYPE).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    try {
                        if (response.isSuccessful() ) {

                            JSONObject json = new JSONObject(response.body().string());
                            String tokenStr = json.getString("access_token");
                            findUserAccount(tokenStr, view);

                            Log.i("##########", "Token : " + tokenStr);
                        }
                    }
                    catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    Log.e(OAuthWebViewClient.class.getSimpleName(),
                            "Fail on getToken : " + t.getMessage());
                }
            });
        }

    }

    private void findUserAccount(String tokenStr, WebView view) {

        profileAPI.getUserAccount(tokenStr).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()) {

                        String profileStr = response.body().string();

                        if (view.getContext() instanceof MainActivity) {
                            ((MainActivity) view.getContext()).updateText(profileStr);
                        }

                        Log.i("##########", "Result : " + profileStr);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.e(OAuthWebViewClient.class.getSimpleName(),
                        "Fail on findUserAccount : " + t.getMessage());
            }
        });
    }

}