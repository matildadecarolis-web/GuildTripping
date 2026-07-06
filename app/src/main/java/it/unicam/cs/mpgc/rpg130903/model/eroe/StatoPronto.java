package it.unicam.cs.mpgc.rpg130903.model.eroe;

public class StatoPronto implements StatoEroe {
    @Override
    public boolean pronto(Eroe eroe) {
        return true;
    }

    @Override
    public StatoEroe riposo(Eroe eroe) {
    return this;
    }

    @Override
    public StatoEroe inMissione(Eroe eroe) {
        return new StatoInMissione();
    }

    @Override
    public StatoEroe ritornaDallaMissione(Eroe eroe, boolean esitoPositivo) {
        return this;
    }
}
