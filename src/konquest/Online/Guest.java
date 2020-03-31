/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Online;

import konquest.Manejadores.Juego.ControlDeTurnos;
import konquest.Sockets.ObservableGuess;
import konquest.mapa.Jugador;
import konquest.mapa.Mapa;
import konquest.ui.FramePrincipal;

/**
 *
 * @author sergio
 */
public class Guest {

    private Jugador jugador;
    private Mapa mapa;
    private FramePrincipal fp;
    private ControlDeTurnos cdt;
    private ObservableGuess observableGuess;

    public void recibirTextoFlotas(String txt) {
        EnviosOnline en = new EnviosOnline();
        cdt.recibirEnviosOnline(en.convertirTextAEnvios(txt,mapa,cdt.getCdr().getRondaActual()));
    }

    public ObservableGuess getObservableGuess() {
        return observableGuess;
    }

    public void setObservableGuess(ObservableGuess observableGuess) {
        this.observableGuess = observableGuess;
    }

    public Guest(Jugador jugador, Mapa mapa, FramePrincipal fp, ControlDeTurnos cdt) {
        this.jugador = jugador;
        this.mapa = mapa;
        this.fp = fp;
        this.cdt = cdt;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public FramePrincipal getFp() {
        return fp;
    }

    public void empezarJuego() {
        cdt.empezarOnline();
    }

}
