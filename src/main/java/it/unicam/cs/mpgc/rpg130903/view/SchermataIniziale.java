package it.unicam.cs.mpgc.rpg130903.view;

import it.unicam.cs.mpgc.rpg130903.controller.ControllerGioco;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import it.unicam.cs.mpgc.rpg130903.model.storico.PartitaSalvata;
import java.util.List;

/**
 * Schermata di avvio: il giocatore sceglie il nome della gilda e inizia la partita.
 */
public final class SchermataIniziale extends VBox {

    private final ControllerGioco controller;
    private final Runnable allAvvio;
    private final TextField campoNome;
    private final Label messaggio;
    private static final int PARTITE_MOSTRATE = 5;

    public SchermataIniziale(ControllerGioco controller, Runnable allAvvio) {
        if (controller == null || allAvvio == null) {
            throw new IllegalArgumentException("Controller e azione di avvio non possono essere nulli");
        }
        this.controller = controller;
        this.allAvvio = allAvvio;

        Label titolo = new Label("GuildTripping");
        titolo.getStyleClass().add("titolo");

        Label istruzione = new Label("Scegli il nome della tua gilda");

        campoNome = new TextField();
        campoNome.setPromptText("Nome della gilda");
        campoNome.setMaxWidth(250);
        campoNome.setOnAction(e -> avvia());

        Button inizia = new Button("Inizia");
        inizia.getStyleClass().add("pulsante-principale");
        inizia.setOnAction(e -> avvia());

        messaggio = new Label();
        messaggio.getStyleClass().add("messaggio-errore");

        setSpacing(15);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);
        getChildren().addAll(titolo, istruzione, campoNome, inizia, messaggio, creaStorico());    }

    private void avvia() {
        String nome = campoNome.getText().trim();
        if (nome.isEmpty()) {
            messaggio.setText("Inserisci un nome per la gilda");
            return;
        }
        controller.nuovaPartita(nome);
        allAvvio.run();
    }

    /**
     * Crea il riquadro con le ultime partite giocate, dalla più recente.
     */
    private VBox creaStorico() {
        Label intestazione = new Label("Ultime partite");
        intestazione.getStyleClass().add("intestazione-storico");
        VBox riquadro = new VBox(6, intestazione);

        List<PartitaSalvata> partite = controller.getStorico();
        if (partite.isEmpty()) {
            riquadro.getChildren().add(new Label("Nessuna partita giocata"));
        }
        int prima = Math.max(0, partite.size() - PARTITE_MOSTRATE);
        for (int i = partite.size() - 1; i >= prima; i--) {
            PartitaSalvata partita = partite.get(i);
            Label riga = new Label(partita.getData() + "   " + partita.getNomeGilda() + "   "
                    + (partita.isVittoria() ? "Vittoria" : "Sconfitta")
                    + "   " + partita.getPunteggio() + "/" + partita.getDifficolta());
            riga.getStyleClass().add(partita.isVittoria() ? "riga-vittoria" : "riga-sconfitta");
            riquadro.getChildren().add(riga);
        }
        riquadro.getStyleClass().add("riepilogo");
        riquadro.setAlignment(Pos.CENTER);
        riquadro.setMaxWidth(420);
        return riquadro;
    }
}