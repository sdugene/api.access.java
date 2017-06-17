package com.api.access;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sebastien Dugene on 30/05/2017.
 */
class HttpConnection
{
    private String method;
    private HttpURLConnection urlConnection = null;

    /**
     * Set HttpConnection
     * Define POST http method
     */
    HttpConnection()
    {
        this.setMethod("POST");
    }

    /**
     * Set the http method
     *
     * @param method http method
     */
    HttpConnection setMethod(String method)
    {
        this.method = method;
        return this;
    }

    /**
     * Call an url and return the response body
     *
     * @param urlString url to call
     * @param params post params
     * @param token security access token
     */
    String response(String urlString, String params, String token)
    {
        String temp;
        StringBuilder response = new StringBuilder();
        InputStream inStream = null;
        try {
            URL url = new URL(urlString);
            if (this.method.equals("POST")) {
                urlConnection = post(url, params, token, "Authorization");
            } else {
                urlConnection = get(url, token, "Authorization");
            }
            inStream = urlConnection.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
            while ((temp = bReader.readLine()) != null) {
                response.append(temp);
            }
            return response.toString();
        } catch (Exception e) {
            //System.out.println(e);
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

    /**
     * Call an url and return authorization header
     *
     * @param urlString url to call
     * @param params post params
     * @param header header sent value
     * @param name name of header to return
     */
    String header(String urlString, String params, String header, String name)
    {
        try {
            URL url = new URL(urlString);
            if (this.method.equals("POST")) {
                urlConnection = post(url, params, header, "device");
            } else {
                urlConnection = get(url, header, "device");
            }

            if (name.equals("responseCode")) {
                return Integer.toString(urlConnection.getResponseCode());
            }
            return urlConnection.getHeaderField(name);
        } catch (Exception e) {
            //System.out.println(e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    /**
     * Create HttpURLConnection with GET http method
     *
     * @param url url to call
     * @param header header sent value
     * @param headerName name of header sent
     */
    private HttpURLConnection get(URL url, String header, String headerName) throws Exception
    {
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty(headerName, header);
        urlConnection.connect();
        return urlConnection;
    }

    /**
     * Create HttpURLConnection with POST http method
     *
     * @param url url to call
     * @param params post params
     * @param header header sent value
     * @param headerName name of header sent
     */
    private HttpURLConnection post(URL url, String params, String header, String headerName) throws Exception
    {
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        if (header != null && headerName != null) {
            urlConnection.setRequestProperty(headerName, header);
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
