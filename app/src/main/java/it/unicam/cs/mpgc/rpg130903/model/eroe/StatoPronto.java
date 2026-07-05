package it.unicam.cs.mpgc.rpg130903.model.eroe;

public class StatoPronto implements StatoEroe {
    @Override
    public boolean pronto(Eroe eroe) {
        return true;
    }

    @Override
    public void riposo(Eroe eroe) {
        System.out.println(eroe.getNome() + " è già riposato e pronto all'azione");
    }

    @Override
    public void inMissione(Eroe eroe) {
        System.out.println(eroe.getNome() + " parte per la missione!");
        eroe.setStato(new StatoInMissione());
    }

    @Override
    public void ritornaDallaMissione(Eroe eroe, boolean esitoPositivo) {
        System.out.println(eroe.getNome() + " non era in missione");
    }
}
