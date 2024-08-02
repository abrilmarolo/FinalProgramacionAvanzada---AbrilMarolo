package org.example.DAO;

import org.example.Model.*;
import org.example.Model.Entrenador;
import org.example.Model.Genero;
import org.example.Model.TiposPokemon.*;

import java.util.Date;
import java.sql.*;


public class EntrenadorDAOImpl implements EntrenadorDAO {
    private static final String URL = "jdbc:h2:./data/test";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "entrenadores";

    public EntrenadorDAOImpl() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String createTableQuery = " DROP TABLE " + TABLE_NAME +  ";" + "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(255), direccion VARCHAR(255), edad INT, fechaNacimiento DATE, genero VARCHAR(255), nacionalidad VARCHAR(255), enfrentamientoGanados VARCHAR(255))";
            statement.executeUpdate(createTableQuery);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void aniadirEntrenador(Entrenador entrenador) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);) {
            String insertQuery = "INSERT INTO " + TABLE_NAME + " (nombre, direccion, edad, fechaNacimiento, genero, nacionalidad) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, entrenador.getNombre());
            pst.setString(2, entrenador.getDireccion());
            pst.setInt(3, entrenador.getEdad());

            pst.setDate(4, new java.sql.Date(entrenador.getFechaNacimiento().getTime()));
            pst.setString(5, entrenador.getGenero().name());
            pst.setString(6, entrenador.getNacionalidad());
            pst.executeUpdate();

            ResultSet generateKeys = pst.getGeneratedKeys();
            if (generateKeys.next()) {
                int id = generateKeys.getInt(1);
                entrenador.setId(id);
            }

            System.out.println("El entrenador " + entrenador.getNombre() + " con la nacionalidad " + entrenador.getNacionalidad() + " de " + entrenador.getEdad() + " años, con la fecha de nacimiento " + entrenador.getFechaNacimiento() + " ,con la direccion " + entrenador.getDireccion() +
                    " y el genero " + entrenador.getGenero() + " se ha añadido");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Entrenador devolverEntrenadorPorID(int id) {
        Entrenador entrenador = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);) {
            String insertQuery = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(insertQuery);
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String direccion = rs.getString("direccion");
                    int edad = rs.getInt("edad");
                    Date fechaNacimiento = rs.getDate("fechaNacimiento");
                    String generoString = rs.getString("genero");
                    Genero genero = Genero.valueOf(generoString.toUpperCase());
                    String nacionalidad = rs.getString("nacionalidad");

                    entrenador = new Entrenador(nombre, genero, edad, fechaNacimiento, nacionalidad, direccion);
                    entrenador.setId(id);
                    String pokemonQuery = "SELECT * FROM pokemones WHERE id_entrenador = ?";
                    try (PreparedStatement pstPokemones = connection.prepareStatement(pokemonQuery)) {
                        pstPokemones.setInt(1, id);
                        try (ResultSet rsPokemones = pstPokemones.executeQuery()) {
                            while (rsPokemones.next()) {
                                int pokemonId = rsPokemones.getInt("id");
                                String especie = rsPokemones.getString("especie");
                                String tipoString = rsPokemones.getString("tipo");
                                TipoPokemon tipo = null;
                                switch (tipoString) {
                                    case "Electrico":
                                        tipo = new Electrico("Electrico");
                                        break;
                                    case "Agua":
                                        tipo = new Agua("Agua");
                                        break;
                                    case "Fuego":
                                        tipo = new Fuego("Fuego");
                                        break;
                                    case "Planta":
                                        tipo = new Planta("Plata");
                                        break;
                                    case "Piedra":
                                        tipo = new Piedra("Piedra");
                                        break;
                                }
                                double energia = rsPokemones.getFloat("energia");
                                double poderTotal = rsPokemones.getFloat("poderTotal");
                                String nombrePoder = rsPokemones.getString("nombrePoder");
                                double daño = rsPokemones.getDouble("daño");
                                double costoPoder = rsPokemones.getDouble("costoPoder");
                                Poder poderAsignado = new Poder(nombrePoder, costoPoder, daño);

                                Pokemon pokemon = new Pokemon(especie, tipo, energia, poderTotal, poderAsignado);
                                pokemon.setId(pokemonId);
                                entrenador.getPokemones().add(pokemon);
                            }
                        }
                    }
                }
            }
            System.out.println("El entrenador buscado es: " + entrenador.getNombre() + " con la nacionalidad " + entrenador.getNacionalidad() + " de " + entrenador.getEdad() + " años, con la fecha de nacimiento " + entrenador.getFechaNacimiento() + " ,con la direccion " + entrenador.getDireccion() +
                    " y el genero " + entrenador.getGenero());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrenador;
    }

    @Override
    public void actualiarEntrenador(Entrenador entrenador) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);) {
            String insertQuery = "UPDATE " + TABLE_NAME + " SET nombre = ? , direccion = ?, edad = ?, fechaNacimiento = ?, genero = ?, nacionalidad = ? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(insertQuery);
            pst.setString(1, entrenador.getNombre());
            pst.setString(2, entrenador.getDireccion());
            pst.setInt(3, entrenador.getEdad());
            pst.setDate(4, new java.sql.Date(entrenador.getFechaNacimiento().getTime()));
            pst.setString(5, String.valueOf(entrenador.getGenero()));
            pst.setString(6, entrenador.getNacionalidad());
            pst.setInt(7, entrenador.getId());
            pst.executeUpdate();
            for (Pokemon pokemon : entrenador.getPokemones()) {
                String updatePokemonQuery = "UPDATE pokemones SET especie = ?, tipo = ?, energia = ?, poderTotal = ?, nombrePoder = ?, daño = ?, costoPoder = ? WHERE id = ?";
                try (PreparedStatement pstPokemon = connection.prepareStatement(updatePokemonQuery)) {
                    pstPokemon.setString(1, pokemon.getEspecie());
                    pstPokemon.setString(2, pokemon.getTipo().getNombre());
                    pstPokemon.setDouble(3, pokemon.getEnergia());
                    pstPokemon.setDouble(4, pokemon.getPoder());
                    pstPokemon.setString(5, pokemon.getPoderAsignado().getNombre());
                    pstPokemon.setDouble(6, pokemon.getPoderAsignado().getDaño());
                    pstPokemon.setDouble(7, pokemon.getPoderAsignado().getCostoPoder());
                    pstPokemon.setInt(8, pokemon.getId());
                    pstPokemon.executeUpdate();
                }
            }
            System.out.println("El entrenador " + entrenador.getNombre() + " con la nacionalidad " + entrenador.getNacionalidad() + " de" + entrenador.getEdad() + " años, con la fecha de nacimiento " + entrenador.getFechaNacimiento() + " ,con la direccion " + entrenador.getDireccion() +
                    " y el genero " + entrenador.getGenero() + " se ha actuallizado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarEntrenador(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);) {
            String insertQuery = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(insertQuery);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
            System.out.println("Se ha eliminado el entrenador con el id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enfrentarse(int entrenadorId1, int entrenadorId2) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Entrenador entrenador1 = devolverEntrenadorPorID(entrenadorId1);
            Entrenador entrenador2 = devolverEntrenadorPorID(entrenadorId2);

            if (entrenador1 == null || entrenador2 == null) {
                System.out.println("Uno o ambos de los entrenadores no se encuentra en la base de datos");
            }
            System.out.println(entrenador1.getPokemones());
            System.out.println(entrenador2.getPokemones());
            Entrenador ganador = entrenador1.enfrentarseA(entrenador2);

            if (ganador != null) {
                int totalVictorias = enfrentamientosGanados(ganador.getId());
                String updateQuery = "UPDATE " + TABLE_NAME + " SET enfrentamientoGanados = ? WHERE id = ?";

                try (PreparedStatement pst = connection.prepareStatement(updateQuery)) {
                    pst.setInt(1, totalVictorias + 1);
                    pst.setInt(2, ganador.getId());
                    pst.executeUpdate();
                }
                System.out.println("El entrenador " + ganador.getNombre() + " ha ganado contra el entrenador con id " + (ganador.equals(entrenador1) ? entrenadorId2 : entrenadorId1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int enfrentamientosGanados(int entrenadorid) {
        int enfrentamientoGanados = 0;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT enfrentamientoGanados FROM " + TABLE_NAME + " WHERE id = ?";
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setInt(1, entrenadorid);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        enfrentamientoGanados = rs.getInt("enfrentamientoGanados");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enfrentamientoGanados;
    }

    public Entrenador pelear(int entrenadorId1, int entrenadorId2) {
        Entrenador entrenador1 = devolverEntrenadorPorID(entrenadorId1);
        Entrenador entrenador2 = devolverEntrenadorPorID(entrenadorId2);

        if (entrenador1 == null) {
            System.out.println("No se encontró el entrenador con ID: " + entrenadorId1);
            return null;
        }

        if (entrenador2 == null) {
            System.out.println("No se encontró el entrenador con ID: " + entrenadorId2);
            return null;
        }

        return entrenador1.enfrentarseA(entrenador2); // Asumiendo que el método enfrentarseA devuelve el ganador
    }
}
