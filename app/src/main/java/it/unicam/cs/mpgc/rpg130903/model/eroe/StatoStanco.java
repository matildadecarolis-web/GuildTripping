package it.unicam.cs.mpgc.rpg130903.model.eroe;

public class StatoStanco implements StatoEroe{


    @Override
    public boolean pronto(Eroe eroe) {
        return false;
    }

    @Override
    public StatoEroe riposo(Eroe eroe) {
        return new StatoPronto();
    }

    @Override
    public StatoEroe inMissione(Eroe eroe) {
        return this;
    }

    @Override
    public StatoEroe ritornaDallaMissione(Eroe eroe, boolean esitoPositivo) {
    return this;
    }
}
