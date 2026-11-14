package it.unicam.cs.mpgc.rpg130903.model.imprevisto;

import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;
import it.unicam.cs.mpgc.rpg130903.model.gilda.Gilda;

import java.util.List;

public final class Epidemia implements Imprevisto {

    private final int salutePersa;

    public Epidemia(int salutePersa) {
        if (salutePersa < 0) {
            throw new IllegalArgumentException("La salute persa non può essere negativa");
        }
        this.salutePersa = salutePersa;
    }

    @Override
    public String getNome() {
        return "Epidemia";
    }

    @Override
    public String getDescrizione() {
        return "Un'epidemia ha colpito la gilda: tutti gli eroi perdono " + salutePersa + " punti salute";
    }

    @Override
    public void applica(Gilda gilda) {
        List<Eroe> eroi = gilda.getEroi();
        for (int i = 0; i < eroi.size(); i++) {
            Eroe eroe = eroi.get(i);
            eroe.perdiSalute(salutePersa);
        }
    }
}
