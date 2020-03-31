/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Sockets;

import javax.swing.JOptionPane;
import konquest.mapa.Jugador;
import konquest.mapa.Mapa;
import konquest.ui.FramePrincipal;

/**
 *
 * @author sergio
 */
public class verificacionesOnline {
    public boolean verificarJugadores(FramePrincipal frame,Mapa mapa){
        int numeroHumanos=0;
        for (int i = 0; i < mapa.getJugadores().size(); i++) {
            if (mapa.getJugadores().get(i).getTipo()==Jugador.TIPO_HUMANO) {
                numeroHumanos++;
            }
        }
        if (numeroHumanos==2) {
            return true;
        }else{
            JOptionPane.showMessageDialog(frame,"Mapas para Online Deben tener 2 HUMANOS","Error De Mapa",JOptionPane.ERROR_MESSAGE);
            
            return false;
        }
    }
}
