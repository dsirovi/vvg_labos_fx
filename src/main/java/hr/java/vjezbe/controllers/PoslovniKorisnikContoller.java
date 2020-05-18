package hr.java.vjezbe.controllers;

import hr.java.vjezbe.baze.BazaPodataka;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
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

public class PoslovniKorisnikContoller {

    List<PoslovniKorisnik> poslovniKorisnik;

    @FXML
    private TextField nazivTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField webTextField;
    @FXML
    private TextField telefonTextField;
    @FXML
    private TableView<PoslovniKorisnik> tableViewPoslovniKorisnik;
    @FXML
    private TableColumn<Korisnik, String> tableViewNazivPoslovnogKorisnika;
    @FXML
    private TableColumn<Korisnik, String> tableViewEmailPoslovnogKorisnika;
    @FXML
    private TableColumn<Korisnik, String> tableViewWebPoslovnogKorisnika;
    @FXML
    private TableColumn<Korisnik, String> tableViewTelefonPoslovnogKorisnika;

    public void pretraziPoslovnogKorisnika() {
        List<PoslovniKorisnik> filtriraniPoslovniKorisnici = poslovniKorisnik.stream().filter(p ->
                p.dohvatiKontakt().toLowerCase().contains(nazivTextField.getText())
                        && p.dohvatiKontakt().toLowerCase().contains(webTextField.getText())
                        && p.dohvatiKontakt().toLowerCase().contains(emailTextField.getText())
                        && p.dohvatiKontakt().toLowerCase().contains(telefonTextField.getText())).collect(Collectors.toList());
        ObservableList<PoslovniKorisnik> pretraga = FXCollections.observableList(filtriraniPoslovniKorisnici);
        tableViewPoslovniKorisnik.setItems(pretraga);
    }

    public void initialize() throws BazaPodatakaException {
        tableViewNazivPoslovnogKorisnika.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        tableViewEmailPoslovnogKorisnika.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableViewWebPoslovnogKorisnika.setCellValueFactory(new PropertyValueFactory<>("web"));
        tableViewTelefonPoslovnogKorisnika.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        poslovniKorisnik = BazaPodataka.dohvatiPoslovnogKorisnikaPremaKriterijima((PoslovniKorisnik) poslovniKorisnik);
        pretraziPoslovnogKorisnika();
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
