package it.unicam.cs.mpgc.rpg130903.model.eroe;

public class StatoStanco implements StatoEroe{


    @Override
    public boolean pronto(Eroe eroe) {
        System.out.println(eroe.getNome() + " ha bisogno di riposo");
        return false;
    }

    @Override
    public void riposo(Eroe eroe) {
        System.out.println(eroe.getNome() + " è pronto per partire");
        eroe.setStato(new StatoPronto());
    }

    @Override
    public void inmissione(Eroe eroe) {
        System.out.println(eroe.getNome() + " è troppo stanco per partire");
    }
}
