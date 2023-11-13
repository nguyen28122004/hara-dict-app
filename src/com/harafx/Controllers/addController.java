package com.harafx.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.harafx.Models.TransferedData;

import javafx.fxml.Initializable;

public class addController extends wordFormController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TransferedData.word.clear();
        loadHTML(ADD_PATH);
    }

}
