package it.unicam.cs.mpgc.rpg130903.model.eroe;

public final class StatEnergia implements Stat {


    @Override
    public String getNome() {
        return "Energia";
    }

    @Override
    public int valore(Eroe eroe) {
        return eroe.getEnergia();
    }

    @Override
    public void ripristina(Eroe eroe) {
        eroe.ripristinaEnergia();
    }
}
