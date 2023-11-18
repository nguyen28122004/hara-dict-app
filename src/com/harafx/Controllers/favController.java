package com.harafx.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.harafx.Models.TransferedData;
import com.harafx.Models.User;

public class favController extends searchController {

    public void initialize(URL location, ResourceBundle resources) {

        dict.loadJson(DICT_PATH);
        TransferedData.dict = dict;

        initButtonControl();
        setListView(User.favList);
        addListViewListener();
    }
}
