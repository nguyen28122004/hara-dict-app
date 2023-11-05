package com.harafx.Models;

import com.harafx.Models.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Dictionary {
    ArrayList<Word> words = new ArrayList<>();

    public Dictionary() {
    }

    public Dictionary(ArrayList<Word> words) {
        this.words = words;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public void loadJson(String path) throws FileNotFoundException, IOException, ParseException {
        JSONArray wordsJA = Json.loadArrayFromFile(path);
        for (Object wordtmp : wordsJA) {
            Word word = new Word();
            word.convertFromJson((JSONObject) wordtmp);
            words.add(word);
            // System.out.println("==========================================");
            // word.debug();
        }
    }
}
