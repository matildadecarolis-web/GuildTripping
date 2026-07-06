package it.unicam.cs.mpgc.rpg130903.model.gilda;

import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;

import java.util.List;

public interface AmministrazioneEroi {

    void espandiListaEroi(int nuovoPrestigio);

    int calcolaStipendiTotali();

    List<Eroe> tutti();

    List<Eroe> pronti();

    double calcolaLivelloMedio();

    void riposaEroi();

}
