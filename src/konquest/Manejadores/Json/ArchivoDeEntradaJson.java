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
import konquest.Manejadores.Tablero.DibujadorDeTablero;
import konquest.Manejadores.Tablero.ManejadorDeCasillas;
import konquest.cup.Json.AnalizadorSintacticoJson;
import konquest.ui.FramePrincipal;
import konquest.jflex.Json.AnalizadorLexicoJson;
import konquest.mapa.Casilla;
import konquest.mapa.Mapa;

/**
 *
 * @author sergio
 */
public class ArchivoDeEntradaJson {

    public void abrirAchivo(File file, FramePrincipal frame) {
        if (file.getPath().endsWith(".Json")) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));

                AnalizadorLexicoJson alj = new AnalizadorLexicoJson(br);
                AnalizadorSintacticoJson asj = new AnalizadorSintacticoJson(alj);
                asj.parse();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArchivoDeEntradaJson.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ArchivoDeEntradaJson.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ArchivoDeEntradaJson.class.getName()).log(Level.SEVERE, null, ex);
            }
            Mapa mapa = new Mapa();
            ManejadorDeCasillas mc= new ManejadorDeCasillas();
            mapa.setCasillas(mc.generarCasillas(8, 5));
            DibujadorDeTablero ddt = new DibujadorDeTablero();
            ddt.dibujarTablero(mapa, frame.getJPanel());
        } else {
            JOptionPane.showMessageDialog(frame, "El Archivo Debe Ser '.Json'", "Error Al Cargar", JOptionPane.ERROR_MESSAGE);
        }

    }
}
