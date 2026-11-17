package it.unicam.cs.mpgc.rpg130903.controller;

import it.unicam.cs.mpgc.rpg130903.model.attivita.Attivita;
import it.unicam.cs.mpgc.rpg130903.model.attivita.AttivitaNonPermessaException;
import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;
import it.unicam.cs.mpgc.rpg130903.model.eventofinale.EventoFinale;
import it.unicam.cs.mpgc.rpg130903.model.imprevisto.Imprevisto;
import it.unicam.cs.mpgc.rpg130903.model.partita.CreatorePartita;
import it.unicam.cs.mpgc.rpg130903.model.partita.EsitoEvento;
import it.unicam.cs.mpgc.rpg130903.model.partita.Motore;
import it.unicam.cs.mpgc.rpg130903.model.partita.StatoPartita;
import it.unicam.cs.mpgc.rpg130903.model.storico.PartitaSalvata;
import it.unicam.cs.mpgc.rpg130903.persistence.StoricoRepository;

import java.io.IOException;
import java.time.LocalDate;

import java.util.List;

/**
 * Collega la GUI al model. Non contiene regole di gioco.
 */
public final class ControllerGioco {

    private final CreatorePartita creatore;
    private final StoricoRepository storico;
    private Motore motore;
    private String nomeGilda;

    public ControllerGioco(CreatorePartita creatore, StoricoRepository storico) {
        if (creatore == null || storico == null) {
            throw new IllegalArgumentException("Creatore della partita e storico non possono essere nulli");
        }
        this.creatore = creatore;
        this.storico = storico;
    }

    /**
     * Avvia una nuova partita, sostituendo quella eventualmente in corso.
     * Va chiamato prima di qualsiasi altro metodo.
     */
    public void nuovaPartita(String nomeGilda) {
        motore = creatore.nuovaPartita(nomeGilda);
        this.nomeGilda = nomeGilda;
    }

    /**
     * Esegue un'attività e restituisce il messaggio da mostrare al giocatore:
     * una conferma se è andata a buon fine, altrimenti il motivo del rifiuto.
     */
    public String eseguiAttivita(Attivita attivita, Eroe eroe) {
        try {
            motore.eseguiAttivita(attivita, eroe);
            return eroe.getNome() + ": " + attivita.getNome() + " eseguito";
        } catch (AttivitaNonPermessaException e) {
            return e.getMessage();
        }
    }

    /**
     * Termina il turno; se con questo la partita si conclude, la salva nello storico.
     */
    public void terminaTurno() {
        motore.terminaTurno();
        if (isPartitaConclusa()) {
            salvaNelloStorico();
        }
    }

    /**
     * @return le partite concluse finora, dalla più vecchia alla più recente;
     * vuota se lo storico non è leggibile
     */
    public List<PartitaSalvata> getStorico() {
        try {
            return storico.carica();
        } catch (IOException e) {
            System.err.println("Impossibile leggere lo storico: " + e.getMessage());
            return List.of();
        }
    }

    private void salvaNelloStorico() {
        try {
            storico.salva(new PartitaSalvata(nomeGilda, motore.getEsito(), LocalDate.now()));
        } catch (IOException e) {
            System.err.println("Impossibile salvare la partita nello storico: " + e.getMessage());
        }
    }

    public boolean isPartitaConclusa() {
        return motore.getStato() != StatoPartita.IN_CORSO;
    }

    public int getTurno() {
        return motore.getTurno();
    }

    public int getAzioniRimaste() {
        return motore.getAzioniRimaste();
    }

    public int getOro() {
        return motore.getOro();
    }

    public List<Eroe> getEroi() {
        return motore.getEroi();
    }

    public List<Attivita> getAttivita() {
        return motore.getAttivita();
    }

    public EventoFinale getEvento() {
        return motore.getEvento();
    }

    public Imprevisto getUltimoImprevisto() {
        return motore.getUltimoImprevisto();
    }

    public EsitoEvento getEsito() {
        return motore.getEsito();
    }

    public boolean isDisponibile(Eroe eroe) {
        return motore.isDisponibile(eroe);
    }

    public boolean isInRecupero(Eroe eroe) {
        return motore.isInRecupero(eroe);
    }

    public String getRegolaEvento() {
        return motore.getRegolaEvento();
    }
}