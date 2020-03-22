/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.mapa;

import java.util.ArrayList;
import konquest.ui.FramePrincipal;

/**
 *
 * @author sergio
 */
public class Planeta {
    private String nombre;
    private int naves;
    private Integer produccion;
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
        String aux = ((String)atributos[0]).substring(1,((String)atributos[0]).length()-1);
        this.nombre = aux;
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

    public Integer getProduccion() {
        return produccion;
    }

    public void setProduccion(Integer produccion) {
        this.produccion = produccion;
    }

    public double getPorcentajeMuertes() {
        return porcentajeMuertes;
    }
    
    
    public static boolean verificarNombresIguales(ArrayList<Planeta> planetas,FramePrincipal fp){
        boolean aux=false;
        for (int i = 0; i < planetas.size()-1; i++) {
            for (int j = i+1; j < planetas.size(); j++) {
                if (planetas.get(i).getNombre().equals(planetas.get(j).getNombre())) {
                    fp.agregarTextoAcciones("Planetas con el mismo Nombre: \""+planetas.get(i).nombre+"\"");
                    aux=true;
                }
            }
        }
        
        return aux;
    }
    
}
