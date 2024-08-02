package org.example.Excepciones;

import org.example.Model.Pokemon;

public class PokemonNoPuedeRecibirMasDaño extends RuntimeException{
    public PokemonNoPuedeRecibirMasDaño(Pokemon pokemon){
        super(pokemon.getEspecie() + " no puede recibir mas daño");
    }
}
