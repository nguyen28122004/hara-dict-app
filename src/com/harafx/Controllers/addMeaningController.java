package com.harafx.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.TransferHandler;

import com.harafx.Models.TransferedData;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class addMeaningController implements Initializable {
    @FXML
    ComboBox<String> wordTypeCBox = new ComboBox<>();

    @FXML
    Pane addMeaningButton = new Pane();
    @FXML
    Pane deleteMeaningButton = new Pane();

    @FXML
    TextField enField = new TextField();
    @FXML
    TextField viField = new TextField();
    @FXML
    TextField exampleField = new TextField();

    void addMeaning() {
        TransferedData.meaningCount += 1;
        TransferedData.obsMeaningCount.set(TransferedData.meaningCount);
    }

    void initButtonControl() {
        addMeaningButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            addMeaning();
            System.out.println(TransferedData.obsMeaningCount);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initButtonControl();
        String[] wordTypeList = { "noun", "pronoun", "verb", "adjective", "adverb", "phrasal verb",
                "indefinite article", "conjunction", "preposition", "determiner", "interjection" };
        wordTypeCBox.getItems().addAll(wordTypeList);
    }

}
