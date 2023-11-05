package com.harafx.Models;

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

}
