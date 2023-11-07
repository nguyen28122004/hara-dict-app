package com.harafx.Models;

import javafx.beans.Observable;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;

public class TransferedData {
    public static Dictionary dict;
    public static Defination def;
    public static Meaning meaning;
    public static int wordIndex = -1;
    public static int meaningCount = 1;
    public static IntegerProperty obsMeaningCount = new SimpleIntegerProperty(meaningCount);
}
