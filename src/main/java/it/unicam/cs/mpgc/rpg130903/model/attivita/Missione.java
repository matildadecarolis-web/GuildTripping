package it.unicam.cs.mpgc.rpg130903.model.attivita;

import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;
import it.unicam.cs.mpgc.rpg130903.model.gilda.Gilda;

public final class Missione implements Attivita {

    private final int costoSalute;
    private final int guadagnoOro;

    public Missione(int costoSalute, int guadagnoOro) {
        if (costoSalute < 0 || guadagnoOro < 0) {
            throw new IllegalArgumentException("I valori non possono essere negativi");
        }
        this.costoSalute = costoSalute;
        this.guadagnoOro = guadagnoOro;
    }

    @Override
    public String getNome() {
        return "Missione";
    }

    @Override
    public String getDescrizione() {
        return "+ " + guadagnoOro + " oro\n- " + costoSalute + " salute";
    }

    @Override
    public void valida(Eroe eroe, Gilda gilda) throws AttivitaNonPermessaException {
        if (eroe.getSalute() < costoSalute) {
            throw new AttivitaNonPermessaException(eroe.getNome() + " non ha salute sufficiente per andare in missione");
        }
    }

    @Override
    public void applica(Eroe eroe, Gilda gilda) {
        eroe.perdiSalute(costoSalute);
        gilda.aggiungiOro(guadagnoOro);
    }
}
