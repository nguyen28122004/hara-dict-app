package com.harafx.Controllers;

import com.harafx.Models.TransferedData;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.DataTruncation;
import java.util.ResourceBundle;

import com.harafx.Models.Dictionary;

public class addController implements Initializable {

    @FXML
    VBox meaningWrapPane = new VBox();

    void addMeaningPane() throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("../view/search-add-meaning.fxml"));
        meaningWrapPane.getChildren().add(node);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            addMeaningPane();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        TransferedData.obsMeaningCount.addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                try {
                    addMeaningPane();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });

    }
}
