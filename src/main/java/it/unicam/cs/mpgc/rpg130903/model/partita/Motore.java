package it.unicam.cs.mpgc.rpg130903.model.partita;

import it.unicam.cs.mpgc.rpg130903.model.attivita.Attivita;
import it.unicam.cs.mpgc.rpg130903.model.attivita.AttivitaNonPermessaException;
import it.unicam.cs.mpgc.rpg130903.model.attivita.Recupero;
import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;
import it.unicam.cs.mpgc.rpg130903.model.gilda.Gilda;
import it.unicam.cs.mpgc.rpg130903.model.imprevisto.GestoreImprevisti;
import it.unicam.cs.mpgc.rpg130903.model.imprevisto.Imprevisto;

import java.util.List;

/**
 * Applica le regole del turno, lo stato della partita invece si trova in Partita.
 * Il motore non crea gli oggetti, li riceve dal costruttore.
 */
public class Motore {

    private final Partita partita;
    private final List<Attivita> attivita;
    private final List<Recupero> recuperi;
    private final CalcoloEsito calcolo;
    private EsitoEvento esito;
    private final GestoreImprevisti imprevisti;
    private Imprevisto ultimoImprevisto;

    public Motore(Partita partita, List<Attivita> attivita, List<Recupero> recuperi,
                  CalcoloEsito calcolo, GestoreImprevisti imprevisti) {
        if (partita == null) {
            throw new IllegalArgumentException("La partita non può essere nulla");
        }
        if (attivita == null || attivita.isEmpty()) {
            throw new IllegalArgumentException("Le attività non possono essere nulle o vuote");
        }
        if (recuperi == null || recuperi.isEmpty()) {
            throw new IllegalArgumentException("I recuperi non possono essere nulli o vuoti");
        }
        if (calcolo == null) {
            throw new IllegalArgumentException("Il calcolo dell'esito non può essere nullo");
        }
        if (imprevisti == null) {
            throw new IllegalArgumentException("Il gestore degli imprevisti non può essere nullo");
        }
        this.partita = partita;
        this.attivita = List.copyOf(attivita);
        this.recuperi = List.copyOf(recuperi);
        this.calcolo = calcolo;
        this.imprevisti = imprevisti;
    }

    public List<Attivita> getAttivita() {
        return attivita;
    }

    /**
     * @return l'esito dell'evento finale, oppure {@code null} se la partita è ancora in corso
     */
    public EsitoEvento getEsito() {
        return esito;
    }

    /**
     * @return l'imprevisto capitato all'inizio del turno corrente,
     * oppure {@code null} se non ne è capitato nessuno
     */
    public Imprevisto getUltimoImprevisto() {
        return ultimoImprevisto;
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
        if (scelta == null) {
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
     * in recupero per tutto il turno. Poi l'eventuale imprevisto.
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
        ultimoImprevisto = imprevisti.applicaImprevisto(gilda);
    }

    /**
     * Chiude il turno corrente. Termina la partita se il turno finale lo risolve.
     */
    public void terminaTurno() {
        controllaInCorso();
        if (partita.getTurno() == partita.getEvento().getTurnoInnesco()) {
            risolviEvento();
            return;
        }
        partita.avanzaTurno();
        iniziaTurno();
    }

    /**
     * Calcola l'esito dell'evento finale e conclude la partita con vittoria o sconfitta.
     */
    private void risolviEvento() {
        esito = calcolo.calcola(partita.getGilda(), partita.getEvento());
        if (esito.isVittoria()) {
            partita.concludi(StatoPartita.VITTORIA);
        } else {
            partita.concludi(StatoPartita.SCONFITTA);
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
