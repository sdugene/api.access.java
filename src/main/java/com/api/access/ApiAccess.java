package com.api.access;

import org.json.simple.JSONObject;

/**
 * Created by Sebastien Dugene on 30/05/2017.
 */
public class ApiAccess
{
    private String url;
    private Token token;
    private HttpConnection httpConnection;

    /**
     * Default constructor
     */
    private ApiAccess()
    {
        httpConnection = new HttpConnection();
    }

    /**
     * Singleton holder
     */
    private static class SingletonHolder
    {
        private final static ApiAccess instance = new ApiAccess();
    }

    /**
     * return apiAccess instance
     */
    public static ApiAccess getInstance()
    {
        return ApiAccess.SingletonHolder.instance;
    }

    /**
     * set api url called
     *
     * @param url api url
     */
    public ApiAccess setUrl(String url)
    {
        this.url = url;
        return this;
    }

    /**
     * Call the login api route
     *
     * @param username user's username
     * @param password user's password
     * @param device user's device unique ID
     */
    public boolean login(String username, String password, String device)
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        String params = jsonObject.toJSONString();
        String token = httpConnection
                .setMethod("POST")
                .header(url+"api/account/user/login", params, device, "Authorization");

        this.token = new Token(token);
        return this.token.getToken() != null;
    }

    /**
     * Call the logout api route
     *
     * @param device user's device unique ID
     */
    public boolean logout(String device)
    {
        String responseCode = httpConnection
                .setMethod("POST")
                .header(url+"api/account/user/logout", "{}", device, "responseCode");
        this.token = new Token();
        return responseCode.equals("200");

    }

    /**
     * Call the token api route
     *
     * @param device user's device unique ID
     */
    public boolean token(String device)
    {
        String token = httpConnection
                .setMethod("POST")
                .header(url+"api/account/user/token", "{}", device, "Authorization");

        this.token = new Token(token);
        return this.token.getToken() != null;

    }

    /**
     * Call the data api route
     *
     * @param tableName database table to return
     */
    public String data(String tableName)
    {
        return httpConnection
                .setMethod("GET")
                .response(url+"data/"+tableName, "{}", token.getToken());
    }

    /**
     * Call the data api route
     *
     * @param tableName database table to return
     * @param id line id to return
     */
    public String data(String tableName, Long id)
    {
        return httpConnection
                .setMethod("GET")
                .response(url+"/data/"+tableName+"/"+Long.toString(id), "{}", token.getToken());
    }

    /**
     * Call the api api route with empty parameters
     *
     * @param controller api controller to call
     * @param method controller method to return
     */
    public String api(String controller, String method)
    {
        return httpConnection
                .setMethod("POST")
                .response(url+"api/"+controller+"/"+method, "{}", token.getToken());
    }

    /**
     * Call the api api route with parameters
     *
     * @param controller api controller to call
     * @param method controller method to return
     * @param params post params sent
     */
    public String api(String controller, String method, String params)
    {
        return httpConnection
                .setMethod("POST")
                .response(url+"api/"+controller+"/"+method, params, token.getToken());
    }

    /**
     * Call the mailer api route
     *
     * @param method mailer method to return
     * @param params post params sent
     */
    public String mailer(String method, String params)
    {
        return httpConnection
                .setMethod("POST")
                .response(url+"api/mailer/"+method, params, token.getToken());
    }
}