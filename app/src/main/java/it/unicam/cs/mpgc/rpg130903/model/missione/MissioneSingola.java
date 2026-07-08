package it.unicam.cs.mpgc.rpg130903.model.missione;

import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;

import java.util.Random;

import static it.unicam.cs.mpgc.rpg130903.model.missione.Quest.*;

public class MissioneSingola implements Quest{
    private final String descrizione;
    private final int difficolta;
    private final int ricompensa;
    private final int reputazione;
    private Assegnabile assegnabile;
    private StatoMissione statoMissione;

    private final CalcolatoreProbabilitaStrategy calcolatoreProbabilita;

    private static final int COSTANTE_RICOMPENSA = 20;
    private static final int COSTANTE_REPUTAZIONE = 10;


    public MissioneSingola(String descrizione, int difficolta, CalcolatoreProbabilitaStrategy calcolatore) {
        this.descrizione = descrizione;
        this.difficolta = difficolta;
        this.ricompensa = difficolta * COSTANTE_RICOMPENSA;
        this.reputazione = difficolta * COSTANTE_REPUTAZIONE;
        this.calcolatoreProbabilita = calcolatore;
        statoMissione = StatoMissione.DISPONIBILE;
        this.assegnabile = null;
    }
    @Override
    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public int getDifficolta() {
        return difficolta;
    }

    @Override
    public int getRicompensa() {
        return ricompensa;
    }

    @Override
    public int getReputazione() {
        return reputazione;
    }

    @Override
    public void assegna(Assegnabile assegnabile) {
        if (statoMissione == Quest.StatoMissione.DISPONIBILE) {
            this.assegnabile = assegnabile;
            statoMissione = Quest.StatoMissione.IN_CORSO;
        } else throw new IllegalStateException("Fra la missione non è disponibile!");
    }

    @Override
    public StatoMissione getStato() {
        return statoMissione;
    }

    @Override
    public EsitoMissione risolvi() {
        if (statoMissione == Quest.StatoMissione.DISPONIBILE || statoMissione == Quest.StatoMissione.CONCLUSA) {
            throw new IllegalStateException("La missione non può essere risolta nello stato attuale!");
        }
        int probabilitaDiSuccesso = this.calcolatoreProbabilita.calcolaProbabilita(assegnabile.getLivelloOperativo(), this.difficolta);

        Random random = new Random();
        int tiroDado = random.nextInt(100) + 1;
        statoMissione = Quest.StatoMissione.CONCLUSA;

        if (tiroDado <= probabilitaDiSuccesso) {
            this.assegnabile.concludiMissione(true);
            return new EsitoMissione (true, this.ricompensa, this.reputazione);
        } else {
            this.assegnabile.concludiMissione(false);
            return new EsitoMissione (false, 0, -this.reputazione);
        }

    }
}
