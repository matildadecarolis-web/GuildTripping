package it.unicam.cs.mpgc.rpg130903.model.missione;

public class EsitoMissione {

    private final boolean esito;
    private final int variazioneFinanze;
    private final int variazioneReputazione;

    public EsitoMissione(boolean esito, int variazioneFinanze, int variazioneReputazione) {
        this.esito = esito;
        this.variazioneFinanze = variazioneFinanze;
        this.variazioneReputazione = variazioneReputazione;
    }

    public boolean isEsito() {
        return esito;
    }

    public int getVariazioneFinanze() {
        return variazioneFinanze;
    }

    public int getVariazioneReputazione() {
        return variazioneReputazione;
    }
}
