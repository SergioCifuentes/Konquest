/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego.Objetos;

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

    public EventoEnvio(int tipo, Planeta origen, Planeta destino, int naves) {
        this.tipo = tipo;
    
        this.origen = origen;
        this.destino = destino;
        this.naves = naves;
    }
    
    public String getTextConsola(){
        switch (tipo) {
            case TIPO_CONQUISTA:
                return origen.getOwner().getNombre()+" conquisto el Planeta "+destino.getNombre()+"\n";
            
            case TIPO_RENFUERZOS:
                return "Renfuerzos llegaron al Planeta "+destino.getNombre()+" desde "+origen.getNombre()+"("+naves+"naves)\n";
                
            case TIPO_DEFENSA:
                return "Planeta "+destino.getNombre()+" se defendio ante el ataque de "+origen.getOwner().getNombre()+"\n";
                
        }
        return null;
    }
}
