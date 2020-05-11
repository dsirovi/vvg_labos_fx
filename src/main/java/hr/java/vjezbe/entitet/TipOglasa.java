package hr.java.vjezbe.entitet;

public enum TipOglasa {
    USLUGE("1", "usluge"),
    AUTOMOBIL("2", "automobil"),
    STAN("3", "stan");

    private final String id;
    private final String naziv;

    TipOglasa(String id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getId() {
        return id;
    }
}
