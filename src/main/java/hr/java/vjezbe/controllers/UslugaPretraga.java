package hr.java.vjezbe.controllers;

import hr.java.vjezbe.entitet.Artikl;
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

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class UslugaPretraga {

    List<Artikl> usluge;
    
    @FXML
    private TextField naslovTextField;
    @FXML
    private TextField opisTextField;
    @FXML
    private TextField cijenaTextField;
    @FXML
    private TableView<Artikl> tableViewUsluge;
    @FXML
    private TableColumn<Artikl, String> tableViewNazivUsluge;
    @FXML
    private TableColumn<Artikl, String> tableViewOpisUsluge;
    @FXML
    private TableColumn<Artikl, String> tableViewCijenaUsluge;

    public void initialize() {

        tableViewNazivUsluge.setCellValueFactory(new PropertyValueFactory<Artikl, String>("naslov"));
        tableViewOpisUsluge.setCellValueFactory(new PropertyValueFactory<Artikl, String>("opis"));
        tableViewCijenaUsluge.setCellValueFactory(new PropertyValueFactory<Artikl, String>("cijena"));
        usluge = Datoteke.dohvatiUsluge("dat/usluge");
        pretraziUsluge();

    }

    public void pretraziUsluge() {
        List<Artikl> filtriraneUsluge = usluge.stream().filter(p ->
                p.getNaslov().toLowerCase().contains(naslovTextField.getText())
                        && p.getOpis().toLowerCase().contains(opisTextField.getText())
                        && p.getCijena().toString().toLowerCase().contains(cijenaTextField.getText())).collect(Collectors.toList());
        ObservableList<Artikl> pretraga = FXCollections.observableList(filtriraneUsluge);
        tableViewUsluge.setItems(pretraga);
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
