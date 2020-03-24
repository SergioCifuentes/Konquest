/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Tablero;

import java.awt.Dimension;
import java.util.ArrayList;
import konquest.Manejadores.Juego.ControlDeTurnos;
import konquest.Manejadores.Juego.EleccionDePlaneta;
import konquest.mapa.Casilla;
import konquest.mapa.Mapa;
import konquest.mapa.Planeta;

/**
 *
 * @author sergio
 */
public class ManejadorDeCasillas {

    public Casilla[][] generarCasillas(int columnas, int filas,ControlDeTurnos cdr,EleccionDePlaneta edp) {
        Casilla[][] casillas = new Casilla[columnas][filas];
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                casillas[i][j] = new Casilla(cdr,edp);
                casillas[i][j].setColumna(i);
                casillas[i][j].setFila(j);
                casillas[i][j].setBounds(i * Casilla.ANCHO, j * Casilla.ALTO, Casilla.ANCHO, Casilla.ALTO);
                casillas[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(254, 254, 254)));
            }
        }
        return casillas;

    }

    public void generarPosicionesDePlanetas(Casilla[][] casillas, ArrayList<Planeta> planetas,Mapa mapa) {
        ArrayList<Dimension> dimensiones = new ArrayList<>();
        for (int i = 0; i < planetas.size(); i++) {
            Dimension diAux=new Dimension();
            boolean repetido = false;
            do {
                repetido = false;
                int columna = (int) (Math.random() * casillas.length );
                int fila = (int) (Math.random() * casillas[0].length );
                diAux=new Dimension(columna, fila);
                for (int j = 0; j < dimensiones.size(); j++) {
                    if (diAux.height==dimensiones.get(j).height&&diAux.width==dimensiones.get(j).width) {
                        repetido=true;
                    }
                }

            } while (repetido);
            dimensiones.add(diAux);
            casillas[diAux.width][diAux.height].setPlaneta(planetas.get(i),mapa);
        }
    }
    
    public void reDibujarCasillas(Casilla[][] casillas){
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[0].length; j++) {
                if (casillas[i][j].getPlaneta()!=null) {
                    casillas[i][j].reDibujar();
                }
            }
        }
    }

}
