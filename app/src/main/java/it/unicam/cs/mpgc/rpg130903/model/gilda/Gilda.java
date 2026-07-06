package it.unicam.cs.mpgc.rpg130903.model.gilda;

public class Gilda {
    private int prestigio;
    private int reputazione;
    private int nextLevelContatore;

    private static final int MOLTIPLICATORE_LEVEL_UP = 50;

    private final AmministrazioneEroi gestoreEroi;
    private final AmministrazioneFinanze gestoreFinanze;

    public Gilda(AmministrazioneEroi gestoreEroi, AmministrazioneFinanze gestoreFinanze, int nextLevelContatore, int reputazione, int prestigio) {
        this.gestoreEroi = gestoreEroi;
        this.gestoreFinanze = gestoreFinanze;
        this.nextLevelContatore = nextLevelContatore;
        this.reputazione = reputazione;
        this.prestigio = prestigio;
    }

    public AmministrazioneEroi getGestoreEroi() {
        return gestoreEroi;
    }

    public AmministrazioneFinanze getGestoreFinanze() {
        return gestoreFinanze;
    }

    public int getPrestigio() {
        return prestigio;
    }

    public int getReputazione() {
        return reputazione;
    }

    public void aggiungiReputazione(int punti){
        this.reputazione += punti;
        this.nextLevelContatore += punti;
        if (punti > 0) {
            controllaLevelUpGilda();
        } else {
            controllaPenalitaReputazione();
        }
    }

    private void controllaPenalitaReputazione(){
        if (this.nextLevelContatore < 0){
            this.nextLevelContatore = 0;
        }
    }

    private void controllaLevelUpGilda(){
        if (this.nextLevelContatore >= (this.prestigio + MOLTIPLICATORE_LEVEL_UP)){
            levelUp();
        }
    }

    private void levelUp(){
        this.prestigio++;
        this.nextLevelContatore = 0;
        this.gestoreEroi.espandiListaEroi(this.prestigio);
    }

    public boolean terminaTurno(){
        return false;
    }

}
