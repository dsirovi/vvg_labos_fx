package hr.java.vjezbe.util;

import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.entitet.TipKorisnika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Datoteke {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final Logger logger = LoggerFactory.getLogger(Datoteke.class);
    private final static String lokacijaDatoteke = "dat/";

    public static List<PrivatniKorisnik> dohvatiPrivatneKorisnike(String lokacijaDatoteke) throws FileNotFoundException {
        List<PrivatniKorisnik> privatniKorisnik = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("dat/privatniKorisnici"))) {
            String line;
            while ((line = in.readLine()) != null && TipKorisnika.PRIVATNI.getId().matches("1")) {
                String ime = in.readLine();
                String prezime = in.readLine();
                String email = in.readLine();
                String telefon = in.readLine();
                privatniKorisnik.add(new PrivatniKorisnik(email, telefon, ime, prezime));
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return privatniKorisnik;
    }

    public static List<PoslovniKorisnik> dohvatiPoslovneKorisnike(String lokacijaDatoteke) throws FileNotFoundException {
        List<PoslovniKorisnik> poslovniKorisnik = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(lokacijaDatoteke))) {
            String line;
            while ((line = in.readLine()) != null && TipKorisnika.POSLOVNI.getId().matches("2")) {
                String naziv = in.readLine();
                String web = in.readLine();
                String email = in.readLine();
                String telefon = in.readLine();
                poslovniKorisnik.add(new PoslovniKorisnik(email, telefon, naziv, web));
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return poslovniKorisnik;
    }
//    public static List<Automobil> dohvatiAutomobile(String lokacijaDatoteke){
//        try(BufferedReader in = new BufferedReader(new FileReader(lokacijaDatoteke))) {
//            String line;
//            while ((line = in.readLine()) != null && TipKorisnika.POSLOVNI.getId().matches("2")){
//                String naziv = in.readLine();
//                String web = in.readLine();
//                String email = in.readLine();
//                String telefon = in.readLine();
//                poslovniKorisnik.add(new PoslovniKorisnik(email, telefon, naziv, web));
//            }
//    }
//}
}

