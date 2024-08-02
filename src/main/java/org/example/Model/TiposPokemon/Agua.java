package org.example.Model.TiposPokemon;

import org.example.Model.Pokemon;
import org.example.Model.TipoPokemon;

public class Agua extends TipoPokemon {
    public Agua(String nombre) {
        super("Agua");
    }

    @Override
    public double calcularDaño(double daño, Pokemon pokemonAtacante, Pokemon pokemonDefensor) {
        if (pokemonDefensor.getTipo().getNombre().equalsIgnoreCase("Fuego")){
            daño = daño + daño * 0.25;
            return daño;
        }
        return daño;
    }
}
