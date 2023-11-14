package com.harafx.Models;

import javafx.beans.Observable;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;

public class TransferedData {
    public static Dictionary dict = new Dictionary();
    public static Word word = new Word();
    public static Defination def = new Defination();
    public static Meaning meaning = new Meaning();
    public static int wordIndex = -1;
}
