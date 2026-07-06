package it.unicam.cs.mpgc.rpg130903.model.missione;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratoreBase implements GeneratoreMissioni{

    private static final String[] NOMI_MISSIONI = {
            "Pattugliamento delle Mura",
            "Scorta al Mercante",
            "Caccia ai Topi Giganti",
            "Caccia ai Goblin",
            "Esplorazione del Dungeon",
            "Scontro con i Banditi",
            "Difesa dell'Avamposto",
            "Recupero dell'Artefatto Perduto"
    };

    private final Random random;

    private final CalcolatoreProbabilitaStrategy calcolatoreProbabilita;

    public GeneratoreBase(CalcolatoreProbabilitaStrategy calcolatore) {
        this.calcolatoreProbabilita = calcolatore;
        this.random = new Random();
    }


    @Override
    public List<Quest> creaMissioniDelGiorno(int numeroEroi, int livelloMinimo, int livelloMassimo) {
        List<Quest> nuoveMissioni = new ArrayList<>();

        for (int i = 0; i < numeroEroi; i++){

            int difficoltaGenerata;

            if (livelloMassimo == livelloMinimo){
                difficoltaGenerata = livelloMinimo;
            } else {
                difficoltaGenerata = random.nextInt((livelloMassimo- livelloMinimo) +1) +livelloMinimo;
            }

            String nomeScelto = NOMI_MISSIONI[random.nextInt(NOMI_MISSIONI.length)];
            nuoveMissioni.add(new MissioneSingola(nomeScelto, difficoltaGenerata, this.calcolatoreProbabilita));
        }
        return nuoveMissioni;
    }
}
