package com.harafx.Controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class generalController {

    @FXML
    AnchorPane contentPane = new AnchorPane();

    void setNewScene(Node node) {
        contentPane.getChildren().setAll(node);
    }

    void showHomePane() {
        Node homeNode;
        try {
            homeNode = (Node) FXMLLoader.load(getClass().getResource("../view/home.fxml"));
            setNewScene(homeNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showSearchPane() {
        Node searchNode;
        try {
            searchNode = (Node) FXMLLoader.load(getClass().getResource("../view/search.fxml"));
            setNewScene(searchNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showGgTranslatePane() {
        Node searchNode;
        try {
            searchNode = (Node) FXMLLoader.load(getClass().getResource("../view/ggtranslate.fxml"));
            setNewScene(searchNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showTimingPane() {
        Node searchNode;
        try {
            searchNode = (Node) FXMLLoader.load(getClass().getResource("../view/timing.fxml"));
            setNewScene(searchNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showFavPane() {
        Node searchNode;
        try {
            searchNode = (Node) FXMLLoader.load(getClass().getResource("../view/fav.fxml"));
            setNewScene(searchNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showGamePane() {
        Node searchNode;
        try {
            searchNode = (Node) FXMLLoader.load(getClass().getResource("../view/game-menu.fxml"));
            setNewScene(searchNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
