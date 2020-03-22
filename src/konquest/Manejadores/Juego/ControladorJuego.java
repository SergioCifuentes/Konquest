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
        ControlDeTurnos cdt =new ControlDeTurnos(mapa.getJugadores(), cdr, fp);
        mapa.setCasillas(mc.generarCasillas(mapa.getDimension().width, mapa.getDimension().height));
        ControladorDeColores cdc = new ControladorDeColores();
        cdc.generarColores(mapa.getJugadores());
        iniciarPosiciones(mapa);
        
        DibujadorDeTablero ddt = new DibujadorDeTablero();
        ddt.dibujarTablero(mapa, fp.getJPanel());
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
