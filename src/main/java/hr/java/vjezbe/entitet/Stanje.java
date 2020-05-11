package hr.java.vjezbe.entitet;

public enum Stanje {
    NOVO("1", "novo"),
    IZVRSNO("2", "izvrsno"),
    RABLJENO("3", "rabljeno"),
    NEISPRAVNO("4", "neispravno");

    private final String id;
    private final String naziv;

    Stanje(String id, String naziv) {
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
