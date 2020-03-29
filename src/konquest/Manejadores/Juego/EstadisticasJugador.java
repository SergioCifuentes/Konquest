/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego;

import java.util.ArrayList;
import konquest.Manejadores.Juego.Objetos.EnvioDeFlota;
import konquest.mapa.Jugador;

/**
 *
 * @author sergio
 */
public class EstadisticasJugador {
    
    private int navesProducidas;
    private Jugador jugador;
    public EstadisticasJugador(Jugador jugador) {
        navesProducidas=0;
        this.jugador=jugador;
    }
    
    public int obtenerProducciones(){
        int producciones=0;
        for (int i = 0; i < jugador.getPlanetas().size(); i++) {
            producciones+=jugador.getPlanetas().get(i).getProduccion();
        }
        return producciones;
    }
    public void aumentarNavesProducidas(int naves){
        navesProducidas+=naves;
    }

    public void disminuirNavesProducidas(int naves){
        navesProducidas-=naves;
    }
    
    public int getNavesProducidas() {
        return navesProducidas;
    }
    
    public static int verificarNavesExistentes(Jugador jugador,ArrayList<EnvioDeFlota> flotasPendientes){
        int naves=0;
        for (int i = 0; i < jugador.getPlanetas().size(); i++) {
            naves+=jugador.getPlanetas().get(i).getNaves();
        }
        for (int i = 0; i < flotasPendientes.size(); i++) {
            
            
            if (flotasPendientes.get(i).getOrdenador().equals(jugador)) {
                naves+=flotasPendientes.get(i).getNaves();
            }
        }
        return naves;
    } 
    
    public int obtenerEnvios(ArrayList<EnvioDeFlota> pendiente,ArrayList<EnvioDeFlota> realizados){
        int envios=0;
        for (int i = 0; i < pendiente.size(); i++) {
            if (pendiente.get(i).getOrdenador().equals(jugador)) {
                envios++;
            }
        }
        for (int i = 0; i < realizados.size(); i++) {
            if ( realizados.get(i).getOrdenador().equals(jugador)) {
                envios++;
            }
        }
        return envios;
    }
}
