/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Online;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import konquest.Manejadores.Juego.Objetos.EnvioDeFlota;
import konquest.Manejadores.Juego.Objetos.Ronda;
import konquest.Manejadores.Tablero.Distancias;
import konquest.Replay.Objetos.EnvioReplay;
import konquest.cup.Online.AnalizadorSintacticoOnline;
import konquest.jflex.Online.AnalizadorLexicoOnline;
import konquest.mapa.Casilla;
import konquest.mapa.Mapa;
import konquest.mapa.Planeta;

/**
 *
 * @author sergio
 */
public class EnviosOnline {
    private Mapa mapa;
    public static String pasarEnvioAOnline(ArrayList<EnvioDeFlota> envios){
        String text="";
        text+="{";
        for (int i = 0; i < envios.size(); i++) {
            text+="\n\t{";
            text+="\n\t\torigen :\""+envios.get(i).getOrigen().getPlaneta().getNombre()+"\",";
            text+="\n\t\tdestino :\""+envios.get(i).getDestino().getPlaneta().getNombre()+"\",";
            text+="\n\t\tnaves :"+envios.get(i).getNaves()+"";
            
            text+="\n\t}";
            if (i<envios.size()-1) {
                text+=",";
            }
        }
        
        
        text+="\n}";
        
        return text;
    }
    
    public ArrayList<EnvioDeFlota> convertirTextAEnvios(String text,Mapa mapa,Ronda ronda){
        this.mapa=mapa;
        ArrayList<EnvioDeFlota> envios=new  ArrayList<>();
        try {
            
            
            AnalizadorLexicoOnline alo = new AnalizadorLexicoOnline(new StringReader(text));
            AnalizadorSintacticoOnline aso = new AnalizadorSintacticoOnline(alo);
            aso.parse();
            ArrayList<EnvioReplay> enRep= aso.getEnvios();
            envios=convertitEnviosReplayAEnvios(enRep,ronda);
            
            
        } catch (Exception ex) {
            Logger.getLogger(EnviosOnline.class.getName()).log(Level.SEVERE, null, ex);
        }
        return envios;
    }
    
        public ArrayList<EnvioDeFlota> convertitEnviosReplayAEnvios(ArrayList<EnvioReplay> envios,Ronda ronda) {
        ArrayList<EnvioDeFlota> enviosFlota = new ArrayList<>();
        for (int i = 0; i < envios.size(); i++) {
            System.out.println(envios.get(i).getNaves());
            System.out.println(envios.get(i).getDestino());
            System.out.println(envios.get(i).getOrigen());
            Planeta origen = obtenerPlaneta(envios.get(i).getOrigen());
            Casilla casillaOrigen = obtenerCasilla(origen);
            
            Casilla casillaDestino = obtenerCasilla(obtenerPlaneta(envios.get(i).getDestino()));
            
            enviosFlota.add(new EnvioDeFlota(casillaOrigen,
                     casillaDestino, origen.getOwner(), envios.get(i).getNaves(),
                     ronda,
                    Distancias.calcularDistancia(casillaOrigen, casillaDestino) + ronda.getNumero()));

        }
        return enviosFlota;
    }
    
    public Casilla obtenerCasilla(Planeta p) {
        for (int i = 0; i < mapa.getCasillas().length; i++) {
            for (int j = 0; j < mapa.getCasillas()[0].length; j++) {
                if (mapa.getCasillas()[i][j].getPlaneta() != null) {

                    if (mapa.getCasillas()[i][j].getPlaneta().getNombre().equals(p.getNombre())) {
                        return mapa.getCasillas()[i][j];
                    }
                }
            }
        }
        return null;
    }

    public Planeta obtenerPlaneta(String nombre) {
        for (int i = 0; i < mapa.getPlanetas().size(); i++) {
            if (mapa.getPlanetas().get(i).getNombre().equals(nombre)) {
                return mapa.getPlanetas().get(i);
            }
        }
        for (int i = 0; i < mapa.getPlanetasNeutrales().size(); i++) {
            if (mapa.getPlanetasNeutrales().get(i).getNombre().equals(nombre)) {
                return mapa.getPlanetasNeutrales().get(i);
            }
        }
        return null;
    }
}
