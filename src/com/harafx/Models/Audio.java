package com.harafx.Models;

import org.json.simple.JSONObject;

public class Audio {
    private String us = new String();
    private String uk = new String();

    public Audio() {
        us = "";
        uk = "";
    }

    public Audio(String us, String uk) {
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

}
