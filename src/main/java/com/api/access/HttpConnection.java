package com.api.access;

public class HttpConnection {
    public String method = "POST";

    public HttpConnection() {}

    public HttpConnection setMethod(String method)
    {
        this.method = method;
        return this;
    }

    public String run()
    {
        return "ok";
    }
}
