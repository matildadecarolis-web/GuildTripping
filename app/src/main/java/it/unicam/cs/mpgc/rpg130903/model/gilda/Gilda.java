package it.unicam.cs.mpgc.rpg130903.model.gilda;

import it.unicam.cs.mpgc.rpg130903.model.missione.EsitoMissione;

import java.util.ArrayList;
import java.util.List;

public class Gilda{
    private int prestigio;
    private int reputazione;
    private int nextLevelContatore;

    private static final int MOLTIPLICATORE_LEVEL_UP = 50;

    private final AmministrazioneEroi gestoreEroi;
    private final AmministrazioneFinanze gestoreFinanze;
    private final AmministrazioneMissioni gestoreMissioni;

    private final List<GestoreTurno> iscrittiAlTurno;


    public Gilda(AmministrazioneEroi gestoreEroi, AmministrazioneFinanze gestoreFinanze, int nextLevelContatore, int reputazione, int prestigio, AmministrazioneMissioni gestoreMissioni) {
        this.gestoreEroi = gestoreEroi;
        this.gestoreFinanze = gestoreFinanze;
        this.nextLevelContatore = nextLevelContatore;
        this.reputazione = reputazione;
        this.prestigio = prestigio;
        this.gestoreMissioni = gestoreMissioni;

        this.iscrittiAlTurno = new ArrayList<>();

        if(gestoreMissioni instanceof GestoreTurno) {
            this.iscrittiAlTurno.add((GestoreTurno) gestoreMissioni);
        }
        if(gestoreEroi instanceof GestoreTurno) {
            this.iscrittiAlTurno.add((GestoreTurno) gestoreEroi);
        }
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

    public void iniziaGiornata() {
        for (GestoreTurno gestore : iscrittiAlTurno) {
            gestore.iniziaGiornata();
        }

        this.gestoreMissioni.generaNuoveMissioni(
                this.gestoreEroi.getNumeroEroi(),
                this.gestoreEroi.getLivelloMinimo(),
                this.gestoreEroi.getLivelloMassimo()
        );
    }

    public ReportTurno terminaTurno() {
        int entrateDelGiorno = 0;
        int reputazioneDelGiorno = 0;

        List<EsitoMissione> esitiDiOggi = this.gestoreMissioni.risolviMissioniGiornaliere();

        for (EsitoMissione esito : esitiDiOggi) {
            entrateDelGiorno += esito.getVariazioneFinanze();
            reputazioneDelGiorno += esito.getVariazioneReputazione();

            this.gestoreFinanze.aggiungiFondi(esito.getVariazioneFinanze());
            this.aggiungiReputazione(esito.getVariazioneReputazione());
        }

        int stipendiTotali = this.gestoreEroi.calcolaStipendiTotali();
        this.gestoreFinanze.deduciFondi(stipendiTotali);

        this.gestoreEroi.riposaEroi();

        boolean bancarotta = this.gestoreFinanze.bancarotta();

        return new ReportTurno(entrateDelGiorno, stipendiTotali, reputazioneDelGiorno, esitiDiOggi, bancarotta);
    }

}
