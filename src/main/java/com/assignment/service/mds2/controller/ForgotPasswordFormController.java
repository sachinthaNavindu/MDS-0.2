package com.assignment.service.mds2.controller;

import com.assignment.service.mds2.dto.CredentialDto;
import com.assignment.service.mds2.model.CredentialModel;
import com.assignment.service.mds2.util.EmailBodyUtil;
import com.assignment.service.mds2.util.OtpCodeGenerateUtil;
import com.assignment.service.mds2.util.RegexUtil;
import com.assignment.service.mds2.util.SendEmailUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ForgotPasswordFormController {

    @FXML
    private ImageView btnBack;

    @FXML
    private JFXButton btnLoginAndUpdate;

    @FXML
    private JFXButton btnOtpSend;

    @FXML
    private JFXComboBox<String> comboBoxUserName;

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

    private final CredentialModel CREDENTIAL_MODEL;

    public ForgotPasswordFormController() {
        CREDENTIAL_MODEL = new CredentialModel();
    }

    static String otpCode = "";

    public void initialize() throws SQLException {
        loadUsernameToComboBox();
    }

    private void loadUsernameToComboBox() throws SQLException {
        ArrayList<String> userNames = CREDENTIAL_MODEL.getUserName();
        ObservableList<String> observableList = FXCollections.observableList(userNames);
        comboBoxUserName.setItems(observableList);
    }

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
    void ClickOtpSend(ActionEvent event) throws SQLException {
        if (comboBoxUserName.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Please Select user name", ButtonType.OK).show();
        }else{
            btnOtpSend.setDisable(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(20));
            pause.setOnFinished(e -> {btnOtpSend.setDisable(false);});
            pause.play();

            otpCode = OtpCodeGenerateUtil.generateOtp();
            String email = CREDENTIAL_MODEL.getRecoverEmail(comboBoxUserName.getValue());
            String emailBody = EmailBodyUtil.generateEmailBody(otpCode);
            SendEmailUtil.sendEmailAsync(email,"Password change", emailBody);
        }
    }

    @FXML
    void btnLoginAndUpdateClick(ActionEvent event) throws SQLException {
            if (comboBoxUserName.getValue() == null || txtOtpCode.getText().isEmpty() || txtPassword.getText().isEmpty() || txtPassword1.getText().isEmpty() ) {
                new Alert(Alert.AlertType.ERROR, "Textfields cannot be empty", ButtonType.OK).show();
            }else{
                if (!(txtOtpCode.getText().equals(otpCode))){
                    new Alert(Alert.AlertType.ERROR, "OTP Code does not match", ButtonType.OK).show();
                } else if (!(RegexUtil.checkPasswords(txtPassword.getText()))) {
                    txtPassword.setStyle(txtPassword.getStyle() + " -fx-text-fill: #b33939");
                }else if(!(txtPassword.getText().equals(txtPassword1.getText()))){
                    new Alert(Alert.AlertType.ERROR, "New password does not match with confirm password field", ButtonType.OK).show();
                }else{
                    CredentialDto credentialDto = new CredentialDto(comboBoxUserName.getValue(),"",txtPassword.getText(),"");
                    boolean isUpdated = CREDENTIAL_MODEL.updatePassword(credentialDto);

                    if (isUpdated){
                        new Alert(Alert.AlertType.INFORMATION, "Password updated", ButtonType.OK).show();
                    }else{
                        new Alert(Alert.AlertType.ERROR, "Password could not be updated", ButtonType.OK).show();
                    }
                }
            }
    }

    @FXML
    void comboBoxUserName(ActionEvent event) {

    }

}
