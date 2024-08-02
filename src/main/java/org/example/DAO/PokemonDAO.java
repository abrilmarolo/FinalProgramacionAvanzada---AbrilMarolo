package org.example.DAO;

import org.example.Model.Pokemon;

public interface PokemonDAO {
    public void aniadirPokemon(Pokemon pokemon);
    public Pokemon devolverPokemonPorID(int id);
    public void actualiarPokemon(Pokemon pokemon);
    public void eliminarPokemon(int id);
    public void capturarPokemon(int id, int idEntrenador);
}
