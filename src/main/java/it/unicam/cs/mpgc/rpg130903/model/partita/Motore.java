package it.unicam.cs.mpgc.rpg130903.model.partita;

import it.unicam.cs.mpgc.rpg130903.model.attivita.Attivita;
import it.unicam.cs.mpgc.rpg130903.model.attivita.AttivitaNonPermessaException;
import it.unicam.cs.mpgc.rpg130903.model.attivita.Recupero;
import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;
import it.unicam.cs.mpgc.rpg130903.model.gilda.Gilda;

import java.util.List;

/**
 * Applica le regole del turno, lo stato della partita invece si trova in Partita.
 * Il motore non crea gli oggetti, li riceve dal costruttore.
 */
public class Motore {

    private final Partita partita;
    private final List<Attivita> attivita;
    private final List<Recupero> recuperi;

    public Motore(Partita partita, List<Attivita> attivita, List<Recupero> recuperi) {
        if (partita == null) {
            throw new IllegalArgumentException("La partita non può essere nulla");
        }
        if (attivita == null || attivita.isEmpty()) {
            throw new IllegalArgumentException("Le attività non possono essere nulle o vuote");
        }
        if (recuperi == null || recuperi.isEmpty()) {
            throw new IllegalArgumentException("I recuperi non possono essere nulli o vuoti");
        }
        this.partita = partita;
        this.attivita = List.copyOf(attivita);
        this.recuperi = List.copyOf(recuperi);
    }

    public List<Attivita> getAttivita() {
        return attivita;
    }

    /**
     * Fa eseguire un'attività a un eroe, se le regole lo permettono, e consuma un'azione.
     * L'attività è rifiutata se non restano azioni, se l'eroe è in recupero forzato
     * in questo turno, se ha una statistica a zero o se non rispetta le regole dell'attività.
     *
     * @throws AttivitaNonPermessaException se l'attività è rifiutata, con un messaggio
     *                                      da mostrare al giocatore
     * @throws IllegalArgumentException     se l'eroe non appartiene alla gilda
     * @throws IllegalStateException        se la partita è conclusa
     */
    public void eseguiAttivita(Attivita scelta, Eroe eroe) throws AttivitaNonPermessaException {
        controllaInCorso();
        Gilda gilda = partita.getGilda();
        if (!gilda.getEroi().contains(eroe)) {
            throw new IllegalArgumentException("L’eroe non appartiene alla gilda");
        }
        if (attivita == null) {
            throw new IllegalArgumentException("L'attività non può essere nulla");
        }
        if (partita.getAzioniRimaste() == 0) {
            throw new AttivitaNonPermessaException("Non ci sono più azioni disponibili");
        }
        if (partita.isInRecupero(eroe)) {
            throw new AttivitaNonPermessaException(eroe.getNome() + " è in recupero per questo turno");
        }
        if (haStatisticaAZero(eroe)) {
            throw new AttivitaNonPermessaException(eroe.getNome() + " potrà agire dal prossimo turno");
        }

        scelta.valida(eroe, gilda);
        scelta.applica(eroe, gilda);
        partita.consumaAzione();
    }

    /**
     * Applica il recupero forzato a ogni eroe con una statistica a zero e lo segna
     * in recupero per tutto il turno.
     */
    private void iniziaTurno() {
        Gilda gilda = partita.getGilda();
        List<Eroe> eroi = gilda.getEroi();
        for (int i = 0; i < eroi.size(); i++) {
            Eroe eroe = eroi.get(i);
            for (int j = 0; j < recuperi.size(); j++) {
                Recupero recupero = recuperi.get(j);
                if (recupero.getStat().isEsaurita(eroe)) {
                    recupero.applicaForzato(eroe, gilda);
                    partita.segnaInRecupero(eroe);
                }
            }
        }
    }

    /**
     * Chiude il turno corrente.
     */
    public void terminaTurno() {
        controllaInCorso();
        if (partita.getTurno() == partita.getEvento().getTurnoInnesco()) {
        } else {
            partita.avanzaTurno();
            iniziaTurno();
        }
    }

    private void controllaInCorso() {
        if (partita.getStato() != StatoPartita.IN_CORSO) {
            throw new IllegalStateException("La partita è già conclusa");
        }
    }

    /**
     * Controlla se una delle statistiche è esaurita.
     */
    private boolean haStatisticaAZero(Eroe eroe) {
        for (int i = 0; i < recuperi.size(); i++) {
            Recupero recupero = recuperi.get(i);
            if (recupero.getStat().isEsaurita(eroe)) {
                return true;
            }
        }
        return false;
    }
}
