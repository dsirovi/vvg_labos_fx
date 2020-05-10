package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

public class Pretraga<T extends Kategorija> {
    List<T> listaArtikala;

    public Pretraga() {
        listaArtikala = new ArrayList<>();
    }

    public void dodajArtikl (T artikl){
        listaArtikala.add(artikl);
    }
    public T dohvatiArtikl(int i){
        return listaArtikala.get(i);
    }
    public List<T> dohvatiListuArtikala(){
        return listaArtikala;
    }
}
