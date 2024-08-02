package org.example.Excepciones;

import org.example.Model.Entrenador;

public class NoSePuedenEnfrentarException extends RuntimeException{
    public NoSePuedenEnfrentarException(Entrenador miEntrenador, Entrenador otroEntrenador) {
        super("El entrenador " + miEntrenador.getNombre() + " no se puede enfrentar al entrenador " + otroEntrenador.getNombre() + " porque no tienen la misma cantidad de pokemones");
    }
}
