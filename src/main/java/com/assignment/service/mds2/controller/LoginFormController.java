package com.assignment.service.mds2.controller;

import com.assignment.service.mds2.model.CredentialModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;


public class LoginFormController {

    @FXML
    private JFXButton btnLogin;

    @FXML
    private Text lblForgotPassWord;

    @FXML
    private Pane paneWelcomePageLoadSpace;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUserName;

    private final CredentialModel CREDENTIAL_MODEL;

    public LoginFormController() {
        CREDENTIAL_MODEL = new CredentialModel();
    }

    @FXML
    void btnLoginClick(ActionEvent event) throws Exception {
        if (txtUserName.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Password & UserName fields can't be EMPTY...!").show();
        }else{
            String userName = txtUserName.getText();
            String password = txtPassword.getText();
            if (CREDENTIAL_MODEL.loginCheck(userName,password)){
                new Alert(Alert.AlertType.INFORMATION,"Login Successful!").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Login Failed!").show();
            }
        }
    }

    @FXML
    void clickForgotPassword(MouseEvent event) throws IOException {
        paneWelcomePageLoadSpace.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/view/forgotPasswordForm.fxml"));
        paneWelcomePageLoadSpace.getChildren().setAll(load);
    }


}
