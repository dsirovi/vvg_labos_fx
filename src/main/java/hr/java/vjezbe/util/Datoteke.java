package hr.java.vjezbe.util;

import hr.java.vjezbe.entitet.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Datoteke {

    public static List<Korisnik> dohvatiPrivatneKorisnike(String lokacijaDatoteke) {
        List<Korisnik> privatniKorisnik = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(lokacijaDatoteke))) {
            while (in.readLine() != null) {
                String ime = in.readLine();
                String prezime = in.readLine();
                String email = in.readLine();
                String telefon = in.readLine();
                privatniKorisnik.add(new PrivatniKorisnik(email, telefon, ime, prezime));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return privatniKorisnik;
    }

    public static List<Korisnik> dohvatiPoslovneKorisnike(String lokacijaDatoteke) {
        List<Korisnik> poslovniKorisnik = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(lokacijaDatoteke))) {
            while (in.readLine() != null) {
                String naziv = in.readLine();
                String web = in.readLine();
                String email = in.readLine();
                String telefon = in.readLine();
                poslovniKorisnik.add(new PoslovniKorisnik(email, telefon, naziv, web));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return poslovniKorisnik;
    }

    public static List<Artikl> dohvatiUsluge(String lokacijaDatoteke) {
        List<Artikl> usluge = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(lokacijaDatoteke))) {
            while (in.readLine() != null) {
                String naslov = in.readLine();
                String opis = in.readLine();
                BigDecimal cijena = BigDecimal.valueOf(Long.parseLong(in.readLine()));
                usluge.add(new Usluga(naslov, opis, cijena));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return usluge;
    }

    public static List<Automobil> dohvatiAutomobile(String lokacijaDatoteke) {
        List<Automobil> automobil = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(lokacijaDatoteke))) {
            while (in.readLine() != null) {
                String naslov = in.readLine();
                String opis = in.readLine();
                BigDecimal snagaKs = BigDecimal.valueOf(Long.parseLong(in.readLine()));
                BigDecimal cijena = BigDecimal.valueOf(Long.parseLong(in.readLine()));
                String stanjeAutomobila = in.readLine();
                Stanje stanje = null;
                if (stanjeAutomobila.equals(Stanje.NOVO.getId())) {
                    stanje = Stanje.NOVO;
                } else if (stanjeAutomobila.equals(Stanje.IZVRSNO.getId())) {
                    stanje = Stanje.IZVRSNO;
                } else if (stanjeAutomobila.equals(Stanje.RABLJENO.getId())) {
                    stanje = Stanje.RABLJENO;
                } else if (stanjeAutomobila.equals(Stanje.NEISPRAVNO.getId())) {
                    stanje = Stanje.NEISPRAVNO;
                }
                automobil.add(new Automobil(naslov, opis, cijena, snagaKs, stanje));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return automobil;
    }

    public static List<Stan> dohvatiStanove(String lokacijaDatoteke) {
        List<Stan> stan = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(lokacijaDatoteke))) {
            while (in.readLine() != null) {
                String naslov = in.readLine();
                String opis = in.readLine();
                BigDecimal kvadratura = BigDecimal.valueOf(Long.parseLong(in.readLine()));
                BigDecimal cijena = BigDecimal.valueOf(Long.parseLong(in.readLine()));
                String stanjeStana = in.readLine();
                Stanje stanje = null;
                if (stanjeStana.equals(Stanje.NOVO.getId())) {
                    stanje = Stanje.NOVO;
                } else if (stanjeStana.equals(Stanje.IZVRSNO.getId())) {
                    stanje = Stanje.IZVRSNO;
                } else if (stanjeStana.equals(Stanje.RABLJENO.getId())) {
                    stanje = Stanje.RABLJENO;
                } else if (stanjeStana.equals(Stanje.NEISPRAVNO.getId())) {
                    stanje = Stanje.NEISPRAVNO;
                }
                stan.add(new Stan(naslov, opis, cijena, stanje, kvadratura));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return stan;
    }
}

