package it.unicam.cs.mpgc.rpg130903.model.partita;

import it.unicam.cs.mpgc.rpg130903.model.attivita.Addestramento;
import it.unicam.cs.mpgc.rpg130903.model.attivita.Attivita;
import it.unicam.cs.mpgc.rpg130903.model.attivita.Missione;
import it.unicam.cs.mpgc.rpg130903.model.attivita.Recupero;
import it.unicam.cs.mpgc.rpg130903.model.configurazione.Configurazione;
import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;
import it.unicam.cs.mpgc.rpg130903.model.eroe.StatEnergia;
import it.unicam.cs.mpgc.rpg130903.model.eroe.StatSalute;
import it.unicam.cs.mpgc.rpg130903.model.gilda.Gilda;
import it.unicam.cs.mpgc.rpg130903.model.gilda.GildaAvventurieri;
import it.unicam.cs.mpgc.rpg130903.model.gilda.GildaBase;
import it.unicam.cs.mpgc.rpg130903.model.gilda.Specializzazione;
import it.unicam.cs.mpgc.rpg130903.model.imprevisto.AttaccoBanditi;
import it.unicam.cs.mpgc.rpg130903.model.imprevisto.DonazioneAnonima;
import it.unicam.cs.mpgc.rpg130903.model.imprevisto.Epidemia;
import it.unicam.cs.mpgc.rpg130903.model.imprevisto.GestoreImprevisti;
import it.unicam.cs.mpgc.rpg130903.model.imprevisto.Imprevisto;
import it.unicam.cs.mpgc.rpg130903.model.imprevisto.MaestroDArmi;

import java.util.List;
import java.util.Random;

/**
 * Costruisce una partita nuova, completa e pronta da giocare.
 * È l'unica classe che legge {@link Configurazione} e che conosce le classi concrete
 * del gioco: tutte le altre ricevono ciò che usano dal costruttore.
 */
public final class CreatorePartita {

    private final Random random;

    public CreatorePartita(Random random) {
        if (random == null) {
            throw new IllegalArgumentException("Il generatore casuale non può essere nullo");
        }
        this.random = random;
    }

    /**
     * Crea una partita nuova e indipendente dalle precedenti: eroi, gilda e turni ripartono da capo.
     */
    public Motore nuovaPartita(String nomeGilda) {
        Specializzazione specializzazione = new GildaAvventurieri();
        List<Eroe> squadra = specializzazione.creaSquadra(Configurazione.MAX_STAT);
        Gilda gilda = new GildaBase(Configurazione.ORO_INIZIALE, squadra, specializzazione, nomeGilda);

        Recupero infermeria = new Recupero(new StatSalute(), Configurazione.COSTO_ORO_INFERMERIA, "Infermeria");
        Recupero giornoLibero = new Recupero(new StatEnergia(), Configurazione.COSTO_ORO_GIORNO_LIBERO, "Giorno Libero");

        List<Attivita> attivita = List.of(
                new Addestramento(Configurazione.COSTO_ENERGIA_ADDESTRAMENTO, Configurazione.COSTO_ORO_ADDESTRAMENTO),
                new Missione(Configurazione.COSTO_SALUTE_MISSIONE, Configurazione.GUADAGNO_ORO_MISSIONE),
                infermeria,
                giornoLibero);
        List<Recupero> recuperi = List.of(infermeria, giornoLibero);

        CalcoloEsito calcolo = new CalcoloStandard(random, List.of(new StatEnergia(), new StatSalute()));
        GestoreImprevisti imprevisti = new GestoreImprevisti(random, creaImprevisti(), Configurazione.PROBABILITA_IMPREVISTO);

        Partita partita = new Partita(Configurazione.AZIONI_PER_TURNO, specializzazione.getEventoFinale(), gilda);
        return new Motore(partita, attivita, recuperi, calcolo, imprevisti);
    }

    private List<Imprevisto> creaImprevisti() {
        return List.of(
                new AttaccoBanditi(Configurazione.ORO_RUBATO_BANDITI),
                new DonazioneAnonima(Configurazione.ORO_DONATO_ANONIMO),
                new Epidemia(Configurazione.SALUTE_PERSA_EPIDEMIA),
                new MaestroDArmi());
    }
}