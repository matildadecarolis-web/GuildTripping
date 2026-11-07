package it.unicam.cs.mpgc.rpg130903.model.attivita;

import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;
import it.unicam.cs.mpgc.rpg130903.model.gilda.Gilda;

/**
 * Un'attività che il giocatore può far svolgere a un eroe durante il turno.
 * Per aggiungere una nuova attività basta una nuova implementazione.
 */
public interface Attivita {

    String getNome();
    String getDescrizione();

    /**
     * Controlla le regole specifiche dell'attività (energia, salute, oro).
     * @throws AttivitaNonPermessaException se le regole non sono rispettate,
     * con un messaggio da mostrare al giocatore
     */
    void valida (Eroe eroe, Gilda gilda);

    /**
     * Chiamato dopo valida, applica gli effetti dell'attività.
     */
    void applica(Eroe eroe, Gilda gilda);
}
