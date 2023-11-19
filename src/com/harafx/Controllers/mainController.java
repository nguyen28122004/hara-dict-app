package com.harafx.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import java.awt.SystemTray;

import com.dustinredmond.fxtrayicon.FXTrayIcon;
import com.harafx.Models.Stopwatch;
import com.harafx.Models.TransferedData;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class mainController extends generalController implements Initializable {

    FXTrayIcon trayIcon;

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
        Stage curStage = (Stage) quitButton.getScene().getWindow();

        ButtonType hide = new ButtonType("Hide to tray", ButtonData.OTHER);
        Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO, hide);
        alert.setHeaderText("Do you want to close the application?");
        alert.showAndWait();

        if (alert.getResult().getButtonData() == ButtonData.NO) {
            return;
        }
        if (alert.getResult().getButtonData() == ButtonData.OTHER) {
            curStage.hide();
            hideToTray();
            return;
        }

        Platform.exit();
        for (Stopwatch thread : TransferedData.threads) {
            System.out.println(thread.getName());
            thread.isStopped = true;
        }
    };

    void minimize() {
        Stage curStage = (Stage) quitButton.getScene().getWindow();
        curStage.setIconified(true);
    }

    void hideToTray() {
        Stage curStage = (Stage) quitButton.getScene().getWindow();
        if (trayIcon == null) {
            trayIcon = new FXTrayIcon(curStage, new Image("resource/icon.jpg"));
        }
        if (trayIcon.isShowing()) {
            return;
        }
        trayIcon.show();
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
