package com.assignment.service.mds2.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class WelcomeFormController {

    @FXML
    private Pane paneWelcomePageLoadSpace;

    public void initialize() throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        paneWelcomePageLoadSpace.getChildren().add(load);
    }
}
