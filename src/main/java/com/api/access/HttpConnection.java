package com.api.access;

import org.json.simple.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public HashMap<String,String> response(String urlString, String params, String token, String headerName)
    {
        InputStream inStream = null;
        URL url = null;
        try {
            url = new URL(urlString.toString());
            if (this.method.equals("POST")) {
                urlConnection = post(url, params, token, headerName);
            } else {
                urlConnection = get(url, token, headerName);
            }
            inStream = urlConnection.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
            String temp, response = "";
            while ((temp = bReader.readLine()) != null) {
                response += temp;
            }
            return Json.jsonToMap(response);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException ignored) {
                }
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return new HashMap<String, String>();
    }

    public String header(String urlString, String params, String headerName)
    {
        InputStream inStream = null;
        URL url = null;
        try {
            url = new URL(urlString.toString());
            if (this.method.equals("POST")) {
                urlConnection = post(url, params, null, null);
            } else {
                urlConnection = get(url, null, null);
            }

            return urlConnection.getHeaderField(headerName);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException ignored) {
                }
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    private HttpURLConnection get(URL url, String token, String headerName) throws Exception
    {
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty(headerName, token);
        urlConnection.connect();
        return urlConnection;
    }

    private HttpURLConnection post(URL url, String params, String token, String headerName) throws Exception
    {
        System.out.println(params);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        if (token != null && headerName != null) {
            urlConnection.setRequestProperty(headerName, token);
        }

        if (params != null) {
            //set the content length of the body
            urlConnection.setRequestProperty("Content-length", params.getBytes().length + "");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);

            //send the json as body of the request
            OutputStream outputStream = urlConnection.getOutputStream();
            outputStream.write(params.getBytes("UTF-8"));
            outputStream.close();
        }

        urlConnection.connect();
        return urlConnection;
    }
}
