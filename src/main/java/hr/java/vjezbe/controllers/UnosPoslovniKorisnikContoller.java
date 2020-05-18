package hr.java.vjezbe.controllers;

import hr.java.vjezbe.baze.BazaPodataka;
import hr.java.vjezbe.entitet.Entitet;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.List;
import java.util.OptionalLong;

public class UnosPoslovniKorisnikContoller {

    List<PoslovniKorisnik> poslovniKorisnici = BazaPodataka.dohvatiPoslovnogKorisnikaPremaKriterijima(null);

    @FXML
    private TextField nazivPoslovnogKorisnika;
    @FXML
    private TextField emailPoslovnogKorisnika;
    @FXML
    private TextField webPoslovnogKorisnik;
    @FXML
    private TextField telefonPoslovnogKorisnika;

    public UnosPoslovniKorisnikContoller() throws BazaPodatakaException {
    }

    public void spremiPoslovnogKorisnika() {

        if (nazivPoslovnogKorisnika.getText().isBlank() || emailPoslovnogKorisnika.getText().isBlank() || webPoslovnogKorisnik.getText().isBlank()
                || telefonPoslovnogKorisnika.getText().isBlank()) {
            Alert upozorenje = new Alert(Alert.AlertType.ERROR);
            upozorenje.setTitle("Pogreska pri unosu");
            upozorenje.setHeaderText("Provjerite podatke o unosu korisnika");
            String opis = "Nedostaje podatak za: \n";
            if (nazivPoslovnogKorisnika.getText().isBlank())
                opis += "naziv korisnika, \n";
            if (emailPoslovnogKorisnika.getText().isBlank())
                opis += "email korisnika, \n";
            if (webPoslovnogKorisnik.getText().isBlank())
                opis += "web korisnika, \n";
            if (telefonPoslovnogKorisnika.getText().isBlank())
                opis += "telefon korisnika ";
            upozorenje.setContentText(opis);
            upozorenje.showAndWait();
        } else {
            OptionalLong maxId = poslovniKorisnici.stream().mapToLong(Entitet::getId).max();

            PoslovniKorisnik noviPoslovniKorisnik = new PoslovniKorisnik(maxId.getAsLong() + 1, emailPoslovnogKorisnika.getText(), telefonPoslovnogKorisnika.getText(), nazivPoslovnogKorisnika.getText(), webPoslovnogKorisnik.getText());
            poslovniKorisnici.add(noviPoslovniKorisnik);


            try {
                BazaPodataka.pohraniPoslovnogKorisnika(noviPoslovniKorisnik);
            } catch (BazaPodatakaException e) {
                Alert upozorenje = new Alert(Alert.AlertType.ERROR);
                upozorenje.setTitle("Greska");
                upozorenje.setContentText(e.getMessage());
                upozorenje.showAndWait();
                e.printStackTrace();
                return;
            }
            poslovniKorisnici.add(noviPoslovniKorisnik);
            Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
            upozorenje.setTitle("Uspjesan unos");
            upozorenje.setHeaderText("Podaci o korisniku su uspjesno pohranjeni");
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
