package com.harafx.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.simple.parser.ParseException;

import com.harafx.Models.Dictionary;
import com.harafx.Models.TransferedData;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class searchController implements Initializable {
    private final String MEANING_PATH = "../view/meaning.fxml";
    private final String ADD_PATH = "../view/search-add.fxml";
    private final String EDIT_PATH = "../view/search-edit.fxml";
    private final String DICT_PATH = "src/resource/dict.json";

    private Dictionary dict = new Dictionary();

    @FXML
    TextField searchField = new TextField();

    @FXML
    ListView<String> targetListView = new ListView<>();

    @FXML
    AnchorPane rightPane = new AnchorPane();

    @FXML
    StackPane addWordButton = new StackPane();
    @FXML
    StackPane deleteWordButton = new StackPane();
    @FXML
    StackPane editWordButton = new StackPane();
    @FXML
    StackPane addFavButton = new StackPane();

    void showMeaningPane() throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getResource(MEANING_PATH));
        rightPane.getChildren().setAll(node);
    }

    void showAddPane() throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getResource(ADD_PATH));
        rightPane.getChildren().setAll(node);
    }

    void showEditPane() throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getResource(EDIT_PATH));
        rightPane.getChildren().setAll(node);
    }

    void initButtonControl() {
        addWordButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                showAddPane();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        editWordButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                showEditPane();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        deleteWordButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            // Delete selected word here
        });
        addFavButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            // Add selected word to fav list here
        });
    }

    public void addListView(ArrayList<String> targetList) {
        targetListView.getItems().addAll(targetList);
    }

    public void switchRightPane(String path) throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getResource(path));
        rightPane.getChildren().setAll(node);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            dict.loadJson(DICT_PATH);
            TransferedData.dict = new Dictionary(dict);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        initButtonControl();
        addListView(dict.getTargetList());

        targetListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                int index = dict.searchWord(arg2);
                if (index != -1) {
                    TransferedData.wordIndex = index;
                    try {
                        switchRightPane(MEANING_PATH);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
