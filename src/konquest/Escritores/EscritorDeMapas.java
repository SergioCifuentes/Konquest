/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Escritores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import konquest.mapa.Jugador;
import konquest.mapa.Mapa;

/**
 *
 * @author sergio
 */
public class EscritorDeMapas {

    public void escribirMapa(Mapa mapa, File file) {
        String textoAEscribir = "";
        textoAEscribir += "{\n\tMAPA: {";
        textoAEscribir += "\n\t\tid:\"" + mapa.getId() + "\",";
        textoAEscribir += "\n\t\t tamaño: {";
        textoAEscribir += "\n\t\t\tfilas:" + mapa.getDimension().height + ",";
        textoAEscribir += "\n\t\t\tcolumnas:" + mapa.getDimension().width ;
        textoAEscribir += "\n\t\t},";
        textoAEscribir += "\n\t\talAzar:" + mapa.isAlAzar() + ",";
        if (mapa.isAlAzar()) {
            textoAEscribir += "\n\t\tplanetasNeutrales:" + mapa.getNumeroPlanetasNeutrales() + ",";
        }        
        textoAEscribir += "\n\t\tmapaCiego:" + mapa.isMapaCiego() + ",";
        textoAEscribir += "\n\t\tacumular:" + mapa.isAcumular() + ",";
        textoAEscribir += "\n\t\tNEUTRALES: {";
        textoAEscribir += "\n\t\t\tmostrarNaves:"+mapa.getConfiNeutrales().isMostrarNaves()+",";
        textoAEscribir += "\n\t\t\tmostrarEstadisticas:"+mapa.getConfiNeutrales().isMostrarEstadisticas()+",";
        textoAEscribir += "\n\t\t\tproduccion:"+mapa.getConfiNeutrales().getProduccion();
        textoAEscribir += "\n\t\t}";
        if (mapa.getFinalizado()!=null) {
            textoAEscribir += ",\n\t\tfinalizacion:" + mapa.getFinalizado();
        }
        
        textoAEscribir += "\n\t},";
        textoAEscribir += "\n\tPLANETAS: [";
        if (!mapa.isAlAzar()) {
            System.out.println(mapa.getPlanetas().size()+"S");
            for (int i = 0; i < mapa.getPlanetas().size(); i++) {
                textoAEscribir  += "\n\t\t{";
                textoAEscribir  += "\n\t\t\tnombre: \""+mapa.getPlanetas().get(i).getNombre()+"\",";
                textoAEscribir  += "\n\t\t\tnaves: "+mapa.getPlanetas().get(i).getNaves()+",";
                textoAEscribir  += "\n\t\t\tproduccion: "+mapa.getPlanetas().get(i).getProduccion()+",";
                textoAEscribir  += "\n\t\t\tporcentajeMuertes: "+mapa.getPlanetas().get(i).getPorcentajeMuertes();
                textoAEscribir  += "\n\t\t}";
                if (i<mapa.getPlanetas().size()-1) {
                    textoAEscribir+=",";
                }
            }
        }
        textoAEscribir += "\n\t],";
        
        textoAEscribir += "\n\tPLANETAS_NEUTRALES: [";
        if (!mapa.isAlAzar()) {
            for (int i = 0; i < mapa.getPlanetasNeutrales().size(); i++) {
                textoAEscribir  += "\n\t\t{";
                textoAEscribir  += "\n\t\t\tnombre: \""+mapa.getPlanetasNeutrales().get(i).getNombre()+"\",";
                textoAEscribir  += "\n\t\t\tnaves: "+mapa.getPlanetasNeutrales().get(i).getNaves()+",";
                textoAEscribir  += "\n\t\t\tproduccion: "+mapa.getPlanetasNeutrales().get(i).getProduccion()+",";
                textoAEscribir  += "\n\t\t\tporcentajeMuertes: "+mapa.getPlanetasNeutrales().get(i).getPorcentajeMuertes();
                textoAEscribir  += "\n\t\t}";
                if (i<mapa.getPlanetasNeutrales().size()-1) {
                    textoAEscribir+=",";
                }
            }
        }
        textoAEscribir += "\n\t],";
        
        textoAEscribir += "\n\tJUGADORES: [";
        for (int i = 0; i < mapa.getJugadores().size(); i++) {
            textoAEscribir  += "\n\t\t{";
            textoAEscribir  += "\n\t\t\tnombre: \""+mapa.getJugadores().get(i).getNombre()+"\",";
            textoAEscribir  += "\n\t\t\tplanetas: [";
            if (!mapa.isAlAzar()) {
                for (int j = 0; j < mapa.getJugadores().get(i).getNombresPlaneta().size(); j++) {
                textoAEscribir  += "\n\t\t\t\t\""+mapa.getJugadores().get(i).getNombresPlaneta().get(j)+"\"";
                if (j<mapa.getJugadores().get(i).getNombresPlaneta().size()-1) {
                    textoAEscribir  += ",";
                }
            }
            }
            
            textoAEscribir  += "\n\t\t\t],";
            switch (mapa.getJugadores().get(i).getTipo()) {
                case Jugador.TIPO_HUMANO:
                    textoAEscribir  += "\n\t\t\ttipo: HUMANO";
                    break;
                case Jugador.TIPO_DIFICIL:
                    textoAEscribir  += "\n\t\t\ttipo: DIFICIL";
                    break;    
                case Jugador.TIPO_FACIL:
                     textoAEscribir  += "\n\t\t\ttipo: FACIL";
                    break;
            }
            
            textoAEscribir  += "\n\t\t}";
            if (i<mapa.getJugadores().size()-1) {
                textoAEscribir+=",";
            }
        }
        textoAEscribir += "\n\t]";
        
       textoAEscribir += "\n}";
        
        
        try {
            
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(textoAEscribir);
            }
        } catch (IOException ex) {
            System.out.println("error AL escribir Mapa");
        }

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

    public File crearFile(File file, String nombre) {
        File fi = new File(file.getPath() + "/" + nombre + ".Json");
        try {
            if (fi.exists()) {
                return eliminarTextoDeFile(file);
            } else {

                fi.createNewFile();
            }
        } catch (IOException ex) {
            System.out.println("error al crearS");
        }
        return fi;
    }

}
