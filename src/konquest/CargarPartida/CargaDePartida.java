/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.CargarPartida;

import java.util.ArrayList;
import konquest.Manejadores.Juego.ControlDeTurnos;
import konquest.Manejadores.Juego.ControladorDeRondas;
import static konquest.Manejadores.Juego.ControladorJuego.agregarNavesProducidasIniciales;
import konquest.Manejadores.Juego.EleccionDePlaneta;
import konquest.Manejadores.Juego.Objetos.EnvioDeFlota;
import konquest.Manejadores.Juego.Objetos.Ronda;
import konquest.Manejadores.Tablero.ControladorDeColores;
import konquest.Manejadores.Tablero.DibujadorDeTablero;
import konquest.Manejadores.Tablero.Distancias;
import konquest.Manejadores.Tablero.ManejadorDeCasillas;
import konquest.Replay.Objetos.ComponenteReplay;
import konquest.Replay.Objetos.EnvioReplay;
import konquest.Replay.Objetos.PartidaInicial;
import konquest.mapa.Casilla;
import konquest.mapa.Jugador;
import konquest.mapa.Mapa;
import konquest.mapa.Planeta;
import konquest.mapa.PlanetaNeutral;
import konquest.ui.FramePrincipal;

/**
 *
 * @author sergio
 */
public class CargaDePartida {

    private Mapa mapa;

    public void empezarCarga(Mapa mapa, ComponenteReplay componentes, FramePrincipal fp, int ronda,boolean online) {
        this.mapa = mapa;
        ManejadorDeCasillas mc = new ManejadorDeCasillas();
        ControladorDeColores cdc = new ControladorDeColores();
        cdc.generarColores(mapa.getJugadores());
        
        ControladorDeRondas cdr = new ControladorDeRondas();
        ControlDeTurnos cdt=null;
        if (online) {
            cdt= new ControlDeTurnos(mapa, cdr, fp, null, null);
        }else{
         cdt= new ControlDeTurnos(mapa, cdr, fp);   
        }
         
        fp.setCdt(cdt);
        fp.setReplay(false);
        EleccionDePlaneta edp = new EleccionDePlaneta(fp, cdt);
        mapa.setCasillas(mc.generarCasillas(mapa.getDimension().width, mapa.getDimension().height, cdt, edp));
        for (int i = 0; i < mapa.getJugadores().size(); i++) {
            int aux = mapa.getJugadores().get(i).getPlanetas().size();
            for (int j = 0; j < aux; j++) {
                mapa.getJugadores().get(i).getPlanetas().remove(0);
            }

        }
        int aux = mapa.getPlanetas().size();
        for (int i = 0; i < aux; i++) {
            mapa.getPlanetas().remove(0);
        }
        aux = mapa.getPlanetasNeutrales().size();
        for (int i = 0; i < aux; i++) {
            mapa.getPlanetasNeutrales().remove(0);
        }
        for (int i = 0; i < componentes.getPartidas().size(); i++) {
            componentes.getPartidas().get(i).restar();
            Planeta planeta;
            if (componentes.getPartidas().get(i).getNombreJugador().equals(PartidaInicial.NEUTRAL)) {
                planeta = crearPlanetaNeutral(componentes.getPartidas().get(i));
                planeta.setPi(componentes.getPartidas().get(i));
            } else {
                planeta = crearPlaneta(componentes.getPartidas().get(i));
                planeta.setPi(componentes.getPartidas().get(i));
                getJugador(componentes.getPartidas().get(i).getNombreJugador()).agregarPlaneta(planeta);
            }
            mapa.getCasillas()[componentes.getPartidas().get(i).getColumna()][componentes.getPartidas().get(i).getFila()].setPlaneta(planeta, mapa);

        }
        agregarNavesProducidasIniciales(mapa.getJugadores());
        cdt.getCdf().GuardarEnvioPorTurno(convertitEnviosReplayAEnvios(componentes.getEnvios()));
        
        for (int i = 0; i < ronda; i++) {
            cdt.getCdf().restarEnvios(cdt);
            cdr.terminarRonda(cdt.getCdf(), cdt);
            
        }
        if (!online) {
           cdt.empezarTurnos();
        }
        
        DibujadorDeTablero ddt = new DibujadorDeTablero();
        ddt.dibujarTablero(mapa, fp.getJPanel());
        
    
   
    }
    
    
        public Planeta crearPlaneta(PartidaInicial pi) {
        Object[] atributos = new Object[4];
        atributos[0] = "\"" + pi.getNombrePlaneta() + "\"";
        atributos[1] = pi.getNaves();
        atributos[2] = pi.getProduccion();
        atributos[3] = pi.getPorcentajeMuertes();
        Planeta pl = new Planeta(atributos);
        pl.setOwner(getJugador(pi.getNombreJugador()));
        mapa.getPlanetas().add(pl);
        return pl;
    }

    public PlanetaNeutral crearPlanetaNeutral(PartidaInicial pi) {
        Object[] atributos = new Object[4];
        atributos[0] = "\"" + pi.getNombrePlaneta() + "\"";
        atributos[1] = pi.getNaves();
        atributos[2] = pi.getProduccion();
        atributos[3] = pi.getPorcentajeMuertes();
        PlanetaNeutral pl = new PlanetaNeutral(atributos);
        mapa.getPlanetasNeutrales().add(pl);
        return pl;
    }

    public Jugador getJugador(String nombre) {
        for (int i = 0; i < mapa.getJugadores().size(); i++) {
            if (mapa.getJugadores().get(i).getNombre().equals(nombre)) {
                return mapa.getJugadores().get(i);
            }
        }

        return null;
    }

    public ArrayList<EnvioDeFlota> convertitEnviosReplayAEnvios(ArrayList<EnvioReplay> envios) {
        ArrayList<EnvioDeFlota> enviosFlota = new ArrayList<>();
        for (int i = 0; i < envios.size(); i++) {

            Planeta origen = obtenerPlaneta(envios.get(i).getOrigen());
            Casilla casillaOrigen = obtenerCasilla(origen);
            Ronda ronda = new Ronda(envios.get(i).getRonda());
            Casilla casillaDestino = obtenerCasilla(obtenerPlaneta(envios.get(i).getDestino()));
            System.out.println(envios.get(i).getOrigen() + "..........." + origen.getOwner());
            enviosFlota.add(new EnvioDeFlota(casillaOrigen,
                    casillaDestino, origen.getOwner(), envios.get(i).getNaves(),
                    ronda,
                    Distancias.calcularDistancia(casillaOrigen, casillaDestino) + ronda.getNumero()));

        }
        return enviosFlota;
    }

    public Casilla obtenerCasilla(Planeta p) {
        for (int i = 0; i < mapa.getCasillas().length; i++) {
            for (int j = 0; j < mapa.getCasillas()[0].length; j++) {
                if (mapa.getCasillas()[i][j].getPlaneta() != null) {

                    if (mapa.getCasillas()[i][j].getPlaneta().getNombre().equals(p.getNombre())) {
                        return mapa.getCasillas()[i][j];
                    }
                }
            }
        }
        return null;
    }

    public Planeta obtenerPlaneta(String nombre) {
        for (int i = 0; i < mapa.getPlanetas().size(); i++) {
            if (mapa.getPlanetas().get(i).getNombre().equals(nombre)) {
                return mapa.getPlanetas().get(i);
            }
        }
        for (int i = 0; i < mapa.getPlanetasNeutrales().size(); i++) {
            if (mapa.getPlanetasNeutrales().get(i).getNombre().equals(nombre)) {
                return mapa.getPlanetasNeutrales().get(i);
            }
        }
        return null;
    }
}
