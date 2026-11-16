package it.unicam.cs.mpgc.rpg130903.view;

import it.unicam.cs.mpgc.rpg130903.controller.ControllerGioco;
import it.unicam.cs.mpgc.rpg130903.model.attivita.Attivita;
import it.unicam.cs.mpgc.rpg130903.model.eroe.Eroe;
import it.unicam.cs.mpgc.rpg130903.model.eventofinale.EventoFinale;
import it.unicam.cs.mpgc.rpg130903.model.imprevisto.Imprevisto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Schermata principale: mostra lo stato della partita e permette
 * di far svolgere le attività agli eroi e di terminare il turno.
 */
public final class SchermataGioco extends BorderPane {

    private static final int NUMERO_COLORI = 4;

    private final ControllerGioco controller;
    private final Runnable allaFine;
    private final Label stato;
    private final HBox eroi;
    private final Label imprevisto;
    private final Label messaggio;

    /**
     * @param controller il controller del gioco
     * @param allaFine   cosa fare quando la partita è conclusa
     */
    public SchermataGioco(ControllerGioco controller, Runnable allaFine) {
        if (controller == null || allaFine == null) {
            throw new IllegalArgumentException("Controller e azione finale non possono essere nulli");
        }
        this.controller = controller;
        this.allaFine = allaFine;

        EventoFinale evento = controller.getEvento();
        Label infoEvento = new Label(evento.getNome() + " al turno " + evento.getTurnoInnesco()
                + " (difficoltà " + evento.getDifficolta() + ")");
        stato = new Label();
        stato.getStyleClass().add("stato-turno");
        VBox alto = new VBox(5, stato, infoEvento);
        alto.setAlignment(Pos.CENTER);
        alto.setPadding(new Insets(0, 0, 15, 0));

        eroi = new HBox(20);
        eroi.setAlignment(Pos.CENTER);

        imprevisto = new Label();
        messaggio = new Label();
        Button terminaTurno = new Button("Termina turno");
        terminaTurno.getStyleClass().add("pulsante-principale");
        terminaTurno.setOnAction(e -> terminaTurno());
        VBox basso = new VBox(10, imprevisto, messaggio, terminaTurno);
        basso.setAlignment(Pos.CENTER);
        basso.setPadding(new Insets(15, 0, 0, 0));

        setTop(alto);
        setCenter(eroi);
        setBottom(basso);
        setPadding(new Insets(20));

        aggiorna();
    }

    private void eseguiAttivita(Attivita attivita, Eroe eroe) {
        messaggio.setText(controller.eseguiAttivita(attivita, eroe));
        aggiorna();
    }

    private void terminaTurno() {
        controller.terminaTurno();
        if (controller.isPartitaConclusa()) {
            allaFine.run();
            return;
        }
        messaggio.setText("");
        aggiorna();
        mostraImprevisto();
    }

    private void mostraImprevisto() {
        Imprevisto ultimo = controller.getUltimoImprevisto();
        if (ultimo == null) {
            return;
        }
        Alert avviso = new Alert(Alert.AlertType.INFORMATION);
        avviso.setTitle("Imprevisto");
        avviso.setHeaderText(ultimo.getNome());
        avviso.setContentText(ultimo.getDescrizione());
        avviso.getDialogPane().getStylesheets().addAll(getScene().getStylesheets());
        avviso.showAndWait();
    }

    private void aggiorna() {
        stato.setText("Turno " + controller.getTurno()
                + "   Azioni rimaste: " + controller.getAzioniRimaste()
                + "   Oro: " + controller.getOro());

        Imprevisto ultimo = controller.getUltimoImprevisto();
        if (ultimo == null) {
            imprevisto.setText("Nessun imprevisto in questo turno");
        } else {
            imprevisto.setText(ultimo.getNome() + ": " + ultimo.getDescrizione());
        }

        eroi.getChildren().clear();
        for (Eroe eroe : controller.getEroi()) {
            eroi.getChildren().add(creaSchedaEroe(eroe));
        }
    }

    private VBox creaSchedaEroe(Eroe eroe) {
        Label nome = new Label(eroe.getNome() + " (" + eroe.getClasse().getNome() + ")");
        nome.getStyleClass().add("nome-eroe");
        Label crescita = new Label(eroe.getClasse().getDescrizione());
        crescita.getStyleClass().add("descrizione-classe");
        crescita.setWrapText(true);
        Label prossimo = new Label("Addestramento: +"
                + eroe.getClasse().getForzaGuadagnata(eroe.getForza()) + " forza");
        prossimo.getStyleClass().add("prossimo-addestramento");
        Label forza = new Label("Forza: " + eroe.getForza());
        Label energia = new Label("Energia: " + eroe.getEnergia() + "/" + eroe.getMaxStat());
        Label salute = new Label("Salute: " + eroe.getSalute() + "/" + eroe.getMaxStat());

        boolean disponibile = controller.isDisponibile(eroe);
        Label disponibilita = new Label(disponibile ? "Disponibile" : "Non disponibile in questo turno");

        VBox scheda = new VBox(6, nome, crescita, prossimo, forza, energia, salute, disponibilita);
        List<Attivita> elenco = controller.getAttivita();
        for (int i = 0; i < elenco.size(); i++) {
            Attivita attivita = elenco.get(i);
            Button pulsante = creaPulsanteAttivita(attivita, i);
            pulsante.setDisable(!disponibile);
            pulsante.setOnAction(e -> eseguiAttivita(attivita, eroe));
            scheda.getChildren().add(pulsante);
        }
        scheda.getStyleClass().add("scheda-eroe");
        scheda.setPrefWidth(230);
        return scheda;
    }

    private Button creaPulsanteAttivita(Attivita attivita, int posizione) {
        Label titolo = new Label(attivita.getNome());
        titolo.getStyleClass().add("titolo-attivita");
        Label effetti = new Label(attivita.getDescrizione());
        effetti.getStyleClass().add("effetti-attivita");

        Button pulsante = new Button();
        pulsante.setGraphic(new VBox(2, titolo, effetti));
        pulsante.getStyleClass().addAll("pulsante-attivita", "attivita-" + (posizione % NUMERO_COLORI));
        pulsante.setMaxWidth(Double.MAX_VALUE);
        return pulsante;
    }
}