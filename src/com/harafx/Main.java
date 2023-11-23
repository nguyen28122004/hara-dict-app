package com.harafx;

import java.net.URL;
import java.util.ResourceBundle;

import com.harafx.Controllers.CBListener;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application implements Initializable {

    public static void main(String[] args) throws Exception {
        Platform.runLater(new CBListener());
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        Stage homeStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/harafx/View/main.fxml"));
        homeStage.setTitle("English App");
        homeStage.setScene(new Scene(root));
        homeStage.initStyle(StageStyle.UNDECORATED);
        homeStage.getIcons().add(new Image("resource/icon.jpg"));
        homeStage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
