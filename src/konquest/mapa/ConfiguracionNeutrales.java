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
public class ConfiguracionNeutrales {

    private boolean mostrarNaves;
    private boolean mostrarEstadisticas;
    private int produccion;

    public ConfiguracionNeutrales(Object[] atributos){
    mostrarNaves=(Boolean)atributos[0];
    mostrarEstadisticas=(Boolean)atributos[1];
    produccion=(Integer)atributos[2];
    }
    public boolean isMostrarNaves() {
        return mostrarNaves;
    }

    public void setMostrarNaves(boolean mostrarNaves) {
        this.mostrarNaves = mostrarNaves;
    }

    public boolean isMostrarEstadisticas() {
        return mostrarEstadisticas;
    }

    public void setMostrarEstadisticas(boolean mostrarEstadisticas) {
        this.mostrarEstadisticas = mostrarEstadisticas;
    }

    public int getProduccion() {
        return produccion;
    }

    public void setProduccion(int produccion) {
        this.produccion = produccion;
    }
    
        public static boolean verificarObligatorios(Object[] atributos){
        for (int i = 0; i < 3; i++) {
            if (atributos[i]==null||atributos[i]=="") {
                return false;
            }
        }
        return true;
    }
    
}
