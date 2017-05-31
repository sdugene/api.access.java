package com.api.access;

import java.util.HashMap;
import java.util.Map;

public class Access
{
    private String url;
    private Token token;
    private HttpConnection httpConnection;

    private Access() {}

    private static class SingletonHolder
    {
        private final static Access instance = new Access();
    }

    public static Access getInstance()
    {
        Access access = Access.SingletonHolder.instance;
        return access;
    }

    public Access setUrl(String url)
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