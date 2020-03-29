/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Replay.Objetos;

import konquest.ui.FramePrincipal;

/**
 *
 * @author sergio
 */
public class EnvioReplay {
    private String origen;
    private String destino;
    private Integer naves;
    private Integer ronda;
    
    private final static String[] ATRIBUTOS={"origen","destino","naves"};
    
    public static boolean verificarObligatorios(Object[] atributos,FramePrincipal fp){
        boolean faltante=true;
        for (int i = 0; i < 3; i++) {
            if (atributos[i]==null||atributos[i]=="") {
                fp.agregarTextoAcciones("Atributo Para Ronda Faltante:"+ATRIBUTOS[i]+"\n");
                faltante=false;
            }
        }

        return faltante;
    }

    public EnvioReplay(Object[] atributos){
        origen=((String)atributos[0]).substring(1,((String)atributos[0]).length()-1);
        destino=((String)atributos[1]).substring(1,((String)atributos[1]).length()-1);
        naves=(Integer)atributos[2];
    }
    
    public void setRonda(Integer ronda) {
        this.ronda = ronda;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public Integer getNaves() {
        return naves;
    }

    public Integer getRonda() {
        return ronda;
    }

    public static String[] getATRIBUTOS() {
        return ATRIBUTOS;
    }
    
    
}
