package it.unicam.cs.mpgc.rpg130903.model.missione;

/**
 * Dati della missione critica che la gilda affronta al termine del turno di innesco.
 * Il turno è nella missione, così nel futuro si può cambiare la scadenza.
 */
public final class MissioneCritica {

    private final String nome;
    private final String descrizione;
    private final int difficolta;
    private final int turnoInnesco;

    public MissioneCritica(String nome, String descrizione, int difficolta, int turnoInnesco) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome non può essere vuoto");
        }
        if (descrizione == null) {
            throw new IllegalArgumentException("La descrizione non può essere vuota");
        }
        if (difficolta <= 0) {
            throw new IllegalArgumentException("La difficoltà deve essere maggiore di 0");
        }
        if (turnoInnesco <= 0) {
            throw new IllegalArgumentException("Il turno d'innesco deve essere maggiore di 0");
        }
        this.nome = nome;
        this.descrizione = descrizione;
        this.difficolta = difficolta;
        this.turnoInnesco = turnoInnesco;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Si vince solo se la difficoltà è minore o uguale al punteggio finale.
     */
    public int getDifficolta() {
        return difficolta;
    }

    public int getTurnoInnesco() {
        return turnoInnesco;
    }
}