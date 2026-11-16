package it.unicam.cs.mpgc.rpg130903.app;

import it.unicam.cs.mpgc.rpg130903.controller.ControllerGioco;
import it.unicam.cs.mpgc.rpg130903.model.partita.CreatorePartita;
import it.unicam.cs.mpgc.rpg130903.view.SchermataIniziale;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Random;

/**
 * Punto di avvio dell'applicazione: crea e collega gli oggetti principali
 * e decide quale schermata mostrare.
 */
public class Applicazione extends Application {

    private ControllerGioco controller;
    private Scene scena;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        controller = new ControllerGioco(new CreatorePartita(new Random()));
        scena = new Scene(new Label(), 900, 650);
        caricaStile();

        mostraSchermataIniziale();

        stage.setTitle("GuildTripping");
        stage.setScene(scena);
        stage.show();
    }

    private void caricaStile() {
        URL stile = getClass().getResource("/css/style.css");
        if (stile != null) {
            scena.getStylesheets().add(stile.toExternalForm());
        }
    }

    private void mostraSchermataIniziale() {
        scena.setRoot(new SchermataIniziale(controller, this::mostraSchermataGioco));
    }

    private void mostraSchermataGioco() {
        scena.setRoot(new Label("Partita iniziata: turno " + controller.getTurno()));
    }
}