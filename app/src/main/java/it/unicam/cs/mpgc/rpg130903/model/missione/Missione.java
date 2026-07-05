package it.unicam.cs.mpgc.rpg130903.model.missione;

import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;

import java.util.Random;

public class Missione {
    private final String descrizione;
    private final int difficolta;
    private final int ricompensa;
    private final int reputazione;
    private Eroe eroeAssegnato;
    private StatoMissione statoMissione;

    private static final int COSTANTE_RICOMPENSA = 20;
    private static final int COSTANTE_REPUTAZIONE = 100;

    public enum StatoMissione {DISPONIBILE, IN_CORSO, CONCLUSA}

    public Missione(String descrizione, int difficolta) {
        this.descrizione = descrizione;
        this.difficolta = difficolta;
        this.ricompensa = difficolta * COSTANTE_RICOMPENSA;
        this.reputazione = difficolta * COSTANTE_REPUTAZIONE;
        statoMissione = StatoMissione.DISPONIBILE;
        this.eroeAssegnato = null;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getRicompensa() {
        return ricompensa;
    }

    public int getReputazione() {
        return reputazione;
    }

    public void assegna(Eroe eroe) {
        if (statoMissione == StatoMissione.DISPONIBILE) {
            this.eroeAssegnato = eroe;
            statoMissione = StatoMissione.IN_CORSO;
        } else throw new IllegalStateException("Fra la missione non è disponibile!");
    }

    public EsitoMissione risolvi() {
        if (statoMissione == StatoMissione.DISPONIBILE || statoMissione == StatoMissione.CONCLUSA) {
            throw new IllegalStateException("Fra la missione non è in corso!");
        }
        System.out.println("--- Risoluzione Missione: " + this.descrizione + " ---");
        System.out.println("Difficoltà: " + this.difficolta + " | Eroe: " + eroeAssegnato.getNome() + " (Livello " + eroeAssegnato.getLivello() + ")");

        int delta = eroeAssegnato.getLivello() - this.difficolta;
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
