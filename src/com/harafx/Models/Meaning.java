package com.harafx.Models;

import org.json.simple.JSONObject;

public class Meaning {
    private String en = new String();
    private String vi = new String();
    private String example = new String();

    public Meaning() {
        en = "";
        vi = "";
        example = "";
    }

    public Meaning(Meaning meaning) {
        this.en = meaning.en;
        this.vi = meaning.vi;
        this.example = meaning.example;
    }

    public Meaning(String en, String vi, String example) {
        this.en = en;
        this.vi = vi;
        this.example = example;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getVi() {
        return vi;
    }

    public void setVi(String vi) {
        this.vi = vi;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public void convertFromJson(JSONObject jo) {
        vi = (String) jo.get("vi_def");
        en = (String) jo.get("en_def");
        example = (String) jo.get("example");
    }

    public JSONObject convertToJSon() {
        JSONObject jo = new JSONObject();
        jo.put("vi_def", vi);
        jo.put("en_def", en);
        jo.put("example", example);
        return jo;
    }

    public void debug() {
        System.out.println("---------------");
        System.out.println("Meanings");
        System.out.println(vi);
        System.out.println(en);
        System.out.println(example);
        System.out.println("---------------");
    }
}
