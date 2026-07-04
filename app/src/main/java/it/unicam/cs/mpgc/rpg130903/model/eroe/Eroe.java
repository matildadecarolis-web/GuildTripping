package it.unicam.cs.mpgc.rpg130903.model.eroe;

public class Eroe {
    private String nome;
    private StatoEroe statocorrente;

    public Eroe(String nome){
        this.nome = nome;
        this.statocorrente = new StatoPronto();
    }

    public String getNome() {
        return nome;
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
        statocorrente.inmissione((this));
    }
}
