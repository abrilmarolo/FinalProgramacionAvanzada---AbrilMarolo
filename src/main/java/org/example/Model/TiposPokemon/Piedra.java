package org.example.Model.TiposPokemon;

import org.example.Model.Pokemon;
import org.example.Model.TipoPokemon;

public class Piedra extends TipoPokemon {
    public Piedra(String nombre) {
        super("Piedra");
    }

    @Override
    public double calcularDaño(double daño, Pokemon pokemonAtacante, Pokemon pokemonDefensor) {
        return daño;
    }
}
