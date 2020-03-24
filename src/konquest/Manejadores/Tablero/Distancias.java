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
public class Distancias {
    
    public static int calcularDistancia(Casilla casilla1,Casilla casillas2){
        int rondas=0;
        if (casilla1.getColumna()!=casillas2.getColumna()) {
            if (casilla1.getColumna()>casillas2.getColumna()) {
                int aux1 =casilla1.getColumna();
                while (aux1>casillas2.getColumna()) {
                    aux1--;
                    rondas++;
                }
            }else{
                int aux1 =casilla1.getColumna();
                while (aux1<casillas2.getColumna()) {
                    aux1++;
                    rondas++;
                }
            }
        }
        
        if (casilla1.getFila()!=casillas2.getFila()) {
            if (casilla1.getFila()>casillas2.getFila()) {
                int aux1 =casilla1.getFila();
                while (aux1>casillas2.getFila()) {
                    aux1--;
                    rondas++;
                }
            }else{
                int aux1 =casilla1.getFila();
                while (aux1<casillas2.getFila()) {
                    aux1++;
                    rondas++;
                }
            }
        }
        
        if (rondas==1) {
            return rondas;
        }
        
        for (int i = rondas; i > 1; i-=2) {
            rondas--;
        }
        
        return rondas;
    }
}
