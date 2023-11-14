package com.harafx.Models;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json {
    static public JSONObject loadObjectFromFile(String path) throws FileNotFoundException, IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(path));
        return (JSONObject) obj;
    }

    static public JSONArray loadArrayFromFile(String path) throws FileNotFoundException, IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(path));
        return (JSONArray) obj;
    }

    static public JSONObject loadObjectFromString(String str) throws ParseException {
        return (JSONObject) new JSONParser().parse(str);
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
