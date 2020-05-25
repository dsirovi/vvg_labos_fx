package hr.java.vjezbe.entitet;

public enum Stanje {
    novo("1"),
    izvrsno("2"),
    rabljeno("3"),
    neispravno("4");

    private final String id;

    Stanje(String id) {
        this.id = id;
    }

    public  static Stanje odrediStanje(String stanje){
        return valueOf(stanje);
    }


    public String getId() {
        return id;
    }
}
