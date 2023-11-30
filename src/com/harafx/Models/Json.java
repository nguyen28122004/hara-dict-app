package com.harafx.Models;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json {
    static public JSONObject loadObjectFromFile(String path) { // JSON String -> JSON OBject
        Object obj;
        try {
            obj = new JSONParser().parse(new FileReader(path));
            return (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public JSONArray loadArrayFromFile(String path) {
        try {
            Object obj;
            obj = new JSONParser().parse(new FileReader(path));
            return (JSONArray) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public JSONObject loadObjectFromString(String str) throws ParseException {
        return (JSONObject) new JSONParser().parse(str);
    }

    static public ArrayList<String> jsonArrayToArrayList(JSONArray ja) {
        ArrayList<String> res = new ArrayList<>();
        if (ja.size() == 0) {
            return res;
        }
        for (Object obj : ja) {
            res.add((String) obj);
        }
        return res;
    }

    // static public JSONObject loadPropObjFromObject(JSONObject jo, String prop)
    // throws ParseException {
    // return (JSONObject) jo.get(prop);
    // }

    // static public JSONArray loadPropArrayFromObject(JSONObject jo, String prop)
    // throws ParseException {
    // return (JSONArray) new JSONParser().parse((String) jo.get(prop));
    // }
}
