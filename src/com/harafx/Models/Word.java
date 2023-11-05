package com.harafx.Models;

import java.util.ArrayList;

public class Word {
    private String target = new String();
    private Pronunciation ipa = new Pronunciation();
    private ArrayList<Defination> defs = new ArrayList<>();
    private Audio audio = new Audio();
    private String en_vi_URL = new String();
    private String en_en_URL = new String();

    public Word() {
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
        this.ipa = ipa;
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
        this.audio = audio;
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
}
