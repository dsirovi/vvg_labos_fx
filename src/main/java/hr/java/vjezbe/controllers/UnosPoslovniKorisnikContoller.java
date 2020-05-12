package hr.java.vjezbe.controllers;

import hr.java.vjezbe.entitet.Entitet;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
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
import java.util.List;
import java.util.OptionalLong;

public class UnosPoslovniKorisnikContoller {

    @FXML
    private TextField nazivPoslovnogKorisnika;
    @FXML
    private TextField emailPoslovnogKorisnika;
    @FXML
    private TextField webPoslovnogKorisnik;
    @FXML
    private TextField telefonPoslovnogKorisnika;

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

            List<PoslovniKorisnik> listItems = Datoteke.dohvatiPoslovneKorisnike("dat/poslovniKorisnici");
            OptionalLong maxId = listItems.stream().mapToLong(Entitet::getId).max();
            PoslovniKorisnik privatniKorisnik = new PoslovniKorisnik(maxId.getAsLong() + 1, emailPoslovnogKorisnika.getText(), telefonPoslovnogKorisnika.getText(), nazivPoslovnogKorisnika.getText(), webPoslovnogKorisnik.getText());
            listItems.add(privatniKorisnik);

            String FILE_NAME = PretragaController.getLokacijaDatoteke() + "poslovniKorisnici";
            try (PrintWriter out = new PrintWriter(new FileWriter(new File(FILE_NAME), true))) {
                out.println(privatniKorisnik.getId());
                out.println(privatniKorisnik.getNaziv());
                out.println(privatniKorisnik.getEmail());
                out.println(privatniKorisnik.getWeb());
                out.println(privatniKorisnik.getTelefon());
            } catch (IOException e) {
                System.err.println(e);
            }

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
