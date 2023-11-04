package com.harafx.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class searchController implements Initializable {
    @FXML
    AnchorPane rightSearchPane = new AnchorPane();

    @FXML
    StackPane addWordButton = new StackPane();
    @FXML
    StackPane deleteWordButton = new StackPane();
    @FXML
    StackPane editWordButton = new StackPane();
    @FXML
    StackPane addFavButton = new StackPane();

    void showMeaningPane() throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getResource("../view/meaning.fxml"));
        rightSearchPane.getChildren().setAll(node);
    }

    void showAddPane() throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getResource("../view/search-add.fxml"));
        rightSearchPane.getChildren().setAll(node);
    }

    void showEditPane() throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getResource("../view/search-edit.fxml"));
        rightSearchPane.getChildren().setAll(node);
    }

    void initButtonControl() {
        addWordButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                showAddPane();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        editWordButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                showEditPane();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        deleteWordButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            // Delete selected word here
        });
        addFavButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            // Add selected word to fav list here
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showMeaningPane();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initButtonControl();
    }
}
