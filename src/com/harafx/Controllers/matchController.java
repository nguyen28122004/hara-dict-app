package com.harafx.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

import com.harafx.Models.Board;
import com.harafx.Models.Cell;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class matchController implements Initializable {

    Board board = new Board();
    ArrayList<Label> labelList = new ArrayList<>();

    @FXML
    GridPane gridPane = new GridPane();

    private void initGameBoard() {
        gridPane.getChildren().clear();
        labelList.clear();

        gridPane.setHgap(20);
        gridPane.setVgap(20);
        board.setCol(gridPane.getColumnCount());
        board.setRow(gridPane.getRowCount());

        board.randBoard();

        for (Cell cell : board.getCells()) {
            Label label = new Label();
            label.setText(cell.getValue());
            label.setTextFill(Color.TRANSPARENT);
            label.setPrefWidth(gridPane.getPrefWidth() / gridPane.getColumnCount() + 10);
            label.setPrefHeight(gridPane.getPrefHeight() / gridPane.getRowCount() + 10);
            label.setWrapText(true);
            label.setAlignment(Pos.CENTER);
            labelList.add(label);
            gridPane.add(label, cell.getCol(), cell.getRow());
        }
        addListener();
    }

    private void updateUI(String first, String second) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (Label label : labelList) {
                    if (label.isDisable() == true) {
                        label.setTextFill(Color.valueOf("#fff"));
                        continue;
                    }
                    if (label.isDisable() == false
                            && (label.getText() == first || label.getText() == second)) {
                        label.setTextFill(Color.TRANSPARENT);
                    }
                }
            }
        }).start();
    }

    private void checkMatch(String first, String second) {
        if (board.matchResult(first, second)) {
            board.foldCount.set(board.foldCount.get() + 2);
            System.out.println(board.foldCount);
            for (Label label : labelList) {
                if (label.getText().equals(first) ||
                        label.getText().equals(second)) {
                    label.setDisable(true);
                }
            }
        }
    }

    void addListener() {
        for (Label label : labelList) {
            label.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                label.setTextFill(Color.valueOf("#fff"));
                board.currentFold.add(label.getText());
                board.curFoldCount.set(board.currentFold.size());
            });
        }

        board.curFoldCount.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                if (arg2.intValue() == 2) {
                    String first = board.currentFold.get(0);
                    String second = board.currentFold.get(1);
                    checkMatch(first, second);

                    board.currentFold.clear();
                    board.curFoldCount.set(0);
                    updateUI(first, second);

                }
            }

        });

        board.foldCount.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                if (arg2.intValue() == board.getCol() * board.getRow()) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText("You are so great!!!\bLet's make another game!!!");
                    alert.showAndWait();
                    initGameBoard();
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGameBoard();

    }

}
