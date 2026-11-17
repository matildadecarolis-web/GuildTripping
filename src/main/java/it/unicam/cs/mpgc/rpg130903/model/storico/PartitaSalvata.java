package it.unicam.cs.mpgc.rpg130903.model.storico;

import it.unicam.cs.mpgc.rpg130903.model.partita.EsitoEvento;

import java.time.LocalDate;

/**
 * Il riassunto di una partita conclusa, da conservare nello storico.
 */
public final class PartitaSalvata {

    private final String nomeGilda;
    private final boolean vittoria;
    private final int punteggio;
    private final int difficolta;
    private final String data;

    public PartitaSalvata(String nomeGilda, EsitoEvento esito, LocalDate data) {
        if (nomeGilda == null || nomeGilda.isBlank()) {
            throw new IllegalArgumentException("Il nome della gilda non può essere vuoto");
        }
        if (esito == null || data == null) {
            throw new IllegalArgumentException("Esito e data non possono essere nulli");
        }
        this.nomeGilda = nomeGilda;
        this.vittoria = esito.isVittoria();
        this.punteggio = esito.getPunteggio();
        this.difficolta = esito.getDifficolta();
        this.data = data.toString();
    }

    public String getNomeGilda() {
        return nomeGilda;
    }

    public boolean isVittoria() {
        return vittoria;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public int getDifficolta() {
        return difficolta;
    }

    public String getData() {
        return data;
    }
}