package com.harafx.Models;

import java.util.ArrayList;

public class Quiz {
    String qText = "";
    ArrayList<String> ans = new ArrayList<>();
    String correct = "";
    String solution = "";

    public static String pathSource = "";

    public String getqText() {
        return qText;
    }

    public void setqText(String qText) {
        this.qText = qText;
    }

    public ArrayList<String> getAns() {
        return ans;
    }

    public void setAns(ArrayList<String> ans) {
        this.ans = ans;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public void debug() {
        System.out.println("=================================");
        System.out.println(qText);
        System.out.println(correct);
        System.out.println(solution);
        System.out.println("=================================");
    }

}
