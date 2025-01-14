package com.assignment.service.mds2.controller;

import com.assignment.service.mds2.model.AdminModel;
import com.assignment.service.mds2.model.CredentialModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreateAccountFormController {

    @FXML
    private ImageView btnBack;

    @FXML
    private JFXButton btnNext;

    @FXML
    private JFXComboBox<String> comboBoxAdminId;

    @FXML
    private Label lblAdminName;

    @FXML
    private Text lblLoginPage;

    @FXML
    private Pane paneWelcomePageLoadSpace;

    @FXML
    private JFXTextField txtConfirmPasswod;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtRecoverEmail;

    @FXML
    private JFXTextField txtUserName;

    private final CredentialModel CREDENTIAL_MODEL;
    private final AdminModel ADMIN_MODEL;

    public CreateAccountFormController() {
        CREDENTIAL_MODEL = new CredentialModel();
        ADMIN_MODEL = new AdminModel();
    }

    public void initialize() throws SQLException {
        loadAdminIdsToComboBox();
    }

    private void loadAdminIdsToComboBox() throws SQLException {
        ArrayList<String> adminIds = CREDENTIAL_MODEL.loadAdminIdEmptyUser();
        ObservableList<String> observableList = FXCollections.observableArrayList(adminIds);
        comboBoxAdminId.setItems(observableList);
    }

    @FXML
    void ClickBackIcon(MouseEvent event) throws IOException {
        paneWelcomePageLoadSpace.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/view/forgotPasswordForm.fxml"));
        paneWelcomePageLoadSpace.getChildren().add(load);
    }

    @FXML
    void ClickComboBoxAdminId(ActionEvent event) throws SQLException {
        String adminId = comboBoxAdminId.getValue();
        lblAdminName.setText(ADMIN_MODEL.getAdminName(adminId));
    }

    @FXML
    void ClickNextToCreateNewAcc(ActionEvent event) {

    }

    @FXML
    void ClickLoginPagelbl(MouseEvent event) throws IOException {
        paneWelcomePageLoadSpace.getChildren().clear();
        Parent load =FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        paneWelcomePageLoadSpace.getChildren().add(load);
    }

}


