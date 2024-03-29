/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import konquest.Manejadores.Juego.ControladorJuego;
import konquest.cup.Json.AnalizadorSintacticoJson;
import konquest.ui.FramePrincipal;
import konquest.jflex.Json.AnalizadorLexicoJson;
import konquest.mapa.Mapa;
import konquest.ui.EditadorMapas;

/**
 *
 * @author sergio
 */
public class ArchivoDeEntradaJson {
    private Mapa mapa;
    private boolean error=false;
    public void abrirAchivo(File file, FramePrincipal frame,boolean jugar) {
        if (file.getPath().endsWith(".Json")) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                frame.agregarTextoAcciones("====Empezando Analisis==================================\n");
                AnalizadorLexicoJson alj = new AnalizadorLexicoJson(br);
                AnalizadorSintacticoJson asj = new AnalizadorSintacticoJson(alj);
                asj.setFrame(frame);
                asj.parse();
                if (asj.error) {
                    error=true;
                    frame.agregarTextoAcciones("Arregle Los ERRORES Para Poder Abrir El Mapa\n");
                } else {
                    if (asj.errorRecuperable) {
                        frame.agregarTextoAcciones("Mapa Cargada con ERRORES\n");
                    }else{
                        frame.agregarTextoAcciones("Mapa Cargada con Exito\n");
                    }
                    this.mapa = asj.getMapa();
                    if (jugar) {
                        ControladorJuego idj=new ControladorJuego();
                    idj.iniciarJuego(mapa, frame);
                    }
                    
                    
                }
                frame.agregarTextoAcciones("=====================================================\n");
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
    
        public void abrirtexto(String text, FramePrincipal frame) {
        
            try {
                frame.agregarTextoAcciones("====Empezando Analisis==================================\n");
                AnalizadorLexicoJson alj = new AnalizadorLexicoJson(new StringReader(text));
                AnalizadorSintacticoJson asj = new AnalizadorSintacticoJson(alj);
                asj.setFrame(frame);
                asj.parse();
                if (asj.error) {
                    error=true;
                    frame.agregarTextoAcciones("Arregle Los ERRORES Para Poder Abrir El Mapa\n");
                } else {
                    if (asj.errorRecuperable) {
                        frame.agregarTextoAcciones("Mapa Cargada con ERRORES\n");
                    }else{
                        frame.agregarTextoAcciones("Mapa Cargada con Exito\n");
                    }
                    this.mapa = asj.getMapa();
                    
                    
                    
                }
                frame.agregarTextoAcciones("=====================================================\n");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArchivoDeEntradaJson.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ArchivoDeEntradaJson.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ArchivoDeEntradaJson.class.getName()).log(Level.SEVERE, null, ex);
            }

       

    }

    public Mapa getMapa() {
        return mapa;
    }

    public boolean isError() {
        return error;
    }
    
    public void abrirAchivoParaEditar(File file, FramePrincipal frame){
        if (file.getPath().endsWith(".Json")) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                frame.agregarTextoAcciones("====Empezando Analisis==================================\n");
                AnalizadorLexicoJson alj = new AnalizadorLexicoJson(br);
                AnalizadorSintacticoJson asj = new AnalizadorSintacticoJson(alj);
                asj.setFrame(frame);
                asj.parse();
                if (asj.error) {
                    frame.agregarTextoAcciones("Arregle Los ERRORES Para Poder Abrir El Mapa\n");
                } else {
                    if (asj.errorRecuperable) {
                        frame.agregarTextoAcciones("Mapa Cargada con ERRORES\n");
                    }else{
                        frame.agregarTextoAcciones("Mapa Cargada con Exito\n");
                    }
                    Mapa mapa = asj.getMapa();
                    EditadorMapas ed = new EditadorMapas(frame, true, mapa, file,frame);
                    ed.setVisible(true);
                    
                    
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
