package it.unicam.cs.mpgc.rpg130903.model.eroe;

/**
 * Statistica ripristinabile di un eroe (energia o salute).
 * Permette alle attività di recupero e ai recuperi forzati di lavorare su "una statistica"
 * senza sapere quale.
 * Aggiungere una nuova statistica significa scrivere una nuova implementazione
 * e aggiungere la statistica in Eroe senza modificare il codice esistente.
 */
public interface Stat {
    String getNome();

    /**
     * @param eroe l'eroe da leggere
     * @return il valore attuale della statistica
     */
    int valore(Eroe eroe);

    void ripristina(Eroe eroe);

    /**
     * Necessario per la missione critica
     */
    default int mancante(Eroe eroe) {
        return eroe.getMaxStat() - valore(eroe);
    }

    default boolean isEsaurita(Eroe eroe) {
        return valore(eroe) == 0;
    }
}
