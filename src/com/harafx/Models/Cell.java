package com.harafx.Models;

import java.util.Random;

public class Cell {
    private int row = 0;
    private int col = 0;
    String value = "";
    boolean isFold = false;

    public Cell() {
        row = 0;
        col = 0;
        value = "";
        isFold = false;
    }

    public Cell(int row, int col, String value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    void randCell(Random rand, int maxRow, int maxCol, String value) {
        this.value = value;
        do {
            row = rand.nextInt(maxRow);
            col = rand.nextInt(maxCol);
        } while (isUsed(row, col));
    }

    boolean isUsed(int row, int col) {
        if (Board.cells.size() == 0) {
            return false;
        }
        for (Cell cell : Board.cells) {
            if (row == cell.getRow() &&
                    col == cell.getCol()) {
                return true;
            }
        }
        return false;
    }
}
