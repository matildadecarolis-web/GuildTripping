package it.unicam.cs.mpgc.rpg130903.model.gilda;

public interface AmministrazioneFinanze {

    int getFondi();

    void aggiungiFondi(int importo);

    boolean deduciFondi(int importo);

    boolean bancarotta();
}
