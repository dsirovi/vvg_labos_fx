package hr.java.vjezbe.baze;

import hr.java.vjezbe.entitet.*;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class BazaPodataka {

    public static final Logger logger = LoggerFactory.getLogger(BazaPodataka.class);
    private static final String DATABASE_CONFIGURATION_FILE = "bazaPodataka.properties";

    private static Connection spajanjeNaBazu() throws IOException, SQLException {
        Properties konfaBaze = new Properties();
        konfaBaze.load(new FileReader(DATABASE_CONFIGURATION_FILE));

        String urlBazePodatak = konfaBaze.getProperty("bazaPodatakaUrl");
        String userBaze = konfaBaze.getProperty("username");
        String passwordBaze = konfaBaze.getProperty("password");

        Connection veza = DriverManager.getConnection(urlBazePodatak, userBaze, passwordBaze);

        return veza;
    }

    public static List<Prodaja> dohvatiProdajuPremaKriterijima(Prodaja prodaja) throws BazaPodatakaException {
        List<Prodaja> listaProdaje = new ArrayList<>();
        try (Connection connection = spajanjeNaBazu()) {
            StringBuilder sqlUpit = new StringBuilder("select distinct korisnik.id as idKorisnika, tipKorisnika.naziv as tipKorisnika, \r\n" + "korisnik.naziv as nazivKorisnika, web, email, telefon, \r\n" + "korisnik.ime, korisnik.prezime, tipArtikla.naziv as tipArtikla,\r\n" + "artikl.naslov, artikl.opis, artikl.cijena, artikl.kvadratura,\r\n" + "artikl.snaga, stanje.naziv as stanje, prodaja.datumObjave, artikl.id as idArtikla\r\n" + "from korisnik inner join \r\n" + "tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika inner join\r\n" + "prodaja on prodaja.idKorisnik = korisnik.id inner join\r\n" + "artikl on artikl.id = prodaja.idArtikl inner join\r\n" + "tipArtikla on tipArtikla.id = artikl.idTipArtikla inner join\r\n" + "stanje on stanje.id = artikl.idStanje where 1=1");
            if (Optional.ofNullable(prodaja).isPresent()) {
                if (Optional.ofNullable(prodaja.getArtikl()).isPresent())
                    sqlUpit.append(" AND prodaja.idArtikl = ").append(prodaja.getArtikl().getId());
                if (Optional.ofNullable(prodaja.getKorisnik()).isPresent())
                    sqlUpit.append(" AND prodaja.idArtikl = ").append(prodaja.getKorisnik().getId());
                if (Optional.ofNullable(prodaja.getDatumObjave()).isPresent()) {
                    sqlUpit.append(" AND prodaja.datumObjave = '").append(prodaja.getDatumObjave().format(DateTimeFormatter.ISO_DATE)).append("'");
                }
            }

            Statement query = connection.createStatement();
            ResultSet resultSet = query.executeQuery(sqlUpit.toString());

            while (resultSet.next()) {
                Korisnik korisnik = null;
                if (resultSet.getString("tipKorisnika").equals("PrivatniKorisnik")) {
                    korisnik = new PrivatniKorisnik(resultSet.getLong("idKorisnika"),
                            resultSet.getString("ime"),
                            resultSet.getString("prezime"),
                            resultSet.getString("email"),
                            resultSet.getString("telefon"));
                } else if (resultSet.getString("tipKorisnika").equals("PoslovniKorisnik")) {
                    korisnik = new PoslovniKorisnik(resultSet.getLong("idKorisnika"),
                            resultSet.getString("nazivKorisnika"),
                            resultSet.getString("web"),
                            resultSet.getString("telefon"),
                            resultSet.getString("email"));
                }
                Artikl artikl = null;
                if (resultSet.getString("tipArtikla").equals("Automobil")) {
                    artikl = new Automobil(resultSet.getLong("idArtikla"),
                            resultSet.getString("naslov"),
                            resultSet.getString("opis"),
                            resultSet.getBigDecimal("cijena"),
                            resultSet.getBigDecimal("snaga"),
                            Stanje.valueOf(resultSet.getString("stanje")));
                } else if (resultSet.getString("tipArtikla").equals("Usluga")) {
                    artikl = new Usluga(resultSet.getLong("idArtikla"),
                            resultSet.getString("naslov"),
                            resultSet.getString("opis"),
                            resultSet.getBigDecimal("cijena"),
                            Stanje.valueOf(resultSet.getString("stanje")));
                } else if (resultSet.getString("tipArtikla").equals("Stan")) {
                    artikl = new Stan(resultSet.getLong("idArtikla"),
                            resultSet.getString("naslov"),
                            resultSet.getString("opis"),
                            resultSet.getBigDecimal("cijena"),
                            Stanje.valueOf(resultSet.getString("stanje")),
                            resultSet.getBigDecimal("kvadratura"));
                }

                Prodaja novaProdaja = new Prodaja(artikl, korisnik, resultSet.getTimestamp("datumObjave").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                listaProdaje.add(novaProdaja);
            }

        } catch (SQLException | IOException e) {
            String poruka = "Došlo je do pogreške u radu s bazom podataka";
            logger.error(poruka, e);
            throw new BazaPodatakaException(poruka, e);
        }
        return listaProdaje;
    }


    public static List<PrivatniKorisnik> dohvatiPrivatnogKorisnikaPremaKriterijima(PrivatniKorisnik
                                                                                           privatniKorisnik) throws BazaPodatakaException {
        List<PrivatniKorisnik> listaPrivatnihKorisnika = new ArrayList<>();
        try (Connection connection = spajanjeNaBazu()) {
            StringBuilder sqlUpit = new StringBuilder("select distinct korisnik.id, ime, prezime, email, telefon " + "from korisnik inner join tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika " + "where tipKorisnika.naziv = 'PrivatniKorisnik'");
            if (Optional.ofNullable(privatniKorisnik).isPresent()) {
                Optional.of(privatniKorisnik).map(PrivatniKorisnik::getId);
                sqlUpit.append(" AND korisnik.id = ").append(privatniKorisnik.getId());

                if (!Optional.ofNullable(privatniKorisnik.getIme()).map(String::isBlank).orElse(true))
                    sqlUpit.append(" AND korisnik.ime LIKE '%").append(privatniKorisnik.getIme()).append("%'");

                if (!Optional.ofNullable(privatniKorisnik.getPrezime()).map(String::isBlank).orElse(true))
                    sqlUpit.append(" AND korisnik.prezime LIKE '%").append(privatniKorisnik.getPrezime()).append("%'");

                if (Optional.of(privatniKorisnik).map(PrivatniKorisnik::getEmail).isPresent())
                    sqlUpit.append(" AND korisnik.email = ").append(privatniKorisnik.getEmail());

                if (Optional.of(privatniKorisnik).map(PrivatniKorisnik::getTelefon).isPresent())
                    sqlUpit.append(" AND korisnik.telefon = ").append(privatniKorisnik.getTelefon());
            }

            Statement query = connection.createStatement();
            ResultSet resultSet = query.executeQuery(sqlUpit.toString());

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String ime = resultSet.getString("ime");
                String prezime = resultSet.getString("prezime");
                String email = resultSet.getString("email");
                String telefon = resultSet.getString("telefon");

                PrivatniKorisnik noviPrivatniKorisnik = new PrivatniKorisnik(id, email, telefon, ime, prezime);
                listaPrivatnihKorisnika.add(noviPrivatniKorisnik);
            }

        } catch (SQLException | IOException e) {
            String poruka = "Došlo je do pogreške u radu s bazom podataka";
            logger.error(poruka, e);
            throw new BazaPodatakaException(poruka, e);
        }
        return listaPrivatnihKorisnika;
    }

    public static List<PoslovniKorisnik> dohvatiPoslovnogKorisnikaPremaKriterijima(PoslovniKorisnik
                                                                                           poslovniKorisnik) throws BazaPodatakaException {
        List<PoslovniKorisnik> listaPoslovnihKorisnika = new ArrayList<>();
        try (Connection connection = spajanjeNaBazu()) {
            StringBuilder sqlUpit = new StringBuilder("select distinct korisnik.id, korisnik.naziv, web, email, telefon " + "from korisnik inner join tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika " + "where tipKorisnika.naziv = 'PoslovniKorisnik'");
            if (Optional.ofNullable(poslovniKorisnik).isPresent()) {
                Optional.of(poslovniKorisnik).map(PoslovniKorisnik::getId);
                sqlUpit.append(" AND korisnik.id = ").append(poslovniKorisnik.getId());

                if (!Optional.ofNullable(poslovniKorisnik.getNaziv()).map(String::isBlank).orElse(true))
                    sqlUpit.append(" AND korisnik.naziv LIKE '%").append(poslovniKorisnik.getNaziv()).append("%'");

                if (!Optional.ofNullable(poslovniKorisnik.getWeb()).map(String::isBlank).orElse(true))
                    sqlUpit.append(" AND korisnik.web LIKE '%").append(poslovniKorisnik.getWeb()).append("%'");

                if (Optional.of(poslovniKorisnik).map(PoslovniKorisnik::getEmail).isPresent())
                    sqlUpit.append(" AND korisnik.email = ").append(poslovniKorisnik.getEmail());

                if (Optional.of(poslovniKorisnik).map(PoslovniKorisnik::getTelefon).isPresent())
                    sqlUpit.append(" AND korisnik.telefon = ").append(poslovniKorisnik.getTelefon());
            }

            Statement query = connection.createStatement();
            ResultSet resultSet = query.executeQuery(sqlUpit.toString());

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String naslov = resultSet.getString("naziv");
                String web = resultSet.getString("web");
                String email = resultSet.getString("email");
                String telefon = resultSet.getString("telefon");

                PoslovniKorisnik noviPoslovniKorisnik = new PoslovniKorisnik(id, email, telefon, naslov, web);
                listaPoslovnihKorisnika.add(noviPoslovniKorisnik);
            }

        } catch (SQLException | IOException e) {
            String poruka = "Došlo je do pogreške u radu s bazom podataka";
            logger.error(poruka, e);
            throw new BazaPodatakaException(poruka, e);
        }
        return listaPoslovnihKorisnika;
    }

    public static List<Usluga> dohvatiUslugePremaKriterijima(Usluga usluga) throws
            BazaPodatakaException {
        List<Usluga> listaUsluga = new ArrayList<>();
        try (Connection connection = spajanjeNaBazu()) {
            StringBuilder sqlUpit = new StringBuilder("SELECT distinct artikl.id, naslov, opis, cijena, stanje.naziv " + "FROM artikl inner join stanje on stanje.id = artikl.idStanje " + "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Usluga'");
            if (Optional.ofNullable(usluga).isPresent()) {
                Optional.of(usluga).map(Usluga::getId);
                sqlUpit.append(" AND artikl.id = ").append(usluga.getId());

                if (!Optional.ofNullable(usluga.getNaslov()).map(String::isBlank).orElse(true))
                    sqlUpit.append(" AND artikl.naslov LIKE '%").append(usluga.getNaslov()).append("%'");

                if (!Optional.ofNullable(usluga.getOpis()).map(String::isBlank).orElse(true))
                    sqlUpit.append(" AND artikl.opis LIKE '%").append(usluga.getOpis()).append("%'");

                if (Optional.of(usluga).map(Usluga::getCijena).isPresent())
                    sqlUpit.append(" AND artikl.cijena = ").append(usluga.getCijena());
            }

            Statement query = connection.createStatement();
            ResultSet resultSet = query.executeQuery(sqlUpit.toString());

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String naslov = resultSet.getString("naslov");
                String opis = resultSet.getString("opis");
                BigDecimal cijena = resultSet.getBigDecimal("cijena");
                String stanje = resultSet.getString("naziv");
                Stanje stanjeUnos = Stanje.odrediStanje(stanje.toUpperCase());

                Usluga novaUsluga = new Usluga(id, naslov, opis, cijena, stanjeUnos);
                listaUsluga.add(novaUsluga);
            }

        } catch (SQLException | IOException e) {
            String poruka = "Došlo je do pogreške u radu s bazom podataka";
            logger.error(poruka, e);
            throw new BazaPodatakaException(poruka, e);
        }
        return listaUsluga;
    }

    public static List<Automobil> dohvatiAutomobilePremaKriterijima(Automobil automobil) throws
            BazaPodatakaException {
        List<Automobil> listaAutomobila = new ArrayList<>();
        try (Connection connection = spajanjeNaBazu()) {
            StringBuilder sqlUpit = new StringBuilder("SELECT distinct artikl.id, naslov, opis, cijena, snaga, stanje.naziv " + "FROM artikl inner join stanje on stanje.id = artikl.idStanje " + " inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Automobil' ");
            if (Optional.ofNullable(automobil).isPresent()) {
                Optional.of(automobil).map(Automobil::getId);
                sqlUpit.append(" AND artikl.id = ").append(automobil.getId());

                if (!Optional.ofNullable(automobil.getNaslov()).map(String::isBlank).orElse(true))
                    sqlUpit.append(" AND artikl.naslov LIKE '%").append(automobil.getNaslov()).append("%'");

                if (!Optional.ofNullable(automobil.getOpis()).map(String::isBlank).orElse(true))
                    sqlUpit.append(" AND artikl.opis LIKE '%").append(automobil.getOpis()).append("%'");

                if (Optional.of(automobil).map(Automobil::getCijena).isPresent())
                    sqlUpit.append(" AND artikl.cijena = ").append(automobil.getCijena());

                if (Optional.of(automobil).map(Automobil::getCijena).isPresent())
                    sqlUpit.append(" AND artikl.snaga = ").append(automobil.getCijena());
            }

            Statement query = connection.createStatement();
            ResultSet resultSet = query.executeQuery(sqlUpit.toString());

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String naslov = resultSet.getString("naslov");
                String opis = resultSet.getString("opis");
                BigDecimal cijena = resultSet.getBigDecimal("cijena");
                BigDecimal snaga = resultSet.getBigDecimal("snaga");
                String stanje = resultSet.getString("naziv");
                Stanje stanjeUnos = Stanje.odrediStanje(stanje.toUpperCase());

                Automobil noviAutomobil = new Automobil(id, naslov, opis, cijena, snaga, stanjeUnos);
                listaAutomobila.add(noviAutomobil);
            }
        } catch (SQLException | IOException e) {
            String poruka = "Došlo je do pogreške u radu s bazom podataka";
            logger.error(poruka, e);
            throw new BazaPodatakaException(poruka, e);
        }
        return listaAutomobila;
    }

    public static List<Stan> dohvatiStanovePremaKriterijima(Stan stan) throws BazaPodatakaException {
        List<Stan> listaStanova = new ArrayList<>();
        try (Connection connection = spajanjeNaBazu()) {
            StringBuilder sqlUpit = new StringBuilder("SELECT distinct artikl.id, naslov, opis, cijena, kvadratura, stanje.naziv " + "FROM artikl inner join stanje on stanje.id = artikl.idStanje " + "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Stan'");
            if (Optional.ofNullable(stan).isPresent()) {
                Optional.of(stan).map(Stan::getId);
                sqlUpit.append(" AND artikl.id = ").append(stan.getId());

                if (!Optional.ofNullable(stan.getNaslov()).map(String::isBlank).orElse(true))
                    sqlUpit.append(" AND artikl.naslov LIKE '%").append(stan.getNaslov()).append("%'");

                if (!Optional.ofNullable(stan.getOpis()).map(String::isBlank).orElse(true))
                    sqlUpit.append(" AND artikl.opis LIKE '%").append(stan.getOpis()).append("%'");

                if (Optional.of(stan).map(Stan::getCijena).isPresent())
                    sqlUpit.append(" AND artikl.cijena = ").append(stan.getCijena());

                if (Optional.of(stan).map(Stan::getKvadratura).isPresent())
                    sqlUpit.append(" AND artikl.kvadratura = ").append(stan.getKvadratura());

                if (Optional.of(stan).map(Stan::getStanje).isPresent())
                    sqlUpit.append(" AND artikl.stanje.naziv = ").append(stan.getStanje());
            }

            Statement query = connection.createStatement();
            ResultSet resultSet = query.executeQuery(sqlUpit.toString());

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String naslov = resultSet.getString("naslov");
                String opis = resultSet.getString("opis");
                BigDecimal cijena = resultSet.getBigDecimal("cijena");
                BigDecimal kvadratura = resultSet.getBigDecimal("kvadratura");
                String stanje = resultSet.getString("naziv");
                Stanje stanjeUnos = Stanje.odrediStanje(stanje.toUpperCase());

                Stan newStan = new Stan(id, naslov, opis, cijena, stanjeUnos, kvadratura);
                listaStanova.add(newStan);
            }

        } catch (SQLException | IOException e) {
            String poruka = "Došlo je do pogreške u radu s bazom podataka";
            logger.error(poruka, e);
            throw new BazaPodatakaException(poruka, e);
        }
        return listaStanova;
    }

    public static void pohraniPrivatnogKorisnika(PrivatniKorisnik privatniKorisnik) throws
            BazaPodatakaException {
        try (Connection veza = spajanjeNaBazu()) {
            PreparedStatement preparedStatement = veza.prepareStatement("insert into korisnik(Ime, Prezime, Email, Telefon, idTipKorisnika) " + "values (?, ?, ?, ?, 1);");
            preparedStatement.setString(1, privatniKorisnik.getIme());
            preparedStatement.setString(2, privatniKorisnik.getPrezime());
            preparedStatement.setString(3, privatniKorisnik.getEmail());
            preparedStatement.setString(4, privatniKorisnik.getTelefon());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException ex) {
            String poruka = "Došlo je do pogreške u radu s bazom podataka";
            logger.error(poruka, ex);
            throw new BazaPodatakaException(poruka, ex);
        }
    }

    public static void pohraniPoslovnogKorisnika(PoslovniKorisnik poslovniKorisnik) throws
            BazaPodatakaException {
        try (Connection veza = spajanjeNaBazu()) {
            PreparedStatement preparedStatement = veza.prepareStatement("insert into korisnik(Naziv, Web, Email, Telefon, idTipKorisnika) " + "values (?, ?, ?, ?, 2);");
            preparedStatement.setString(1, poslovniKorisnik.getNaziv());
            preparedStatement.setString(2, poslovniKorisnik.getWeb());
            preparedStatement.setString(3, poslovniKorisnik.getEmail());
            preparedStatement.setString(4, poslovniKorisnik.getTelefon());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException ex) {
            String poruka = "Došlo je do pogreške u radu s bazom podataka";
            logger.error(poruka, ex);
            throw new BazaPodatakaException(poruka, ex);
        }
    }

    public static void pohraniNoviAutomobil(Automobil automobil) throws BazaPodatakaException {
        try (Connection veza = spajanjeNaBazu()) {
            PreparedStatement preparedStatement = veza.prepareStatement("insert into artikl(Naslov, Opis, Cijena, Snaga, idStanje, idTipArtikla) " + "values (?, ?, ?, ?, ?, 1);");
            preparedStatement.setString(1, automobil.getNaslov());
            preparedStatement.setString(2, automobil.getOpis());
            preparedStatement.setBigDecimal(3, automobil.getCijena());
            preparedStatement.setBigDecimal(4, automobil.getSnagaKs());
            preparedStatement.setLong(5, (automobil.getStanje().ordinal() + 1));
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException ex) {
            String poruka = "Došlo je do pogreške u radu s bazom podataka";
            logger.error(poruka, ex);
            throw new BazaPodatakaException(poruka, ex);
        }
    }

    public static void pohraniNovuUslugu(Usluga usluga) throws BazaPodatakaException {
        try (Connection veza = spajanjeNaBazu()) {
            PreparedStatement preparedStatement = veza.prepareStatement("insert into artikl(Naslov, Opis, Cijena, idStanje, idTipArtikla) " + "values (?, ?, ?, ?, 2);");
            preparedStatement.setString(1, usluga.getNaslov());
            preparedStatement.setString(2, usluga.getOpis());
            preparedStatement.setBigDecimal(3, usluga.getCijena());
            preparedStatement.setLong(4, (usluga.getStanje().ordinal() + 1));
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException ex) {
            String poruka = "Došlo je do pogreške u radu s bazom podataka";
            logger.error(poruka, ex);
            throw new BazaPodatakaException(poruka, ex);
        }
    }

    public static void pohraniNoviStan(Stan stan) throws BazaPodatakaException {
        try (Connection veza = spajanjeNaBazu()) {
            PreparedStatement preparedStatement = veza.prepareStatement("insert into artikl(Naslov, Opis, Cijena, Kvadratura, idStanje, idTipArtikla) " + "values (?, ?, ?, ?, ?, 3);");
            preparedStatement.setString(1, stan.getNaslov());
            preparedStatement.setString(2, stan.getOpis());
            preparedStatement.setBigDecimal(3, stan.getCijena());
            preparedStatement.setBigDecimal(4, stan.getKvadratura());
            preparedStatement.setLong(5, (stan.getStanje().ordinal() + 1));
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException ex) {
            String poruka = "Došlo je do pogreške u radu s bazom podataka";
            logger.error(poruka, ex);
            throw new BazaPodatakaException(poruka, ex);
        }
    }
}
