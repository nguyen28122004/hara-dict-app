package com.harafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application implements Initializable {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        Stage homeStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("View/main.fxml"));
        homeStage.setTitle("English App");
        homeStage.setScene(new Scene(root));
        homeStage.initStyle(StageStyle.UNDECORATED);
        homeStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
