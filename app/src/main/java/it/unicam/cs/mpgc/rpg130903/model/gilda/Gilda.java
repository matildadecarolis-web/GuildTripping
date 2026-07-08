package it.unicam.cs.mpgc.rpg130903.model.gilda;

import it.unicam.cs.mpgc.rpg130903.model.missione.EsitoMissione;

import java.util.List;

public class Gilda implements GestoreTurno{
    private int prestigio;
    private int reputazione;
    private int nextLevelContatore;

    private static final int MOLTIPLICATORE_LEVEL_UP = 50;

    private final AmministrazioneEroi gestoreEroi;
    private final AmministrazioneFinanze gestoreFinanze;
    private final AmministrazioneMissioni gestoreMissioni;


    public Gilda(AmministrazioneEroi gestoreEroi, AmministrazioneFinanze gestoreFinanze, int nextLevelContatore, int reputazione, int prestigio, AmministrazioneMissioni gestoreMissioni) {
        this.gestoreEroi = gestoreEroi;
        this.gestoreFinanze = gestoreFinanze;
        this.nextLevelContatore = nextLevelContatore;
        this.reputazione = reputazione;
        this.prestigio = prestigio;
        this.gestoreMissioni = gestoreMissioni;
    }

    public AmministrazioneEroi getGestoreEroi() {
        return gestoreEroi;
    }

    public AmministrazioneFinanze getGestoreFinanze() {
        return gestoreFinanze;
    }

    public AmministrazioneMissioni getGestoreMissioni() {
        return gestoreMissioni;
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

    @Override
    public void iniziaGiornata() {
        ((GestoreTurno) this.gestoreMissioni).iniziaGiornata();

        this.gestoreMissioni.generaNuoveMissioni(
                this.gestoreEroi.getNumeroEroi(),
                this.gestoreEroi.getLivelloMinimo(),
                this.gestoreEroi.getLivelloMassimo()
        );
    }

    public void terminaTurno() {
        List<EsitoMissione> esitiDiOggi = this.gestoreMissioni.risolviMissioniGiornaliere();

        for (EsitoMissione esito : esitiDiOggi) {
            this.gestoreFinanze.aggiungiFondi(esito.getVariazioneFinanze());
            this.aggiungiReputazione(esito.getVariazioneReputazione());
        }

        int stipendiTotali = this.gestoreEroi.calcolaStipendiTotali();
        boolean pagamentoRiuscito = this.gestoreFinanze.deduciFondi(stipendiTotali);

        if (!pagamentoRiuscito || this.gestoreFinanze.bancarotta()) {
            System.out.println("ATTENZIONE: La Gilda è in bancarotta!");
        }

        this.gestoreEroi.riposaEroi();
    }

}
