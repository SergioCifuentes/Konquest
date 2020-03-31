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
import konquest.mapa.Jugador;
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
    private EnvioDeFlota envioFlota;
    private Jugador jugadorAnterior;
    private Planeta origen;
    private Jugador ordenador;
    private Planeta destino;
    private int naves;
    private int ronda;
    private int produccionAnterior;
    public Planeta getOrigen() {
        return origen;
    }

    public int getProduccionAnterior() {
        return produccionAnterior;
    }

    public void setProduccionAnterior(int produccionAnterior) {
        this.produccionAnterior = produccionAnterior;
    }

    public int getNaves() {
        return naves;
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

    public EnvioDeFlota getEnvioFlota() {
        return envioFlota;
    }

    public void setEnvioFlota(EnvioDeFlota envioFlota) {
        this.envioFlota = envioFlota;
    }

    public Jugador getJugadorAnterior() {
        return jugadorAnterior;
    }

    public void setJugadorAnterior(Jugador jugadorAnterior) {
        this.jugadorAnterior = jugadorAnterior;
    }

    public Jugador getOrdenador() {
        return ordenador;
    }

    public void setOrdenador(Jugador ordenador) {
        this.ordenador = ordenador;
    }


    
    
    
    public EventoEnvio(int tipo, Planeta origen, Planeta destino, int naves,int ronda,Jugador ordenador) {
        this.tipo = tipo;
    this.ronda=ronda;
        this.origen = origen;
        this.destino = destino;
        this.naves = naves;
        this.ordenador=ordenador;
    }
    
    public void appendTextConsola(JPanel panel,JTextPane textPane){
        
        switch (tipo) {
            case TIPO_CONQUISTA:
                TextoDeAcciones.appendToPane(textPane, ordenador.getNombre(), ordenador.getColor());
                TextoDeAcciones.appendToPane(textPane, " conquisto el Planeta ", Color.WHITE);
                TextoDeAcciones.appendToPane(textPane, destino.getNombre()+"\n", destino.getOwner().getColor());
                break;
            
            case TIPO_RENFUERZOS:
                TextoDeAcciones.appendToPane(textPane, "Renfuerzos llegaron al Planeta ", Color.WHITE);
                TextoDeAcciones.appendToPane(textPane, destino.getNombre()+"\n", destino.getOwner().getColor());
                TextoDeAcciones.appendToPane(textPane, " desde ", Color.WHITE);
                TextoDeAcciones.appendToPane(textPane, ordenador.getNombre(), ordenador.getColor());
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
                TextoDeAcciones.appendToPane(textPane, ordenador.getNombre()+"\n", ordenador.getColor());
                break;
        }
    }
}
