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

public class mainController extends generalController implements Initializable {
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
