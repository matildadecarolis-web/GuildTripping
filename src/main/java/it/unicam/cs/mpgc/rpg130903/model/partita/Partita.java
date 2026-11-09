package it.unicam.cs.mpgc.rpg130903.model.partita;

import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;
import it.unicam.cs.mpgc.rpg130903.model.gilda.Gilda;
import it.unicam.cs.mpgc.rpg130903.model.missione.MissioneCritica;

import java.util.ArrayList;
import java.util.List;

public class Partita {
    private final Gilda gilda;
    private final MissioneCritica missione;
    private final int azioniPerTurno;
    private final List<Eroe> eroiInRecupero;
    private int turno;
    private int azioniRimaste;
    private StatoPartita stato;

    public Partita(int azioniPerTurno, MissioneCritica missione, Gilda gilda) {
        if (gilda == null || missione == null) {
            throw new IllegalArgumentException("Sia la gilda che la missione devono essere valide");
        }
        if (azioniPerTurno < 0) {
            throw new IllegalArgumentException("Il numero di missioni non può essere negativo");
        }
        this.gilda = gilda;
        this.missione = missione;
        this.azioniPerTurno = azioniPerTurno;
        this.eroiInRecupero = new ArrayList<>();
        this.turno = 1;
        this.azioniRimaste = azioniPerTurno;
        this.stato = StatoPartita.IN_CORSO;
    }


}
