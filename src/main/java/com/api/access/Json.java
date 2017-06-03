package com.api.access;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Json {
    public static HashMap<String,String> jsonToMap(String string)
    {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(string);
            if (obj instanceof JSONArray) {
                return null;
            } else {
                return new Gson().fromJson(
                    string, new TypeToken<HashMap<String,String>>() {}.getType()
                );
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<HashMap<String,String>> jsonToListOfMap(String string)
    {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(string);
            if (obj instanceof JSONArray) {
                return null;
            } else {
                return new Gson().fromJson(string,
                        new TypeToken<List<Map<String, Object>>>() {}.getType());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static JSONObject jsonDecode(String string)
    {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(string);
            if (obj instanceof JSONArray) {
                return null;
            } else {
                return (JSONObject) obj;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String jsonEncode(HashMap<String,Object> map)
    {
        Gson gson = new Gson();
        String json = gson.toJson(map);
        return json;
    }
}