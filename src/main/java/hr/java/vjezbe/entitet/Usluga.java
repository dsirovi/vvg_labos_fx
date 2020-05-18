package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Extenda klasu artikl i ispisuje tekst oglasa
 */
public class Usluga extends Artikl implements Serializable {

    private static final long serialVersionUID = 1083603976211692215L;

    public Usluga(long id, String naslov, String opis, BigDecimal cijena, Stanje stanje) {
        super(id, naslov, opis, cijena, stanje);
    }

    public Usluga(String naslov, String opis, BigDecimal cijena) {
        super(naslov, opis, cijena, null);
    }

    @Override
    public String tekstOglasa() {
        return String.format("Naslov: %s \nOpis: %s \ncijena: %s", getNaslov(), getOpis(), getCijena());
    }
}
