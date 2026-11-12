package it.unicam.cs.mpgc.rpg130903.model.eventofinale;

public final class EsitoEvento {

    private final int forza;
    private final int dado;
    private final int difficolta;
    private final int punteggio;
    private final boolean vittoria;

    /**
     * Esito dell'evento finale: forza degli eroi, tiro del dado, difficoltà e punteggio.
     * Punteggio e vittoria sono calcolati nel costruttore, così l'esito è sempre coerente.
     */
    public EsitoEvento(int forza, int dado, int difficolta) {
        if (forza < 0 || dado < 0 || difficolta < 0) {
            throw new IllegalArgumentException("I dati non possono essere negativi");
        }
        this.forza = forza;
        this.dado = dado;
        this.difficolta = difficolta;
        this.punteggio = forza + dado;
        this.vittoria = punteggio >= difficolta;
    }

    public int getForza() {
        return forza;
    }

    public int getDado() {
        return dado;
    }

    public int getDifficolta() {
        return difficolta;
    }

    public int getPunteggio() {
        return punteggio;
    }

    /**
     * @return {@code true} se il punteggio è maggiore o uguale alla difficoltà
     */
    public boolean isVittoria() {
        return vittoria;
    }
}
