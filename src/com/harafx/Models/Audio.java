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
        this.us = new String(us);
        this.uk = new String(uk);
    }

    public Audio(Audio audio) {
        this.us = new String(audio.us);
        this.uk = new String(audio.uk);
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

    public void clear() {
        us = "";
        uk = "";
    }

}
