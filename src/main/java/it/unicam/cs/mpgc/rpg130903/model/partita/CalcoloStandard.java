package it.unicam.cs.mpgc.rpg130903.model.partita;

import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;
import it.unicam.cs.mpgc.rpg130903.model.eroe.Stat;
import it.unicam.cs.mpgc.rpg130903.model.eventofinale.EventoFinale;
import it.unicam.cs.mpgc.rpg130903.model.gilda.Gilda;

import java.util.List;
import java.util.Random;

/**
 * Calcolo standard dell'evento finale.
 * Ogni eroe conta con tutta la sua forza se tutte le statistiche sono almeno a metà,
 * con metà della forza (arrotondata per difetto) se almeno una è sotto la metà,
 * e zero se almeno una è a zero. Alla somma si aggiunge un d20;
 * si vince se il punteggio è maggiore o uguale alla difficoltà.
 */
public final class CalcoloStandard implements CalcoloEsito {

    private static final int FACCE_DADO = 20;

    private final Random random;
    private final List<Stat> statistiche;

    public CalcoloStandard(Random random, List<Stat> statistiche) {
        if (random == null) {
            throw new IllegalArgumentException("Il generatore casuale non può essere nullo");
        }
        if (statistiche == null || statistiche.isEmpty()) {
            throw new IllegalArgumentException("Le statistiche non possono essere nulle o vuote");
        }
        this.random = random;
        this.statistiche = List.copyOf(statistiche);
    }

    @Override
    public EsitoEvento calcola(Gilda gilda, EventoFinale evento) {
        int forza = 0;
        List<Eroe> eroi = gilda.getEroi();

        for (int i = 0; i < eroi.size(); i++) {
            Eroe eroe = eroi.get(i);
            if (haStatisticaAZero(eroe)) {
                // non aggiunge niente: l'eroe conta zero
            } else if (haStatisticaSottoMeta(eroe)) {
                forza += eroe.getForza() / 2;
            } else {
                forza += eroe.getForza();
            }
        }

        int dado = random.nextInt(FACCE_DADO) + 1;
        return new EsitoEvento(forza, dado, evento.getDifficolta());
    }

    @Override
    public String getDescrizione() {
        String nomi = "";
        for (int i = 0; i < statistiche.size(); i++) {
            if (i > 0) {
                nomi = nomi + " o ";
            }
            nomi = nomi + statistiche.get(i).getNome().toLowerCase();
        }
        return "All'evento ogni eroe combatte con tutta la sua forza, con metà se " + nomi
                + " scende sotto metà, e non combatte se arriva a zero.";
    }

    private boolean haStatisticaAZero(Eroe eroe) {
        for (int i = 0; i < statistiche.size(); i++) {
            if (statistiche.get(i).isEsaurita(eroe)) {
                return true;
            }
        }
        return false;
    }

    private boolean haStatisticaSottoMeta(Eroe eroe) {
        for (int i = 0; i < statistiche.size(); i++) {
            if (statistiche.get(i).isSottoMeta(eroe)) {
                return true;
            }
        }
        return false;
    }
}