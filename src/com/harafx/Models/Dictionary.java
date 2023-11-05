package com.harafx.Models;

import java.util.ArrayList;

public class Dictionary {
    ArrayList<Word> words = new ArrayList<>();

    public Dictionary(ArrayList<Word> words) {
        this.words = words;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

}
