package hr.java.vjezbe.controllers;

import hr.java.vjezbe.baze.BazaPodataka;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Automobil;
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

public class AutomobiliController {

    List<Automobil> automobil;

    @FXML
    private TextField naslovTextField;
    @FXML
    private TextField opisTextField;
    @FXML
    private TextField snagaTextField;
    @FXML
    private TextField cijenaTextField;
    @FXML
    private TableView<Artikl> tableViewAutomobili;
    @FXML
    private TableColumn<Artikl, String> tableColumnAutomobiliNaslov;
    @FXML
    private TableColumn<Artikl, String> tableColumnAutomobiliOpis;
    @FXML
    private TableColumn<Artikl, String> tableColumnAutomobiliCijena;
    @FXML
    private TableColumn<Artikl, String> tableColumnAutomobiliSnaga;
    @FXML
    private TableColumn<Artikl, String> tableColumnAutomobiliStanje;

    public void pretraziAutomobile() {
        List<Artikl> filtriraniAutomobili = automobil.stream().filter(p ->
                p.getNaslov().toLowerCase().contains(naslovTextField.getText())
                        && p.getOpis().toLowerCase().contains(opisTextField.getText())
                        && p.getSnagaKs().toString().toLowerCase().contains(snagaTextField.getCharacters())
                        && p.getCijena().toString().toLowerCase().contains(cijenaTextField.getText())).collect(Collectors.toList());
        ObservableList<Artikl> pretraga = FXCollections.observableList(filtriraniAutomobili);
        tableViewAutomobili.setItems(pretraga);
    }

    public void initialize() throws BazaPodatakaException {
        tableColumnAutomobiliNaslov.setCellValueFactory(new PropertyValueFactory<Artikl, String>("naslov"));
        tableColumnAutomobiliOpis.setCellValueFactory(new PropertyValueFactory<Artikl, String>("opis"));
        tableColumnAutomobiliSnaga.setCellValueFactory(new PropertyValueFactory<Artikl, String>("snagaKs"));
        tableColumnAutomobiliCijena.setCellValueFactory(new PropertyValueFactory<Artikl, String>("cijena"));
        tableColumnAutomobiliStanje.setCellValueFactory(new PropertyValueFactory<Artikl, String>("stanje"));
        automobil = BazaPodataka.dohvatiAutomobilePremaKriterijima((Automobil) automobil);
        pretraziAutomobile();
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
