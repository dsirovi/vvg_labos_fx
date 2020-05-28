package hr.java.vjezbe.controllers;

import hr.java.vjezbe.baze.BazaPodataka;
import hr.java.vjezbe.entitet.Entitet;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

public class UnosUslugaController {

    public List<Usluga> listItems = BazaPodataka.dohvatiUslugePremaKriterijima(null);

    @FXML
    private TextField naslovTextField;
    @FXML
    private TextField opisTextField;
    @FXML
    private TextField cijenaTextField;
    @FXML
    private ComboBox<Stanje> stanjeComboBox;

    public UnosUslugaController() throws BazaPodatakaException {
    }

    public void initialize() {

        List<Stanje> stanjaList = Arrays.stream(Stanje.values())
//                .map(Stanje::getNaziv)
                .collect(Collectors.toList());


        stanjeComboBox.setItems(FXCollections.observableArrayList(stanjaList));
    }

    public void spremiUslugu() throws BazaPodatakaException {
        if (naslovTextField.getText().isBlank() || opisTextField.getText().isBlank() || cijenaTextField.getText().isBlank() || stanjeComboBox.getValue() == null) {
            Alert upozorenje = new Alert(Alert.AlertType.ERROR);
            upozorenje.setTitle("Pogreska pri unosu");
            upozorenje.setHeaderText("Provjerite podatke o unosu usluge");
            String opis = "Nedostaje podatak za: \n";
            if (naslovTextField.getText().isBlank())
                opis += "naslov usluge,\n ";
            if (opisTextField.getText().isBlank())
                opis += "opis usluge, \n";
            if (cijenaTextField.getText().isBlank())
                opis += "cijena usluge, \n";
            if (stanjeComboBox.getValue() == null)
                opis += "stanje usluge ";
            upozorenje.setContentText(opis);
            upozorenje.showAndWait();
        } else {

            OptionalLong maxId = listItems.stream().mapToLong(Entitet::getId).max();
            Usluga nova = new Usluga(maxId.getAsLong() + 1, naslovTextField.getText(), opisTextField.getText(), new BigDecimal(cijenaTextField.getText()), stanjeComboBox.getValue());
            listItems.add(nova);

            try {
                BazaPodataka.pohraniNovuUslugu(nova);
            } catch (BazaPodatakaException e) {
                Alert upozorenje = new Alert(Alert.AlertType.ERROR);
                upozorenje.setTitle("Greska");
                upozorenje.setContentText(e.getMessage());
                upozorenje.showAndWait();
                e.printStackTrace();
                return;
            }
            listItems.add(nova);
            Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
            upozorenje.setTitle("Uspjesan unos");
            upozorenje.setHeaderText("Podaci o usluzi su uspjesno pohranjeni");
            upozorenje.showAndWait();
        }
    }

    public void prikaziPretraguProdaja() {
        BorderPane root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Prodaja.fxml"));
            Main.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
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

    public void unesiProdaju() {
        BorderPane root;
        try {
            root = FXMLLoader.load(getClass().getResource("/UnosProdaja.fxml"));
            Main.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
