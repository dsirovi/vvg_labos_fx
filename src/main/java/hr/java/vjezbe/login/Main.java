package hr.java.vjezbe.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage Stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Stage.setTitle("JavaFX application");
        Stage.setScene(new Scene(root, 300, 275));
        Stage.show();
    }

    static void setMainPage(BorderPane root) throws IOException {
        Parent mainPage = FXMLLoader.load(Main.class.getResource("/Automobili.fxml"));
        Scene scene = new Scene(mainPage, 600, 500);
        Stage window = (Stage) root.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
