/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import konquest.Manejadores.Juego.Objetos.ControlDeFlotas;
import konquest.Manejadores.Juego.Objetos.Ronda;
import konquest.ui.Estadisticas;

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
        ManejadorDeProducciones manjeadorDeProducciones = new ManejadorDeProducciones();
        manjeadorDeProducciones.producirNaves(cdt.getMapa().getTodosLosPlanetas(),cdt.getMapa().isAcumular());
        rondas.add(rondaActual);
        rondaActual=new Ronda(rondaActual.getNumero()+1);
        cdf.realizarEnviosDeRonda(cdt);
        VerificacionFinalizado verificacionFinalizado= new VerificacionFinalizado();
        verificacionFinalizado.verificarJugadoresVivos(cdt.getMapa().getJugadores(), cdf.getEnviosEnProceso());
        if (verificacionFinalizado.verificarGanado(cdt.getMapa(),rondaActual.getNumero())) {
            cdt.getFramePrincipal().MarcarGanador();
                Estadisticas estadisticas= new Estadisticas(cdt.getFramePrincipal(), true, cdt.getMapa().getJugadores(), verificacionFinalizado.getGanadores(), cdf.getEnviosEnProceso(),cdf.getEnviosRealizados());
                estadisticas.setCdt(cdt);
                estadisticas.setVisible(true);
           
        }
    }

    public ArrayList<Ronda> getRondas() {
        return rondas;
    }

    public Ronda getRondaActual() {
        return rondaActual;
    }
    
    
}
