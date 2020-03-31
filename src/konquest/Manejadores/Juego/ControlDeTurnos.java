/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego;

import java.util.ArrayList;
import konquest.Manejadores.Juego.EnemigosPC.ManejadorEnemigoPC;
import konquest.Manejadores.Juego.Objetos.ControlDeFlotas;
import konquest.Manejadores.Juego.Objetos.EnvioDeFlota;
import konquest.Manejadores.Juego.Objetos.EventoEnvio;
import konquest.Manejadores.Tablero.DibujadorDeTablero;
import konquest.Manejadores.Tablero.Distancias;
import konquest.Manejadores.Tablero.ManejadorDeCasillas;
import konquest.Online.EnviosOnline;
import konquest.Online.Guest;
import konquest.Online.Host;
import konquest.mapa.Jugador;
import konquest.mapa.Mapa;
import konquest.ui.Estadisticas;
import konquest.ui.FramePrincipal;

/**
 *
 * @author sergio
 */
public class ControlDeTurnos {

    private Jugador jugadorEnTurnoActual;
    private ArrayList<Jugador> jugadoresEnOrden;
    private Mapa mapa;
    private ControladorDeRondas cdr;
    private FramePrincipal fp;
    private ArrayList<EnvioDeFlota> flotasDeTurno;
    private ControlDeFlotas cdf;
    private boolean mensajeRecibido = false;

    public Jugador getJugadorEnTurnoActual() {
        return jugadorEnTurnoActual;
    }

