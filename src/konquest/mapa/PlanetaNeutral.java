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
    
    public PlanetaNeutral(String nombre, int naves, int produccion, float porcentajeMuertes) {
        super(nombre, naves, produccion, porcentajeMuertes);
        setOwner(null);
    }
    
    
    
}
