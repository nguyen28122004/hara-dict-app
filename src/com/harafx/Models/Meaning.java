package com.harafx.Models;

public class Meaning {
    private String en = new String();
    private String vi = new String();
    private String example = new String();

    public Meaning() {
        en = "";
        vi = "";
        example = "";
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
}
