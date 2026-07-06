package it.unicam.cs.mpgc.rpg130903.model.eroe;

public interface ProgressioneEroeStrategy {

    int calcolaNuovoStipendio(int livello);
    boolean verificaLevelUp(int missioniCompletate, int livelloAttuale);
}
