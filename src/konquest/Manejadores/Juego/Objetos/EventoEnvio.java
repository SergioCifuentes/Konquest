/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego.Objetos;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import konquest.Manejadores.Tablero.ControladorDeColores;
import konquest.contrladoresUI.TextoDeAcciones;
import konquest.mapa.Planeta;

/**
 *
 * @author sergio
 */
public class EventoEnvio {
    private int tipo;
    /*1.conquista
      2.defensa
      3.renfuerzos
    */
    public static final int TIPO_CONQUISTA=1;
    public static final int TIPO_DEFENSA=2;
    public static final int TIPO_RENFUERZOS=3;
    
    private Planeta origen;
    private Planeta destino;
    private int naves;
    private int ronda;
    public Planeta getOrigen() {
        return origen;
    }

    public Planeta getDestino() {
        return destino;
    }

    public int getRonda() {
        return ronda;
    }

    public int getTipo() {
        return tipo;
    }



    
    
    public EventoEnvio(int tipo, Planeta origen, Planeta destino, int naves,int ronda) {
        this.tipo = tipo;
    this.ronda=ronda;
        this.origen = origen;
        this.destino = destino;
        this.naves = naves;
    }
    
    public void appendTextConsola(JPanel panel,JTextPane textPane){
        
        switch (tipo) {
            case TIPO_CONQUISTA:
                TextoDeAcciones.appendToPane(textPane, origen.getOwner().getNombre(), origen.getOwner().getColor());
                TextoDeAcciones.appendToPane(textPane, " conquisto el Planeta ", Color.WHITE);
                TextoDeAcciones.appendToPane(textPane, destino.getNombre()+"\n", destino.getOwner().getColor());
                break;
            
            case TIPO_RENFUERZOS:
                TextoDeAcciones.appendToPane(textPane, "Renfuerzos llegaron al Planeta ", Color.WHITE);
                TextoDeAcciones.appendToPane(textPane, destino.getNombre()+"\n", destino.getOwner().getColor());
                TextoDeAcciones.appendToPane(textPane, " desde ", Color.WHITE);
                TextoDeAcciones.appendToPane(textPane, origen.getOwner().getNombre(), origen.getOwner().getColor());
                TextoDeAcciones.appendToPane(textPane, "("+naves+"naves)\n", Color.WHITE);
                
                
                break;
                
            case TIPO_DEFENSA:
                 TextoDeAcciones.appendToPane(textPane, "Planeta ", Color.WHITE);
                 if (destino.getOwner()==null) {
                    TextoDeAcciones.appendToPane(textPane, destino.getNombre(), ControladorDeColores.NEUTRALES);
                }else{
                    TextoDeAcciones.appendToPane(textPane, destino.getNombre(), destino.getOwner().getColor());
                 }
                
                TextoDeAcciones.appendToPane(textPane, " se defendio ante el ataque de ", Color.WHITE);
                TextoDeAcciones.appendToPane(textPane, origen.getOwner().getNombre()+"\n", origen.getOwner().getColor());
                break;
        }
    }
}
