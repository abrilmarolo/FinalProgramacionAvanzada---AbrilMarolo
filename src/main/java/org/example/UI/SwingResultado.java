package org.example.UI;

import org.example.DAO.EntrenadorDAOImpl;
import org.example.Model.Entrenador;
import org.example.Model.Pokemon;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingResultado extends JFrame {
    private EntrenadorDAOImpl entrenadorDAO;

    public SwingResultado(int entrenadorId, int otroEntrenadorId) {
        this.setTitle("Ganador");
        this.setSize(480, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        entrenadorDAO = new EntrenadorDAOImpl();
        Entrenador ganador = entrenadorDAO.pelear(entrenadorId, otroEntrenadorId);

        JPanel panel = new JPanel();

        if (ganador != null) {
            JLabel labelGanador = new JLabel("Ganador: " + ganador.getNombre());
            labelGanador.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(labelGanador);

            List<Pokemon> pokemones = ganador.getPokemones();
            for (Pokemon pokemon : pokemones) {
                JLabel labelPokemon = new JLabel("Pokémon: " + pokemon.getEspecie() + " - Tipo: " + pokemon.getTipo() + " - Energía: " + pokemon.getEnergia());
                labelPokemon.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(labelPokemon);
            }
        } else {
            JLabel errorLabel = new JLabel("Error: No se pudo determinar el ganador.");
            errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(errorLabel);
        }

        this.add(panel);
        this.setVisible(true);

    }

}
