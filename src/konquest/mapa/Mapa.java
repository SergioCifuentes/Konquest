/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.mapa;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class Mapa {
    private String id;
    private Casilla[][] casillas;
    private boolean alAzar =false;
    private int numeroPlanetasNeutrales;
    private boolean mapaCiego=false;
    private boolean acumular=false;
    private ConfiguracionNeutrales confiNeutrales;
    private ArrayList<Planeta> planetas;
    private ArrayList<Planeta> planetasNeutrales;
    private ArrayList<Jugador> jugadores;
    private Integer finalizado;

    public String getId() {
        return id;
    }

    public void setCasillas(Casilla[][] casillas) {
        this.casillas = casillas;
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }

    public boolean isAlAzar() {
        return alAzar;
    }

    public int getPlanetasNeutrales() {
        return numeroPlanetasNeutrales;
    }

    public boolean isMapaCiego() {
        return mapaCiego;
    }

    public boolean isAcumular() {
        return acumular;
    }

    public ConfiguracionNeutrales getConfiNeutrales() {
        return confiNeutrales;
    }

    public Integer getFinalizado() {
        return finalizado;
    }
    
    
    
}
