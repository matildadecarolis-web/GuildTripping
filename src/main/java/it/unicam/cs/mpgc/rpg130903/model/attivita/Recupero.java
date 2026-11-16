package it.unicam.cs.mpgc.rpg130903.model.attivita;

import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;
import it.unicam.cs.mpgc.rpg130903.model.eroe.Stat;
import it.unicam.cs.mpgc.rpg130903.model.gilda.Gilda;

/**
 * Attività che riporta al massimo una statistica dell'eroe, pagando oro.
 * Infermeria (salute) e Giorno Libero (energia) sono due istanze di questa classe.
 */
public final class Recupero implements Attivita {

    private final int costoOro;
    private final Stat stat;
    private final String nome;

    public Recupero(Stat stat, int costoOro, String nome) {
        if (costoOro < 0) {
            throw new IllegalArgumentException("Il costo non può essere negativo");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome non può rimanere vuoto");
        }
        if (stat == null) {
            throw new IllegalArgumentException("La statistica non può essere nulla");
        }
        this.stat = stat;
        this.costoOro = costoOro;
        this.nome = nome;
    }

    public Stat getStat() {
        return stat;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getDescrizione() {
        return "+ " + stat.getNome().toLowerCase() + " al massimo\n- " + costoOro + " oro";
    }

    @Override
    public void valida(Eroe eroe, Gilda gilda) throws AttivitaNonPermessaException {
        if (!gilda.puoPermettersi(costoOro)) {
            throw new AttivitaNonPermessaException("La gilda non ha abbastanza oro per " + nome);
        }
    }

    @Override
    public void applica(Eroe eroe, Gilda gilda) {
        gilda.spendi(costoOro);
        stat.ripristina(eroe);
    }

    /**
     * Applica il recupero forzato a un eroe esausto o malato: paga il costo anche se l'oro
     * non basta (al massimo fino a zero) e non richiede la validazione.
     *
     * @return l'oro effettivamente pagato
     */
    public int applicaForzato(Eroe eroe, Gilda gilda) {
        int pagato = gilda.pagaFinoAZero(costoOro);
        stat.ripristina(eroe);
        return pagato;
    }
}
