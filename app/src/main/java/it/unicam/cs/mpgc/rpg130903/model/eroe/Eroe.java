package it.unicam.cs.mpgc.rpg130903.model.eroe;

import it.unicam.cs.mpgc.rpg130903.model.missione.Assegnabile;

public class Eroe implements Assegnabile {
    private final String nome;
    private StatoEroe statoCorrente;
    private int livello;
    private int stipendio;
    private int missioniCompletate;
    private ProgressioneEroeStrategy strategiaProgressione;

    private static final int COSTANTE_LIVELLO = 1;
    private static final int COSTANTE_STIPENDIO = 10;


    public Eroe(String nome){
        this(nome, 1, new ProgressioneStandard());
    }

    public Eroe(String nome, int livelloIniziale, ProgressioneEroeStrategy strategia){
        this.nome = nome;
        this.livello = livelloIniziale;
        this.statoCorrente = new StatoPronto();
        this.missioniCompletate = 0;
        this.strategiaProgressione = strategia;
        this.stipendio = this.strategiaProgressione.calcolaNuovoStipendio(this.livello);
    }

    @Override
    public int getLivelloOperativo() {
        return livello;
    }

    @Override
    public String getNomeSchieramento() {
        return nome;
    }

    public int getStipendio() {
        return stipendio;
    }

    public boolean pronto() {
        return statoCorrente.pronto(this);
    }

    public void riposo(){
        this.statoCorrente = this.statoCorrente.riposo(this);
    }

    public void inMissione(){
        this.statoCorrente = this.statoCorrente.inMissione((this));
    }

    public void aggiungiMissioneCompletata() {
        this.missioniCompletate++;
        controllaLevelUp();
    }

    public void controllaLevelUp(){
        if (this.strategiaProgressione.verificaLevelUp(this.missioniCompletate, this.livello)){
            this.livello++;
            this.missioniCompletate = 0;
            this.stipendio = this.strategiaProgressione.calcolaNuovoStipendio(this.livello);
        }
    }

    public void concludiMissione (boolean esitoPositivo){
        this.statoCorrente = this.statoCorrente.ritornaDallaMissione(this, esitoPositivo);
    }
}
