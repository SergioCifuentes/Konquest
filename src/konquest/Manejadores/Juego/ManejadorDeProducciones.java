/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego;

import java.util.ArrayList;
import konquest.mapa.Planeta;

/**
 *
 * @author sergio
 */
public class ManejadorDeProducciones {

    public void producirNaves(ArrayList<Planeta> planetas, boolean acumular) {
        for (int i = 0; i < planetas.size(); i++) {
            planetas.get(i).recibirNavesAleadas(planetas.get(i).getProduccion());
            if (planetas.get(i).getOwner() != null) {
                planetas.get(i).getOwner().getEstadisticas().aumentarNavesProducidas(planetas.get(i).getProduccion());
            }
        }
        if (acumular) {
            aumentarProducciones(planetas);
        }

    }

    public void desProducirNaves(ArrayList<Planeta> planetas, boolean acumular) {
        if (acumular) {
            disminuirProducciones(planetas);
        }
        for (int i = 0; i < planetas.size(); i++) {
            planetas.get(i).restarNaves(planetas.get(i).getProduccion());
            if (planetas.get(i).getOwner() != null) {
                planetas.get(i).getOwner().getEstadisticas().disminuirNavesProducidas(planetas.get(i).getProduccion());
            }
        }

    }

    private void aumentarProducciones(ArrayList<Planeta> planetas) {

        for (int i = 0; i < planetas.size(); i++) {
            planetas.get(i).aumentarProduccion();
        }
    }

    private void disminuirProducciones(ArrayList<Planeta> planetas) {

        for (int i = 0; i < planetas.size(); i++) {
            planetas.get(i).disminuirProduccion();
        }
    }
}
