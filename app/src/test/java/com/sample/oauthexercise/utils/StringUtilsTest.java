package com.sample.oauthexercise.utils;

import org.junit.Test;
import static org.junit.Assert.*;


public class StringUtilsTest {

    @Test
    public void getAuthorizationURL() {

        String authURL = "https://us.battle.net/oauth/authorize?" +
                "client_id=89xgfvvzjb6bj46547g4vtx4v8s8x4z7&redirect_uri=https://myoauth.com&response_type=code";

        assertEquals(authURL, StringUtils.getAuthorizationURL());
    }

}