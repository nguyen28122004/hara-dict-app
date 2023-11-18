package com.harafx.Models;

import java.util.Random;

public class MatchPair {
    Cell target = new Cell();
    Cell meaning = new Cell();

    public MatchPair() {
    }

    public MatchPair(Cell target, Cell meaning) {
        this.target = target;
        this.meaning = meaning;
    }

    public Cell getTarget() {
        return target;
    }

    public void setTarget(Cell target) {
        this.target = target;
    }

    public Cell getMeaning() {
        return meaning;
    }

    public void setMeaning(Cell meaning) {
        this.meaning = meaning;
    }

    public void randMatchPair(Word word, int maxRow, int maxCol) {
        Random rand = new Random();
        String meaningString = word.getDefs().get(0).getMeanings().get(0).getVi();

        while (target.getCol() == meaning.getCol()
                && target.getRow() == meaning.getRow()) {
            target.randCell(rand, maxRow, maxCol, word.getTarget());
            meaning.randCell(rand, maxRow, maxCol, meaningString);
        }

    }
}
