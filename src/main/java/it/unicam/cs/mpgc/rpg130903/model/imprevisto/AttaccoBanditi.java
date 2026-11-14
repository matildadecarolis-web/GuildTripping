package it.unicam.cs.mpgc.rpg130903.model.imprevisto;

import it.unicam.cs.mpgc.rpg130903.model.gilda.Gilda;

/**
 * Se la gilda ha meno oro della quantità rubata, i banditi prendono tutto quello che c’è.
 */
public final class AttaccoBanditi implements Imprevisto {

    private final int oroRubato;

    public AttaccoBanditi(int oroRubato) {
        if (oroRubato < 0) {
            throw new IllegalArgumentException("L'oro rubato non può essere negativo");
        }
        this.oroRubato = oroRubato;
    }

    @Override
    public String getNome() {
        return "Attacco dei Banditi";
    }

    @Override
    public String getDescrizione() {
        return "Dei banditi hanno attaccato la gilda e rubato fino a " + oroRubato + " monete";
    }

    @Override
    public void applica(Gilda gilda) {
        gilda.pagaFinoAZero(oroRubato);
    }
}
