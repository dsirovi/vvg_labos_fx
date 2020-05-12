package hr.java.vjezbe.controllers;

import hr.java.vjezbe.entitet.Entitet;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.util.Datoteke;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

public class UnosStanovaController {

    @FXML
    private TextField naslovTextField;
    @FXML
    private TextField opisTextField;
    @FXML
    private TextField kvadraturaTextField;
    @FXML
    private TextField cijenaTextField;
    @FXML
    private ComboBox<Stanje> stanjeComboBox;

    public void initialize(){

        List<Stanje> stanjaList = Arrays.stream(Stanje.values())
//                .map(Stanje::getNaziv)
                .collect(Collectors.toList());


        stanjeComboBox.setItems(FXCollections.observableArrayList(stanjaList));
    }

    public void spremiStan() {
        if (naslovTextField.getText().isBlank() || opisTextField.getText().isBlank() || kvadraturaTextField.getText().isBlank() || stanjeComboBox.getValue() == null || cijenaTextField.getText().isBlank()) {
            Alert upozorenje = new Alert(Alert.AlertType.ERROR);
            upozorenje.setTitle("Pogreska pri unosu");
            upozorenje.setHeaderText("Provjerite podatke o unosu stana");
            String opis = "Nedostaje podatak za: \n";
            if (naslovTextField.getText().isBlank())
                opis += "naslov stana, \n";
            if (opisTextField.getText().isBlank())
                opis += "opis stana, \n";
            if (kvadraturaTextField.getText().isBlank())
                opis += "kvadratura stana, \n";
            if (cijenaTextField.getText().isBlank())
                opis += "cijena stana, \n";
            if (stanjeComboBox.getValue() == null)
                opis += "stanje stana ";
            upozorenje.setContentText(opis);
            upozorenje.showAndWait();
        } else {

            List<Stan> listItems = Datoteke.dohvatiStanove("dat/stanovi");
            OptionalLong maxId = listItems.stream().mapToLong(Entitet::getId).max();
            Stan stan = new Stan(maxId.getAsLong() + 1, naslovTextField.getText(), opisTextField.getText(), new BigDecimal(cijenaTextField.getText()), stanjeComboBox.getValue(), new BigDecimal(kvadraturaTextField.getText()));
            listItems.add(stan);

            String FILE_NAME = PretragaController.getLokacijaDatoteke() + "stanovi";
            try (PrintWriter out = new PrintWriter(new FileWriter(new File(FILE_NAME), true))) {
                out.println(stan.getId());
                out.println(stan.getNaslov());
                out.println(stan.getOpis());
                out.println(stan.getKvadratura());
                out.println(stan.getCijena());
                out.println(stan.getStanje().getId());
            } catch (IOException e) {
                System.err.println(e);
            }

            Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
            upozorenje.setTitle("Uspjesan unos");
            upozorenje.setHeaderText("Podaci o stanu su uspjesno pohranjeni");
            upozorenje.showAndWait();
        }

    }

    public void prikaziPretraguUsluga() {
        BorderPane root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Usluga.fxml"));
            Main.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prikaziPretraguAutomobila() {
        BorderPane root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Automobil.fxml"));
            Main.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prikaziPretraguStanova() {
        BorderPane root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Stan.fxml"));
            Main.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prikaziPretraguPrivatnihKorisnika() {
        BorderPane root;
        try {
            root = FXMLLoader.load(getClass().getResource("/PrivatniKorisnik.fxml"));
            Main.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prikaziPretraguPoslovnihKorisnika() {
        BorderPane root;
        try {
            root = FXMLLoader.load(getClass().getResource("/PoslovniKorisnik.fxml"));
            Main.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unesiPrivatnogKorisnika() {
        BorderPane root;
        try {
            root = FXMLLoader.load(getClass().getResource("/unosPrivatniKorisnik.fxml"));
            Main.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unesiPoslovnogKorisnika() {
        BorderPane root;
        try {
            root = FXMLLoader.load(getClass().getResource("/unosPoslovniKorisnik.fxml"));
            Main.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unesiUslugu() {
        BorderPane root;
        try {
            root = FXMLLoader.load(getClass().getResource("/unosUsluga.fxml"));
            Main.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unesiAutomobil() {
        BorderPane root;
        try {
            root = FXMLLoader.load(getClass().getResource("/unosAutomobil.fxml"));
            Main.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unesiStan() {
        BorderPane root;
        try {
            root = FXMLLoader.load(getClass().getResource("/unosStan.fxml"));
            Main.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
