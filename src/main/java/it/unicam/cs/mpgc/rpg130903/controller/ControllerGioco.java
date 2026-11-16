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

import java.util.List;

/**
 * Collega la GUI al model. Non contiene regole di gioco.
 */
public final class ControllerGioco {

    private final CreatorePartita creatore;
    private Motore motore;

    public ControllerGioco(CreatorePartita creatore) {
        if (creatore == null) {
            throw new IllegalArgumentException("Il creatore della partita non può essere nullo");
        }
        this.creatore = creatore;
    }

    /**
     * Avvia una nuova partita, sostituendo quella eventualmente in corso.
     * Va chiamato prima di qualsiasi altro metodo.
     */
    public void nuovaPartita(String nomeGilda) {
        motore = creatore.nuovaPartita(nomeGilda);
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

    public void terminaTurno() {
        motore.terminaTurno();
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
}