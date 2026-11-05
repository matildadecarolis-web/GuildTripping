package it.unicam.cs.mpgc.rpg130903.model.eroe;

import it.unicam.cs.mpgc.rpg130903.model.classe.Classe;

/**
 * Implementazione standard di {@link Eroe}.
 * <p>
 * Custodisce lo stato dell'eroe e ne garantisce i vincoli
 * Un eroe appena creato ha energia e salute al massimo e la forza iniziale della sua classe.
 * </p>
 */

public class EroeBase implements Eroe{

    private final String nome;
    private final Classe classe;
    private final int maxStat;
    private int forza;
    private int energia;
    private int salute;

    public EroeBase(String nome, Classe classe, int maxStat) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome non può essere vuoto");
        }

        if ( classe == null) {
            throw new IllegalArgumentException("La classe non può essere nulla");
        }

        if (maxStat <= 0) {
            throw new IllegalArgumentException("Le statistiche devono essere positive");
        }

        this.nome = nome;
        this.classe = classe;
        this.maxStat = maxStat;
        this.energia = maxStat;
        this.salute = maxStat;
        this.forza = classe.getForzaIniziale();
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public Classe getClasse() {
        return classe;
    }

    @Override
    public int getEnergia() {
        return energia;
    }

    @Override
    public int getSalute() {
        return salute;
    }

    @Override
    public int getForza() {
        return forza;
    }

    @Override
    public int getMaxStat() {
        return maxStat;
    }

    /**
     * Aumenta la forza della quantità stabilita dalla classe dell'eroe.
     * Non consuma energia né oro: i costi appartengono all'attività di addestramento.
     *
     * @return la forza totale dopo l'allenamento
     */

    @Override
    public int allenamento() {
        int guadagno = classe.getForzaGuadagnata(forza);
        forza += guadagno;
        return forza;
    }

    @Override
    public boolean isEsausto() {
        return energia == 0;
    }

    @Override
    public boolean isMalato() {
        return salute == 0;
    }

    @Override
    public void spendiEnergia(int valore) {
        energia = diminuisci(energia, valore);
    }

    @Override
    public void perdiSalute(int valore) {
        salute = diminuisci(salute, valore);
    }

    @Override
    public void ripristinaEnergia() {
        energia = maxStat;
    }

    @Override
    public void ripristinaSalute() {
        salute = maxStat;
    }

    /**
     * Sottrae una quantità da un valore senza scendere sotto zero.
     *
     * @param corrente valore attuale
     * @param valore   quantità da sottrarre, non negativa
     * @return il nuovo valore, mai negativo
     * @throws IllegalArgumentException se {@code valore} è negativo
     */
    private static int diminuisci(int corrente, int valore) {
        if (valore < 0) {
            throw new IllegalArgumentException("Il valore non può essere negativo");
        }
        return Math.max(0, corrente - valore);
    }
}
