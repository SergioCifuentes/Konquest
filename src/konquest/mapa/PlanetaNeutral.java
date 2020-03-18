/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.mapa;

/**
 *
 * @author sergio
 */
public class PlanetaNeutral extends Planeta{
    
    public PlanetaNeutral(Object[] atributos) {
        super(atributos);
        setOwner(null);
    }
    public static boolean verificarObligatorios(Object[] atributos){
        for (int i = 0; i < 4; i++) {
            if (i!=3&&(atributos[i]==null||atributos[i]=="")) {
                return false;
            }
        }
        return true;
    }
    
    
    
    
}
