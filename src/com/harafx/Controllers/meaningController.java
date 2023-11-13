package com.harafx.Controllers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.text.html.parser.Element;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.harafx.Models.Defination;
import com.harafx.Models.Meaning;
import com.harafx.Models.TransferedData;
import com.harafx.Models.Word;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class meaningController extends wordFormController implements Initializable {

    Word word = new Word();

    @FXML
    Pane usAudioButton = new Pane();
    @FXML
    Pane ukAudioButton = new Pane();

    @FXML
    Label targetLabel = new Label();
    @FXML
    Label usPronunciation = new Label();
    @FXML
    Label ukPronunciation = new Label();

    void playAudio(String url) {
        Media sound = new Media(url);
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        System.out.println(url);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Word word = TransferedData.dict.getWords().get(TransferedData.wordIndex);
        targetLabel.setText(word.getTarget());
        usPronunciation.setText("/" + word.getIpa().getUs() + "/");
        ukPronunciation.setText("/" + word.getIpa().getUk() + "/");

        usAudioButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            playAudio(word.getAudio().getUs());
        });

        ukAudioButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            playAudio(word.getAudio().getUk());
        });

        loadHTML(MEANING_PATH);
    }

}
