/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego;

import java.util.ArrayList;
import konquest.Manejadores.Juego.Objetos.EnvioDeFlota;
import konquest.mapa.Jugador;
import konquest.ui.FramePrincipal;

/**
 *
 * @author sergio
 */
public class ControlDeTurnos {
    private Jugador jugadorEnTurnoActual;
    private ArrayList<Jugador> jugadoresEnOrden;
    private ControladorDeRondas cdr;
    private FramePrincipal fp;
    public Jugador getJugadorEnTurnoActual() {
        return jugadorEnTurnoActual;
    }

    
    
    public ControlDeTurnos(ArrayList<Jugador> jugadors,ControladorDeRondas cdr,FramePrincipal fp){
        this.cdr=cdr;
        this.fp=fp;
        this.jugadoresEnOrden=jugadors;
        jugadorEnTurnoActual=jugadors.get(0);
        mandarInfoAFram();
    }
    
    public void terminarTurno(ArrayList<EnvioDeFlota> flotas){
        
    }
    private void mandarInfoAFram(){
        fp.obtenerInfoDeTurno(jugadorEnTurnoActual, cdr.getRondaActual());
    }
}
