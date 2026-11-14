package it.unicam.cs.mpgc.rpg130903.model.gilda;

import it.unicam.cs.mpgc.rpg130903.model.classe.Guerriera;
import it.unicam.cs.mpgc.rpg130903.model.classe.Ladra;
import it.unicam.cs.mpgc.rpg130903.model.classe.Mago;
import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;
import it.unicam.cs.mpgc.rpg130903.model.eroe.EroeBase;
import it.unicam.cs.mpgc.rpg130903.model.eventofinale.EventoFinale;

import java.util.List;

/**
 * Specializzazione della prima release: tre eroi, uno per classe, contro l’assalto dei Goblin.
 */
public final class GildaAvventurieri implements Specializzazione {

    private static final int DIFFICOLTA_GOBLIN = 150;
    private static final int TURNO_INNESCO = 15;
    private final EventoFinale eventoGoblin = new EventoFinale(
            "Assalto dei Goblin",
            "I Goblin attaccheranno la città: la gilda dovrà schierare tutti i suoi eroi.",
            DIFFICOLTA_GOBLIN,
            TURNO_INNESCO);

    @Override
    public String getNome() {
        return "Gilda degli Avventurieri";
    }

    @Override
    public String getDescrizione() {
        return "Una gilda versatile con una guerriera, una ladra e un mago.";
    }

    @Override
    public List<Eroe> creaSquadra(int maxStat) {
        return List.of(
                new EroeBase("Rebecca", new Guerriera(), maxStat),
                new EroeBase("Robin", new Ladra(), maxStat),
                new EroeBase("Merlino", new Mago(), maxStat));
    }

    @Override
    public EventoFinale getEventoFinale() {
        return eventoGoblin;
    }
}
