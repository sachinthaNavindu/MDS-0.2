package com.assignment.service.mds2.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ApplicationLoadFormController {

    @FXML
    private AnchorPane applicationLoadPane;

    public void initialize() throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/welcomeForm.fxml"));
        applicationLoadPane.getChildren().add(load);
    }

}
