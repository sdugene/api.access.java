package com.api.access;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiAccess
{
    private String url;
    private Token token;
    private HttpConnection httpConnection;

    private ApiAccess()
    {
        httpConnection = new HttpConnection();
    }

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

    public Token getToken()
    {
        return this.token;
    }

    public boolean login(String username, String password, String device)
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        jsonObject.put("device", device);
        String params = jsonObject.toJSONString();
        String token = httpConnection
                .setMethod("POST")
                .header(url+"api/account/user/login", params, "Authorization");

        this.token = new Token(token);
        if (this.token != null) {
            return true;
        }
        return false;
    }

    public boolean token(String device)
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("device", device);
        String params = jsonObject.toJSONString();
        String token = httpConnection
                .setMethod("POST")
                .header(url+"api/account/user/login", params, "Authorization");

        this.token = new Token(token);
        if (this.token != null) {
            return true;
        }
        return false;

    }

    public String data(String tableName)
    {
        return httpConnection
                .setMethod("GET")
                .response(url+"data/"+tableName, "{}", token.getToken(), "Authorization");
    }

    public String data(String tableName, Long id)
    {
        return httpConnection
                .setMethod("GET")
                .response(url+"/data/"+tableName+"/"+Long.toString(id), "{}", token.getToken(), "Authorization");
    }

    public String api(String controller, String method, String params)
    {
        return httpConnection
                .setMethod("POST")
                .response(url+"api/"+controller+"/"+method, params, token.getToken(), "Authorization");
    }

    public String mailer(String method, String params)
    {
        return httpConnection
                .setMethod("POST")
                .response(url+"api/mailer/"+method, params, token.getToken(), "Authorization");
    }
}