package it.unicam.cs.mpgc.rpg130903.model.missione;

import java.util.List;

public interface GeneratoreMissioni {
    List<Quest> creaMissioniDelGiorno (int numeroEroi, int livelloMinimo, int livelloMassimo);
}
