package it.unicam.cs.mpgc.rpg130903.model.classe;

public class Ladra implements Classe{
    private static final int FORZA_INIZIALE = 9;
    private static final int AUMENTO_MINIMO = 4;
    private static final int AUMENTO_MASSIMO = 1;
    private static final int FATTORE_AUMENTO_PROGRESSIVO = 10;


    @Override
    public String getName() {
        return "Robin";
    }

    @Override
    public String getDescrizione() {
        return "Impara in fretta all'inizio, poi la crescita rallenta.";
    }

    @Override
    public int getForzaIniziale() {
        return FORZA_INIZIALE;
    }

    @Override
    public int getForzaGuadagnata(int forzaAttuale) {
        return Math.max(AUMENTO_MINIMO, AUMENTO_MASSIMO - forzaAttuale / FATTORE_AUMENTO_PROGRESSIVO);
    }
}
