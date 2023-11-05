package com.harafx.Models;

import java.util.ArrayList;

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

}
