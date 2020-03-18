/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Tablero;

import konquest.mapa.Casilla;

/**
 *
 * @author sergio
 */
public class ManejadorDeCasillas {
    public Casilla[][] generarCasillas(int columnas,int filas){
      Casilla[][] casillas = new Casilla[columnas][filas];
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                 casillas[i][j]=new Casilla();
                    casillas[i][j].setBounds(i*Casilla.ANCHO,j*Casilla.ALTO,Casilla.ANCHO,Casilla.ALTO);
                    casillas[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

                    casillas[i][j].setText(i+" "+j);
            }
        }
        return casillas;
          
    }
}
