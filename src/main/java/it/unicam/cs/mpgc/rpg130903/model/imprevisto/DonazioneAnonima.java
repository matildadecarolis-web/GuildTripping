package it.unicam.cs.mpgc.rpg130903.model.imprevisto;

import it.unicam.cs.mpgc.rpg130903.model.gilda.Gilda;

public final class DonazioneAnonima implements Imprevisto {

    private final int oroDonato;

    public DonazioneAnonima(int oroDonato) {
        if (oroDonato < 0) {
            throw new IllegalArgumentException("L'oro donato non può essere negativo");
        }
        this.oroDonato = oroDonato;
    }

    @Override
    public String getNome() {
        return "Donazione Anonima";
    }

    @Override
    public String getDescrizione() {
        return "La gilda ha ricevuto una donazione anonima di " + oroDonato + " monete";
    }

    @Override
    public void applica(Gilda gilda) {
        gilda.aggiungiOro(oroDonato);
    }
}
