package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;

import java.io.Serializable;
import java.math.BigDecimal;

public class Stan extends Artikl implements Nekretnina, Serializable {

    private static final long serialVersionUID = 196354176215960634L;

    private BigDecimal kvadratura;

    public Stan(long id, String naslov, String opis, BigDecimal cijena, Stanje stanje, BigDecimal kvadratura) {
        super(id, naslov, opis, cijena, stanje);
        this.kvadratura = kvadratura;
    }

    public Stan(String naslov, String opis, BigDecimal cijena, Stanje stanje, BigDecimal kvadratura) {
        super(naslov, opis, cijena, stanje);
        this.kvadratura = kvadratura;
    }

    public BigDecimal getKvadratura() {
        return kvadratura;
    }

    public void setKvadratura(BigDecimal kvadratura) {
        this.kvadratura = kvadratura;
    }

    @Override
    public String tekstOglasa() {
        String tekstIzracunaPoreza;
        try {
            tekstIzracunaPoreza = izracunajPorez(getCijena()).toString();
        }catch (CijenaJePreniskaException e){
            tekstIzracunaPoreza = e.getMessage();
        }
       return String.format(
               "Naslov nakretnine: %s \nStanje nekretnine: %s \nOpis nekretnine: %s \nSKvadratura nekretnine: %s \nCijena nekretnine: %s \nPorez nekretnine: %s",
               getNaslov(),
               getStanje().toString().toLowerCase(),
               getOpis(),
               kvadratura,
               getCijena(),
               tekstIzracunaPoreza
       );

    }
}
