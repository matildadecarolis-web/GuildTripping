package it.unicam.cs.mpgc.rpg130903.model.gilda;

import it.unicam.cs.mpgc.rpg130903.model.missione.Assegnabile;
import it.unicam.cs.mpgc.rpg130903.model.missione.EsitoMissione;
import it.unicam.cs.mpgc.rpg130903.model.missione.Quest;

import java.util.List;

public interface AmministrazioneMissioni {

        List<Quest> disponibili();

        List<Quest> inCorso();

        List<EsitoMissione> risolviMissioniGiornaliere();

        void assegnaMissione(Quest missione, Assegnabile eroe);

        void generaNuoveMissioni (int numeroEroi, int livelloMin, int livelloMax);

        List<EsitoMissione> getEsitiGiornalieri();
}
