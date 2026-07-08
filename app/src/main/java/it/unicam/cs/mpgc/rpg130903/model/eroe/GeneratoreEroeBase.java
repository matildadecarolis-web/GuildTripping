package it.unicam.cs.mpgc.rpg130903.model.eroe;

import java.util.ArrayList;
import java.util.List;

public class GeneratoreEroeBase implements GeneratoreEroi {

    private int contatoreEroi;
    private final ProgressioneEroeStrategy strategiaProgressione;

    public GeneratoreEroeBase(ProgressioneEroeStrategy strategia) {
        this.strategiaProgressione = strategia;
        this.contatoreEroi = 0;
    }

    @Override
    public List<Eroe> generaEroi(int quantita, int livelloIniziale) {
        List<Eroe> nuoviEroi = new ArrayList<>();

        for (int i = 0; i < quantita; i++) {
            // Logica di creazione incapsulata qui!
            String nomeEroe = "Eroe numero " + contatoreEroi;
            nuoviEroi.add(new Eroe(nomeEroe, livelloIniziale, this.strategiaProgressione));
            contatoreEroi++;
        }

        return nuoviEroi;
    }
}
