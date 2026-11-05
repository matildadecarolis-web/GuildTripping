package it.unicam.cs.mpgc.rpg130903.model.eroe;

public class StatSalute implements Stat{

    public String getNome(){
        return "Salute";
    }

    @Override
    public int valore(Eroe eroe) {
        return eroe.getSalute();
    }

    @Override
    public void riempi(Eroe eroe) {
        eroe.riempiSalute();
    }
}
