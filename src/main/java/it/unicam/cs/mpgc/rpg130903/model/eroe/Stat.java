package it.unicam.cs.mpgc.rpg130903.model.eroe;

/**
 * Statistica ripristinabile di un eroe (energia o salute).
 * <p>
 * Permette alle attività di recupero e ai recuperi forzati di lavorare su "una statistica"
 * senza sapere quale. Aggiungere una nuova statistica (per esempio il morale) significa
 * scrivere una nuova implementazione, senza modificare chi la usa.
 * </p>
 */
public interface Stat {
    String getNome();

    /**
     * @param eroe l'eroe da leggere
     * @return il valore attuale della statistica
     */
    int valore(Eroe eroe);

    void riempi(Eroe eroe);

    /**
     * Necessario per la missione critica
     */
    default int mancante(Eroe eroe) {
        return eroe.getMaxStat() - valore(eroe);
    }

    default boolean isDepleted(Eroe eroe) {
        return valore(eroe) == 0;
    }
}
