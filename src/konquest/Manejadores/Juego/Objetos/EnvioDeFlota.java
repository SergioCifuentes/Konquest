/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego.Objetos;

import konquest.mapa.Casilla;
import konquest.mapa.Jugador;

/**
 *
 * @author sergio
 */
public class EnvioDeFlota {
    private Casilla origen;
    private Casilla destino;
    private Jugador ordenador;
    private int naves;
    private Ronda ronda;
    private int turnoDestino;

    public Ronda getRonda() {
        return ronda;
    }

    
    
    public EnvioDeFlota(Casilla origen, Casilla destino, Jugador ordenador, int naves, Ronda ronda, int turnoDestino) {
        this.origen = origen;
        this.destino = destino;
        this.ordenador = ordenador;
        this.naves = naves;
        this.ronda = ronda;
        this.turnoDestino = turnoDestino;
    }

    public Casilla getOrigen() {
        return origen;
    }

    public void setOrdenador(Jugador ordenador) {
        this.ordenador = ordenador;
    }

    public Casilla getDestino() {
        return destino;
    }

    public Jugador getOrdenador() {
        return ordenador;
    }

    public int getNaves() {
        return naves;
    }


   

    public int getTurnoDestino() {
        return turnoDestino;
    }
    
    
    
    
}
