package org.example.Model.TiposPokemon;

import org.example.Model.Pokemon;
import org.example.Model.TipoPokemon;

public class Planta extends TipoPokemon {
    public Planta(String nombre) {
        super("Planta");
    }

    @Override
    public double calcularDaño(double daño, Pokemon pokemonAtacante, Pokemon pokemonDefensor) {
        if (pokemonDefensor.getTipo().getNombre().equalsIgnoreCase("Piedra")){
            daño = 0;
            return daño;
        }
        return daño;
    }
}
