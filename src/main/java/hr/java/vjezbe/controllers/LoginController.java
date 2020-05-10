package hr.java.vjezbe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    public void prikaziPretragu(ActionEvent event) {

        String username = usernameTextField.getText();
        String password = passwordField.getText();


        boolean error = false;
        StringBuffer errorMessage = new StringBuffer();
        if (username.isBlank()) {
            errorMessage.append("Please enter username");
            error = true;
        }
        if (password.isEmpty()) {
            errorMessage.append("Invalid password!");
            error = true;
        }

        if (error) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Please enter correct username/password");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Successful");
            alert.setHeaderText(null);
            alert.setContentText("Welcome " + usernameTextField.getText() + ", to JavaFX application");
            alert.showAndWait();

            try {
                Parent center = FXMLLoader.load(getClass().getResource("/Pretraga.fxml"));
                Scene pretraga = new Scene(center);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(pretraga);
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

