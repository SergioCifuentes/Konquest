/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Online;

import konquest.Manejadores.Juego.ControlDeTurnos;
import konquest.Manejadores.Tablero.DibujadorDeTablero;
import konquest.Sockets.ObservableHost;
import konquest.mapa.Jugador;
import konquest.mapa.Mapa;
import konquest.ui.FramePrincipal;

/**
 *
 * @author sergio
 */
public class Host {
    private Jugador jugador;
    private Mapa mapa;
    private FramePrincipal fp;
    private ObservableHost observableHost;
private ControlDeTurnos cdt;
    public Host(Mapa mapa, FramePrincipal fp,ControlDeTurnos cdt) {
        this.mapa = mapa;
        this.fp = fp;
        this.cdt=cdt;
    }

    public void recibirTextoFlotas(String txt){
        EnviosOnline en = new EnviosOnline();
    cdt.recibirEnviosOnline(en.convertirTextAEnvios(txt,mapa,cdt.getCdr().getRondaActual()));
        
        
    }
    
    public Jugador getJugador() {
        return jugador;
    }

    public ObservableHost getObservableHost() {
        return observableHost;
    }

    public void setObservableHost(ObservableHost observableHost) {
        this.observableHost = observableHost;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public FramePrincipal getFp() {
        return fp;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setCdt(ControlDeTurnos cdt) {
        this.cdt = cdt;
    }
    
    
    
    
    
    public void mostrarConectado(){
         fp.getJTextPane().setText("Pareja Encontrada");
        DibujadorDeTablero ddt = new DibujadorDeTablero();
        ddt.dibujarTablero(mapa, fp.getJPanel());
        cdt.empezarOnline();
    }
}
