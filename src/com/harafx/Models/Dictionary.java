package com.harafx.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Dictionary {
    private ArrayList<String> targetList = new ArrayList<>();
    private ArrayList<Word> words = new ArrayList<>();
    public IntegerProperty size = new SimpleIntegerProperty(0);

    public Dictionary() {
    }

    public Dictionary(Dictionary dict) {
        this.words = dict.getWords();
        this.targetList = dict.getTargetList();
    }

    public ArrayList<String> getTargetList() {
        return targetList;
    }

    public void setTargetList(ArrayList<String> targetList) {
        this.targetList = targetList;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public void addWord(Word word) {
        this.words.add(word);
        this.targetList.add(word.getTarget());
    }

    public void removeWord(int index) {
        words.remove(index);
        targetList.remove(index);
    }

    public int searchWord(String target) {
        // if (target == "" || target == null) {
        // return -1;
        // }
        // for (int i = 0; i < targetList.size(); i++) {
        // if (target.equals(targetList.get(i))) {
        // return i;
        // }
        // }
        // return -1;

        return targetList.indexOf(target);
    }

    public int size() {
        return words.size();
    }

    public void sort() {
        words.sort((o1, o2) -> o1.getTarget().compareTo(o2.getTarget()));
        targetList.sort((o1, o2) -> o1.compareTo(o2));
    }

    public void loadJson(String path) {
        words.clear();
        targetList.clear();
        JSONArray wordsJA = Json.loadArrayFromFile(path);
        for (Object wordtmp : wordsJA) {
            Word word = new Word();
            word.convertFromJson((JSONObject) wordtmp);
            words.add(word);
            targetList.add(word.getTarget());
            // System.out.println("==========================================");
            // word.debug();
        }
        size.set(targetList.size());
        this.sort();
    }

    public void saveJson(String path) throws IOException {
        JSONArray dictJA = new JSONArray();
        for (Word word : words) {
            dictJA.add(word.convertToJson());
        }
        PrintWriter pw = new PrintWriter(path);
        pw.write(dictJA.toJSONString());
        pw.flush();
        pw.close();
    }

}
