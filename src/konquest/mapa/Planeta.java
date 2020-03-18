/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.mapa;

/**
 *
 * @author sergio
 */
public class Planeta {
    private String nombre;
    private int naves;
    private int produccion;
    private float porcentajeMuertes;
    private Jugador owner;
    
    
    public Planeta(String nombre, int naves, int produccion, float porcentajeMuertes) {
        this.nombre = nombre;
        this.naves = naves;
        this.produccion = produccion;
        this.porcentajeMuertes = porcentajeMuertes;
    }

    public Jugador getOwner() {
        return owner;
    }

    public void setOwner(Jugador owner) {
        this.owner = owner;
    }
     
    
    public String getNombre() {
        return nombre;
    }

    public int getNaves() {
        return naves;
    }

    public int getProduccion() {
        return produccion;
    }

    public float getPorcentajeMuertes() {
        return porcentajeMuertes;
    }
    
    
}
