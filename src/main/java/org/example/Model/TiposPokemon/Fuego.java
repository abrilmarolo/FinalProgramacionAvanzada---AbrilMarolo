package org.example.Model.TiposPokemon;

import org.example.Model.Pokemon;
import org.example.Model.TipoPokemon;

public class Fuego extends TipoPokemon {
    public Fuego(String nombre) {
        super("Fuego");
    }

    @Override
    public double calcularDaño(double daño, Pokemon pokemonAtacante, Pokemon pokemonDefensor) {
        if (pokemonDefensor.getTipo().getNombre().equalsIgnoreCase("Planta")){
            daño = daño + daño * 0.25;
            return daño;
        }
        return daño;
    }
}
