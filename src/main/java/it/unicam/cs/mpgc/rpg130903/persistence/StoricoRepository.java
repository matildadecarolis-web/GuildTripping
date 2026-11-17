package it.unicam.cs.mpgc.rpg130903.persistence;

import it.unicam.cs.mpgc.rpg130903.model.storico.PartitaSalvata;

import java.io.IOException;
import java.util.List;

/**
 * Conserva le partite concluse tra un avvio e l'altro del gioco.
 * Il modo in cui vengono salvate dipende dall'implementazione.
 */
public interface StoricoRepository {

    /**
     * Aggiunge una partita conclusa allo storico.
     */
    void salva(PartitaSalvata partita) throws IOException;

    /**
     * @return le partite salvate, dalla più vecchia alla più recente; vuota se non ce ne sono
     * @throws IOException se lo storico esiste ma non può essere letto
     */
    List<PartitaSalvata> carica() throws IOException;
}