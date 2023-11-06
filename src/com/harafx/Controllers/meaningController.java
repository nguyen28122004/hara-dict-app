package com.harafx.Controllers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.text.html.parser.Element;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.harafx.Models.Defination;
import com.harafx.Models.Meaning;
import com.harafx.Models.TransferedData;
import com.harafx.Models.Word;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class meaningController implements Initializable {

    String htmlString = new String();

    @FXML
    Label targetLabel = new Label();
    @FXML
    Label usPronunciation = new Label();
    @FXML
    Label ukPronunciation = new Label();
    @FXML
    WebView defPane = new WebView();

    @FXML
    WebEngine driver = new WebEngine();

    public void loadHTML(String path) {
        try (FileReader file = new FileReader(new File(path))) {

            int i;
            while ((i = file.read()) != -1) {
                htmlString += (char) i;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void setDefElements() {
        Document doc = Jsoup.parse(htmlString);
        org.jsoup.nodes.Element body = doc.body();
        org.jsoup.nodes.Element defsEl = body.select(".defination-wrap").get(0);
        org.jsoup.nodes.Element defEl = defsEl.select("#sample").get(0);
        org.jsoup.nodes.Element wordTypeEl = defEl.select(".word-type").get(0);
        org.jsoup.nodes.Element meaningEl = defEl.select(".meanings").get(0);
        org.jsoup.nodes.Element viEl = meaningEl.select(".vi").get(0);
        org.jsoup.nodes.Element enEl = meaningEl.select(".en").get(0);
        org.jsoup.nodes.Element exampleEl = meaningEl.select(".example").get(0);

        Word word = TransferedData.dict.getWords().get(TransferedData.wordIndex);

        for (Defination def : word.getDefs()) {
            wordTypeEl.text(def.getType());
            for (Meaning meaning : def.getMeanings()) {
                viEl = meaningEl.select(".vi").get(0);
                enEl = meaningEl.select(".en").get(0);
                exampleEl = meaningEl.select(".example").get(0);
                viEl.text(meaning.getVi());
                enEl.text(meaning.getEn());
                exampleEl.text(meaning.getExample());
                meaningEl.append(meaningEl.select(".meaning").get(0).outerHtml());
            }
            meaningEl.select(".meaning").get(0).remove();
            // meaningEl.firstElementChild().remove();
            defsEl.append(defEl.outerHtml());
            defsEl.append("<hr class=\"solid\">");
            meaningEl.html(meaningEl.select(".meaning").get(0).outerHtml());
            System.out.println(meaningEl.html());
            System.out.println("=================");
            // break;
        }
        defEl.remove();
        htmlString = doc.toString();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Word word = TransferedData.dict.getWords().get(TransferedData.wordIndex);
        targetLabel.setText(word.getTarget());
        usPronunciation.setText("/" + word.getIpa().getUs() + "/");
        ukPronunciation.setText("/" + word.getIpa().getUk() + "/");

        defPane.setContextMenuEnabled(false);
        defPane.setBlendMode(BlendMode.DARKEN);
        driver = defPane.getEngine();
        loadHTML("src/com/harafx/view/meaning.html");
        setDefElements();
        driver.loadContent(htmlString);
    }

}
