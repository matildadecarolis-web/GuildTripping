package it.unicam.cs.mpgc.rpg130903.model.gilda;

import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;

import java.util.ArrayList;
import java.util.List;

public class GestoreEroi implements AmministrazioneEroi {

    private List<Eroe> listaEroi;

    private static int contatoreEroi = 0;
    private static final int EROI_BASE = 3;
    private static final double FATTORE_ESPANSIONE = 1.5;

    public GestoreEroi(int numeroEroiIniziali) {
        this.listaEroi = new ArrayList<>();
        for (int i = 0; i < numeroEroiIniziali; i++){
            this.listaEroi.add(new Eroe("Recluta numero " + contatoreEroi));
            contatoreEroi ++;
        }
    }

    private int calcolaCapienzaMassima (int prestigio) {
        return (int) (EROI_BASE + (prestigio * FATTORE_ESPANSIONE));
    }

    @Override
    public void espandiListaEroi(int nuovoPrestigio) {
        int target = calcolaCapienzaMassima(nuovoPrestigio);
        int differenza = target - listaEroi.size();
        if (differenza > 0) {
            for (int i = 0; i < differenza; i++) {
                this.listaEroi.add(new Eroe("Nuovo eroe " + contatoreEroi, nuovoPrestigio));
                contatoreEroi ++;
            }
        }
       return;
    }

    @Override
    public int calcolaStipendiTotali() {
        int totaleStipendi = 0;

        for (Eroe eroe : listaEroi) {
            totaleStipendi += eroe.getStipendio();
        }
        return totaleStipendi;
    }

    @Override
    public List<Eroe> tutti() {
        return new ArrayList<>(this.listaEroi);
    }

    @Override
    public List<Eroe> pronti() {
        List<Eroe> disponibili = new ArrayList<>();
        for (Eroe eroe : listaEroi){
            if (eroe.pronto()){
                disponibili.add(eroe);
            }
        }
        return disponibili;
    }

    @Override
    public double calcolaLivelloMedio() {
        if (listaEroi.isEmpty()){
            return 0.0;
        }
        double sommaLivello = 0;
        for (Eroe eroe : listaEroi) {
            sommaLivello += eroe.getLivelloOperativo();
        }
        return sommaLivello/ listaEroi.size();
    }

    public int getNumeroEroi() {
        return this.listaEroi.size();
    }

    public int getLivelloMinimo() {
        if (listaEroi.isEmpty()) return 1;
        int min = listaEroi.getFirst().getLivelloOperativo();
        for (Eroe e : listaEroi) {
            if (e.getLivelloOperativo() < min) min = e.getLivelloOperativo();
        }
        return min;
    }

    public int getLivelloMassimo() {
        if (listaEroi.isEmpty()) return 1;
        int max = listaEroi.getFirst().getLivelloOperativo();
        for (Eroe e : listaEroi) {
            if (e.getLivelloOperativo() > max) max = e.getLivelloOperativo();
        }
        return max;
    }

    @Override
    public void riposaEroi() {
    for (Eroe eroe: this.listaEroi){
        eroe.riposo();
        }
    }
}
