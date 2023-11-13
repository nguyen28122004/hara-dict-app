package com.harafx.Controllers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.harafx.Models.Defination;
import com.harafx.Models.Meaning;
import com.harafx.Models.TransferedData;
import com.harafx.Models.Word;

import javafx.fxml.FXML;
import javafx.scene.effect.BlendMode;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class wordFormController {

    final String MEANING_PATH = "src/com/harafx/view/meaning.html";
    final String ADD_PATH = "src/com/harafx/view/add.html";
    final String EDIT_PATH = "src/com/harafx/view/edit.html";

    String htmlString = new String();

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

        defPane.setContextMenuEnabled(false);
        defPane.setBlendMode(BlendMode.DARKEN);
        driver = defPane.getEngine();
        setDefElements();
        driver.loadContent(htmlString);
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

        Word word = TransferedData.word;

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
            defsEl.append(defEl.outerHtml());
            defsEl.append("<hr class=\"solid\">");
            meaningEl.html(meaningEl.select(".meaning").get(0).outerHtml());
        }
        if (word.getDefs().size() > 0) {
            defEl.remove();
        }
        htmlString = doc.toString();
        System.out.println(htmlString);
    }

}
