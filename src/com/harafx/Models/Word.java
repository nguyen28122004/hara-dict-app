package com.harafx.Models;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Word {
    private String target = new String();
    private Pronunciation ipa = new Pronunciation();
    private ArrayList<Defination> defs = new ArrayList<>();
    private Audio audio = new Audio();
    private String en_vi_URL = new String();
    private String en_en_URL = new String();

    public Word() {
    }

    public Word(Word word) {
        this.target = word.getTarget();
        this.ipa = word.getIpa();
        this.defs = word.getDefs();
        this.audio = word.getAudio();
        this.en_en_URL = word.getEn_en_URL();
        this.en_vi_URL = word.getEn_vi_URL();
    }

    public Word(String target, Pronunciation ipa, ArrayList<Defination> defs) {
        this.target = target;
        this.ipa = ipa;
        this.defs = defs;
    }

    public Word(String target, Pronunciation ipa, ArrayList<Defination> defs, Audio audio, String en_vi_URL,
            String en_en_URL) {
        this.target = target;
        this.ipa = ipa;
        this.defs = defs;
        this.audio = audio;
        this.en_vi_URL = en_vi_URL;
        this.en_en_URL = en_en_URL;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Pronunciation getIpa() {
        return ipa;
    }

    public void setIpa(Pronunciation ipa) {
        this.ipa.setUk(ipa.getUk());
        this.ipa.setUs(ipa.getUs());
    }

    public ArrayList<Defination> getDefs() {
        return defs;
    }

    public void setDefs(ArrayList<Defination> defs) {
        this.defs = defs;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio.setUs(audio.getUs());
        this.audio.setUk(audio.getUk());
    }

    public String getEn_vi_URL() {
        return en_vi_URL;
    }

    public void setEn_vi_URL(String en_vi_URL) {
        this.en_vi_URL = en_vi_URL;
    }

    public String getEn_en_URL() {
        return en_en_URL;
    }

    public void setEn_en_URL(String en_en_URL) {
        this.en_en_URL = en_en_URL;
    }

    public void addDef(Defination def) {
        this.defs.add(def);
    }

    public void convertFromJson(JSONObject jo) { // Convert JSONObject to Object (Word) in Java
        target = (String) jo.get("target");
        ipa.convertFromJson((JSONObject) jo.get("ipa"));

        JSONArray defsJA = (JSONArray) jo.get("definations");
        for (Object tmp : defsJA) {
            Defination def = new Defination();
            def.convertFromJson((JSONObject) tmp);
            defs.add(def);
        }

        audio.convertFromJson((JSONObject) jo.get("audio"));
        en_vi_URL = (String) jo.get("en_vi_url");
        en_en_URL = (String) jo.get("en_en_url");
    }

    public JSONObject convertToJson() {
        JSONObject jo = new JSONObject();
        JSONArray defsJA = new JSONArray();

        jo.put("target", target);
        jo.put("ipa", ipa.convertToJson());

        for (Defination defination : defs) {
            defsJA.add(defination.convertToJson());
        }
        jo.put("definations", defsJA);
        jo.put("audio", audio.convertToJson());
        jo.put("en_vi_url", en_vi_URL);
        jo.put("en_en_url", en_en_URL);
        return jo;
    }

    public void clear() {
        target = "";
        ipa.clear();
        defs.clear();
        audio.clear();
        en_vi_URL = "";
        en_en_URL = "";
    }

    public void debug() {
        System.out.println("Target: " + target);
        ipa.debug();
        for (Defination defination : defs) {
            defination.debug();
        }
    }

}
