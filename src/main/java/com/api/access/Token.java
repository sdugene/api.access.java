package com.api.access;

import java.util.Date;

public class Token {
    private String token;
    private Date date;

    public Token(String token)
    {
        this.token = token;
        this.date = new Date();
    }

    public String getToken()
    {
        return this.token;
    }
}