package it.unicam.cs.mpgc.rpg130903.model.imprevisto;

import it.unicam.cs.mpgc.rpg130903.model.gilda.Gilda;

/**
 * Un evento casuale che può capitare all'inizio di un turno e modifica la gilda o i suoi eroi.
 * Per aggiungere un imprevisto basta una nuova implementazione.
 */
public interface Imprevisto {

    String getNome();

    String getDescrizione();

    void applica(Gilda gilda);
}
