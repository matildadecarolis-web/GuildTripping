package it.unicam.cs.mpgc.rpg130903.model.eroe;

import it.unicam.cs.mpgc.rpg130903.model.classe.Classe;

public interface Eroe {
    String getNome();
    Classe getClasse();
    int getEnergia();
    int getSalute();
    int getForza();

    int getMaxStat();

    int allenamento();

    boolean stanco();
    boolean malato();

    void spendiEnergia(int valore);
    void spediSalute(int valore);

    void riempiEnergia();
    void riempiSalute();

}
