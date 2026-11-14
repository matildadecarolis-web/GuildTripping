package it.unicam.cs.mpgc.rpg130903.model.eventofinale;

import it.unicam.cs.mpgc.rpg130903.model.gilda.Gilda;

/**
 * Calcola l'esito dell'evento finale a partire dallo stato della gilda.
 * Per una regola diversa basta una nuova implementazione.
 */
public interface CalcoloEsito {
    EsitoEvento calcola(Gilda gilda, EventoFinale evento);
}
