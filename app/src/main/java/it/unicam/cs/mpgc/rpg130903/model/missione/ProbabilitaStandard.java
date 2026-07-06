package it.unicam.cs.mpgc.rpg130903.model.missione;

public class ProbabilitaStandard implements CalcolatoreProbabilitaStrategy{

    private static final int BASE_PROBABILITA = 70;
    private static final int MOLTIPLICATORE_DELTA = 10;
    private static final int MAX_PROBABILITA = 95;
    private static final int MIN_PROBABILITA = 5;


    @Override
    public int calcolaProbabilita(int livelloEroe, int difficoltaMissione) {
        int delta = livelloEroe - difficoltaMissione;
        int probabilitaDiSuccesso = BASE_PROBABILITA + (delta * MOLTIPLICATORE_DELTA);

        if (probabilitaDiSuccesso > MAX_PROBABILITA) {
            return MAX_PROBABILITA;
        }
        if (probabilitaDiSuccesso < MIN_PROBABILITA) {
            return MIN_PROBABILITA;
        }

        return probabilitaDiSuccesso;
    }
}
