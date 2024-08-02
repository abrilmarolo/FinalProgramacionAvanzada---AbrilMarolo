package org.example.Logica;

import org.example.DAO.EntrenadorDAO;
import org.example.DAO.EntrenadorDAOImpl;
import org.example.DAO.PokemonDAO;
import org.example.DAO.PokemonDAOImpl;
import org.example.Model.*;
import org.example.Model.TiposPokemon.*;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntrenadorDAO entrenadorDAO = new EntrenadorDAOImpl();
        PokemonDAO pokemonDAO = new PokemonDAOImpl();

        Electrico electrico = new Electrico("Electrico");
        Agua agua = new Agua("Agua");
        Planta planta = new Planta("Planta");
        Fuego fuego = new Fuego("Fuego");
        Piedra piedra = new Piedra("Piedra");

        Poder poderPicachu = new Poder("Impactrueno Thunder Shock", 5, 30);
        Poder poderBulbasur = new Poder("Tormenta Floral", 5, 30);
        Poder poderVaporeon = new Poder("Rayo Aurora Aurora Beam", 5, 50);
        Poder poderCharizard = new Poder("Gruñido Growl", 5, 50);
        Poder poderOnix = new Poder("Tumba Rocas Rock Tomb", 5, 25);
        Poder poderLunatone  = new Poder("LanzarrocasRock Throwt", 5, 35);

        Date fechaNac1 = new Date(101,2,17);
        Date fechaNac2 = new Date(102,4,27);

        Entrenador entrenador1 = new Entrenador("Pedro", Genero.MASCULINO, 22, fechaNac1, "Argentina","San Juan 4123");
        entrenadorDAO.aniadirEntrenador(entrenador1);
        Entrenador entrenador2 = new Entrenador("Victoria", Genero.FEMENINO, 24, fechaNac2, "Uruguaya","Tomás Giribaldi 2290");
        entrenadorDAO.aniadirEntrenador(entrenador2);

        Pokemon pokemon1 = new Pokemon ("Pikachu", electrico, 0, 100, poderPicachu);
        pokemonDAO.aniadirPokemon(pokemon1);
        Pokemon pokemon2 = new Pokemon ("Bulbasaur", planta, 0, 100, poderBulbasur);
        pokemonDAO.aniadirPokemon(pokemon2);
        Pokemon pokemon3 = new Pokemon ("Vaporeon", agua, 0, 100 , poderVaporeon);
        pokemonDAO.aniadirPokemon(pokemon3);
        Pokemon pokemon4 = new Pokemon ("Charizard", fuego, 0, 100, poderCharizard);
        pokemonDAO.aniadirPokemon(pokemon4);
        Pokemon pokemon5 = new Pokemon ("Onix",piedra, 0, 100, poderOnix);
        pokemonDAO.aniadirPokemon(pokemon5);
        Pokemon pokemon6 = new Pokemon ("Lunatone", piedra, 0, 100,poderLunatone );
        pokemonDAO.aniadirPokemon(pokemon6);

        entrenador1.capturarPokemon(pokemon1);
        pokemonDAO.capturarPokemon(1,1);
        entrenador1.capturarPokemon(pokemon2);
        pokemonDAO.capturarPokemon(2,1);
        entrenador1.capturarPokemon(pokemon3);
        pokemonDAO.capturarPokemon(3,1);
        entrenador2.capturarPokemon(pokemon4);
        pokemonDAO.capturarPokemon(4,2);
        entrenador2.capturarPokemon(pokemon5);
        pokemonDAO.capturarPokemon(5,2);
        entrenador2.capturarPokemon(pokemon6);
        pokemonDAO.capturarPokemon(6,2);

        System.out.println("------------------------------------");
        System.out.println("El entrenador " + entrenador1.getNombre() + " ha decidido curar a " + pokemon1.getEspecie() +", "+pokemon2.getEspecie() + " y " + pokemon3.getEspecie());
        pokemon1.aumentarVida(100);
        pokemon2.aumentarVida(100);
        pokemon3.aumentarVida(100);

        System.out.println("El entrenador " + entrenador2.getNombre() + " ha decidido curar a " + pokemon4.getEspecie() +", "+pokemon5.getEspecie() + " y " + pokemon6.getEspecie());
        pokemon4.aumentarVida(100);
        pokemon5.aumentarVida(100);
        pokemon6.aumentarVida(100);
        entrenadorDAO.actualiarEntrenador(entrenador1); // actualizar vida pokemones y lo tome el DAO de entrenadore
        entrenadorDAO.actualiarEntrenador(entrenador2); // actualizar vida pokemones y lo tome el DAO de entrenadore
        System.out.println("------------------------------------");
        entrenador1.enfrentarseA(entrenador2);
        System.out.println("------------------------------------");
        entrenadorDAO.enfrentarse(1,2);

        System.out.println("------------------------------------");
        System.out.println("DAO");
        entrenadorDAO.devolverEntrenadorPorID(1);
        entrenadorDAO.devolverEntrenadorPorID(2);
        pokemonDAO.devolverPokemonPorID(2);
        pokemonDAO.actualiarPokemon(pokemon4);
        pokemonDAO.eliminarPokemon(1);



    }
}