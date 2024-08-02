package org.example.Model;

import org.example.Excepciones.PokemonNoPuedeRecibirMasDaño;
import org.example.Excepciones.PokemonnoPuedeRecibirMasVida;

public class Pokemon {
    private String especie;
    private TipoPokemon tipo;
    private Poder poderAsignado;
    private double energia, poder;
    private int id;
    private int idEntrenador;

    public Pokemon(String especie, TipoPokemon tipo, double energia, double poder, Poder poderAsignado) {
        this.especie = especie;
        this.tipo = tipo;
        this.energia = energia;
        this.poder = poder;
        this.poderAsignado = poderAsignado;
        this.id=id;
        this.idEntrenador=idEntrenador;
    }

    public Pokemon(String especie, TipoPokemon tipo, double energia, double poder, Poder poderAsignado, int idEntrenador) {
        this.especie = especie;
        this.tipo = tipo;
        this.energia = energia;
        this.poder = poder;
        this.poderAsignado = poderAsignado;
        this.id=id;
        this.idEntrenador=idEntrenador;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public TipoPokemon getTipo() {
        return tipo;
    }

    public void setTipo(TipoPokemon tipo) {
        this.tipo = tipo;
    }

    public double getEnergia() {
        return energia;
    }

    public void setEnergia(double energia) {
        this.energia = energia;
    }

    public double getPoder() {
        return poder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPoder(double poder) {
        this.poder = poder;
    }

    public int getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public Poder getPoderAsignado() {
        return poderAsignado;
    }

    public void setPoderAsignado(Poder poderAsignado) {
        this.poderAsignado = poderAsignado;
    }

    public void atacar(Pokemon otroPokemon){
        System.out.println(this.especie + " está atacando a " + otroPokemon.getEspecie());
        double dañoBase = this.poderAsignado.getDaño();
        double dañoCalculado = this.tipo.calcularDaño(dañoBase, this, otroPokemon);
        this.poder -= this.poderAsignado.getCostoPoder();
        otroPokemon.restarVida(dañoCalculado);
    }

    public void restarVida(double cant){
        if (this.energia - cant < 0){
            throw new PokemonNoPuedeRecibirMasDaño(this);
        }else{
            this.energia -= cant;
        }
    }

    public void aumentarVida(double vida){
        if (this.energia + vida <= 100){
            this.energia += vida;
            System.out.println(this.especie + " ahora tiene " + this.energia + " vida" );
        }else{
            throw new PokemonnoPuedeRecibirMasVida(this);
        }
    }
}
