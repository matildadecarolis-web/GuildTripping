package it.unicam.cs.mpgc.rpg130903.model.eroe;

public interface StatoEroe {

    boolean pronto(Eroe eroe);

    void riposo(Eroe eroe);

    void inMissione(Eroe eroe);

    void ritornaDallaMissione (Eroe eroe, boolean esitoPositivo);
}
