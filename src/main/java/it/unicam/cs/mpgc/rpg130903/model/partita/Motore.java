package it.unicam.cs.mpgc.rpg130903.model.partita;

import it.unicam.cs.mpgc.rpg130903.model.attivita.Attivita;
import it.unicam.cs.mpgc.rpg130903.model.attivita.Recupero;
import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;

import java.util.List;

public class Motore {

    private final Partita partita;
    private final List<Attivita> attivita;
    private final List<Recupero> recuperi;

    public Motore(Partita partita, List<Attivita> attivita, List<Recupero> recuperi) {
        if (partita == null ) {
            throw new IllegalArgumentException("La partita non può essere nulla");
        }
        if (attivita == null || attivita.isEmpty()){
            throw new IllegalArgumentException("L'attività non può essere nulla o vuota");
        }
        if (recuperi == null || recuperi.isEmpty()){
            throw new IllegalArgumentException("I recuperi non possono essere nulli o vuoti");
        }
        this.partita = partita;
        this.attivita = List.copyOf(attivita);
        this.recuperi = List.copyOf(recuperi);
    }

    public List<Attivita> getAttivita() {
        return attivita;
    }

    private void controllaInCorso() {
        if (partita.getStato() != StatoPartita.IN_CORSO) {
            throw new IllegalStateException("La partita è già conclusa");
        }
    }

    /**
     * Controlla se una delle due statistiche è esaurita.
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
