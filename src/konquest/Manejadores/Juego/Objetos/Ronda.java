/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego.Objetos;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class Ronda {
private int numero;
private ArrayList<EnvioDeFlota> envioDeTropases;

    public Ronda(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public ArrayList<EnvioDeFlota> getEnvioDeTropases() {
        return envioDeTropases;
    }


}
