package it.unicam.cs.mpgc.rpg130903.model.eroe;

public interface StatoEroe {

    boolean pronto(Eroe eroe);

    StatoEroe riposo(Eroe eroe);

    StatoEroe inMissione(Eroe eroe);

    StatoEroe ritornaDallaMissione (Eroe eroe, boolean esitoPositivo);
}
