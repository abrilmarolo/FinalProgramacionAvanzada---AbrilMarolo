package org.example.Model.TiposPokemon;

import org.example.Model.Pokemon;
import org.example.Model.TipoPokemon;

public class Electrico extends TipoPokemon {
    public Electrico(String nombre) {
        super("Electrico");
    }

    @Override
    public double calcularDaño(double daño, Pokemon pokemonAtacante, Pokemon pokemonDefensor) {
        if (pokemonDefensor.getTipo().getNombre().equalsIgnoreCase("Agua")){
            daño = daño + daño * 0.5;
            pokemonAtacante.restarVida(daño * 0.05);
            return daño;
        }
        return daño;
    }
}
