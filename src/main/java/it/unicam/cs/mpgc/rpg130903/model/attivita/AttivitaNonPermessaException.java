package it.unicam.cs.mpgc.rpg130903.model.attivita;

/**
 * Lanciata quando un'attività non può essere eseguita perché le sue regole non sono rispettate.
 * Il messaggio è pensato per essere mostrato al giocatore.
 */
public class AttivitaNonPermessaException extends Exception {
    public AttivitaNonPermessaException(String messaggio) {
        super(messaggio);
    }
}
