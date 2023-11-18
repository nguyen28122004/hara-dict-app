package com.harafx.Models;

import java.util.ArrayList;
import java.util.Random;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Board {
    int row = 5;
    int col = 4;

    private Word word = new Word();
    private ArrayList<String> wordTargets = new ArrayList<>();

    public static ArrayList<Cell> cells = new ArrayList<>();
    ArrayList<MatchPair> pairs = new ArrayList<>();

    public IntegerProperty foldCount = new SimpleIntegerProperty(0);
    public ArrayList<String> currentFold = new ArrayList<>();
    public IntegerProperty curFoldCount = new SimpleIntegerProperty(currentFold.size());

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        Board.cells = cells;
    }

    public ArrayList<MatchPair> getPairs() {
        return pairs;
    }

    public void setPairs(ArrayList<MatchPair> pairs) {
        this.pairs = pairs;
    }

    // ==============================
    public boolean matchResult(String str1, String str2) {
        int index = -1;
        for (Cell cell : cells) {
            if (cell.getValue().equals(str1)) {
                index = cells.indexOf(cell);
                MatchPair tmpPair = pairs.get((int) Math.ceil(index / 2));
                if (tmpPair.getTarget().getValue().equals(str1) &&
                        tmpPair.getMeaning().getValue().equals(str2))
                    return true;
                if (tmpPair.getTarget().getValue().equals(str2) &&
                        tmpPair.getMeaning().getValue().equals(str1))
                    return true;
            }
        }
        return false;

    }

    public void randBoard() {
        word.clear();
        wordTargets.clear();
        cells.clear();
        pairs.clear();

        Dictionary dict = TransferedData.dict;

        Random rand = new Random();

        for (int i = 0; i < row * col / 2; i++) {
            randWord(dict, rand);
            MatchPair matchPair = new MatchPair();
            matchPair.randMatchPair(word, row, col);

            pairs.add(matchPair);
            cells.add(matchPair.getTarget());
            cells.add(matchPair.getMeaning());
        }

    }

    private void randWord(Dictionary dict, Random rand) {
        do {
            int wordIndex = rand.nextInt(dict.size());
            word = dict.getWords().get(wordIndex);
        } while (isSatisfied());
    }

    private boolean isSatisfied() {
        return (word.getTarget().length() > 13 ||
                word.getTarget().length() <= 1 ||
                word.getDefs().get(0).getMeanings().get(0).getVi().length() <= 1 ||
                wordTargets.indexOf(word.getTarget()) != -1);
    }
}
