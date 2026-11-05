package it.unicam.cs.mpgc.rpg130903.model.classe;

/**
 * La classe di un eroe (Guerriero, Mago, Ladro...).
 * Decide la forza iniziale e come cresce la forza ccon l'addestramento.
 */
public interface Classe {

    String getName();
    String getDescrizione();
    int getForzaIniziale();

    /**
     * Calcola quanta forza guadagna un eroe con un addestramento.
     *
     * @param forzaAttuale forza attuale dell'eroe
     * @return forza guadagnata (sempre positiva)
     */

    int getForzaGuadagnata(int forzaAttuale);
    }
