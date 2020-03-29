/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego;

import java.util.ArrayList;
import konquest.Manejadores.Tablero.ControladorDeColores;
import konquest.Manejadores.Tablero.DibujadorDeTablero;
import konquest.Manejadores.Tablero.ManejadorDeCasillas;
import konquest.mapa.*;
import konquest.ui.FramePrincipal;

/**
 *
 * @author sergio
 */
public class ControladorJuego {

    public void iniciarJuego(Mapa mapa, FramePrincipal fp) {
        ManejadorDeCasillas mc = new ManejadorDeCasillas();
        ControladorDeRondas cdr= new ControladorDeRondas();
        ControlDeTurnos cdt =new ControlDeTurnos(mapa, cdr, fp);
        fp.setCdt(cdt);
        fp.setReplay(false);
        EleccionDePlaneta edp =new EleccionDePlaneta(fp, cdt);
        mapa.setCasillas(mc.generarCasillas(mapa.getDimension().width, mapa.getDimension().height,cdt,edp));
        ControladorDeColores cdc = new ControladorDeColores();
        cdc.generarColores(mapa.getJugadores());
        iniciarPosiciones(mapa);
        agregarNavesProducidasIniciales(mapa.getJugadores());
        DibujadorDeTablero ddt = new DibujadorDeTablero();
        ddt.dibujarTablero(mapa, fp.getJPanel());
    }
    
    public static void agregarNavesProducidasIniciales(ArrayList<Jugador> jugadores){
        for (int i = 0; i < jugadores.size(); i++) {
            for (int j = 0; j < jugadores.get(i).getPlanetas().size(); j++) {
                jugadores.get(i).getEstadisticas().aumentarNavesProducidas(jugadores.get(i).getPlanetas().get(j).getNaves());
            }
        }
    }
    
    
    public void iniciarPosiciones(Mapa mapa){
        ArrayList<Planeta> planetasAux=new ArrayList<>();
        for (int i = 0; i < mapa.getPlanetas().size(); i++) {
            planetasAux.add(mapa.getPlanetas().get(i));
        }
        
        for (int i = 0; i < mapa.getPlanetasNeutrales().size(); i++) {
            planetasAux.add(mapa.getPlanetasNeutrales().get(i));
        }
        ManejadorDeCasillas mc = new ManejadorDeCasillas();
        mc.generarPosicionesDePlanetas(mapa.getCasillas(),planetasAux,mapa);
    }
    
}
