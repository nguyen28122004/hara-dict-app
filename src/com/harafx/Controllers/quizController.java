package com.harafx.Controllers;

import com.harafx.Models.Json;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;
import java.awt.Desktop;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.harafx.Models.Quiz;
import com.harafx.Models.User;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class quizController implements Initializable {
    final String AVATAR_PATH = "resource/unnamed.jpg";

    ArrayList<Quiz> quizs = new ArrayList<>();
    Quiz quiz = new Quiz();

    @FXML
    Circle avatarCircle = new Circle();
    @FXML
    Label usernameLabel = new Label();

    @FXML
    Button showAnsButton = new Button();
    @FXML
    Button nextQuesButton = new Button();
    @FXML
    Button swapAnsButton = new Button();
    @FXML
    Button halfButton = new Button();
    @FXML
    Button callRelativesButton = new Button();

    // @FXML
    // Text qText = new Text();

    @FXML
    WebView qTextWebview = new WebView();
    @FXML
    WebEngine qTextDriver = new WebEngine();

    @FXML
    Button buttonA = new Button();
    @FXML
    Button buttonB = new Button();
    @FXML
    Button buttonC = new Button();
    @FXML
    Button buttonD = new Button();

    ArrayList<Button> ansButtons = new ArrayList<>();

    void setAvatar(String path) {
        Image img = new Image(path);
        avatarCircle.setFill(new ImagePattern(img));
    }

    void setUserInfo() {
        usernameLabel.setText(User.name);
        setAvatar(AVATAR_PATH);
    }

    void loadQuiz(String path) {
        JSONArray qJA = Json.loadArrayFromFile(path);

        for (Object qObject : qJA) {
            JSONObject qJO = (JSONObject) qObject;
            Quiz quiz = new Quiz();

            quiz.setqText((String) qJO.get("qText"));
            quiz.setCorrect((String) qJO.get("correct"));
            quiz.setSolution((String) qJO.get("solution"));
            quiz.setAns(Json.jsonArrayToArrayList((JSONArray) qJO.get("ans")));
            quizs.add(quiz);
        }
    }

    void initButtonControl() {
        swapAnsButton.setOnAction(event -> setQuiz());
        showAnsButton.setOnAction(event -> showCorrectAns(event));
        nextQuesButton.setOnAction(event -> randQuiz());
        halfButton.setOnAction(event -> removeHalfAns());
        callRelativesButton.setOnAction(event -> callRelatives());

        for (Button button : ansButtons) {
            button.setOnAction(event -> showCorrectAns(event));
        }

    }

    void setQuiz() {
        halfButton.setDisable(false);
        for (Button button : ansButtons) {
            button.setStyle("-fx-background-color:#16325c");
            button.setDisable(false);
        }

        // qText.setText(quiz.getqText());
        String qTextHTML = "<p style=\"width:99%;font-weight:bold;font-size:21px;font-family:'Segoe UI';color:#16325c\">"
                + quiz.getqText().replaceAll("\n", "<br>")
                + "</p>"
                + " <style>" +
                "       input:checked+label {" +
                "       background-color: rgb(157, 0, 0);" +
                "    }" +
                "     ::-webkit-scrollbar {" +
                "        width: 7px;" +
                "    }" +
                "     ::-webkit-scrollbar-track {" +
                "        background-color: transparent;" +
                "    }" +
                "     ::-webkit-scrollbar-thumb {" +
                "        border-radius: 6px;" +
                "        background: #16325c;" +
                "    }" +
                "</style>";
        System.out.println(qTextHTML);
        qTextDriver.loadContent(qTextHTML);

        Collections.shuffle(quiz.getAns());
        buttonA.setText(quiz.getAns().get(0));
        buttonB.setText(quiz.getAns().get(1));
        buttonC.setText(quiz.getAns().get(2));
        buttonD.setText(quiz.getAns().get(3));
    }

    void showCorrectAns(Event event) {
        for (Button button : ansButtons) {
            if (button.getText().equals(quiz.getCorrect())) {
                continue;
            } else if (event == null) {
                button.setDisable(true);
            } else {
                Button thisButton = (Button) event.getTarget();
                if (thisButton.getText().equals(button.getText())) {
                    thisButton.setStyle("-fx-background-color:red");
                }
                button.setDisable(true);
            }
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setGraphic(null);
        alert.setHeaderText("Explanation:");
        alert.setContentText(quiz.getSolution());
        alert.getDialogPane().getStylesheets()
                .add(getClass().getClassLoader().getResource("style/dialog.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("myDialog");
        alert.show();
    }

    void randQuiz() {
        Random rand = new Random();
        int index = rand.nextInt(quizs.size());

        quiz = quizs.get(index);

        setQuiz();
    }

    void removeHalfAns() {
        halfButton.setDisable(true);
        int index = 0;
        int tmp_index = 0;
        int count = 2;
        Random rand = new Random();
        for (Button button : ansButtons) {
            if (button.getText().equals(quiz.getCorrect())) {
                index = ansButtons.indexOf(button);
                break;
            }
        }
        while (count > 0) {
            int tmp = rand.nextInt(4);
            if (tmp != index && tmp != tmp_index) {
                ansButtons.get(tmp).setDisable(true);
                tmp_index = tmp;
                count--;
            }
        }
    }

    void callRelatives() {
        try {
            Desktop.getDesktop().browse(new URI("https://www.google.com/"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ansButtons.add(buttonA);
        ansButtons.add(buttonB);
        ansButtons.add(buttonC);
        ansButtons.add(buttonD);

        qTextDriver = qTextWebview.getEngine();

        initButtonControl();
        setUserInfo();
        loadQuiz(Quiz.pathSource);
        randQuiz();
    }
}
