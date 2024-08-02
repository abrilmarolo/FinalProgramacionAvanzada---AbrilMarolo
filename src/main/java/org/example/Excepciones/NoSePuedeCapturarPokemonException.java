package org.example.Excepciones;

import org.example.Model.Entrenador;
import org.example.Model.Pokemon;

public class NoSePuedeCapturarPokemonException extends RuntimeException {
    public NoSePuedeCapturarPokemonException(Entrenador entrenador, Pokemon pokemon) {
        super ("No se puede capturar el pokemon " + pokemon.getEspecie() + " porque ya tiene 5 pokemones o el pokemon no tiene 0 de vida");
    }
}
