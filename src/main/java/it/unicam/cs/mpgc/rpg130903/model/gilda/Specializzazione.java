package it.unicam.cs.mpgc.rpg130903.model.gilda;

import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;
import it.unicam.cs.mpgc.rpg130903.model.eventofinale.EventoFinale;

import java.util.List;

/**
 * La specializzazione scelta a inizio partita (una per ora).
 * Determina la missione critica e la squadra iniziale.
 * In futuro si possono fare più specializzazioni con altre implementazioni.
 */
public interface Specializzazione {
    String getNome();

    String getDescrizione();

    /**
     * Crea una nuova squadra di tre eroi, con statistiche al massimo.
     */
    List<Eroe> creaSquadra(int maxStat);

    EventoFinale getEventoFinale();

}
