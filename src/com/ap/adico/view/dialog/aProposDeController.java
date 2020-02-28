package com.ap.adico.view.dialog;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class aProposDeController implements Initializable {
    @FXML
    Label vJava;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vJava.setText(System.getProperty("java.version"));
    }
}
