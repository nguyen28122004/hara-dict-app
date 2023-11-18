package com.harafx.Controllers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class htmlController {
    String htmlString = new String();

    public void loadHTMLString(String path) {
        htmlString = "";
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
}
