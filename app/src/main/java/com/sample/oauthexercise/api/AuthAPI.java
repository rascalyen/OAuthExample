package com.sample.oauthexercise.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface AuthAPI {

    @POST("token")
    @FormUrlEncoded
    Call<ResponseBody> getToken(@Field("client_id") String key, @Field("client_secret") String secret,
                                @Field("redirect_uri") String redirect, @Field("code") String code,
                                @Field("grant_type") String type);

}