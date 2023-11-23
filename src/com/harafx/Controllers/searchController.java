package com.harafx.Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.harafx.Models.Dictionary;
import com.harafx.Models.TransferedData;
import com.harafx.Models.User;
import com.harafx.Models.Word;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class searchController implements Initializable {
    protected final String MEANING_PATH = "com/harafx/View/meaning.fxml";
    protected final String ADD_PATH = "com/harafx/View/add.fxml";
    protected final String EDIT_PATH = "com/harafx/View/edit.fxml";
    protected final String DICT_PATH = "src/resource/dict.json";
    protected final String FAV_IMG_FILLED = "src/resource/dark blue icon/heart-filled.png";
    protected final String FAV_IMG_OUTLINE = "src/resource/dark blue icon/heart-outline.png";

    protected Dictionary dict = new Dictionary();
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
    @FXML
    ImageView favImg = new ImageView();

    void showMeaningPane() throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getClassLoader().getResource(MEANING_PATH));
        rightPane.getChildren().setAll(node);
    }

    void showAddPane() throws IOException {
        TransferedData.wordIndex = -1;
        TransferedData.word = new Word();
        targetListView.getSelectionModel().clearSelection();

        Node node = (Node) FXMLLoader.load(getClass().getClassLoader().getResource(ADD_PATH));
        rightPane.getChildren().setAll(node);
    }

    void showEditPane() throws IOException {
        String target = targetListView.getSelectionModel().getSelectedItem();
        int index = dict.searchWord(target);

        TransferedData.wordIndex = index;
        TransferedData.word = new Word(dict.getWords().get(index));

        Node node = (Node) FXMLLoader.load(getClass().getClassLoader().getResource(EDIT_PATH));
        rightPane.getChildren().setAll(node);
    }

    private void deleteWord() {
        Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Do you want to remove the selected word?");
        alert.showAndWait();

        if (alert.getResult().getButtonData() == ButtonData.NO) {
            return;
        }

        TransferedData.dict.removeWord(TransferedData.wordIndex);
        setListView(TransferedData.dict.getTargetList());

        try {
            TransferedData.dict.saveJson(DICT_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        alert.setAlertType(AlertType.INFORMATION);
        alert.setHeaderText("Your word is removed");
        alert.show();
        TransferedData.dict.loadJson(DICT_PATH);
        rightPane.getChildren().clear();
    }

    private void setFavImage(String path) {
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        favImg.setImage(image);
    }

    private void addFavWord() {
        String target = targetListView.getSelectionModel().getSelectedItem();

        if (target == null) {
            return;
        }

        if (User.favList.indexOf(target) == -1) {
            setFavImage(FAV_IMG_FILLED);
            User.favList.add(target);
        } else {
            setFavImage(FAV_IMG_OUTLINE);
            User.favList.remove(target);
        }

        try {
            User.saveUser("src/resource/user.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    void initButtonControl() {

        addWordButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            try {
                TransferedData.wordIndex = -1;
                TransferedData.word = new Word();
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
            TransferedData.word = new Word(dict.getWords().get(index));
            try {
                showEditPane();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        deleteWordButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            deleteWord();
        });

        addFavButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            addFavWord();
        });
    }

    public void setListView(ArrayList<String> targetList) {
        targetListView.getItems().setAll(targetList);

        filteredTargetList = new FilteredList<>(FXCollections.observableArrayList(targetList), p -> true);
    }

    public void addListViewListener() {
        targetListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                int index = dict.searchWord(arg2);

                if (index == -1) {
                    return;
                }

                if (User.favList.indexOf(arg2) == -1) {
                    setFavImage(FAV_IMG_OUTLINE);
                } else {
                    setFavImage(FAV_IMG_FILLED);
                }

                TransferedData.wordIndex = index;
                TransferedData.word = new Word(TransferedData.dict.getWords().get(index));
                try {
                    switchRightPane(MEANING_PATH);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        TransferedData.dict.size.addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                setListView(dict.getTargetList());
            }

        });
    }

    public void switchRightPane(String path) throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getClassLoader().getResource(path));
        rightPane.getChildren().setAll(node);
    }

    public void search() {
        targetListView.getSelectionModel().selectFirst();
        String target = searchField.getText();

        filteredTargetList
                .setPredicate(target.isEmpty() ? p -> true : p -> p.toLowerCase().startsWith(target.toLowerCase()));
        targetListView.getItems().setAll(filteredTargetList);
        targetListView.getSelectionModel().selectFirst();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dict.loadJson(DICT_PATH);
        TransferedData.dict = dict;

        initButtonControl();
        setListView(dict.getTargetList());
        addListViewListener();
    }
}
