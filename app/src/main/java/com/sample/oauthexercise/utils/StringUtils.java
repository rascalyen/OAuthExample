package com.sample.oauthexercise.utils;

import com.sample.oauthexercise.BuildConfig;


public class StringUtils {


    public static String getAuthorizationURL() {

        return BuildConfig.AUTH_URL + "authorize?client_id=" + BuildConfig.KEY
                + "&redirect_uri=" + BuildConfig.REDIRECT + "&response_type=code" ;
    }

}