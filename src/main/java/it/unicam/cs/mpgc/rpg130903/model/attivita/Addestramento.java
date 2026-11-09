package it.unicam.cs.mpgc.rpg130903.model.attivita;

import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;
import it.unicam.cs.mpgc.rpg130903.model.gilda.Gilda;

public final class Addestramento implements Attivita {

    private final int costoEnergia;
    private final int costoOro;

    public Addestramento(int costoEnergia, int costoOro) {
        if (costoOro < 0 || costoEnergia < 0) {
            throw new IllegalArgumentException("I costi non possono essere negativi");
        }
        this.costoEnergia = costoEnergia;
        this.costoOro = costoOro;
    }

    @Override
    public String getNome() {
        return "Addestramento";
    }

    @Override
    public String getDescrizione() {
        return "L'eroe si allena: aumenta la forza, ma consuma energia e oro.";
    }

    @Override
    public void valida(Eroe eroe, Gilda gilda) throws AttivitaNonPermessaException {
        if (eroe.getEnergia() < this.costoEnergia) {
            throw new AttivitaNonPermessaException(eroe.getNome() + " non ha abbastanza energia per allenarsi");
        }

        if (!gilda.puoPermettersi(costoOro)) {
            throw new AttivitaNonPermessaException("La gilda non ha abbastanza oro");
        }
    }

    @Override
    public void applica(Eroe eroe, Gilda gilda) {
        gilda.spendi(costoOro);
        eroe.spendiEnergia(costoEnergia);
        eroe.allenamento();
    }
}
