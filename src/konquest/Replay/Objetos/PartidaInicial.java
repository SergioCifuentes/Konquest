/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Replay.Objetos;

import konquest.ui.FramePrincipal;

/**
 *
 * @author sergio
 */
public class PartidaInicial {
    public static final String NEUTRAL="NEUTRAL";
    private String nombrePlaneta;
    private int fila;
    private int columna;
    private String nombreJugador;
    private int naves;
    private int produccion;
    private Double porcentajeMuertes;
    private final static String[] ATRIBUTOS={"nombre","fila","columan","dueño","naves","produccion","porcentajeMuertes"};
    
    public PartidaInicial(Object[] atributos){
        nombrePlaneta=((String)atributos[0]).substring(1,((String)atributos[0]).length()-1);
        fila=(Integer) atributos[1];
        columna=(Integer) atributos[2];
        if (atributos[3]!=null) {
            nombreJugador=((String)atributos[3]).substring(1,((String)atributos[3]).length()-1);
        }else{
            nombreJugador=NEUTRAL;
        }
        
        naves=(Integer) atributos[4];
        produccion=(Integer) atributos[5];
        porcentajeMuertes=(Double)atributos[6];
        
        
    }
    
    public void restar(){
        fila--;
        columna--;
    }
    public static boolean verificarObligatorios(Object[] atributos,FramePrincipal fp){
        boolean faltante=true;
        for (int i = 0; i < 7; i++) {
            if (i!=3) {
                if (atributos[i]==null||atributos[i]=="") {
                fp.agregarTextoAcciones("Atributo Para Planeta Faltante:"+ATRIBUTOS[i]+"\n");
                faltante=false;
            }
            }
            
        }

        return faltante;
    }

    public String getNombrePlaneta() {
        return nombrePlaneta;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public int getNaves() {
        return naves;
    }

    public int getProduccion() {
        return produccion;
    }

    public Double getPorcentajeMuertes() {
        return porcentajeMuertes;
    }

    public static String[] getATRIBUTOS() {
        return ATRIBUTOS;
    }
    
}
