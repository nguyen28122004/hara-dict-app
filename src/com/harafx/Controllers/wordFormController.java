package com.harafx.Controllers;

import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.harafx.Models.Defination;
import com.harafx.Models.Json;
import com.harafx.Models.Meaning;
import com.harafx.Models.TransferedData;
import com.harafx.Models.Word;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class wordFormController extends htmlController {

    final String MEANING_PATH = "src/com/harafx/view/meaning.html";
    final String FORM_PATH = "src/com/harafx/view/form.html";
    final String DICT_PATH = "src/resource/dict.json";

    @FXML
    AnchorPane wrapFormPane = new AnchorPane();

    @FXML
    Label targetLabel = new Label();
    @FXML
    TextField targetField = new TextField();

    @FXML
    Button okButton = new Button();
    @FXML
    Button cancelButton = new Button();

    @FXML
    WebView defPane = new WebView();

    @FXML
    WebEngine driver = new WebEngine();

    Document doc = Jsoup.parse(htmlString);
    org.jsoup.nodes.Element body;
    org.jsoup.nodes.Element us;
    org.jsoup.nodes.Element uk;
    org.jsoup.nodes.Element defsEl;
    org.jsoup.nodes.Element defEl;
    org.jsoup.nodes.Element wordTypeEl;
    org.jsoup.nodes.Element meaningEl;
    org.jsoup.nodes.Element viEl;
    org.jsoup.nodes.Element enEl;
    org.jsoup.nodes.Element exampleEl;

    void parseElements() {
        doc = Jsoup.parse(htmlString);
        body = doc.body();
        us = body.select("#us").get(0);
        uk = body.select("#uk").get(0);
        defsEl = body.select(".definations-wrap").get(0);
        defEl = defsEl.select("#sample").get(0);
        wordTypeEl = defEl.select(".word-type").get(0);
        meaningEl = defEl.select(".meanings").get(0);
        viEl = meaningEl.select(".vi").get(0);
        enEl = meaningEl.select(".en").get(0);
        exampleEl = meaningEl.select(".example").get(0);
    }

    public void loadHTML(String path) {
        loadHTMLString(path);
        parseElements();

        defPane.setContextMenuEnabled(false);
        defPane.setBlendMode(BlendMode.DARKEN);
        driver = defPane.getEngine();
        setDefElements();
        driver.loadContent(htmlString);

    }

    void setType(Element wordTypeEl, String wordType) {
        Elements options = wordTypeEl.select("#word-type>option");

        options.get(0).attr("selected", "selected");

        for (Element option : options) {
            if (option.text().equals(wordType)) {
                option.attr("selected", "selected");
            } else {
                option.removeAttr("selected");
            }
        }
    }

    private void setMeanings(Defination def) {
        for (Meaning meaning : def.getMeanings()) {

            viEl = meaningEl.select(".vi").get(0);
            enEl = meaningEl.select(".en").get(0);
            exampleEl = meaningEl.select(".example").get(0);

            viEl.text(meaning.getVi());
            enEl.text(meaning.getEn());
            exampleEl.text(meaning.getExample());
            meaningEl.append(meaningEl.select(".meaning").get(0).outerHtml());
        }
    }

    private void setDefs(Word word) {
        for (Defination def : word.getDefs()) {

            // wordTypeEl.text(def.getType());
            setType(wordTypeEl, def.getType());

            setMeanings(def);

            meaningEl.select(".meaning").get(0).remove();
            defEl.removeAttr("id");
            defsEl.append(defEl.outerHtml());
            defEl.attr("id", "sample");
            meaningEl.html(meaningEl.select(".meaning").get(0).outerHtml());
        }
    }

    private void clearSample() {
        setType(wordTypeEl, "");
        meaningEl.html(meaningEl.select(".meaning").get(0).outerHtml());
        viEl = meaningEl.select(".vi").get(0);
        enEl = meaningEl.select(".en").get(0);
        exampleEl = meaningEl.select(".example").get(0);
        viEl.text("");
        enEl.text("");
        exampleEl.text("");
    }

    Word getDefElement() {
        String jsonString = (String) driver.executeScript("getJsonString()");
        // System.out.println(jsonString);
        Word word = new Word();

        try {
            word.convertFromJson(Json.loadObjectFromString(jsonString));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        word.setTarget(targetField.getText());

        return word;
    }

    void setDefElements() {

        Word word = TransferedData.word;
        // word.debug();

        targetField.setText(word.getTarget());

        us.text(word.getIpa().getUs());
        uk.text(word.getIpa().getUk());

        setDefs(word);

        clearSample();
        defEl.attr("style", "display:none;");
        htmlString = doc.toString();
        // System.out.println(htmlString);
    }

}
