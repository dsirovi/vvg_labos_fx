package hr.java.vjezbe.controllers;

import hr.java.vjezbe.baze.BazaPodataka;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PrivatniKorisnikContoller {

    List<PrivatniKorisnik> privatniKorisnik;

    @FXML
    private TextField imeTextField;
    @FXML
    private TextField prezimeTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField telefonTextField;
    @FXML
    private TableView<PrivatniKorisnik> tableViewPrivatniKorisnik;
    @FXML
    private TableColumn<Korisnik, String> tableViewImePrivatnogKorisnika;
    @FXML
    private TableColumn<Korisnik, String> tableViewPrezimePrivatnogKorisnika;
    @FXML
    private TableColumn<Korisnik, String> tableViewEmailPrivatnogKorisnika;
    @FXML
    private TableColumn<Korisnik, String> tableViewTelefonPrivatnogKorisnika;

    public void pretraziPrivatnogKorisnika() {
        List<PrivatniKorisnik> filtriraniPrivatniKorisnici = privatniKorisnik.stream().filter(p ->
                p.dohvatiKontakt().toLowerCase().contains(imeTextField.getText())
                        && p.dohvatiKontakt().toLowerCase().contains(prezimeTextField.getText())
                        && p.dohvatiKontakt().toLowerCase().contains(emailTextField.getText())
                        && p.dohvatiKontakt().toLowerCase().contains(telefonTextField.getText())).collect(Collectors.toList());
        ObservableList<PrivatniKorisnik> pretraga = FXCollections.observableList(filtriraniPrivatniKorisnici);
        tableViewPrivatniKorisnik.setItems(pretraga);
    }


    public void initialize() throws BazaPodatakaException {
        tableViewImePrivatnogKorisnika.setCellValueFactory(new PropertyValueFactory<Korisnik, String>("ime"));
        tableViewPrezimePrivatnogKorisnika.setCellValueFactory(new PropertyValueFactory<Korisnik, String>("prezime"));
        tableViewEmailPrivatnogKorisnika.setCellValueFactory(new PropertyValueFactory<Korisnik, String>("email"));
        tableViewTelefonPrivatnogKorisnika.setCellValueFactory(new PropertyValueFactory<Korisnik, String>("telefon"));
        privatniKorisnik = BazaPodataka.dohvatiPrivatnogKorisnikaPremaKriterijima((PrivatniKorisnik) privatniKorisnik);
        pretraziPrivatnogKorisnika();
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
}
