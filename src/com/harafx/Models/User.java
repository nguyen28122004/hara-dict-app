package com.harafx.Models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class User {
    public static String name = new String();
    public static String email = new String();
    public static ArrayList<String> favList = new ArrayList<>();

    public User() {

    }

    public User(String name, String email) {
        User.name = name;
        User.email = email;
    }

    public User(String name, String email, JSONArray favJa) {
        User.name = name;
        User.email = email;
        favList = Json.jsonArrayToArrayList(favJa);
    }

    public static void saveUser(String path) throws FileNotFoundException {
        JSONObject user = new JSONObject();
        JSONArray fav = new JSONArray();
        fav.addAll(favList);

        user.put("name", name);
        user.put("email", email);
        user.put("fav", fav);

        PrintWriter pw = new PrintWriter(path);
        pw.write(user.toJSONString());

        pw.flush();
        pw.close();
    }

    public static void loadUser(String path)
            throws FileNotFoundException, IOException, ParseException {
        JSONObject user = Json.loadObjectFromFile(path);
        name = (String) user.get("name");
        email = (String) user.get("email");

        JSONArray favJa = (JSONArray) user.get("fav");
        favList = Json.jsonArrayToArrayList(favJa);
    }
}
