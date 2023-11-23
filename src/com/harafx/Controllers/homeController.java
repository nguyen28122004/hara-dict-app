package com.harafx.Controllers;

import com.harafx.Models.TransferedData;
import com.harafx.Models.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.parser.ParseException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class homeController extends generalController implements Initializable {
    protected final String DICT_PATH = "src/resource/dict.json";
    @FXML
    TextField nameField = new TextField();

    @FXML
    AnchorPane GridBox1 = new AnchorPane();
    @FXML
    AnchorPane GridBox2 = new AnchorPane();
    @FXML
    AnchorPane GridBox3 = new AnchorPane();
    @FXML
    AnchorPane GridBox4 = new AnchorPane();

    @FXML
    AnchorPane contentPane = new AnchorPane();

    public void loadName() throws FileNotFoundException, IOException, ParseException {
        User.loadUser("src/resource/user.json");
        nameField.setText(User.name);
    }

    public void saveName(KeyEvent event) throws FileNotFoundException {
        User.name = nameField.getText();
        User.saveUser("src/resource/user.json");
    }

    private void initButtonControl() {
        GridBox1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            showSearchPane();
        });
        GridBox2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            showGgTranslatePane();
        });
        GridBox3.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            showGamePane();
        });
        GridBox4.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            showFavPane();
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TransferedData.dict.loadJson(DICT_PATH);
        try {
            loadName();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        initButtonControl();
    }

}
