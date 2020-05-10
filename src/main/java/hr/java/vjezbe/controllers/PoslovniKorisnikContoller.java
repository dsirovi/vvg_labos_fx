package hr.java.vjezbe.controllers;

import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.util.Datoteke;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.FileNotFoundException;
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
    private TableView<Korisnik> tableViewPoslovniKorisnik;
    @FXML
    private TableColumn<Korisnik, String> tableViewNazivPrivatnogKorisnika;
    @FXML
    private TableColumn<Korisnik, String> tableViewEmailPrivatnogKorisnika;
    @FXML
    private TableColumn<Korisnik, String> tableViewWebPrivatnogKorisnika;
    @FXML
    private TableColumn<Korisnik, String> tableViewTelefonPrivatnogKorisnika;

    public void pretraziPoslovnogKorisnika() {
        List<Korisnik> filtriraniPoslovniKorisnici = poslovniKorisnik.stream().filter(p ->
                p.dohvatiKontakt().toLowerCase().contains(nazivTextField.getText())
                        && p.dohvatiKontakt().toLowerCase().contains(webTextField.getText())
                        && p.dohvatiKontakt().toLowerCase().contains(emailTextField.getText())
                        && p.dohvatiKontakt().toLowerCase().contains(telefonTextField.getText())).collect(Collectors.toList());
        ObservableList<Korisnik> pretraga = FXCollections.observableList(filtriraniPoslovniKorisnici);
        tableViewPoslovniKorisnik.setItems(pretraga );
    }

    public void initialize() throws FileNotFoundException {
        tableViewNazivPrivatnogKorisnika.setCellValueFactory(new PropertyValueFactory<Korisnik, String>("naziv"));
        tableViewWebPrivatnogKorisnika.setCellValueFactory(new PropertyValueFactory<Korisnik, String>("web"));
        tableViewEmailPrivatnogKorisnika.setCellValueFactory(new PropertyValueFactory<Korisnik, String>("email"));
        tableViewTelefonPrivatnogKorisnika.setCellValueFactory(new PropertyValueFactory<Korisnik, String>("telefon"));
        poslovniKorisnik = Datoteke.dohvatiPoslovneKorisnike("dat/poslovniKorisnici");
        pretraziPoslovnogKorisnika();
    }

    public void prikaziPretraguUsluga() {
        BorderPane root;
        try {
            root = (BorderPane) FXMLLoader.load(getClass().getResource("/Usluga.fxml"));
            Main.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prikaziPretraguAutomobila() {
        BorderPane root;
        try {
            root = (BorderPane) FXMLLoader.load(getClass().getResource("/Automobil.fxml"));
            Main.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prikaziPretraguStanova() {
        BorderPane root;
        try {
            root = (BorderPane) FXMLLoader.load(getClass().getResource("/Stan.fxml"));
            Main.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prikaziPretraguPrivatnihKorisnika() {
        BorderPane root;
        try {
            root = (BorderPane) FXMLLoader.load(getClass().getResource("/PrivatniKorisnik.fxml"));
            Main.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prikaziPretraguPoslovnihKorisnika() {
        BorderPane root;
        try {
            root = (BorderPane) FXMLLoader.load(getClass().getResource("/PoslovniKorisnik.fxml"));
            Main.setMainPage(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
