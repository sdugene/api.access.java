package com.api.access;

import org.json.simple.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {
    public String method;
    HttpURLConnection urlConnection = null;

    public HttpConnection()
    {
        this.setMethod("POST");
    }

    public HttpConnection setMethod(String method)
    {
        this.method = method;
        return this;
    }

    public String run(String urlString, String params)
    {
        InputStream inStream = null;
        URL url = null;
        System.out.println(urlString);
        System.out.println(this.method);
        try {
            url = new URL(urlString.toString());
            urlConnection = get(url);
            inStream = urlConnection.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
            String temp, response = "";
            while ((temp = bReader.readLine()) != null) {
                response += temp;
            }
            System.out.println(response);
            return response;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (inStream != null) {
                try {
                    // this will close the bReader as well
                    inStream.close();
                } catch (IOException ignored) {
                }
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }



        return "ok";
    }

    private HttpURLConnection get(URL url) throws Exception
    {
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("auth", "3c725b82ec06903cc43c6fe0980c0e7d");
        urlConnection.connect();
        return urlConnection;
    }

    private HttpURLConnection post(URL url, String params) throws Exception
    {
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("auth", "3c725b82ec06903cc43c6fe0980c0e7d");
        OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
        wr.write(params);
        urlConnection.connect();
        return urlConnection;
    }
}
