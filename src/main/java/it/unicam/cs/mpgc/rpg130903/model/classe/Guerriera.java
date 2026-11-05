package it.unicam.cs.mpgc.rpg130903.model.classe;

public class Guerriera implements Classe{

    private static final int FORZA_INIZIALE = 12;
    private static final int FATTORE_AUMENTO_PROGRESSIVO = 3;


    @Override
    public String getName() {
        return "Rebecca";
    }

    @Override
    public String getDescrizione() {
        return "Parte forte e migliora sempre allo stesso ritmo.";
    }

    @Override
    public int getForzaIniziale() {
        return FORZA_INIZIALE;
    }

    @Override
    public int getForzaGuadagnata(int forzaAttuale) {
        return FATTORE_AUMENTO_PROGRESSIVO;
    }
}
