/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Replay.Objetos;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import konquest.mapa.Mapa;
import konquest.ui.FramePrincipal;

/**
 *
 * @author sergio
 */
public class ComponenteReplay {

    private Boolean terminado;
    private ArrayList<PartidaInicial> partidas;
    private ArrayList<EnvioReplay> envios;

    public ComponenteReplay() {
        envios = new ArrayList<>();
    }

    public void agregarEnvios(ArrayList<EnvioReplay> enviosReplays) {
        for (int i = 0; i < enviosReplays.size(); i++) {
            this.envios.add(enviosReplays.get(i));
        }
    }

    public Boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean termindo) {
        this.terminado = termindo;
    }

    public ArrayList<PartidaInicial> getPartidas() {
        return partidas;
    }

    public void setPartidas(ArrayList<PartidaInicial> partidas) {
        this.partidas = partidas;
    }

    public ArrayList<EnvioReplay> getEnvios() {
        return envios;
    }

    public void setEnvios(ArrayList<EnvioReplay> envios) {
        this.envios = envios;
    }
    
    
    public static boolean verificarReplay(Mapa mapa, ComponenteReplay componenteReplay, FramePrincipal fp) {

        for (int i = 0; i < componenteReplay.getPartidas().size() - 1; i++) {
            for (int j = i + 1; j < componenteReplay.getPartidas().size(); j++) {
                if (componenteReplay.getPartidas().get(i).getNombrePlaneta().equals(componenteReplay.getPartidas().get(j).getNombrePlaneta())) {
                    JOptionPane.showMessageDialog(fp, "Planeta " + componenteReplay.getPartidas().get(i).getNombrePlaneta() + " se repite", "ERROR Archivo Replay", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        }
        if (mapa.getPlanetas().size()+mapa.getPlanetasNeutrales().size() != componenteReplay.getPartidas().size()) {
            JOptionPane.showMessageDialog(fp, "Planetas en replay difieren con planetas en mapa", "Replay No Corresponde A Mapa", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean encontrado = false;
        for (int i = 0; i < mapa.getPlanetas().size(); i++) {
            encontrado = false;
            for (int j = 0; j < componenteReplay.getPartidas().size(); j++) {
                if (mapa.getPlanetas().get(i).getNombre().equals(componenteReplay.getPartidas().get(j).getNombrePlaneta())) {
                    encontrado = true;
                }

            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(fp, "Planeta " + mapa.getPlanetas().get(i).getNombre() + " No se encuentra en Replay ", "Replay No Corresponde A Mapa", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
//Verificacion de Dimensiones
        for (int i = 0; i < componenteReplay.getPartidas().size(); i++) {
            if (componenteReplay.getPartidas().get(i).getFila() > mapa.getDimension().height) {
                JOptionPane.showMessageDialog(fp, "Planeta :" + componenteReplay.getPartidas().get(i).getNombrePlaneta() + " Fila :" + componenteReplay.getPartidas().get(i).getFila() + " \n fila fuera de capacidad de mapa", "Replay No Corresponde A Mapa", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (componenteReplay.getPartidas().get(i).getColumna() > mapa.getDimension().width) {
                JOptionPane.showMessageDialog(fp, "Planeta :" + componenteReplay.getPartidas().get(i).getNombrePlaneta() + " Columna :" + componenteReplay.getPartidas().get(i).getColumna() + " \n fila fuera de capacidad de mapa", "Replay No Corresponde A Mapa", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        for (int i = 0; i < mapa.getPlanetasNeutrales().size(); i++) {
            encontrado = false;
            for (int j = 0; j < componenteReplay.getPartidas().size(); j++) {
                if (mapa.getPlanetasNeutrales().get(i).getNombre().equals(componenteReplay.getPartidas().get(j).getNombrePlaneta())) {
                    encontrado = true;
                }

            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(fp, "Planeta Neutral " + mapa.getPlanetas().get(i).getNombre() + " No se encuentra en Replay ", "Replay No Corresponde A Mapa", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        //Repeticion de Posiciones Iniciales
        for (int i = 0; i < componenteReplay.getPartidas().size() - 1; i++) {
            for (int j = i + 1; j < componenteReplay.getPartidas().size(); j++) {
                if (componenteReplay.getPartidas().get(i).getFila()==componenteReplay.getPartidas().get(j).getFila()&&componenteReplay.getPartidas().get(i).getColumna()==componenteReplay.getPartidas().get(j).getColumna()) {
                    JOptionPane.showMessageDialog(fp, "Planeta: " + componenteReplay.getPartidas().get(i).getNombrePlaneta() + " y Planeta: "+componenteReplay.getPartidas().get(j).getNombrePlaneta()
                            +"\n Se encuentran en la misma posicion", "ERROR Archivo Replay", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        }
        
        
        encontrado = false;
        //Verificar Jugadores
        for (int i = 0; i < componenteReplay.getPartidas().size(); i++) {
            encontrado = false;
            if (!componenteReplay.getPartidas().get(i).getNombreJugador().equals(PartidaInicial.NEUTRAL)) {
                for (int j = 0; j < mapa.getJugadores().size(); j++) {
                if (mapa.getJugadores().get(j).getNombre().equals(componenteReplay.getPartidas().get(i).getNombreJugador())) {
                    encontrado = true;
                }

            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(fp, "Jugador " + mapa.getPlanetas().get(i).getNombre() + " No se encuentra en Mapa ", "Replay No Corresponde A Mapa", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            }
            
        }
        boolean encontradoOrigen;
        boolean encontradoDestino;
        for (int i = 0; i < componenteReplay.getEnvios().size(); i++) {
            encontradoOrigen = false;
            encontradoDestino = false;
            for (int j = 0; j < componenteReplay.getPartidas().size(); j++) {
                if (componenteReplay.getPartidas().get(j).getNombrePlaneta().equals(componenteReplay.getEnvios().get(i).getOrigen())) {
                    encontradoOrigen = true;
                } else if (componenteReplay.getPartidas().get(j).getNombrePlaneta().equals(componenteReplay.getEnvios().get(i).getDestino())) {
                    encontradoDestino = true;
                }
            }
            if (!encontradoDestino) {
                if (componenteReplay.getEnvios().get(i).getDestino().equals(componenteReplay.getEnvios().get(i).getOrigen())) {
                    JOptionPane.showMessageDialog(fp, "Ronda :" + componenteReplay.getEnvios().get(i).getRonda() + " Origen y Destino son Iguales ", "Error Replay", JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                    JOptionPane.showMessageDialog(fp, "Ronda :" + componenteReplay.getEnvios().get(i).getRonda() + " Planeta Destino : " + componenteReplay.getEnvios().get(i).getDestino() + " no existe", "Error Replay", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            if (!encontradoOrigen) {
                JOptionPane.showMessageDialog(fp, "Ronda :" + componenteReplay.getEnvios().get(i).getRonda() + " Planeta Origen : " + componenteReplay.getEnvios().get(i).getOrigen() + " no existe", "Error Replay", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }

}
