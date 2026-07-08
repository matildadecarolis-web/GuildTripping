package it.unicam.cs.mpgc.rpg130903.model.gilda;

import it.unicam.cs.mpgc.rpg130903.model.missione.EsitoMissione;

import java.util.ArrayList;
import java.util.List;

public class ReportTurno {
    private final int entrateTotali;
    private final int stipendiPagati;
    private final int variazioneReputazione;
    private final List<EsitoMissione> missioniRisolte;
    private final boolean rischioBancarotta;

    public ReportTurno(int entrateTotali, int stipendiPagati, int variazioneReputazione, List<EsitoMissione> missioniRisolte, boolean rischioBancarotta) {
        this.entrateTotali = entrateTotali;
        this.stipendiPagati = stipendiPagati;
        this.variazioneReputazione = variazioneReputazione;
        this.missioniRisolte = missioniRisolte;
        this.rischioBancarotta = rischioBancarotta;
    }

    public int getEntrateTotali() {
        return entrateTotali;
    }

    public int getStipendiPagati() {
        return stipendiPagati;
    }

    public int getVariazioneReputazione() {
        return variazioneReputazione;
    }

    public List<EsitoMissione> getMissioniRisolte() {
        return new ArrayList<>(missioniRisolte);
    }

    public boolean isRischioBancarotta() {
        return rischioBancarotta;
    }

    public int getBilancioNetto() {
        return entrateTotali - stipendiPagati;
    }
}
