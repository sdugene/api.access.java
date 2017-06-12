package com.api.access;

import java.util.Date;

public class Token {
    private String token;
    private Date date;

    public Token(String token)
    {
        if (Token.checkToken(token)) {
            this.token = token;
            this.date = new Date();
        }
    }

    public String getToken()
    {
        return this.token;
    }

    public static boolean checkToken(String token)
    {
        String pattern = "^Bearer[\\s].*$";
        if (token != null && token.matches(pattern)) return true;
        return false;
    }
}