    public ControladorDeRondas getCdr() {
        return cdr;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public ControlDeFlotas getCdf() {
        return cdf;
    }

    public ControlDeTurnos(Mapa mapa, ControladorDeRondas cdr, FramePrincipal fp) {
        this.cdr = cdr;
        this.fp = fp;
        this.mapa = mapa;
        cdf = new ControlDeFlotas();
        flotasDeTurno = new ArrayList<>();
        this.jugadoresEnOrden = mapa.getJugadores();
        jugadorEnTurnoActual = mapa.getJugadores().get(0);
        empezarTurnos();
        mandarInfoAFram();
    }

    private Host host;
    private Guest guest;

    public ControlDeTurnos(Mapa mapa, ControladorDeRondas cdr, FramePrincipal fp, Host host, Guest guest) {
        this.cdr = cdr;
        this.fp = fp;
        this.mapa = mapa;
        this.host = host;
        this.guest = guest;
        cdf = new ControlDeFlotas();
        flotasDeTurno = new ArrayList<>();
        this.jugadoresEnOrden = mapa.getJugadores();

        if (host != null) {
            jugadorEnTurnoActual = host.getJugador();
        } else if (guest != null) {
            jugadorEnTurnoActual = guest.getJugador();
        }

    }

    public void eliminarFlotaDeTurno(EnvioDeFlota en){
        en.getOrigen().getPlaneta().recibirNavesAleadas(en.getNaves());
        flotasDeTurno.remove(en);
        redibujarTablero();
    }
    
    public void empezarOnline() {
        flotasDeTurno = new ArrayList<>();
        empezarTurnos();
        mandarInfoAFram();
    }

    public void terminarTurnoOnline() {
        ArrayList<EnvioDeFlota> flotasAMandar = new ArrayList<>();
        if (!(flotasDeTurno == null) && !(flotasDeTurno.isEmpty())) {
            cdf.GuardarEnvioPorTurno(flotasDeTurno);
            flotasAMandar = flotasDeTurno;
        }

        flotasDeTurno = new ArrayList<>();
        if (host != null) {
            for (int i = 0; i < jugadoresEnOrden.size(); i++) {
                if (jugadoresEnOrden.get(i).getTipo() != Jugador.TIPO_HUMANO) {
                    ManejadorEnemigoPC mepc = new ManejadorEnemigoPC(mapa, jugadoresEnOrden.get(i),cdr.getRondaActual());
                    ArrayList<EnvioDeFlota> flotasAux = mepc.realizarAtaques(cdf.obtenerEnviosPendientesDeJugador(jugadorEnTurnoActual));
                    if (!(flotasAux == null) && !(flotasAux.isEmpty())) {
                        cdf.GuardarEnvioPorTurno(flotasAux);
                        for (int j = 0; j < flotasAux.size(); j++) {
                            flotasAMandar.add(flotasAux.get(j));
                        }
                    }
                }
            }
        }
        mandarFlotasOnline(flotasAMandar);
        if (mensajeRecibido) {
            cdr.terminarRonda(cdf, this);
            if (!fp.isGanador()) {
                if (host!=null) {
                    jugadorEnTurnoActual = host.getJugador();
                }else{
                    jugadorEnTurnoActual= guest.getJugador();
                }
                    empezarTurnos();
                }
            esperando=false;
            mensajeRecibido=false;
        } else {
            esperarOponenteOnline();
        }

    }
    
    private boolean esperando = false;

    
    public void mandarFlotasOnline(ArrayList<EnvioDeFlota> envios){
        String text = EnviosOnline.pasarEnvioAOnline(envios);
        if (host!=null) {
            host.getObservableHost().mandarTexto(text);
        }else{
            guest.getObservableGuess().mandarTexto(text);
        }
        
    }
    public boolean isEsperando() {
        return esperando;
    }

    public void esperarOponenteOnline() {
        esperando = true;
        fp.esperarTurnoRival();
    }
    
    public void recibirEnviosOnline(ArrayList<EnvioDeFlota> envios){
        
        if (!(envios == null) && !(envios.isEmpty())) {
            cdf.GuardarEnvioPorTurno(envios);
            for (int i = 0; i < envios.size(); i++) {
                envios.get(i).getOrigen().getPlaneta().restarNaves(envios.get(i).getNaves());
            }
        }
        if (esperando) {
            cdr.terminarRonda(cdf, this);
            if (!fp.isGanador()) {
                if (host!=null) {
                    jugadorEnTurnoActual = host.getJugador();
                }else{
                    jugadorEnTurnoActual= guest.getJugador();
                }
                    
                    empezarTurnos();
                    
                }
            esperando=false;
            mensajeRecibido=false;
        }else{
            mensajeRecibido =true;
        }
        
        
        
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
        jugadorEnTurnoActual = guest.getJugador();
    }

    public void terminarTurno(ArrayList<EnvioDeFlota> flotas) {
        
        if (!(flotas == null) && !(flotas.isEmpty())) {
            cdf.GuardarEnvioPorTurno(flotas);
        }
        flotasDeTurno = new ArrayList<>();
        for (int i = 0; i < jugadoresEnOrden.size(); i++) {
            if (i == jugadoresEnOrden.size() - 1) {
                cdr.terminarRonda(cdf, this);
                if (!fp.isGanador()) {
                    jugadorEnTurnoActual = jugadoresEnOrden.get(0);
                    empezarTurnos();
                    break;
                }

            } else {
                if (jugadoresEnOrden.get(i).equals(jugadorEnTurnoActual)) {
                    jugadorEnTurnoActual = jugadoresEnOrden.get(i + 1);
                    empezarTurnos();
                    break;
                }
            }
        }
    }

    public void empezarTurnosOnline() {

    }

    public void empezarTurnos() {
        if (jugadorEnTurnoActual.getPlanetas().size() > 0) {
            if (jugadorEnTurnoActual.isVivo()) {

                if (jugadorEnTurnoActual.getTipo() == Jugador.TIPO_HUMANO) {
                    mandarInfoAFram();
                } else {
                    
                    ManejadorEnemigoPC mepc = new ManejadorEnemigoPC(mapa, jugadorEnTurnoActual,cdr.getRondaActual());
                    terminarTurno(mepc.realizarAtaques(cdf.obtenerEnviosPendientesDeJugador(jugadorEnTurnoActual)));
                    redibujarTablero();
                }
            } else {
                terminarTurno(null);
            }
        } else {
            terminarTurno(null);
        }

    }

    private void mandarInfoAFram() {
        fp.obtenerInfoDeTurno(jugadorEnTurnoActual, cdr.getRondaActual());
    }

    public void finalizarTurno() {
        terminarTurno(flotasDeTurno);
    }

    public void mostrarEstadistics() {
        Estadisticas estadisticas = new Estadisticas(fp, true, jugadoresEnOrden, cdf.getEnviosEnProceso(), cdf.getEnviosRealizados());
        estadisticas.setVisible(true);
    }

    public void terminarEnvio() {
        EnvioDeFlota edf = new EnvioDeFlota(fp.getPrimerCasilla(), fp.getSegundaCasilla(),
                jugadorEnTurnoActual, fp.getNaves(), cdr.getRondaActual(),
                cdr.getRondaActual().getNumero() + Distancias.calcularDistancia(fp.getPrimerCasilla(), fp.getSegundaCasilla()));
        fp.getPrimerCasilla().getPlaneta().restarNaves(fp.getNaves());

        redibujarTablero();
        flotasDeTurno.add(edf);
        fp.pedirPrimerPlaneta();
    }

    public ArrayList<EnvioDeFlota> getFlotasDeTurno() {
        return flotasDeTurno;
    }

    public void redibujarTablero() {
        ManejadorDeCasillas manejadorDeCasillas = new ManejadorDeCasillas();
        manejadorDeCasillas.reDibujarCasillas(mapa.getCasillas(), false);
        DibujadorDeTablero ddt = new DibujadorDeTablero();
        ddt.dibujarTablero(mapa, fp.getJPanel());

    }

    public FramePrincipal getFramePrincipal() {
        return fp;
    }
}
