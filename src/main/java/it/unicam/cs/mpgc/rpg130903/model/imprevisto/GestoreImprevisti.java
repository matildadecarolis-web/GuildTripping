package it.unicam.cs.mpgc.rpg130903.model.imprevisto;

import it.unicam.cs.mpgc.rpg130903.model.gilda.Gilda;

import java.util.List;
import java.util.Random;

/**
 * Decide se all'inizio di un turno capita un imprevisto, lo sceglie a caso tra quelli
 * disponibili e lo applica alla gilda.
 */
public final class GestoreImprevisti {

    private final Random random;
    private final List<Imprevisto> imprevisti;
    private final int probabilita;

    public GestoreImprevisti(Random random, List<Imprevisto> imprevisti, int probabilita) {
        if (random == null) {
            throw new IllegalArgumentException("Il generatore casuale non può essere nullo");
        }
        if (imprevisti == null || imprevisti.isEmpty()) {
            throw new IllegalArgumentException("Gli imprevisti non possono essere nulli o vuoti");
        }
        if (probabilita < 0 || probabilita > 100) {
            throw new IllegalArgumentException("La probabilità deve essere tra 0 e 100");
        }
        this.random = random;
        this.imprevisti = List.copyOf(imprevisti);
        this.probabilita = probabilita;
    }

    public Imprevisto applicaImprevisto(Gilda gilda) {
        int numeroEstratto = random.nextInt(100);
        if (numeroEstratto >= probabilita) {
            return null;
        }
        Imprevisto imprevisto = imprevisti.get(random.nextInt(imprevisti.size()));
        imprevisto.applica(gilda);
        return imprevisto;
    }
}
