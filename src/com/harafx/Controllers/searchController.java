package com.harafx.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.xml.crypto.dsig.spec.XPathType.Filter;

import org.json.simple.parser.ParseException;

import com.harafx.Models.Dictionary;
import com.harafx.Models.TransferedData;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class searchController implements Initializable {
    private final String MEANING_PATH = "../view/meaning.fxml";
    private final String ADD_PATH = "../view/add.fxml";
    private final String EDIT_PATH = "../view/search-edit.fxml";
    private final String DICT_PATH = "src/resource/dict.json";

    private Dictionary dict = new Dictionary();
    FilteredList<String> filteredTargetList;

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
                targetListView.getSelectionModel().clearSelection();
                showAddPane();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        editWordButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            String target = targetListView.getSelectionModel().getSelectedItem();
            int index = dict.searchWord(target);

            TransferedData.wordIndex = index;
            TransferedData.word = dict.getWords().get(index);
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

        filteredTargetList = new FilteredList<>(FXCollections.observableArrayList(targetList), p -> true);

    }

    public void addListViewListener() {
        targetListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                int index = dict.searchWord(arg2);
                if (index != -1) {
                    TransferedData.wordIndex = index;
                    TransferedData.word = TransferedData.dict.getWords().get(index);
                    try {
                        switchRightPane(MEANING_PATH);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void switchRightPane(String path) throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getResource(path));
        rightPane.getChildren().setAll(node);
    }

    public void search() {
        targetListView.getSelectionModel().selectFirst();
        String target = searchField.getText();
        System.out.println(target);

        filteredTargetList
                .setPredicate(target.isEmpty() ? p -> true : p -> p.toLowerCase().startsWith(target.toLowerCase()));
        targetListView.getItems().setAll(filteredTargetList);
        targetListView.getSelectionModel().selectFirst();
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
        addListViewListener();
    }
}
