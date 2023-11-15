package com.harafx.Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.xml.crypto.dsig.spec.XPathType.Filter;

import org.json.simple.parser.ParseException;

import com.harafx.Models.Dictionary;
import com.harafx.Models.TransferedData;
import com.harafx.Models.User;
import com.harafx.Models.Word;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class favController extends searchController {

    public void initialize(URL location, ResourceBundle resources) {

        try {
            dict.loadJson(DICT_PATH);
            TransferedData.dict = dict;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        initButtonControl();
        setListView(User.favList);
        addListViewListener();
    }
}
