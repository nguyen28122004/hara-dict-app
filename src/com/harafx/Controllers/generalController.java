package com.harafx.Controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class generalController {

    final String HOME_VIEW_PATH = "../view/home.fxml";
    final String SEARCH_VIEW_PATH = "../view/search.fxml";
    final String GGTRANS_VIEW_PATH = "../view/ggtranslate.fxml";
    final String TIMING_VIEW_PATH = "../view/timing.fxml";
    final String FAV_VIEW_PATH = "../view/fav.fxml";
    final String GAME_MENU_VIEW_PATH = "../view/game-menu.fxml";

    @FXML
    AnchorPane contentPane = new AnchorPane();

    void setNewScene(Node node) {
        contentPane.getChildren().setAll(node);
    }

    void showHomePane() {
        Node homeNode;
        try {
            homeNode = (Node) FXMLLoader.load(getClass().getResource(HOME_VIEW_PATH));
            setNewScene(homeNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showSearchPane() {
        Node searchNode;
        try {
            searchNode = (Node) FXMLLoader.load(getClass().getResource(SEARCH_VIEW_PATH));
            setNewScene(searchNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showGgTranslatePane() {
        Node searchNode;
        try {
            searchNode = (Node) FXMLLoader.load(getClass().getResource(GGTRANS_VIEW_PATH));
            setNewScene(searchNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showTimingPane() {
        Node searchNode;
        try {
            searchNode = (Node) FXMLLoader.load(getClass().getResource(TIMING_VIEW_PATH));
            setNewScene(searchNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showFavPane() {
        Node searchNode;
        try {
            searchNode = (Node) FXMLLoader.load(getClass().getResource(FAV_VIEW_PATH));
            setNewScene(searchNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showGamePane() {
        Node searchNode;
        try {
            searchNode = (Node) FXMLLoader.load(getClass().getResource(GAME_MENU_VIEW_PATH));
            setNewScene(searchNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
