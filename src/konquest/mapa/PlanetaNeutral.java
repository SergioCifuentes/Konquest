/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.mapa;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class PlanetaNeutral extends Planeta{
    
    public PlanetaNeutral(Object[] atributos) {
        super(atributos);
        setOwner(null);
        neutral=true;
    }
    public static boolean verificarObligatorios(Object[] atributos){
        for (int i = 0; i < 4; i++) {
            if (i!=2&&(atributos[i]==null||atributos[i]=="")) {
                return false;
            }
        }
        return true;
    }
    
    public static void ingresarProduccion(ArrayList<PlanetaNeutral>planetaNeutrals,int produccionGeneral){
        for (int i = 0; i < planetaNeutrals.size(); i++) {
            if (planetaNeutrals.get(i).getProduccion()==null) {
                planetaNeutrals.get(i).setProduccion(produccionGeneral);
            }
        }
    }
    
    @Override
    public void serConquistado(Jugador jugadorNuevo,int navesRestantes){
        produccion=produccionOriginal;
        neutral=false;
        this.owner=jugadorNuevo;
        this.naves=navesRestantes;
    }
    
}
