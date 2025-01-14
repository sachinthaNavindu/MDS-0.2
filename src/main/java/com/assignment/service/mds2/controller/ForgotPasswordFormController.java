package com.assignment.service.mds2.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class ForgotPasswordFormController {

    @FXML
    private ImageView btnBack;

    @FXML
    private JFXButton btnLoginAndUpdate;

    @FXML
    private JFXButton btnOtpSend;

    @FXML
    private JFXComboBox<?> comboBoxUserName;

    @FXML
    private Text lblCreateAcc;

    @FXML
    private Pane paneWelcomePageLoadSpace;

    @FXML
    private JFXTextField txtOtpCode;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtPassword1;

    @FXML
    void ClickBackIcon(MouseEvent event) throws IOException {
        paneWelcomePageLoadSpace.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        paneWelcomePageLoadSpace.getChildren().add(root);
    }

    @FXML
    void ClickCreateAcc(MouseEvent event) throws IOException {
        paneWelcomePageLoadSpace.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/view/createAccForm.fxml"));
        paneWelcomePageLoadSpace.getChildren().add(load);
    }

    @FXML
    void ClickOtpSend(ActionEvent event) {

    }

    @FXML
    void btnLoginAndUpdateClick(ActionEvent event) {

    }

    @FXML
    void comboBoxUserName(ActionEvent event) {

    }

}
