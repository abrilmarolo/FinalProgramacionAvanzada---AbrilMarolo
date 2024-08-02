package org.example.Model;
import org.example.Excepciones.NoSePuedeCapturarPokemonException;
import org.example.Excepciones.NoSePuedenEnfrentarException;

import java.util.Date;
import java.util.LinkedList;

public class Entrenador {
    private String nombre, direccion, nacionalidad;
    private Date fechaNacimiento;
    private int edad;
    private Genero genero;
    private LinkedList<Pokemon> pokemones;
    private int id;

    public Entrenador(String nombre, Genero genero, int edad, Date fechaNacimiento, String nacionalidad ,String direccion) {
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.direccion = direccion;
        this.pokemones = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public LinkedList<Pokemon> getPokemones() {
        return pokemones;
    }

    public void setPokemones(LinkedList<Pokemon> pokemones) {
        this.pokemones = pokemones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Entrenador enfrentarseA(Entrenador otroentrenador){
        int misPuntos = 0;
        int susPuntos = 0;

        System.out.println("Enfrentamiento entre "+ this.nombre+" y " + otroentrenador.nombre);
        if (this.getPokemones().size() == otroentrenador.getPokemones().size()) {
            for (int i = 0; i < this.pokemones.size(); i++) {
                Pokemon miPokemon = this.pokemones.get(i);
                Pokemon suPokemon = otroentrenador.getPokemones().get(i);
                System.out.println("-------------------");
                System.out.println("Se enfrentan Los pokemones: " + miPokemon.getEspecie() + " del entrenador " + this.nombre + " y " + suPokemon.getEspecie() + " del entrenador " + otroentrenador.nombre);

                while (miPokemon.getEnergia() > 0 && suPokemon.getEnergia() > 0) {
                    miPokemon.atacar(suPokemon);
                    System.out.println(suPokemon.getEspecie() + " tiene " + suPokemon.getEnergia() + " de vida restante.");

                    if (suPokemon.getEnergia() > 0) {
                        suPokemon.atacar(miPokemon);
                        System.out.println(miPokemon.getEspecie() + " tiene " + miPokemon.getEnergia() + " de vida restante.");
                    }
                }

                if (miPokemon.getEnergia() > 0) {
                    System.out.println("-------------------");
                    System.out.println(miPokemon.getEspecie() + " ha ganado contra " + suPokemon.getEspecie());
                    misPuntos++;
                } else {
                    System.out.println("-------------------");
                    System.out.println(suPokemon.getEspecie() + " ha ganado contra " + miPokemon.getEspecie());
                    susPuntos++;
                }
            }

            if (misPuntos > susPuntos) {
                System.out.println("-------------------");
                System.out.print("El ganador es " + this.nombre);
                return this;
            } else {
                System.out.println("-------------------");
                System.out.println("El ganador es " + otroentrenador.nombre);
                return otroentrenador;
            }
        } else {
            throw new NoSePuedenEnfrentarException(this, otroentrenador);
        }

    }

    public void capturarPokemon(Pokemon pokemon){
        if (this.pokemones.size()>=5 || pokemon.getEnergia()!=0){
            throw new NoSePuedeCapturarPokemonException(this, pokemon);
        }else{
            pokemones.add(pokemon);
            System.out.println("Ha capturado exitosamente a " + pokemon.getEspecie() +" que tiene " + pokemon.getEnergia() + " de vida y " + pokemon.getPoder() + " de poder");
        }
    }


}
