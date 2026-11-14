package it.unicam.cs.mpgc.rpg130903.model.configurazione;

/**
 * Configurazione raggruppa le costanti del gioco evitando di spargerle per le varie classi.
 * Per bilanciare il gioco si modificano solo questi valori.
 * I costi sono positivi: indicano quanto si toglie.
 */
public final class Configurazione {

    public static final int MAX_STAT = 100;
    public static final int ORO_INIZIALE = 50;

    public static final int AZIONI_PER_TURNO = 5;

    public static final int ORO_RUBATO_BANDITI = 20;
    public static final int ORO_DONATO_ANONIMO = 30;
    public static final int SALUTE_PERSA_EPIDEMIA = 20;
    public static final int PROBABILITA_IMPREVISTO = 20; //percentuale

    public static final int COSTO_ENERGIA_ADDESTRAMENTO = 25;
    public static final int COSTO_ORO_ADDESTRAMENTO = 10;
    public static final int COSTO_SALUTE_MISSIONE = 20;
    public static final int GUADAGNO_ORO_MISSIONE = 25;
    public static final int COSTO_ORO_INFERMERIA = 15;
    public static final int COSTO_ORO_GIORNO_LIBERO = 10;

    private Configurazione() {

    }
}
