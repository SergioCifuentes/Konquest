/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Replay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import konquest.CargarPartida.CargaDePartida;
import konquest.Manejadores.Json.ArchivoDeEntradaJson;
import konquest.Replay.Objetos.ComponenteReplay;
import konquest.cup.Replay.AnalizadorSintacticoReplay;

import konquest.jflex.Replay.AnalizadorLexicoReplays;
import konquest.mapa.Mapa;
import konquest.ui.FramePrincipal;

/**
 *
 * @author sergio
 */
public class ManejadorDeEntrada {
    
    public void abrirAchivo(File file, FramePrincipal frame,Mapa mapa,boolean replay) {
        if (file.getPath().endsWith(".Json")) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                if (replay) {
                    frame.agregarTextoAcciones("====Empezando Analisis Replay==================================\n");
                }else{
                    frame.agregarTextoAcciones("====Empezando Carga De Partida==================================\n");
                }
                
                AnalizadorLexicoReplays alr= new AnalizadorLexicoReplays(br);
                AnalizadorSintacticoReplay asr = new AnalizadorSintacticoReplay(alr);
                asr.setFrame(frame);
                asr.parse();
                if (asr.error) {
                    if (replay) {
                        frame.agregarTextoAcciones("Arregle Los ERRORES Para Poder Abrir El Replay\n");
                    }else{
                        frame.agregarTextoAcciones("Arregle Los ERRORES Para Poder Abrir la Partida Guardad\n");
                    }
                    
                } else {
                    if (asr.errorRecuperable) {
                        if (replay) {
                            frame.agregarTextoAcciones("Replay Cargada con ERRORES\n");
                        }else{
                            frame.agregarTextoAcciones("Partida Cargada con ERRORES\n");
                        }
                        
                    }else{
                        if (replay) {
                            frame.agregarTextoAcciones("Replay Cargada con Exito\n");
                        }else{
                            frame.agregarTextoAcciones("Partida Cargada con Exito\n");
                        }
                        
                    }
                    ComponenteReplay co = asr.getReplay();
                    //REPLAY
                    if (replay) {
                        if (co.isTerminado()) {
                            if (ComponenteReplay.verificarReplay(mapa, co,frame)) {
                                frame.agregarTextoAcciones("Enlace Exitoso\n");
                                ControladorReplay cr= new ControladorReplay();
                                cr.empezarReplay(mapa, co, frame);
                            }else{
                                 frame.agregarTextoAcciones("Enlace No Correcto\n");
                            }
                    }else{
                         JOptionPane.showMessageDialog(frame,"Esta Partida no ha termindo","Partida en Proeceso",JOptionPane.ERROR_MESSAGE);
                     }
                        //CARGADEPARTIDA
                    }else{
                        if (co.isTerminado()) {
                            JOptionPane.showMessageDialog(frame,"Esta Partida ya ha termindo","Partida Terminado",JOptionPane.ERROR_MESSAGE);
                    }else{
                         if (ComponenteReplay.verificarReplay(mapa, co,frame)) {
                                frame.agregarTextoAcciones("Enlace Exitoso\n");
                                CargaDePartida cargaDePartida = new CargaDePartida();
                                cargaDePartida.empezarCarga(mapa,co, frame,asr.getRondaMayor());
                            }else{
                             frame.agregarTextoAcciones("Enlace No Correcto\n");
                         }
                     }
                    }
                     
                    
                }
                frame.agregarTextoAcciones("=====================================================");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArchivoDeEntradaJson.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ArchivoDeEntradaJson.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ArchivoDeEntradaJson.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(frame, "El Archivo Debe Ser '.Json'", "Error Al Cargar", JOptionPane.ERROR_MESSAGE);
        }

    }
    
}
