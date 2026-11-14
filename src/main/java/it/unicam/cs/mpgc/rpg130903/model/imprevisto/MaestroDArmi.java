package it.unicam.cs.mpgc.rpg130903.model.imprevisto;

import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;
import it.unicam.cs.mpgc.rpg130903.model.gilda.Gilda;

import java.util.List;

public final class MaestroDArmi implements Imprevisto {
    @Override
    public String getNome() {
        return "Maestro d'armi";
    }

    @Override
    public String getDescrizione() {
        return "Un maestro d'armi fa visita alla gilda: tutti gli eroi si allenano gratuitamente.";
    }

    @Override
    public void applica(Gilda gilda) {
        List<Eroe> eroi = gilda.getEroi();
        for (int i = 0; i < eroi.size(); i++) {
            Eroe eroe = eroi.get(i);
            eroe.allenamento();
        }
    }
}
