package it.unicam.cs.mpgc.rpg130903.model.eroe;

import it.unicam.cs.mpgc.rpg130903.model.classe.Classe;

/**
 * Un eroe della gilda.
 * Espone lo stato dell'eroe e solo modifiche con un significato di gioco
 * (allenarsi, spendere energia, perdere salute, recuperare).
 * I vincoli sulle statistiche restano nell'implementazione.
 * Un eroe non conosce attività.
 */
public interface Eroe {
    String getNome();

    Classe getClasse();

    int getEnergia();

    int getSalute();

    int getForza();

    int getMaxStat();

    void allenamento();

    void spendiEnergia(int valore);

    void perdiSalute(int valore);

    void ripristinaEnergia();

    void ripristinaSalute();

}
