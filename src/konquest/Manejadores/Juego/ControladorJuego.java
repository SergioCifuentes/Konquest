/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego;

import java.util.ArrayList;
import java.util.Random;
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
        if (mapa.isAlAzar()) {
            generadoresDeAzar(mapa);
        }
        
        iniciarPosiciones(mapa);
        agregarNavesProducidasIniciales(mapa.getJugadores());
        DibujadorDeTablero ddt = new DibujadorDeTablero();
        ddt.dibujarTablero(mapa, fp.getJPanel());
    }
    
    
    
    public void generadoresDeAzar(Mapa mapa){
        int aux=mapa.getPlanetas().size();
        for (int i = 0; i < aux; i++) {
            mapa.getPlanetas().remove(0);
        }
        aux=mapa.getPlanetasNeutrales().size();
        for (int i = 0; i < aux; i++) {
            mapa.getPlanetasNeutrales().remove(0);
        }
        
        for (int i = 0; i <mapa.getJugadores().size(); i++) {
            aux=mapa.getJugadores().get(i).getPlanetas().size();
            for (int j = 0; j < aux; j++) {
                mapa.getJugadores().get(i).getPlanetas().remove(0);
            }
            
        }
        for (int i = 0; i < mapa.getJugadores().size(); i++) {
            Planeta planeta= new Planeta(generarAtributosDePlanetaAlAzar());
            mapa.getJugadores().get(i).agregarPlaneta(planeta);
            planeta.setOwner(mapa.getJugadores().get(i));
            mapa.getPlanetas().add(planeta);
        }
        for (int i = 0; i < mapa.getNumeroPlanetasNeutrales(); i++) {
            PlanetaNeutral planetaNeutral= new PlanetaNeutral(generarAtributosDePlanetaAlAzar());
            mapa.getPlanetasNeutrales().add(planetaNeutral);
        }
        
        
    }
    
    
    
    String [] abecedario = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
"K", "L", "M","N","O","P","Q","R","S","T","U","V","W", "X","Y","Z" };
    private ArrayList<String> nombresPlanetas;
    public Object[] generarAtributosDePlanetaAlAzar(){
        Object[] atrObjects=new Object[4];
        if (nombresPlanetas==null) {
            nombresPlanetas=new ArrayList<>();
        }
        String nombre="A";
        if (nombresPlanetas.size()>=26) {
            boolean aux=true;
            while (aux) {
                nombre=abecedario[(int)(Math.random()*26)]+abecedario[(int)(Math.random()*26)];
                aux=false;
                for (int i = 0; i < nombresPlanetas.size(); i++) {
                    if (nombre.equals(nombresPlanetas.get(i))) {
                        aux=true;
                        break;
                    }
                }
            }
            nombresPlanetas.add(nombre);
        }else{
            boolean aux=true;
            while (aux) {
                nombre=abecedario[(int)(Math.random()*26)];
                aux=false;
                for (int i = 0; i < nombresPlanetas.size(); i++) {
                    if (nombre.equals(nombresPlanetas.get(i))) {
                        aux=true;
                        break;
                    }
                }
            }
            nombresPlanetas.add(nombre);
        }
        atrObjects[0]="\""+nombre+"\"";
        atrObjects[1]=(int) (Math.random() * 20 + 5);
        atrObjects[2]=(int) (Math.random() * 5 + 5);
        double min = 0.999;
        double max = 0.01;
        Random r = new Random();
        String sDou = String.format("%.3f", min + (max - min) * r.nextDouble());
        Double s = Double.valueOf(sDou);
        atrObjects[3]= s;
        
        return atrObjects;
    }
    
    
    public static void agregarNavesProducidasIniciales(ArrayList<Jugador> jugadores){
        for (int i = 0; i < jugadores.size(); i++) {
            for (int j = 0; j < jugadores.get(i).getPlanetas().size(); j++) {
                jugadores.get(i).getEstadisticas().aumentarNavesProducidas(jugadores.get(i).getPlanetas().get(j).getNaves());
            }
        }
    }
    
    
    public static void iniciarPosiciones(Mapa mapa){
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
