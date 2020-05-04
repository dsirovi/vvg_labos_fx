package hr.java.vjezbe.login;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    public void login(){

        String username = usernameTextField.getText();
        String password = passwordField.getText();

        boolean error = false;
        StringBuffer errorMessage = new StringBuffer();
        if(username.isBlank()){
            errorMessage.append("Please enter username");
            error = true;
        }
        if (password.isEmpty()){
            errorMessage.append("Invalid password!");
            error = true;
        }

        Alert alert;
        if (error){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Please enter correct username/password");

        }else{
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Successful");
            alert.setHeaderText(null);
            alert.setContentText("Welcome " + usernameTextField.getText() + ", to JavaFX application");

        }
        alert.showAndWait();
    }
}
