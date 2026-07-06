package it.unicam.cs.mpgc.rpg130903.model.missione;

import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;

import java.util.Random;

public class MissioneSingola implements Quest{
    private final String descrizione;
    private final int difficolta;
    private final int ricompensa;
    private final int reputazione;
    private Assegnabile assegnabile;
    private StatoMissione statoMissione;

    private static final int COSTANTE_RICOMPENSA = 20;
    private static final int COSTANTE_REPUTAZIONE = 100;


    public MissioneSingola(String descrizione, int difficolta) {
        this.descrizione = descrizione;
        this.difficolta = difficolta;
        this.ricompensa = difficolta * COSTANTE_RICOMPENSA;
        this.reputazione = difficolta * COSTANTE_REPUTAZIONE;
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
        if (statoMissione == StatoMissione.DISPONIBILE) {
            this.assegnabile = assegnabile;
            statoMissione = StatoMissione.IN_CORSO;
        } else throw new IllegalStateException("Fra la missione non è disponibile!");
    }

    @Override
    public StatoMissione getStato() {
        return statoMissione;
    }

    @Override
    public EsitoMissione risolvi() {
        if (statoMissione == StatoMissione.DISPONIBILE || statoMissione == StatoMissione.CONCLUSA) {
            throw new IllegalStateException("Fra la missione non è in corso!");
        }
        System.out.println("--- Risoluzione Missione: " + this.descrizione + " ---");
        System.out.println("Difficoltà: " + this.difficolta + " | Eroe: " + assegnabile.getNomeSchieramento() + " (Livello " + assegnabile.getLivelloOperativo() + ")");

        int delta = assegnabile.getLivelloOperativo() - this.difficolta;
        int probabilitaDiSuccesso = 70;
        probabilitaDiSuccesso += (delta * 10);

        if (probabilitaDiSuccesso > 95) {
            probabilitaDiSuccesso = 95;
        }
        if (probabilitaDiSuccesso < 5) {
            probabilitaDiSuccesso = 5;
        }

        System.out.println("Probabilità di successo stimata: " + probabilitaDiSuccesso + "%");

        Random random = new Random();
        int tiroDado = random.nextInt(100) + 1;
        statoMissione = StatoMissione.CONCLUSA;

        if (tiroDado <= probabilitaDiSuccesso) {
            return new EsitoMissione (true, this.ricompensa, this.reputazione);
        } else {
            return new EsitoMissione (false, 0, -this.reputazione);
        }

    }
}
