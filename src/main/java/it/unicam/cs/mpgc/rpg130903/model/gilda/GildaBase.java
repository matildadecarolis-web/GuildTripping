package it.unicam.cs.mpgc.rpg130903.model.gilda;

import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;

import java.util.List;

/**
 * Implementazione standard di {@link Gilda}.
 */
public final class GildaBase implements Gilda {

    private final String nome;
    private final Specializzazione specializzazione;
    private final List<Eroe> eroi;
    private int oro;

    /**
     * @param eroi la squadra, non vuota; viene copiata, quindi modifiche successive
     * alla lista originale non hanno effetto sulla gilda.
     */
    public GildaBase(int oroIniziale, List<Eroe> eroi, Specializzazione specializzazione, String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome non può essere vuoto");
        }
        if (specializzazione == null) {
            throw new IllegalArgumentException("La specializzazione non può essere nulla");
        }
        if (eroi == null || eroi.isEmpty()) {
            throw new IllegalArgumentException("La lista eroi non può essere vuota");
        }
        if (oroIniziale < 0){
            throw new IllegalArgumentException("L'oro non può essere negativo");
        }

        this.nome = nome;
        this.specializzazione = specializzazione;
        this.eroi = List.copyOf(eroi);
        this.oro = oroIniziale;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public Specializzazione getSpecializzazione() {
        return specializzazione;
    }

    @Override
    public List<Eroe> getEroi() {
        return eroi;
    }

    @Override
    public int getOro() {
        return oro;
    }

    @Override
    public boolean puoPermettersi(int costo) {
        controllaNonNegativo(costo);
        return oro >= costo;
    }

    @Override
    public void spendi(int costo) {
        if (!puoPermettersi(costo)){
            throw new IllegalStateException();
        }
        else {
            oro -= costo;
        }
    }

    @Override
    public int pagaFinoAZero(int costo) {
        controllaNonNegativo(costo);
        int pagato = Math.min(oro, costo);
        oro -= pagato;
        return pagato;
    }

    @Override
    public void aggiungiOro(int quantita) {
        controllaNonNegativo(quantita);
        oro += quantita;
    }

    private static void controllaNonNegativo(int quantita) {
        if (quantita < 0) {
            throw new IllegalArgumentException("La quantità non può essere negativa");
        }
    }
}
