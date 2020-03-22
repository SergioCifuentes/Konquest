/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego.Objetos;

import konquest.mapa.Jugador;
import konquest.mapa.Planeta;

/**
 *
 * @author sergio
 */
public class EnvioDeFlota {
    private Planeta origen;
    private Planeta destino;
    private Jugador ordenador;
    private int naves;
    private float produccion;
    private Ronda ronda;
    private int turnoDestino;

    public Planeta getOrigen() {
        return origen;
    }

    public Planeta getDestino() {
        return destino;
    }

    public Jugador getOrdenador() {
        return ordenador;
    }

    public int getNaves() {
        return naves;
    }

    public float getProduccion() {
        return produccion;
    }

   

    public int getTurnoDestino() {
        return turnoDestino;
    }
    
    
    
    
}
