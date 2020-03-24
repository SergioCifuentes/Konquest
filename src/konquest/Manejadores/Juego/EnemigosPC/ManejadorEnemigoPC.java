/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego.EnemigosPC;

import java.util.ArrayList;
import konquest.Manejadores.Juego.Objetos.EnvioDeFlota;
import konquest.mapa.*;

/**
 *
 * @author sergio
 */
public class ManejadorEnemigoPC {
    private Jugador jugador;
    private Mapa mapa;
    public ManejadorEnemigoPC(Mapa mapa,Jugador jugador){
        this.jugador=jugador;
        this.mapa=mapa;
    }
    
    public ArrayList<EnvioDeFlota> realizarAtaques(){
        return null;
    }
}
