package it.unicam.cs.mpgc.rpg130903.model.eroe;

public class ProgressioneStandard implements ProgressioneEroeStrategy{

    private static final int COSTANTE_LIVELLO = 1;
    private static final int COSTANTE_STIPENDIO = 10;

    @Override
    public int calcolaNuovoStipendio(int livello) {
        return livello * COSTANTE_STIPENDIO;
    }

    @Override
    public boolean verificaLevelUp(int missioniCompletate, int livelloAttuale) {
        return missioniCompletate >= (livelloAttuale + COSTANTE_LIVELLO);
    }
}
