package com.harafx.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import com.harafx.Models.Quiz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class menuGameController extends htmlController implements Initializable {
    final String FILL = "com/harafx/View/quiz.fxml";
    final String QUIZ = "com/harafx/View/quiz.fxml";
    final String MATCH = "com/harafx/View/match.fxml";

    ArrayList<String> gamePath = new ArrayList<>();
    int currentGameSelect = -1;

    @FXML
    AnchorPane wrapPane = new AnchorPane();

    @FXML
    WebView infoWebview = new WebView();
    @FXML
    WebEngine driver = new WebEngine();

    @FXML
    AnchorPane cardGameButton = new AnchorPane();
    @FXML
    AnchorPane matchGameButton = new AnchorPane();

    @FXML
    Button startButton = new Button();

    void switchView(String path) {
        Node node;
        try {
            node = FXMLLoader.load(getClass().getClassLoader().getResource(path));
            wrapPane.getChildren().setAll(node);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void initButtonControl() {
        cardGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            currentGameSelect = 1;
            loadHTMLString("src/com/harafx/view/quizInfo.html");
            driver.loadContent(htmlString);
        });

        matchGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            currentGameSelect = 2;
            loadHTMLString("src/com/harafx/view/matchGameInfo.html");
            driver.loadContent(htmlString);
        });
        startButton.setOnAction(event -> {
            if (currentGameSelect == 1) {
                quizGame();
            }
            if (currentGameSelect == 2) {
                matchGame();
            }
        });
    }

    private void quizGame() {
        int quizCollectionIndex = (int) driver.executeScript("getSelectedIndex();");
        while (quizCollectionIndex == -1 ||
                (quizCollectionIndex >= 6 && quizCollectionIndex <= 8)) {
            quizCollectionIndex = (new Random()).nextInt(21);
        }
        Quiz.pathSource = "src/resource/quiz/" + quizCollectionIndex + ".json";
        System.out.println(Quiz.pathSource);
        switchView(gamePath.get(currentGameSelect));
    }

    private void matchGame() {
        switchView(gamePath.get(currentGameSelect));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gamePath.add(FILL);
        gamePath.add(QUIZ);
        gamePath.add(MATCH);
        initButtonControl();
        driver = infoWebview.getEngine();

        currentGameSelect = 1;
        loadHTMLString("src/com/harafx/view/quizInfo.html");
        driver.loadContent(htmlString);
    }
}
