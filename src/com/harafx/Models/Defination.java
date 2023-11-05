package com.harafx.Models;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Defination {
    private String type = new String();
    private ArrayList<Meaning> meanings = new ArrayList<>();

    public Defination() {
    }

    public Defination(String type, ArrayList<Meaning> meanings) {
        this.type = type;
        this.meanings = meanings;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Meaning> getMeanings() {
        return meanings;
    }

    public void setMeanings(ArrayList<Meaning> meanings) {
        this.meanings = meanings;
    }

    public void convertFromJson(JSONObject tmp2) {
        type = (String) tmp2.get("type");

        JSONArray meaningsJA = (JSONArray) tmp2.get("meanings");
        for (Object tmp : meaningsJA) {
            Meaning meaning = new Meaning();
            meaning.convertFromJson((JSONObject) tmp);
            meanings.add(new Meaning(meaning));
        }
    }

    public void debug() {
        System.out.println("Type: " + type);
        for (Meaning meaning : meanings) {
            meaning.debug();
        }
    }
}
