package com.harafx.Controllers;

import java.io.IOException;

import com.harafx.Models.TranslateAPI;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class CBListener implements Runnable {

    StringProperty clipboardContent = new SimpleStringProperty("");

    private Button initStage(Stage popup) {
        Robot robot = new Robot();
        Button button = new Button("Translate");
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color:#16325c; -fx-background-radius:10;   " + //
                "   -fx-border-color: white;" + //
                "    -fx-border-width: 2px;" + //
                "    -fx-border-radius: 10px;");
        VBox vBox = new VBox(button);
        vBox.setStyle("-fx-background-color: transparent");

        popup.setScene(new Scene(vBox));
        popup.setAlwaysOnTop(true);
        popup.setX(robot.getMouseX() + 10);
        popup.setY(robot.getMouseY() + 10);
        popup.setTitle("Auto Translate");
        popup.getIcons().add(new Image("resource/icon.jpg"));

        popup.focusedProperty().addListener((obs, was, isNow) -> {
            if (isNow == false) {
                popup.close();
            }
        });
        popup.show();
        return button;
    }

    private void translate(Stage popup, Text text, String target) {
        new Thread(() -> {
            String tmp = new String();
            try {
                tmp = TranslateAPI.translate("en", "vi", target);
                System.out.println(tmp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            final String tmp2 = tmp;
            Platform.runLater(() -> {
                updateText(popup, text, tmp2);
            });
        }).start();
    }

    private void updateText(Stage popup, Text text, final String tmp2) {
        text.setText(tmp2);
        text.setStyle("-fx-text-fill:#16325c");
        ScrollPane scrollPane = new ScrollPane(text);
        scrollPane.setStyle(
                "-fx-background:#cccccc ;-fx-padding:10; -fx-text-fill:#16325c; -fx-font-size:16; -fx-max-height:300");
        popup.setScene(new Scene(scrollPane));
    }

    @Override
    public void run() {
        Clipboard cb = Clipboard.getSystemClipboard();
        Stage popup = new Stage();
        popup.initStyle(StageStyle.UNDECORATED);

        clipBoardListener(cb, popup);

    }

    private void clipBoardListener(Clipboard cb, Stage popup) {
        new com.sun.glass.ui.ClipboardAssistance(com.sun.glass.ui.Clipboard.SYSTEM) {
            @Override
            public void contentChanged() {
                popup.hide();
                // System.out.print("System clipboard content changed: ");
                // System.out.println(cb.getString());

                Button button = initStage(popup);

                button.setOnAction(event -> {
                    String res = new String("111");
                    Text text = new Text(res);

                    text.setWrappingWidth(400);
                    text.styleProperty().set(
                            "-fx-padding:10px; -fx-text-fill:#fff; -fx-background-color:#16325c;-fx-background-radius:10;"
                                    + //
                                    "   -fx-border-color: white;" + //
                                    "    -fx-border-width: 2px;" + //
                                    "    -fx-border-radius: 10px;");

                    String target = (String) cb.getContent(DataFormat.PLAIN_TEXT);
                    translate(popup, text, target);
                });
            }

        };
    }

}
