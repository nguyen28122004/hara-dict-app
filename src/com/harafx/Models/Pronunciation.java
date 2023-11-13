package com.harafx.Models;

import org.json.simple.JSONObject;

public class Pronunciation {
    private String us = new String();
    private String uk = new String();

    public Pronunciation() {
    }

    public Pronunciation(String us, String uk) {
        this.us = us;
        this.uk = uk;
    }

    public String getUs() {
        return us;
    }

    public void setUs(String us) {
        this.us = us;
    }

    public String getUk() {
        return uk;
    }

    public void setUk(String uk) {
        this.uk = uk;
    }

    public void convertFromJson(JSONObject jo) {
        us = (String) jo.get("us");
        uk = (String) jo.get("uk");
    }

    public JSONObject convertToJson() {
        JSONObject jo = new JSONObject();
        jo.put("us", us);
        jo.put("uk", uk);
        return jo;
    }

    public void debug() {
        System.out.println("--------");
        System.out.println("PRONUNCIATION");
        System.out.println("US: " + us);
        System.out.println("UK: " + uk);
        System.out.println("--------");
    }

    public void clear() {
        us = "";
        uk = "";
    }
}
