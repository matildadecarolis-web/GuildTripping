package it.unicam.cs.mpgc.rpg130903.model.partita;

import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;
import it.unicam.cs.mpgc.rpg130903.model.eventofinale.EventoFinale;
import it.unicam.cs.mpgc.rpg130903.model.gilda.Gilda;

import java.util.ArrayList;
import java.util.List;

/**
 * Non contiene regole, ma solo lo stato della partita (turno corrente, azioni rimaste ecc.)
 * Le decisioni vengono prese dal motore di gioco.
 */
public final class Partita {
    private final Gilda gilda;
    private final EventoFinale evento;
    private final int azioniPerTurno;
    private final List<Eroe> eroiInRecupero;
    private int turno;
    private int azioniRimaste;
    private StatoPartita stato;

    public Partita(int azioniPerTurno, EventoFinale evento, Gilda gilda) {
        if (gilda == null || evento == null) {
            throw new IllegalArgumentException("Sia la gilda che l'evento devono essere valide");
        }
        if (azioniPerTurno <= 0) {
            throw new IllegalArgumentException("Il numero di azioni deve essere positivo");
        }
        this.gilda = gilda;
        this.evento = evento;
        this.azioniPerTurno = azioniPerTurno;
        this.eroiInRecupero = new ArrayList<>();
        this.turno = 1;
        this.azioniRimaste = azioniPerTurno;
        this.stato = StatoPartita.IN_CORSO;

    }

    public Gilda getGilda() {
        return gilda;
    }

    public EventoFinale getEvento() {
        return evento;
    }

    public int getTurno() {
        return turno;
    }

    public int getAzioniRimaste() {
        return azioniRimaste;
    }

    public StatoPartita getStato() {
        return stato;
    }

    /**
     * Controlla se ci sono azioni disponibli, se ci sono toglie un'azione.
     */
    public void consumaAzione() {
        controllaInCorso();
        if (azioniRimaste == 0) {
            throw new IllegalStateException("Non ci sono più azioni in questo turno");
        }
        azioniRimaste--;
    }

    public void segnaInRecupero(Eroe eroe) {
        if (eroe == null) {
            throw new IllegalArgumentException("L'eroe non può essere nullo");
        }
        if (!eroiInRecupero.contains(eroe)) {
            eroiInRecupero.add(eroe);
        }
    }

    public boolean isInRecupero(Eroe eroe) {
        return eroiInRecupero.contains(eroe);
    }

    /**
     * Passa al turno successivo e resetta i valori.
     * Non controlla il turno, quello lo fa il motore.
     */
    public void avanzaTurno() {
        controllaInCorso();
        turno++;
        azioniRimaste = azioniPerTurno;
        eroiInRecupero.clear();
    }

    /**
     * Conclude la partita con l'esito dell'evento finale.
     * Accetta solo VITTORIA o SCONFITTA.
     */
    public void concludi(StatoPartita esito) {
        controllaInCorso();
        if (esito == null || esito == StatoPartita.IN_CORSO) {
            throw new IllegalArgumentException("Una partita si conclude solo con vittoria o sconfitta");
        }
        stato = esito;
    }

    private void controllaInCorso() {
        if (stato != StatoPartita.IN_CORSO) {
            throw new IllegalStateException("La partita è già conclusa");
        }
    }
}
