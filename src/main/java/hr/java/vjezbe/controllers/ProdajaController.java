package hr.java.vjezbe.controllers;

import hr.java.vjezbe.baze.BazaPodataka;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ProdajaController {

    List<Prodaja> prodaja;

    @FXML
    private ComboBox<String> artiklCombobox;
    @FXML
    private ComboBox<String> korinsnikCombobox;
    @FXML
    private DatePicker datumDatePicker;
    @FXML
    private TableView<Prodaja> tableViewProdaja;
    @FXML
    private TableColumn<Prodaja, String> tableViewOglasProdaje;
    @FXML
    private TableColumn<Prodaja, String> tableViewKorisnikProdaje;
    @FXML
    private TableColumn<Prodaja, String> tableViewDatumProdaje;

    public void pretraziProdaju() {
        List<Prodaja> filtriranaProdaja = prodaja.stream().filter(p ->
                p.getArtikl().tekstOglasa().contains(artiklCombobox.getValue())
                        && p.getKorisnik().dohvatiKontakt().toLowerCase().contains(korinsnikCombobox.getValue())
                        && p.getDatumObjave().toString().toLowerCase().contains(datumDatePicker.getValue().toString())).collect(Collectors.toList());
        ObservableList<Prodaja> pretraga = FXCollections.observableList(filtriranaProdaja);
        tableViewProdaja.setItems(pretraga);
    }

    public void initialize() throws BazaPodatakaException {
        tableViewOglasProdaje.setCellValueFactory(new PropertyValueFactory<Prodaja, String>("oglas"));
        tableViewKorisnikProdaje.setCellValueFactory(new PropertyValueFactory<Prodaja, String>("korisnik"));
        tableViewDatumProdaje.setCellValueFactory(new PropertyValueFactory<Prodaja, String>("datum"));
        prodaja = BazaPodataka.dohvatiProdajuPremaKriterijima((Prodaja) prodaja);
        pretraziProdaju();
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
