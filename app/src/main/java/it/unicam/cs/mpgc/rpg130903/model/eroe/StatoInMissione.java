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
    public void inmissione(Eroe eroe) {
        System.out.println(eroe.getNome() + " è già sul campo di battaglia");
    }
}
