package hr.java.vjezbe.controllers;

import hr.java.vjezbe.entitet.Entitet;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
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

public class UnosPrivatniKorisnikController {

    @FXML
    private TextField imePrivatnogKorisnika;
    @FXML
    private TextField prezimePrivatnogKorisnika;
    @FXML
    private TextField emailPrivatnogKorisnika;
    @FXML
    private TextField telefonPrivatnogKorisnika;

    public void spremiPrivatnogKorisnika() {

        if (imePrivatnogKorisnika.getText().isBlank() || prezimePrivatnogKorisnika.getText().isBlank() || emailPrivatnogKorisnika.getText().isBlank()
                || telefonPrivatnogKorisnika.getText().isBlank()) {
            Alert upozorenje = new Alert(Alert.AlertType.ERROR);
            upozorenje.setTitle("Pogreska pri unosu");
            upozorenje.setHeaderText("Provjerite podatke o unosu korisnika");
            String opis = "Nedostaje podatak za: \n";
            if (imePrivatnogKorisnika.getText().isBlank())
                opis += "ime korisnika, \n";
            if (prezimePrivatnogKorisnika.getText().isBlank())
                opis += "prezime korisnika, \n";
            if (emailPrivatnogKorisnika.getText().isBlank())
                opis += "email korisnika, \n";
            if (telefonPrivatnogKorisnika.getText().isBlank())
                opis += "telefon korisnika ";
            upozorenje.setContentText(opis);
            upozorenje.showAndWait();
        } else {

            List<PrivatniKorisnik> listItems = Datoteke.dohvatiPrivatneKorisnike("dat/privatniKorisnici");
            OptionalLong maxId = listItems.stream().mapToLong(Entitet::getId).max();
            PrivatniKorisnik privatniKorisnik = new PrivatniKorisnik(maxId.getAsLong() + 1, emailPrivatnogKorisnika.getText(), telefonPrivatnogKorisnika.getText(), imePrivatnogKorisnika.getText(), prezimePrivatnogKorisnika.getText());
            listItems.add(privatniKorisnik);

            String FILE_NAME = PretragaController.getLokacijaDatoteke() + "privatniKorisnici";
            try (PrintWriter out = new PrintWriter(new FileWriter(new File(FILE_NAME), true))) {
                out.println(privatniKorisnik.getId());
                out.println(privatniKorisnik.getIme());
                out.println(privatniKorisnik.getPrezime());
                out.println(privatniKorisnik.getEmail());
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
