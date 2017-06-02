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

    public Map<String, Object> data(String tableName)
    {
        String test = httpConnection.run(url+"/data/"+tableName, "{}");
        System.out.println(test);
        Map<String, Object> result = new HashMap();
        return result;
    }

    public Map<String, Object> data(String tableName, Long id)
    {
        String test = httpConnection.run(url+"/data/"+tableName+"/"+Long.toString(id), "{}");
        System.out.println(test);
        Map<String, Object> result = new HashMap();
        return result;
    }

    public Map<String, Object> api(String controller, String method, String params)
    {
        Map<String, Object> result = new HashMap();
        return result;
    }

    public Map<String, Object> mailer(String method, String params)
    {
        String controller = "mail";
        Map<String, Object> result = new HashMap();
        return result;
    }
}