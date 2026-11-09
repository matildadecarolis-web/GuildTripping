package it.unicam.cs.mpgc.rpg130903.model.eroe;

public class StatSalute implements Stat {

    @Override
    public String getNome() {
        return "Salute";
    }

    @Override
    public int valore(Eroe eroe) {
        return eroe.getSalute();
    }

    @Override
    public void ripristina(Eroe eroe) {
        eroe.ripristinaSalute();
    }
}
