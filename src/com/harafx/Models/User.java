package com.harafx.Models;

import com.harafx.Models.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class User {
    String name = new String();
    String email = new String();

    public User() {

    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void saveUser(String path) throws FileNotFoundException {
        JSONObject user = new JSONObject();
        user.put("name", this.name);
        user.put("email", this.email);

        PrintWriter pw = new PrintWriter(path);
        pw.write(user.toJSONString());

        pw.flush();
        pw.close();
    }

    public static void saveUser(String path, User userObject) throws FileNotFoundException {
        JSONObject user = new JSONObject();
        user.put("name", userObject.name);
        user.put("email", userObject.email);
        PrintWriter pw = new PrintWriter(path);
        pw.write(user.toJSONString());

        pw.flush();
        pw.close();
    }

    public static User loadUser(String path)
            throws FileNotFoundException, IOException, ParseException {
        JSONObject user = Json.loadObjectFromFile(path);
        return new User((String) user.get("name"), (String) user.get("email"));
    }
}
