package it.unicam.cs.mpgc.rpg130903.model.missione;

import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;

public interface Quest {
    EsitoMissione risolvi();

    String getDescrizione();

    int getDifficolta();

    int getRicompensa();

    int getReputazione();

    void assegna(Assegnabile assegnabile);

    public enum StatoMissione {DISPONIBILE, IN_CORSO, CONCLUSA}

    StatoMissione getStato();

}
