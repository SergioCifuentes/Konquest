/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Replay;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import konquest.Manejadores.Juego.Objetos.EnvioDeFlota;
import konquest.Replay.Objetos.PartidaInicial;
import konquest.contrladoresUI.EscritorDeMapas;
import konquest.mapa.Planeta;

/**
 *
 * @author sergio
 */
public class CreadorArchivoReplay {

    public void crearArchivo(File file, ArrayList<Planeta> planetas, ArrayList<EnvioDeFlota> envios) {
        String textoAEscribir = "";
        textoAEscribir += "{\n\tterminado: true,";
        textoAEscribir += "\n\tPLANETAS: [";
        for (int i = 0; i < planetas.size(); i++) {
            textoAEscribir+="\n\t\t{";
            textoAEscribir+="\n\t\t\tnombre: \""+planetas.get(i).getNombre()+"\",";
            textoAEscribir+="\n\t\t\tfila: "+(planetas.get(i).getPi().getFila()+1)+",";
            textoAEscribir+="\n\t\t\tcolumna: "+(planetas.get(i).getPi().getColumna()+1)+",";
            if (!planetas.get(i).getPi().getNombreJugador().equals(PartidaInicial.NEUTRAL)) {
                textoAEscribir+="\n\t\t\tdueño: \""+planetas.get(i).getPi().getNombreJugador()+"\",";
            }
            textoAEscribir+="\n\t\t\tnaves: "+planetas.get(i).getPi().getNaves()+",";
            textoAEscribir+="\n\t\t\tproduccion: "+planetas.get(i).getPi().getProduccion()+",";
            textoAEscribir+="\n\t\t\tporcentajeMuertes: "+planetas.get(i).getPi().getPorcentajeMuertes();
            textoAEscribir+="\n\t\t}";
            if (i<planetas.size()-1) {
                    textoAEscribir+=",";
                }
        }
        textoAEscribir += "\n\t],";
        textoAEscribir += "\n\tRONDAS: {";
        int ronda=0;
        boolean segundo=false;
        System.out.println("enviosss "+envios.size());
        for (int i = 0; i < envios.size(); i++) {
            if (i==0) {
                ronda=envios.get(i).getRonda().getNumero();
                textoAEscribir += "\n\t\t"+ronda+": [";
            }
            if (envios.get(i).getRonda().getNumero()==ronda) {
                if (segundo) {
                    textoAEscribir+=",";
                }
                textoAEscribir += "\n\t\t\t{";
                textoAEscribir += "\n\t\t\torigen: \""+envios.get(i).getOrigen().getPlaneta().getNombre()+"\",";
                textoAEscribir += "\n\t\t\tdestino: \""+envios.get(i).getDestino().getPlaneta().getNombre()+"\",";
                textoAEscribir += "\n\t\t\tnaves: "+envios.get(i).getNaves();
                textoAEscribir += "\n\t\t\t}";
                segundo=true;
            }else{
                textoAEscribir += "\n\t\t],";
                segundo = false;
                ronda=envios.get(i).getRonda().getNumero();
                textoAEscribir += "\n\t\t"+ronda+": [";
                i--;
            }
        }
        textoAEscribir += "\n\t\t]";
        textoAEscribir += "\n\t}";
        textoAEscribir += "\n}";
        
        try {
            
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(textoAEscribir);
            }
        } catch (IOException ex) {
            System.out.println("error AL escribir Mapa");
        }
    }

    public File crearFile(File file, String nombre) {
        File fi = new File(file.getPath() + "/" + nombre + ".Json");
        try {
            if (fi.exists()) {
                return eliminarTextoDeFile(file);
            } else {

                fi.createNewFile();
            }
        } catch (IOException ex) {

        }
        return fi;
    }
    
        public File eliminarTextoDeFile(File file) {
        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write("");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EscritorDeMapas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EscritorDeMapas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file;
    }
}
