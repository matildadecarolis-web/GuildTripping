package it.unicam.cs.mpgc.rpg130903.model.eroe;

public class Eroe {
    private final String nome;
    private StatoEroe statocorrente;
    private int livello;
    private int stipendio;
    private int missionicompletate;


    public Eroe(String nome){
        this.nome = nome;
        this.statocorrente = new StatoPronto();
        this.livello = 1;
        this.missionicompletate = 0;
        this.calcolaStipendio();
    }

    public String getNome() {
        return nome;
    }

    public int getLivello() {
        return livello;
    }

    public int getStipendio() {
        return stipendio;
    }

    public void setStato(StatoEroe NuovoStato){
        this.statocorrente = NuovoStato;
    }

    public boolean pronto() {
        return statocorrente.pronto(this);
    }

    public void riposo(){
        statocorrente.riposo(this);
    }

    public void inmissione(){
        statocorrente.inMissione((this));
    }

    public void aggiungiMissioneCompletata() {
        this.missionicompletate++;
        controllaLevelUp();
    }

    public void controllaLevelUp(){
        int costantelivello = 1;
        if (this.missionicompletate >= (this.livello + costantelivello)){
            this.livello++;
            this.missionicompletate = 0;
            calcolaStipendio();
            System.out.println("Congratulazioni " + this.nome + " è ora al livello" + this.livello + " e il suo stipendio è aumentato. Nuovo Stipendio" + this.stipendio);
        }
    }

    public void calcolaStipendio(){
        int costantestipendio = 10;
        this.stipendio = this.livello * costantestipendio;
    }

    public void concludiMissione (boolean esitoPositivo){
        statocorrente.ritornaDallaMissione(this, esitoPositivo);
    }
}
