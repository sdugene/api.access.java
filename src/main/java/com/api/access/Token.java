package com.api.access;

/**
 * Created by Sebastien Dugene on 30/05/2017.
 */
class Token
{
    private String token;

    /**
     * Default constructor
     */
    Token()
    {
        this.token = null;
    }

    /**
     * Set Token
     * Call checkToken method
     *
     * @param token the token value
     */
    Token(String token)
    {
        if (this.checkToken(token)) {
            this.token = token;
        }
    }

    /**
     * Return the token
     */
    String getToken()
    {
        return this.token;
    }

    /**
     * Check the token content
     *
     * @param token token to check
     */
    private boolean checkToken(String token)
    {
        String pattern = "^Bearer[\\s].*$";
        return token != null && token.matches(pattern);
    }
}