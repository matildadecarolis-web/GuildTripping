package it.unicam.cs.mpgc.rpg130903.model.gilda;

import it.unicam.cs.mpgc.rpg130903.model.missione.Assegnabile;
import it.unicam.cs.mpgc.rpg130903.model.missione.EsitoMissione;
import it.unicam.cs.mpgc.rpg130903.model.missione.Quest;
import it.unicam.cs.mpgc.rpg130903.model.missione.GeneratoreMissioni;

import java.util.ArrayList;
import java.util.List;

public class GestoreMissioni implements AmministrazioneMissioni, GestoreTurno{

    private List<Quest> listaQuest;
    private final GeneratoreMissioni fabbricaMissioni;
    private List<EsitoMissione> esitiGiornalieri;

    public GestoreMissioni(GeneratoreMissioni fabbricaMissioni) {
        this.listaQuest = new ArrayList<>();
        this.fabbricaMissioni = fabbricaMissioni;
        this.esitiGiornalieri = new ArrayList<>();
    }

    @Override
    public void generaNuoveMissioni(int numeroEroi, int livelloMin, int livelloMax) {
        List<Quest> nuove = this.fabbricaMissioni.creaMissioniDelGiorno(numeroEroi, livelloMin, livelloMax);
        this.listaQuest.addAll(nuove);
    }

    @Override
    public List<Quest> disponibili() {
        List<Quest> disponibili = new ArrayList<>();
        for (Quest quest : listaQuest){
            if (quest.getStato() == Quest.StatoMissione.DISPONIBILE){
                disponibili.add(quest);
            }
        }
        return disponibili;
    }

    @Override
    public List<Quest> inCorso() {
        List<Quest> inCorso = new ArrayList<>();
        for (Quest quest : listaQuest){
            if (quest.getStato() == Quest.StatoMissione.IN_CORSO){
                inCorso.add(quest);
            }
        }
        return inCorso;
    }

    @Override
    public List<EsitoMissione> risolviMissioniGiornaliere() {
        List<EsitoMissione> listaEsiti = new ArrayList<>();

        List <Quest> missioniAttive = this.inCorso();

        for (Quest quest : missioniAttive){
            EsitoMissione esito = quest.risolvi();
            listaEsiti.add(esito);
        }

        return listaEsiti;
    }

    @Override
    public void assegnaMissione(Quest missione, Assegnabile eroe) {
    missione.assegna(eroe);
    eroe.inMissione();
    }


    @Override
    public void iniziaGiornata() {
        List<Quest> missioniDaMantenere = new ArrayList<>();
        for (Quest quest : listaQuest){
            if (quest.getStato() != Quest.StatoMissione.DISPONIBILE) {
                missioniDaMantenere.add(quest);
            }
        }
        this.listaQuest = missioniDaMantenere;
        this.esitiGiornalieri.clear();
    }

    @Override
    public void terminaTurno() {
        this.esitiGiornalieri = this.risolviMissioniGiornaliere();
        }
}
