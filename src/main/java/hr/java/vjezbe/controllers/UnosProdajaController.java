package hr.java.vjezbe.controllers;

import hr.java.vjezbe.baze.BazaPodataka;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Entitet;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

public class UnosProdajaController {

    List<Prodaja> prodaja = BazaPodataka.dohvatiProdajuPremaKriterijima(null);
    List<Artikl> artikl = BazaPodataka.dohvatiSveArtikle(null);
    List<Korisnik> korisnici = BazaPodataka.dohvatiSveKorisnike(null);

    @FXML
    private ComboBox<Artikl> artiklCombobox;
    @FXML
    private ComboBox<Korisnik> korinsnikCombobox;
    @FXML
    private DatePicker datumDatePicker;

    public UnosProdajaController() throws BazaPodatakaException {
    }

    public void initialize() throws BazaPodatakaException {
        artikl.stream().map(Artikl::tekstOglasa).collect(Collectors.toList());
        artiklCombobox.setItems(FXCollections.observableList(artikl));

        korisnici.stream()
                .map(Korisnik::dohvatiKontakt)
                .collect(Collectors.toList());
        korinsnikCombobox.setItems(FXCollections.observableList(korisnici));
    }

    public void spremiProdaju() {

        if (artiklCombobox.getValue() == null || korinsnikCombobox.getValue() == null || datumDatePicker.getValue() == null) {
            Alert upozorenje = new Alert(Alert.AlertType.ERROR);
            upozorenje.setTitle("Pogreska pri unosu");
            upozorenje.setHeaderText("Provjerite podatke o unosu prodaje");
            String opis = "Nedostaje podatak za: \n";
            if (artiklCombobox.getValue() == null)
                opis += "artikl, \n";
            if (korinsnikCombobox.getValue() == null)
                opis += "korisnika, \n";
            if (datumDatePicker.getValue() == null)
                opis += "datum objave \n";
            upozorenje.setContentText(opis);
            upozorenje.showAndWait();
        } else {
            OptionalLong maxId = prodaja.stream().mapToLong(Entitet::getId).max();

            Prodaja novaProdaja = new Prodaja(maxId.getAsLong() + 1, artiklCombobox.getValue(),korinsnikCombobox.getValue(), LocalDate.parse(datumDatePicker.getValue().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            prodaja.add(novaProdaja);


            try {
                BazaPodataka.pohraniProdaju(novaProdaja);
            } catch (BazaPodatakaException e) {
                Alert upozorenje = new Alert(Alert.AlertType.ERROR);
                upozorenje.setTitle("Greska");
                upozorenje.setContentText(e.getMessage());
                upozorenje.showAndWait();
                e.printStackTrace();
                return;
            }
            prodaja.add(novaProdaja);
            Alert upozorenje = new Alert(Alert.AlertType.CONFIRMATION);
            upozorenje.setTitle("Uspjesan unos");
            upozorenje.setHeaderText("Podaci o korisniku su uspjesno pohranjeni");
            upozorenje.showAndWait();
        }

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

    public void unesiProdaju() {
        BorderPane root;
        try {
            root = FXMLLoader.load(getClass().getResource("/UnosProdaja.fxml"));
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
