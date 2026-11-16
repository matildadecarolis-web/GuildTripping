package it.unicam.cs.mpgc.rpg130903.view;

import it.unicam.cs.mpgc.rpg130903.controller.ControllerGioco;
import it.unicam.cs.mpgc.rpg130903.model.eventofinale.EventoFinale;
import it.unicam.cs.mpgc.rpg130903.model.partita.EsitoEvento;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Schermata di fine partita: mostra come è andato l'evento finale
 * e permette di iniziare una nuova partita.
 */
public final class SchermataFinale extends VBox {

    /**
     * @param controller       il controller del gioco
     * @param allaNuovaPartita cosa fare quando il giocatore vuole ricominciare
     */
    public SchermataFinale(ControllerGioco controller, Runnable allaNuovaPartita) {
        if (controller == null || allaNuovaPartita == null) {
            throw new IllegalArgumentException("Controller e azione di nuova partita non possono essere nulli");
        }
        EsitoEvento esito = controller.getEsito();
        EventoFinale evento = controller.getEvento();

        Label titolo = new Label(esito.isVittoria() ? "Vittoria!" : "Sconfitta");
        titolo.getStyleClass().add(esito.isVittoria() ? "titolo-vittoria" : "titolo-sconfitta");

        Label racconto = new Label(esito.isVittoria()
                ? "La tua gilda ha respinto l'" + evento.getNome() + "!"
                : "La tua gilda non è riuscita a fermare l'" + evento.getNome() + ".");
        racconto.getStyleClass().add("racconto-finale");

        Label forza = new Label("Forza della gilda: " + esito.getForza());
        Label dado = new Label("Tiro del dado: " + esito.getDado());
        forza.getStyleClass().add("voce-riepilogo");
        dado.getStyleClass().add("voce-riepilogo");
        Label difficolta = new Label("Difficoltà dell'evento: " + esito.getDifficolta());
        difficolta.getStyleClass().add("voce-riepilogo");
        Label punteggio = new Label("Punteggio: " + esito.getPunteggio());
        punteggio.getStyleClass().add("punteggio-finale");
        VBox riepilogo = new VBox(8, forza, dado, difficolta, punteggio);
        riepilogo.getStyleClass().add("riepilogo");
        riepilogo.setAlignment(Pos.CENTER);
        riepilogo.setMaxWidth(340);

        Label regola = new Label(controller.getRegolaEvento());
        regola.getStyleClass().add("regola-evento");
        regola.setWrapText(true);
        regola.setMaxWidth(500);

        Button nuovaPartita = new Button("Nuova partita");
        nuovaPartita.getStyleClass().add("pulsante-principale");
        nuovaPartita.setOnAction(e -> allaNuovaPartita.run());

        setSpacing(20);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);
        getChildren().addAll(titolo, racconto, riepilogo, regola, nuovaPartita);
    }
}