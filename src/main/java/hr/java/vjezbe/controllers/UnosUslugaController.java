package hr.java.vjezbe.controllers;

import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Entitet;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.util.Datoteke;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.OptionalLong;

public class UnosUslugaController {

    @FXML
    private TextField naslovTextField;
    @FXML
    private TextField opisTextField;
    @FXML
    private TextField cijenaTextField;

    public void spremiUslugu() {
        if (naslovTextField.getText().isBlank() || opisTextField.getText().isBlank() || cijenaTextField.getText().isBlank()) {
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
            upozorenje.setContentText(opis);
            upozorenje.showAndWait();
        } else {

            List<Artikl> listItems = Datoteke.dohvatiUsluge("dat/usluge");
            OptionalLong maxId = listItems.stream().mapToLong(Entitet::getId).max();
            Usluga usluga = new Usluga(maxId.getAsLong() + 1, naslovTextField.getText(), opisTextField.getText(), new BigDecimal(cijenaTextField.getText()));
            listItems.add(usluga);

            String FILE_NAME = PretragaController.getLokacijaDatoteke() + "usluge";
            try (PrintWriter out = new PrintWriter(new FileWriter(new File(FILE_NAME), true))) {
                out.println(usluga.getId());
                out.println(usluga.getNaslov());
                out.println(usluga.getOpis());
                out.println(usluga.getCijena());
            } catch (IOException e) {
                System.err.println(e);
            }

            Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
            upozorenje.setTitle("Uspjesan unos");
            upozorenje.setHeaderText("Podaci o usluzi su uspjesno pohranjeni");
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
