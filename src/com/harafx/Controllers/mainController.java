package com.harafx.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class mainController implements Initializable {
    @FXML
    AnchorPane titleBar = new AnchorPane();
    @FXML
    StackPane quitButton = new StackPane();
    @FXML
    StackPane minimizeButton = new StackPane();

    @FXML
    ImageView expandButon = new ImageView();

    @FXML
    StackPane homeButton = new StackPane();
    @FXML
    StackPane searchButton = new StackPane();
    @FXML
    StackPane googleApiButton = new StackPane();
    @FXML
    StackPane favoriteButton = new StackPane();
    @FXML
    StackPane timingButton = new StackPane();
    @FXML
    StackPane gameListButton = new StackPane();
    @FXML
    StackPane settingButton = new StackPane();

    @FXML
    AnchorPane contentPane = new AnchorPane();

    void setNewScene(Node node) {
        contentPane.getChildren().setAll(node);
    }

    void setTitleBarDrag(boolean bool) {
        if (!bool) {
            return;
        }
        titleBar.setOnMousePressed(pressEvent -> {
            titleBar.setOnMouseDragged(dragEvent -> {
                Stage curStage = (Stage) quitButton.getScene().getWindow();
                curStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                curStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }

    void quit() {
        Platform.exit();
    };

    void minimize() {
        Stage curStage = (Stage) quitButton.getScene().getWindow();
        curStage.setIconified(true);
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

    void initButtonControl() throws IOException {
        setTitleBarDrag(true);
        quitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            quit();
        });
        minimizeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            minimize();
        });
        homeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            showHomePane();
        });
        searchButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            showSearchPane();
        });
        googleApiButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            showGgTranslatePane();
        });
        timingButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            showTimingPane();
        });
        favoriteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            showFavPane();
        });
        gameListButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            showGamePane();
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initButtonControl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showHomePane();
    }
}
