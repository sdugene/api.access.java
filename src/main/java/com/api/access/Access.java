package com.api.access;

public class Access
{
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
}