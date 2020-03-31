/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Escritores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import static konquest.Escritores.CreadorArchivoReplay.escribirPlanetasIniciales;
import konquest.Manejadores.Juego.Objetos.EnvioDeFlota;
import konquest.mapa.Planeta;

/**
 *
 * @author sergio
 */
public class Guardador {

    public void crearArchivo(File file, ArrayList<Planeta> planetas, ArrayList<EnvioDeFlota> envios, int rondaFinal) {
        String textoAEscribir = obtenerTexto(planetas, envios, rondaFinal);
        

        try {

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(textoAEscribir);
            }
        } catch (IOException ex) {
            System.out.println("error AL escribir Mapa");
        }
    }
    
    public static String obtenerTexto( ArrayList<Planeta> planetas, ArrayList<EnvioDeFlota> envios, int rondaFinal){
        String textoAEscribir="";
        textoAEscribir += "{\n\tterminado: false,";
        textoAEscribir += escribirPlanetasIniciales(planetas);

        textoAEscribir += "\n\tRONDAS: {";
        int ronda = 0;
        boolean segundo = false;
        for (int i = 0; i < envios.size(); i++) {
            if (i == 0) {
                ronda = envios.get(i).getRonda().getNumero();
                textoAEscribir += "\n\t\t" + ronda + ": [";
            }
            if (envios.get(i).getRonda().getNumero() == ronda) {
                if (segundo) {
                    textoAEscribir += ",";
                }
                textoAEscribir += "\n\t\t\t{";
                textoAEscribir += "\n\t\t\torigen: \"" + envios.get(i).getOrigen().getPlaneta().getNombre() + "\",";
                textoAEscribir += "\n\t\t\tdestino: \"" + envios.get(i).getDestino().getPlaneta().getNombre() + "\",";
                textoAEscribir += "\n\t\t\tnaves: " + envios.get(i).getNaves();
                textoAEscribir += "\n\t\t\t}";
                segundo = true;
            } else {
                textoAEscribir += "\n\t\t],";
                segundo = false;
                ronda = envios.get(i).getRonda().getNumero();
                textoAEscribir += "\n\t\t" + ronda + ": [";
                i--;
            }
            if (i==envios.size()-1) {
                textoAEscribir += "\n\t\t]";
            }
        }
        
        if (ronda < rondaFinal) {
            textoAEscribir += ",";
            textoAEscribir += "\n\t\t" + rondaFinal + ": [";
            textoAEscribir += "\n\t\t]";
        } 
        
        textoAEscribir += "\n\t}";
        textoAEscribir += "\n}";
        return textoAEscribir;
    }
}
