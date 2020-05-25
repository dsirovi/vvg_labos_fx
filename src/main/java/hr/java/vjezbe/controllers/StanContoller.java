package hr.java.vjezbe.controllers;

import hr.java.vjezbe.baze.BazaPodataka;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Stan;
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

public class StanContoller {

    List<Stan> stan;

    @FXML
    private TextField naslovTextField;
    @FXML
    private TextField opisTextField;
    @FXML
    private TextField kvadraturaTextField;
    @FXML
    private TextField cijenaTextField;
    @FXML
    private TableView<Artikl> tableViewStanovi;
    @FXML
    private TableColumn<Artikl, String> tableColumnStanoviNaslov;
    @FXML
    private TableColumn<Artikl, String> tableColumnStanoviOpis;
    @FXML
    private TableColumn<Artikl, String> tableColumnStanoviCijena;
    @FXML
    private TableColumn<Artikl, String> tableColumnStanoviKvadratura;
    @FXML
    private TableColumn<Artikl, String> tableColumnStanoviStanje;

    public void pretraziStanove() {
        List<Artikl> filtriraniStanovi = stan.stream().filter(p ->
                p.getNaslov().contains(naslovTextField.getText())
                        && p.getOpis().toLowerCase().contains(opisTextField.getText())
                        && p.getKvadratura().toString().toLowerCase().contains(kvadraturaTextField.getCharacters())
                        && p.getCijena().toString().toLowerCase().contains(cijenaTextField.getText())).collect(Collectors.toList());
        ObservableList<Artikl> pretraga = FXCollections.observableList(filtriraniStanovi);
        tableViewStanovi.setItems(pretraga);
    }

    public void initialize() throws BazaPodatakaException {
        tableColumnStanoviNaslov.setCellValueFactory(new PropertyValueFactory<>("naslov"));
        tableColumnStanoviOpis.setCellValueFactory(new PropertyValueFactory<>("opis"));
        tableColumnStanoviKvadratura.setCellValueFactory(new PropertyValueFactory<>("kvadratura"));
        tableColumnStanoviCijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        tableColumnStanoviStanje.setCellValueFactory(new PropertyValueFactory<>("stanje"));
        stan = BazaPodataka.dohvatiStanovePremaKriterijima(null);
        pretraziStanove();
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
