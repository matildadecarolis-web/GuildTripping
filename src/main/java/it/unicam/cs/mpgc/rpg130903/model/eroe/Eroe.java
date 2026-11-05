package it.unicam.cs.mpgc.rpg130903.model.eroe;

import it.unicam.cs.mpgc.rpg130903.model.classe.Classe;

/**
 * Un eroe della gilda.
 * <p>
 * Espone lo stato dell'eroe e solo modifiche con un significato di gioco
 * (allenarsi, spendere energia, perdere salute, recuperare). I vincoli sulle
 * statistiche restano nell'implementazione, così nessuna classe esterna può
 * portarle fuori dall'intervallo valido.
 * </p>
 * <p>
 * Un eroe non conosce attività, turni o motore di gioco: decidere quando un eroe
 * si allena o recupera è responsabilità di altre classi.
 * </p>

 */
public interface Eroe {
    String getNome();
    Classe getClasse();
    int getEnergia();
    int getSalute();
    int getForza();

    int getMaxStat();

    int allenamento();

    boolean isEsausto();
    boolean isMalato();

    void spendiEnergia(int valore);
    void perdiSalute(int valore);

    void riempiEnergia();
    void riempiSalute();

}
