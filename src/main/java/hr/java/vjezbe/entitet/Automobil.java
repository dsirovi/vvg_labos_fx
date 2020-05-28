package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Prima podatke o vozilu i sprema ih automobil te prosljeduje u artikle
 * na osnovu konjskih snaga sprema u odredenu grupu osiguranja
 */
public class Automobil extends Artikl implements Vozilo, Serializable {

    private static final BigDecimal GRANICA_PRVE_KATEGORIJE = BigDecimal.valueOf(105);
    private static final BigDecimal GRANICA_DRUGE_KATEGORIJE = BigDecimal.valueOf(140);
    private static final BigDecimal GRANICA_TRECE_KATEGORIJE = BigDecimal.valueOf(180);
    private static final BigDecimal GRANICA_CETVRTE_KATEGORIJE = BigDecimal.valueOf(250);
    private static final BigDecimal GRANICA_PETE_KATEGORIJE = BigDecimal.valueOf(300);
    private static final long serialVersionUID = -6737819761612494475L;

    private BigDecimal snagaKs;

    public Automobil(long id, String naslov, String opis, BigDecimal cijena, BigDecimal snagaKs, Stanje stanje) {
        super(id, naslov, opis, cijena, stanje);
        this.snagaKs = snagaKs;
    }

    public Automobil(String naslov, String opis, BigDecimal cijena, BigDecimal snagaKs, Stanje stanje) {
        super(naslov, opis, cijena, stanje);
        this.snagaKs = snagaKs;
    }

    public BigDecimal getSnagaKs() {
        return snagaKs;
    }

    public void setSnagaKs(BigDecimal snagaKs) {
        this.snagaKs = snagaKs;
    }

    @Override
    public BigDecimal izracunajGrupuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException {
        int grupa;
        if (snagaKs.compareTo(GRANICA_PRVE_KATEGORIJE) <= 0) {
            grupa = 1;
        } else if (snagaKs.compareTo(GRANICA_DRUGE_KATEGORIJE) <= 0) {
            grupa = 2;
        } else if (snagaKs.compareTo(GRANICA_TRECE_KATEGORIJE) <= 0) {
            grupa = 3;
        } else if (snagaKs.compareTo(GRANICA_CETVRTE_KATEGORIJE) <= 0) {
            grupa = 4;
        } else if (snagaKs.compareTo(GRANICA_PETE_KATEGORIJE) <= 0){
            grupa = 5;
        }else {
            throw new NemoguceOdreditiGrupuOsiguranjaException();
        }
        return BigDecimal.valueOf(grupa);
    }

    @Override
    public String tekstOglasa() {
        String tekstIzracunaOsiguranja;
        try {
            tekstIzracunaOsiguranja = izracunajCijenuOsiguranja().toString();
        }catch (NemoguceOdreditiGrupuOsiguranjaException e){
            tekstIzracunaOsiguranja = e.getMessage();
        }
        return String.format(
                "Naslov automobila: %s Opis automobila: %s Stanje automobila: %s Snaga automobila u kW: %s Izracun osiguranja automobila: %s Cijena automobila: %s",
                getNaslov(),
                getOpis(),
                getStanje().toString(),
                izracunajKw(snagaKs).setScale(2, RoundingMode.CEILING),
                tekstIzracunaOsiguranja,
                getCijena()
        );
    }
}
