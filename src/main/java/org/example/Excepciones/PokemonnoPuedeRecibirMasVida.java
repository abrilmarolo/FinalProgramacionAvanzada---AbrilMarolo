package org.example.Excepciones;

import org.example.Model.Pokemon;

public class PokemonnoPuedeRecibirMasVida extends RuntimeException{
    public PokemonnoPuedeRecibirMasVida(Pokemon pokemon){
        super (pokemon.getEspecie() + " no puede recibir más vida");
    }
}
