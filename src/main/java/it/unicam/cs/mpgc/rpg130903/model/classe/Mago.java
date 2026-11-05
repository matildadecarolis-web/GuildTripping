package it.unicam.cs.mpgc.rpg130903.model.classe;

/**
 * Il guadagno cresce di un punto per ogni decina di forza.
 */
public final class Mago implements Classe {

    private static final int FORZA_INIZIALE = 6;
    private static final int AUMENTO_BASE = 2;
    private static final int FATTORE_AUMENTO_PROGRESSIVO = 10;

    @Override
    public String getNome() {
        return "Mago";
    }

    @Override
    public String getDescrizione() {
        return "Parte debole, ma la crescita accelera con l'addestramento.";
    }

    @Override
    public int getForzaIniziale() {
        return FORZA_INIZIALE;
    }

    @Override
    public int getForzaGuadagnata(int forzaAttuale) {
        return AUMENTO_BASE + forzaAttuale / FATTORE_AUMENTO_PROGRESSIVO;
    }
}
