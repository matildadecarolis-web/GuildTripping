package it.unicam.cs.mpgc.rpg130903.model.eroe;

import it.unicam.cs.mpgc.rpg130903.model.missione.Assegnabile;

public class Eroe implements Assegnabile {
    private final String nome;
    private StatoEroe statoCorrente;
    private int livello;
    private int stipendio;
    private int missioniCompletate;


    public Eroe(String nome){
        this(nome, 1);
    }

    public Eroe(String nome, int livelloIniziale){
        this.nome = nome;
        this.livello = livelloIniziale;
        this.statoCorrente = new StatoPronto();
        this.missioniCompletate = 0;
        this.calcolaStipendio();
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

    public void inmissione(){
        this.statoCorrente = this.statoCorrente.inMissione((this));
    }

    public void aggiungiMissioneCompletata() {
        this.missioniCompletate++;
        controllaLevelUp();
    }

    public void controllaLevelUp(){
        int costantelivello = 1;
        if (this.missioniCompletate >= (this.livello + costantelivello)){
            this.livello++;
            this.missioniCompletate = 0;
            calcolaStipendio();
            System.out.println("Congratulazioni " + this.nome + " è ora al livello" + this.livello + " e il suo stipendio è aumentato. Nuovo Stipendio" + this.stipendio);
        }
    }

    public void calcolaStipendio(){
        int costantestipendio = 10;
        this.stipendio = this.livello * costantestipendio;
    }

    public void concludiMissione (boolean esitoPositivo){
        this.statoCorrente = this.statoCorrente.ritornaDallaMissione(this, esitoPositivo);
    }
}
