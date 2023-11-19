package com.harafx.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.harafx.Models.TransferedData;
import com.harafx.Models.Word;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class addController extends wordFormController implements Initializable {

    private void addWord() {
        Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.OK, ButtonType.CANCEL);
        alert.setHeaderText("Do you want to add \"" + targetField.getText() + "\" to your dictionary?");
        alert.showAndWait();
        if (alert.getResult() != ButtonType.OK) {
            return;
        }

        Word word = getDefElement();

        if (word.getTarget().isEmpty()) {
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("Please fill all the field before adding");
            alert.show();
            return;
        }
        if (TransferedData.dict.searchWord(word.getTarget()) != -1) {
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("This word is already in your dictionary");
            alert.show();
            return;
        }

        System.out.println("BEFORE==========================");
        TransferedData.dict.getWords().get(TransferedData.dict.size() - 2).debug();
        TransferedData.dict.addWord(word);
        System.out.println("AFTER==========================");
        TransferedData.dict.getWords().get(TransferedData.dict.size() - 3).debug();
        try {
            TransferedData.dict.saveJson(DICT_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TransferedData.dict.loadJson(DICT_PATH);

        alert.setAlertType(AlertType.INFORMATION);
        alert.setHeaderText("Your word is added. Please reload the application");
        alert.show();
        wrapFormPane.getChildren().clear();
    }

    void initButtonControl() {
        okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            addWord();
        });

        cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            wrapFormPane.getChildren().clear();
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TransferedData.word.clear();
        loadHTML(FORM_PATH);

        driver.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {

            @Override
            public void changed(ObservableValue<? extends State> arg0, State arg1, State arg2) {
                if (arg2 == State.SUCCEEDED) {
                    driver.executeScript("addDef()");
                    initButtonControl();
                }
            }
        });
    }

}
