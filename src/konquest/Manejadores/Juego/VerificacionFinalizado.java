/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego;

import java.util.ArrayList;
import konquest.Manejadores.Juego.Objetos.EnvioDeFlota;
import konquest.mapa.Jugador;
import konquest.mapa.Mapa;


/**
 *
 * @author sergio
 */
public class VerificacionFinalizado {
    private ArrayList<Jugador> ganadores;

    public VerificacionFinalizado() {
        ganadores=new ArrayList<>();
    }
    
    
    
    public boolean verificarGanado(Mapa mapa,int rondaActual){
        boolean verificado =false;
        int vivos=0;
        for (int i = 0; i < mapa.getJugadores().size(); i++) {
            if (mapa.getJugadores().get(i).isVivo()) {
                ganadores.add(mapa.getJugadores().get(i));
                System.out.println("vivo: "+mapa.getJugadores().get(i));
                vivos++;
            }
        }
        System.out.println("=====");
        System.out.println("&&&&"+ganadores.get(0).getPlanetas().size()+"&&&"+mapa.getPlanetas().size()+" "+mapa.getPlanetasNeutrales().size());
        if (vivos>1) {
            ganadores.removeAll(ganadores);
        }
        
        if (vivos==1&&ganadores.get(0).getPlanetas().size()==mapa.getPlanetas().size()+mapa.getPlanetasNeutrales().size()) {
            verificado=true;
        }else if(mapa.getFinalizado()!=null){
            if (mapa.getFinalizado()==rondaActual) {
                obtenerGanadoresMasPlanetas(mapa.getJugadores());
                verificado=true;
                
            }
        }
        
        return verificado;
    }

    public ArrayList<Jugador> getGanadores() {
        return ganadores;
    }
    
    
    public void obtenerGanadoresMasPlanetas(ArrayList<Jugador> jugadores){
        ganadores.removeAll(ganadores);
        ganadores.add(jugadores.get(0));
        for (int i = 1; i < jugadores.size(); i++) {
            if (jugadores.get(i).getPlanetas().size()>ganadores.get(0).getPlanetas().size()) {
                ganadores.removeAll(ganadores);
                ganadores.add(jugadores.get(i));
            }else if (jugadores.get(i).getPlanetas().size()==ganadores.get(0).getPlanetas().size()) {
                ganadores.add(jugadores.get(i));
            }
        }
    }
    
    public void verificarJugadoresVivos(ArrayList<Jugador> jugadores,ArrayList<EnvioDeFlota> enviosPendientes){
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).isVivo()) {

                if (EstadisticasJugador.verificarNavesExistentes(jugadores.get(i), enviosPendientes)==0) {
                    jugadores.get(i).setVivo(false);
                }
                System.out.println("");
            }
        }
    }
}
