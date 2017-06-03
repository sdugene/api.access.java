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

    public HashMap<String,String> response(String urlString, String params)
    {
        InputStream inStream = null;
        URL url = null;
        try {
            url = new URL(urlString.toString());
            if (this.method.equals("POST")) {
                urlConnection = post(url, params);
            } else {
                urlConnection = get(url);
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

    public String header(String urlString, String params, String name)
    {
        InputStream inStream = null;
        URL url = null;
        try {
            url = new URL(urlString.toString());
            if (this.method.equals("POST")) {
                urlConnection = post(url, params);
            } else {
                urlConnection = get(url);
            }

            Map<String, List<String>> map = urlConnection.getHeaderFields();

            System.out.println("Printing Response Header...\n");

            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                System.out.println("Key : " + entry.getKey()
                        + " ,Value : " + entry.getValue());
            }

            inStream = urlConnection.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
            String temp, response = "";
            while ((temp = bReader.readLine()) != null) {
                response += temp;
            }
            System.out.println(response);


            return urlConnection.getHeaderField(name);
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

        byte[] outputInBytes = params.getBytes("UTF-8");
        OutputStream os = urlConnection.getOutputStream();
        os.write( outputInBytes );
        os.close();


        urlConnection.connect();
        return urlConnection;
    }
}
