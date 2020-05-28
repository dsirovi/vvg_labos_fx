package hr.java.vjezbe.controllers;

import hr.java.vjezbe.baze.BazaPodataka;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Korisnik;
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

    List<Prodaja> prodaja = BazaPodataka.dohvatiProdajuPremaKriterijima(null);
    List<Artikl> artikl = BazaPodataka.dohvatiSveArtikle(null);
    List<Korisnik> korisnici = BazaPodataka.dohvatiSveKorisnike(null);

    @FXML
    private ComboBox<Artikl> artiklCombobox;
    @FXML
    private ComboBox<Korisnik> korinsnikCombobox;
    @FXML
    private DatePicker datumDatePicker;
    @FXML
    private TableView<Prodaja> tableViewProdaja;
    @FXML
    private TableColumn<Artikl, String> tableViewOglasProdaje;
    @FXML
    private TableColumn<Korisnik, String> tableViewKorisnikProdaje;
    @FXML
    private TableColumn<Prodaja, String> tableViewDatumProdaje;

    public ProdajaController() throws BazaPodatakaException {
    }

//    public void korisniciComboBox(List<Korisnik> korisnici) throws BazaPodatakaException {
//        korisnici.stream().map(Korisnik::dohvatiKontakt).collect(Collectors.toList());
//        korinsnikCombobox.setItems(FXCollections.observableList(korisnici));
//    }

    public void pretraziProdaju() {
        List<Prodaja> filtriranaProdaja = prodaja.stream().filter(p ->
                p.getArtikl().tekstOglasa().contains(artiklCombobox.toString())
                        && p.getKorisnik().dohvatiKontakt().toLowerCase().contains(korinsnikCombobox.toString())
                        && p.getDatumObjave().toString().toLowerCase().contains(datumDatePicker.getValue().toString())).collect(Collectors.toList());
        ObservableList<Prodaja> pretraga = FXCollections.observableList(filtriranaProdaja);
        tableViewProdaja.setItems(pretraga);
    }

    public void initialize() throws BazaPodatakaException {
        /*ObservableList<Prodaja> podaci = FXCollections.observableList(prodaja);
        tableViewOglasProdaje.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Artikl, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Artikl, String> prodajaStringCellDataFeatures) {
                SimpleStringProperty property = new SimpleStringProperty();
                property.setValue(prodajaStringCellDataFeatures.getValue().tekstOglasa());
                return property;
            }
        });

        tableViewKorisnikProdaje.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Korisnik, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Korisnik, String> prodajaStringCellDataFeatures) {
                SimpleStringProperty property = new SimpleStringProperty();
                property.setValue(prodajaStringCellDataFeatures.getValue().dohvatiKontakt());
                return property;
            }
        });

        tableViewDatumProdaje.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Prodaja, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Prodaja, String> prodajaStringCellDataFeatures) {
                SimpleStringProperty property = new SimpleStringProperty();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
                property.setValue(prodajaStringCellDataFeatures.getValue().getDatumObjave().format(formatter));
                return property;
            }
        });
        tableViewProdaja.setItems(podaci);*/


        artikl.stream()
                .map(Artikl::tekstOglasa)
                .collect(Collectors.toList());
        artiklCombobox.setItems(FXCollections.observableList(artikl));

        korisnici.stream()
                .map(Korisnik::dohvatiKontakt)
                .collect(Collectors.toList());
        korinsnikCombobox.setItems(FXCollections.observableList(korisnici));

        tableViewDatumProdaje.setCellValueFactory(new PropertyValueFactory<Prodaja, String>("datum"));
        prodaja = BazaPodataka.dohvatiProdajuPremaKriterijima(null);
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
