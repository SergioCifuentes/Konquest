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
import konquest.Replay.Objetos.PartidaInicial;
import konquest.mapa.Casilla;
import konquest.mapa.Mapa;
import konquest.mapa.Planeta;

/**
 *
 * @author sergio
 */
public class ManejadorDeCasillas {

    public Casilla[][] generarCasillas(int columnas, int filas,ControlDeTurnos cdt,EleccionDePlaneta edp) {
        Casilla[][] casillas = new Casilla[columnas][filas];
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                casillas[i][j] = new Casilla(cdt,edp);
                casillas[i][j].setColumna(i);
                casillas[i][j].setFila(j);
                casillas[i][j].setBounds(i * Casilla.ANCHO, j * Casilla.ALTO, Casilla.ANCHO, Casilla.ALTO);
                casillas[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(254, 254, 254)));
            }
        }
        return casillas;

    }
    
        public Casilla[][] generarCasillasReplay(int columnas, int filas) {
        Casilla[][] casillas = new Casilla[columnas][filas];
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                casillas[i][j] = new Casilla(null,null);
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
            Object [] atributosPi= new Object[7];
            atributosPi[0]="\""+planetas.get(i).getNombre()+"\"";
            atributosPi[1]=diAux.height;
            atributosPi[2]=diAux.width;
            if (planetas.get(i).getOwner()!=null) {
                atributosPi[3]="\""+planetas.get(i).getOwner().getNombre()+"\"";
            }
            atributosPi[4]=planetas.get(i).getNaves();
            atributosPi[5]=planetas.get(i).getProduccion();
            atributosPi[6]=planetas.get(i).getPorcentajeMuertes();
            
            
            
            planetas.get(i).setPi(new PartidaInicial(atributosPi));
        }
    }
    
    public void reDibujarCasillas(Casilla[][] casillas,boolean replay){
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[0].length; j++) {
                if (casillas[i][j].getPlaneta()!=null) {
                    casillas[i][j].reDibujar(replay);
                }
            }
        }
    }

}
