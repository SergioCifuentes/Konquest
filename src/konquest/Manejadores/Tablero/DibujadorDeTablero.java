/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Tablero;

import java.awt.Color;
import javax.swing.JPanel;
import konquest.mapa.Casilla;
import konquest.mapa.Mapa;

/**
 *
 * @author sergio
 */
public class DibujadorDeTablero {

    public void dibujarTablero(Mapa mapa, JPanel panel) {
        panel.removeAll();
        Casilla[][] casillas = mapa.getCasillas();
        panel.setSize(casillas.length * Casilla.ANCHO, casillas[0].length * Casilla.ALTO);
        panel.setPreferredSize(panel.getSize());
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[0].length; j++) {
                Casilla casilla = casillas[i][j];
                panel.add(casilla);
                casilla.setVisible(true);
                
            }
        }

        
    }
}
