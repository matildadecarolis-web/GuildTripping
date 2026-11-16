package it.unicam.cs.mpgc.rpg130903.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Punto di avvio dell'applicazione JavaFX.
 */
public class Applicazione extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Label titolo = new Label("GuildTripping");
        Scene scena = new Scene(titolo, 800, 600);

        stage.setTitle("GuildTripping");
        stage.setScene(scena);
        stage.show();
    }
}