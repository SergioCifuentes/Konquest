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
import konquest.Manejadores.Tablero.DibujadorDeTablero;
import konquest.Manejadores.Tablero.Distancias;
import konquest.Manejadores.Tablero.ManejadorDeCasillas;
import konquest.mapa.Jugador;
import konquest.mapa.Mapa;
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

    public Jugador getJugadorEnTurnoActual() {
        return jugadorEnTurnoActual;
    }
    
    public ControladorDeRondas getCdr() {
        return cdr;
    }
    public Mapa getMapa(){
        return mapa;
    }
    
    public ControlDeTurnos(Mapa mapa, ControladorDeRondas cdr, FramePrincipal fp) {
        this.cdr = cdr;
        this.fp = fp;
        this.mapa = mapa;
        cdf=new ControlDeFlotas();
        flotasDeTurno = new ArrayList<>();
        this.jugadoresEnOrden = mapa.getJugadores();
        jugadorEnTurnoActual = mapa.getJugadores().get(0);
        empezarTurnos();
        mandarInfoAFram();
    }
    
    public void terminarTurno(ArrayList<EnvioDeFlota> flotas) {
        System.out.println(flotas);
        if (!(flotas == null) && !(flotas.isEmpty())) {
            cdf.GuardarEnvioPorTurno(flotas);
        }
        flotasDeTurno = new ArrayList<>();
        for (int i = 0; i < jugadoresEnOrden.size(); i++) {
            if (i == jugadoresEnOrden.size() - 1) {
                cdr.terminarRonda(cdf,this);
                jugadorEnTurnoActual = jugadoresEnOrden.get(0);
                empezarTurnos();
                break;
            } else {
                if (jugadoresEnOrden.get(i).equals(jugadorEnTurnoActual)) {
                    jugadorEnTurnoActual = jugadoresEnOrden.get(i + 1);
                    empezarTurnos();
                    break;
                }
            }
        }
    }

    public void empezarTurnos() {
        if (jugadorEnTurnoActual.isVivo()) {
            
            if (jugadorEnTurnoActual.getTipo() == Jugador.TIPO_HUMANO) {
                mandarInfoAFram();
            } else {
                ManejadorEnemigoPC mepc = new ManejadorEnemigoPC(mapa, jugadorEnTurnoActual);
                terminarTurno(mepc.realizarAtaques());
                redibujarTablero();
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
    
    public void terminarEnvio() {
        EnvioDeFlota edf = new EnvioDeFlota(fp.getPrimerCasilla(), fp.getSegundaCasilla(),
                 jugadorEnTurnoActual, fp.getNaves(), cdr.getRondaActual(),
                cdr.getRondaActual().getNumero() + Distancias.calcularDistancia(fp.getPrimerCasilla(), fp.getSegundaCasilla()));
        fp.getPrimerCasilla().getPlaneta().restarNaves(fp.getNaves());
        
        
        redibujarTablero();
        flotasDeTurno.add(edf);
        fp.pedirPrimerPlaneta();
    }
    
    public void redibujarTablero(){
        ManejadorDeCasillas manejadorDeCasillas = new ManejadorDeCasillas();
        manejadorDeCasillas.reDibujarCasillas(mapa.getCasillas());
        DibujadorDeTablero ddt = new DibujadorDeTablero();
        ddt.dibujarTablero(mapa, fp.getJPanel());
        
    }
    
}
