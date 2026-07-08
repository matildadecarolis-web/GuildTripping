package it.unicam.cs.mpgc.rpg130903.model.eroe;

public class StatoInMissione implements StatoEroe{

    @Override
    public boolean pronto(Eroe eroe) {
        return false;
    }

    @Override
    public StatoEroe riposo(Eroe eroe) {
        return this;
    }

    @Override
    public StatoEroe inMissione(Eroe eroe) {
        return this;
    }

    @Override
    public StatoEroe ritornaDallaMissione(Eroe eroe, boolean esitoPositivo) {

        if (esitoPositivo){
            eroe.aggiungiMissioneCompletata();
        }
        return new StatoStanco();
    }
}
