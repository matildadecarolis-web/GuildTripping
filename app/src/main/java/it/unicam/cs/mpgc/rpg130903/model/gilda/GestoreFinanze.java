package it.unicam.cs.mpgc.rpg130903.model.gilda;

public class GestoreFinanze implements AmministrazioneFinanze, GestoreTurno{

    private int saldo;

    public GestoreFinanze(int saldo) {
        this.saldo = saldo;
    }

    @Override
    public int getFondi() {
        return saldo;
    }

    @Override
    public void aggiungiFondi(int importo) {
        if (importo > 0){
            saldo += importo;
        }
    }

    @Override
    public boolean deduciFondi(int importo) {
        if (importo > 0 && saldo >= importo) {
            saldo -= importo;
            return true;
        }
        return false;
    }

    @Override
    public boolean bancarotta() {
        return saldo <= 0;
    }

    @Override
    public void iniziaGiornata() {

    }

    @Override
    public void terminaTurno() {

    }
}
