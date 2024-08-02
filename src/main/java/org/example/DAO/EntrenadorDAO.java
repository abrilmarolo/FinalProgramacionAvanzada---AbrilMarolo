package org.example.DAO;

import org.example.Model.Entrenador;

public interface EntrenadorDAO {
    public void aniadirEntrenador(Entrenador entrenador);
    public Entrenador devolverEntrenadorPorID(int id);
    public void actualiarEntrenador(Entrenador entrenador);
    public void eliminarEntrenador(int id);
    public void enfrentarse(int entrenadorid, int otroEntrenadorid);
    public int enfrentamientosGanados(int entrenadorid);
}
