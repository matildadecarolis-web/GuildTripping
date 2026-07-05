package it.unicam.cs.mpgc.rpg130903.model.eroe;

public class StatoInMissione implements StatoEroe{

    @Override
    public boolean pronto(Eroe eroe) {
        System.out.println(eroe.getNome() + " è già in missione");
        return false;
    }

    @Override
    public void riposo(Eroe eroe) {
        System.out.println(eroe.getNome() + " è in missione non può riposare");
    }

    @Override
    public void inMissione(Eroe eroe) {
        System.out.println(eroe.getNome() + " è già sul campo di battaglia");
    }

    @Override
    public void ritornaDallaMissione(Eroe eroe, boolean esitoPositivo) {
        System.out.println(eroe.getNome() + " è tornato dalla sua avventura.");

        if (esitoPositivo){
            System.out.println("Vittoria! La missione è stata un successo.");
            eroe.aggiungiMissioneCompletata();
        }
        else {
            System.out.println("Sconfitta... La missione è fallita");
        }
        eroe.setStato(new StatoStanco());
    }
}
