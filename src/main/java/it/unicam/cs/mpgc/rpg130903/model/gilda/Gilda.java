package it.unicam.cs.mpgc.rpg130903.model.gilda;

import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;

import java.util.List;

/**
 * La gilda del giocatore: nome, specializzazione, eroi e oro.
 * L'oro non scende mai sotto zero, non vengono accettati valori negativi e la squadra non può essere modificata dall'esterno.
 */
public interface Gilda {

    String getNome();

    Specializzazione getSpecializzazione();

    /**
     * @return gli eroi della gilda, in una lista non modificabile
     */
    List<Eroe> getEroi();

    int getOro();

    boolean puoPermettersi(int costo);

    /**
     * Toglie l'oro per un'attività scelta dal giocatore.
     */
    void spendi(int costo);

    /**
     * Toglie l'oro per un recupero forzato: se non basta, l'oro arriva a zero
     * senza errori.
     */
    int pagaFinoAZero(int costo);

    void aggiungiOro(int quantita);
}