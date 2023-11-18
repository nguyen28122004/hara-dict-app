package com.harafx.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Stopwatch extends Thread {
    public int sec = 0;
    public int min = 0;
    public int hour = 0;
    public int totalTime = 0; // in sec
    public IntegerProperty obsTimer = new SimpleIntegerProperty(0);
    public boolean isStopped = false;

    public Stopwatch(int sec) {
        this.totalTime = sec;
        this.hour = totalTime / 3600;
        this.min = (totalTime - this.hour * 3600) / 60;
        this.sec = totalTime - 3600 * this.hour - 60 * this.min;
    }

    public Stopwatch(int min, int sec) {
        this.totalTime = min * 60 + sec;
        this.sec = sec;
        this.min = min;
        this.hour = this.totalTime / 3600;
    }

    public Stopwatch(int hour, int min, int sec) {
        this.hour = hour;
        this.min = min;
        this.sec = sec;
        this.totalTime = hour * 3600 + min * 60 + sec;
    }

    public Stopwatch(Stopwatch timer) {
        this.hour = timer.hour;
        this.min = timer.min;
        this.sec = timer.sec;
        this.totalTime = this.hour * 3600 + this.min * 60 + this.sec;
    }

    public void startTimer() {
        isStopped = false;
        while (totalTime > 0 && isStopped == false) {

            if (totalTime % 3600 == 0 && this.hour > 0)
                this.hour--;

            if (totalTime % 60 == 0 && this.min > 0)
                this.min--;
            else if (totalTime % 60 == 0 && this.min == 0)
                this.min = 59;

            try {
                totalTime--;
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
            this.sec = totalTime % 60;
            if (isStopped || Thread.interrupted()) {
                break;
            }
            this.obsTimer.set(this.totalTime);
        }
    }

    public void stopTimer() {
        isStopped = true;
    }

    @Override
    public void run() {
        startTimer();

    }

}
