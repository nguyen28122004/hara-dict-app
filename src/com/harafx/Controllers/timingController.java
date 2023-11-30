package com.harafx.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.harafx.Models.Stopwatch;
import com.harafx.Models.TransferedData;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class timingController implements Initializable {
    Stopwatch timer;

    @FXML
    TextField timerLabel = new TextField();
    @FXML
    Pane pauseButton = new Pane();
    @FXML
    Pane continueButton = new Pane();
    @FXML
    Pane reButton = new Pane();

    boolean parseTimeString() {

        Alert alert = new Alert(AlertType.INFORMATION);

        String timeString = timerLabel.getText();
        String[] timeStrArray = timeString.split(":");

        timer.hour = Integer.parseInt(timeStrArray[0]);
        timer.min = Integer.parseInt(timeStrArray[1]);
        timer.sec = Integer.parseInt(timeStrArray[2]);

        if (timer.min > 59 || timer.min < 0 || timer.sec > 59 || timer.sec < 0 || timer.hour > 12) {
            alert.setHeaderText("Please follow the format hh:mm:ss");
            alert.setContentText("hh is smaller than 12\nmm is smaller than 60\\ss is smaller than 60");
            alert.show();
            return false;
        }

        return true;
    }

    void setTimerLabel() {
        String str = new String();
        str += (timer.hour > 9 ? timer.hour : ("0" + timer.hour)) + ":";
        str += (timer.min > 9 ? timer.min : ("0" + timer.min)) + ":";
        str += timer.sec > 9 ? timer.sec : ("0" + timer.sec);
        // System.out.println(str);
        timerLabel.setText(str);
    }

    void addListener() {
        timer.obsTimer.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        setTimerLabel();
                    }
                });

                if (arg2.intValue() == 0) {
                    pauseButton.setVisible(false);
                    continueButton.setVisible(true);
                    timerLabel.setEditable(true);
                }
            }
        });
    }

    void initButonControl() {

        pauseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            pauseTimer();
        });

        continueButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            startTimer();
        });

        reButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            resetTimer();
        });
    }

    private void resetTimer() {
        timer.stopTimer();
        pauseButton.setVisible(false);
        continueButton.setVisible(true);
        timerLabel.setEditable(true);
        timerLabel.setText("00:30:00");
    }

    private void startTimer() {
        if (parseTimeString() == false) {
            return;
        }

        pauseButton.setVisible(true);
        continueButton.setVisible(false);
        timer = new Stopwatch(timer);
        timerLabel.setEditable(false);
        addListener();
        timer.setName("Countdown");
        TransferedData.threads.add(timer);
        timer.start();
    }

    private void pauseTimer() {
        pauseButton.setVisible(false);
        continueButton.setVisible(true);
        timerLabel.setEditable(true);
        timer.stopTimer();
    }

    private void setTimer() {
        for (Stopwatch thread : TransferedData.threads) {
            if (thread.getName().equals("Countdown")) {
                timer = thread;
                setTimerLabel();
                startTimer();
                return;
            }
        }
        timer = new Stopwatch(0, 0, 0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initButonControl();
        setTimer();

    }

}
