package hr.java.vjezbe.login;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PretragaController {


    public void prikaziPretraguAutomobila() {
        try {
            BorderPane root = FXMLLoader.load(getClass().getResource("/Automobili.fxml"));
            Main.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void prikaziPretraguAutomobila() {
//        try {
//            Parent center = FXMLLoader.load(getClass().getResource("/Automobili.fxml"));
//            Scene automobil = new Scene(center);
//            Stage window = (Stage) myMenuBar.getScene().getWindow();
//            window.setScene(automobil);
//            window.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
