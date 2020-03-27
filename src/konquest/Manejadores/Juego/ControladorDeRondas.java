/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego;

import java.util.ArrayList;
import konquest.Manejadores.Juego.Objetos.ControlDeFlotas;
import konquest.Manejadores.Juego.Objetos.Ronda;

/**
 *
 * @author sergio
 */
public class ControladorDeRondas {
    private ArrayList<Ronda> rondas;
    private Ronda rondaActual;
    
    
    public ControladorDeRondas(){
        rondaActual=new Ronda(1);
        rondas=new ArrayList<>();
        
}
    
    public void terminarRonda(ControlDeFlotas cdf,ControlDeTurnos cdt){
        ManjeadorDeProducciones manjeadorDeProducciones = new ManjeadorDeProducciones();
        manjeadorDeProducciones.producirNaves(cdt.getMapa().getTodosLosPlanetas(),cdt.getMapa().isAcumular());
        rondas.add(rondaActual);
        rondaActual=new Ronda(rondaActual.getNumero()+1);
        cdf.realizarEnviosDeRonda(cdt);
    }

    public ArrayList<Ronda> getRondas() {
        return rondas;
    }

    public Ronda getRondaActual() {
        return rondaActual;
    }
    
    
}
