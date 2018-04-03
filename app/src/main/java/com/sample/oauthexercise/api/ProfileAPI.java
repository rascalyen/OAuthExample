package com.sample.oauthexercise.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ProfileAPI {

    @GET("account/user")
    Call<ResponseBody> getUserAccount(@Query("access_token") String token);

}