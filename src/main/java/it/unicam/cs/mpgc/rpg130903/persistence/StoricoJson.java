package it.unicam.cs.mpgc.rpg130903.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import it.unicam.cs.mpgc.rpg130903.model.storico.PartitaSalvata;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Storico delle partite salvato in un file JSON.
 */
public final class StoricoJson implements StoricoRepository {

    private final Path file;
    private final Gson gson;

    public StoricoJson(Path file) {
        if (file == null) {
            throw new IllegalArgumentException("Il percorso del file non può essere nullo");
        }
        this.file = file;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void salva(PartitaSalvata partita) throws IOException {
        if (partita == null) {
            throw new IllegalArgumentException("La partita non può essere nulla");
        }
        List<PartitaSalvata> partite = new ArrayList<>(carica());
        partite.add(partita);
        try (Writer writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            gson.toJson(partite, writer);
        }
    }

    @Override
    public List<PartitaSalvata> carica() throws IOException {
        if (!Files.exists(file)) {
            return List.of();
        }
        try (Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            PartitaSalvata[] partite = gson.fromJson(reader, PartitaSalvata[].class);
            if (partite == null) {
                return List.of();
            }
            return List.of(partite);
        } catch (JsonParseException e) {
            throw new IOException("Il file dello storico è danneggiato", e);
        }
    }
}