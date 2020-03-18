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
    private double porcentajeMuertes;
    private Jugador owner;
    
    public static boolean verificarObligatorios(Object[] atributos){
        for (int i = 0; i < 4; i++) {
            if (atributos[i]==null||atributos[i]=="") {
                return false;
            }
        }
        return true;
    }
    public Planeta(Object[] atributos) {
        this.nombre = (String)atributos[0];
        this.naves = (Integer)atributos[1];
        this.produccion = (Integer)atributos[2];
        this.porcentajeMuertes = (Double)atributos[3];
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

    public double getPorcentajeMuertes() {
        return porcentajeMuertes;
    }
    
    
}
