package com.api.access;

import java.util.HashMap;
import java.util.Map;

public class ApiAccess
{
    private String url;
    private Token token;
    private HttpConnection httpConnection;

    private ApiAccess() {}

    private static class SingletonHolder
    {
        private final static ApiAccess instance = new ApiAccess();
    }

    public static ApiAccess getInstance()
    {
        ApiAccess apiAccess = ApiAccess.SingletonHolder.instance;
        return apiAccess;
    }

    public ApiAccess setUrl(String url)
    {
        this.url = url;
        return this;
    }

    public void login(String username, String Password, String device)
    {
        String token = "jeSuisUnToken";
        this.token = new Token(token);
        //return this.token;
    }

    public Token getToken()
    {
        return this.token;
    }

    public void token(String device)
    {
        String token = "jeSuisUnAncienToken";
        this.token = new Token(token);
        //return this.token;
    }

    public Map<String, Object> data(String params)
    {
        Map<String, Object> result = new HashMap();
        return result;
    }

    public Map<String, Object> api(String params)
    {
        Map<String, Object> result = new HashMap();
        return result;
    }

    public Map<String, Object> mailer(String params)
    {
        Map<String, Object> result = new HashMap();
        return result;
    }
}