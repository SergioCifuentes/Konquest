/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Replay;

import java.awt.Color;
import java.util.ArrayList;
import konquest.Manejadores.Juego.ManejadorDeProducciones;
import konquest.Manejadores.Juego.Objetos.ControlDeFlotas;
import konquest.Manejadores.Juego.Objetos.EnvioDeFlota;
import konquest.Manejadores.Juego.Objetos.EventoEnvio;
import konquest.Manejadores.Juego.Objetos.Ronda;
import konquest.Manejadores.Juego.VerificacionFinalizado;
import konquest.Manejadores.Tablero.DibujadorDeTablero;
import konquest.Manejadores.Tablero.ManejadorDeCasillas;
import konquest.contrladoresUI.TextoDeAcciones;
import konquest.mapa.Jugador;
import konquest.mapa.Mapa;
import konquest.ui.Estadisticas;
import konquest.ui.FramePrincipal;

/**
 *
 * @author sergio
 */
public class RondasReplay {

    private Ronda rondaActual;
    private ArrayList<Ronda> rondas;
    private ArrayList<EnvioDeFlota> enviosPendientes;
    private ArrayList<EnvioDeFlota> enviosRealizados;
    private ArrayList<EventoEnvio> eventos;
    private FramePrincipal fp;
    private Mapa mapa;

    public RondasReplay(FramePrincipal fp, Mapa mapa,ArrayList<EnvioDeFlota> envios) {
        this.fp = fp;
        this.mapa = mapa;
        this.enviosPendientes=envios;
        rondaActual = new Ronda(1);
        rondas = new ArrayList<>();
        eventos = new ArrayList<>();
        enviosRealizados = new ArrayList<>();
        enviarInfoFrame();
    }


    public void enviarInfoFrame() {

        fp.empezarReplay(rondaActual.getNumero());
    }

    private void informarEventos(ArrayList<EventoEnvio> edfs) {
        fp.getJTextPane().setText("");
        int auxRonda=0;
        for (int i = 0; i < edfs.size(); i++) {
            if (edfs.get(i).getRonda()!=auxRonda) {
                auxRonda=edfs.get(i).getRonda();
                TextoDeAcciones.appendToPane(fp.getJTextPane(), "Ronda " + auxRonda + ": \n", Color.WHITE);
            }
            edfs.get(i).appendTextConsola(fp.getJPanelAcciones(), fp.getJTextPane());
        }
        
    }

    public void nextRonda() {
        
        ManejadorDeProducciones mp = new ManejadorDeProducciones();
        mp.producirNaves(mapa.getTodosLosPlanetas(), mapa.isAcumular());
        
        enviosPendientes = ordenarEnvios(enviosPendientes);
        rondas.add(rondaActual);
        rondaActual = new Ronda(rondaActual.getNumero() + 1);
        int in = 0;
        for (int i = 0; i < enviosPendientes.size(); i++) {
            if (enviosPendientes.get(i).getRonda().getNumero()==rondaActual.getNumero()-1) {
                
                enviosPendientes.get(i).getOrigen().getPlaneta().restarNaves(enviosPendientes.get(i).getNaves());
            }
        }
        for (int i = 0; i < enviosPendientes.size(); i++) {
            
            if (enviosPendientes.get(i).getTurnoDestino() == rondaActual.getNumero()) {
                Jugador aux=enviosPendientes.get(i).getDestino().getPlaneta().getOwner();
                int pro = enviosPendientes.get(i).getDestino().getPlaneta().getProduccion();
                EventoEnvio even=ControlDeFlotas.realizarEnvio(enviosPendientes.get(i));
                even.setEnvioFlota(enviosPendientes.get(i));
                System.out.println("pr "+pro);
                if (mapa.isAcumular()) {
                    even.setProduccionAnterior(pro);
                }else{
                    even.setProduccionAnterior(pro);
                }
                
                if (aux!=null) {
                    even.setJugadorAnterior(aux);
                }else{
                    even.setJugadorAnterior(null);
                }
                
                eventos.add(even);
                enviosRealizados.add(enviosPendientes.get(i));
                in++;
            } else {
                break;
            }
            
        }
        for (int j = 0; j < in; j++) {
            enviosPendientes.remove(0);
        }
        VerificacionFinalizado vf= new VerificacionFinalizado();
        agregarNuevosDuenos();
        
        vf.verificarJugadoresVivos(mapa.getJugadores(), obtenerEnviosDeRonda(rondaActual.getNumero()));
        if (vf.verificarGanado(mapa,rondaActual.getNumero())) {
            fp.MarcarGanador();
                Estadisticas estadisticas= new Estadisticas(fp, true, mapa.getJugadores(), vf.getGanadores(),enviosPendientes ,enviosRealizados);
                estadisticas.ocultarGuardar();
                estadisticas.setVisible(true);
        }
        redibujarTablero();
        informarEventos(eventos);
        enviarInfoFrame();
    }
    
    
    public void agregarNuevosDuenos(){
        for (int i = 0; i < enviosPendientes.size(); i++) {
            if (enviosPendientes.get(i).getOrdenador()==null) {
                if (enviosPendientes.get(i).getOrigen().getPlaneta().getOwner()!=null) {
                    enviosPendientes.get(i).setOrdenador(enviosPendientes.get(i).getOrigen().getPlaneta().getOwner());
                }
            }
        }
    }

    public ArrayList<EnvioDeFlota> obtenerEnviosDeRonda(int ronda){
        ordenarEnvios(enviosPendientes);
        ArrayList<EnvioDeFlota> envioAux = new ArrayList<>();
        for (int i = 0; i < enviosPendientes.size(); i++) {
            if (enviosPendientes.get(i).getRonda().getNumero()<ronda) {
                envioAux.add(enviosPendientes.get(i));
            }
        }
        return envioAux;
    }
    
    public void mostrarEstadistics(){
        agregarNuevosDuenos();
        
        Estadisticas estadisticas= new Estadisticas(fp, true, mapa.getJugadores(),obtenerEnviosDeRonda(rondaActual.getNumero()),enviosRealizados);
        estadisticas.setVisible(true);
    }
    
        public void redibujarTablero(){
        ManejadorDeCasillas manejadorDeCasillas = new ManejadorDeCasillas();
        manejadorDeCasillas.reDibujarCasillas(mapa.getCasillas(),true);
        DibujadorDeTablero ddt = new DibujadorDeTablero();
        ddt.dibujarTablero(mapa, fp.getJPanel());
        
    }
    
    public void returnRonda() {
        System.out.println(enviosRealizados);
        enviosRealizados=ordenarEnviosRealizados(enviosRealizados,true);
        rondas.remove(rondas.size()-1);
        rondaActual = new Ronda(rondaActual.getNumero() - 1);
        int in = 0;
        
        for (int i = 0; i < enviosRealizados.size(); i++) {
            System.out.println(enviosRealizados.get(i).getNaves()+"  "+enviosRealizados.get(i).getRonda().getNumero()+enviosRealizados.get(i).getTurnoDestino());
            if (enviosRealizados.get(i).getRonda().getNumero()==rondaActual.getNumero()) {
                enviosRealizados.get(i).getOrigen().getPlaneta().recibirNavesAleadas(enviosRealizados.get(i).getNaves());
                EventoEnvio evento=obtenerEventoDeEnvio(enviosRealizados.get(i));
                desHacer(evento);
                eventos.remove(evento);
                in++;
                
            }else if (enviosRealizados.get(i).getTurnoDestino() == rondaActual.getNumero()+1) {
                enviosPendientes.add(enviosRealizados.get(i));
                EventoEnvio evento=obtenerEventoDeEnvio(enviosRealizados.get(i));
                desHacer(evento);
                eventos.remove(evento);
                in++;
            } else {
                break;
            }
            
        }
        for (int j = 0; j < in; j++) {
            enviosRealizados.remove(0);
        }
        for (int i = 0; i < enviosPendientes.size(); i++) {
            if (enviosPendientes.get(i).getRonda().getNumero()==rondaActual.getNumero()) {
                System.out.println("eP: "+enviosPendientes.get(i).getOrigen().getPlaneta().getNombre()+" "+enviosPendientes.get(i).getNaves());
                enviosPendientes.get(i).getOrigen().getPlaneta().recibirNavesAleadas(enviosPendientes.get(i).getNaves());
                in++;
            }
        }
        ManejadorDeProducciones mp = new ManejadorDeProducciones();
        mp.desProducirNaves(mapa.getTodosLosPlanetas(), mapa.isAcumular());
        enviosPendientes = ordenarEnvios(enviosPendientes);
        
        
        redibujarTablero();
        informarEventos(eventos);
        enviarInfoFrame();
        
        
        
    }
    public void desHacer(EventoEnvio evento){
        
        switch (evento.getTipo()) {
            case EventoEnvio.TIPO_CONQUISTA:
                Double naves1 = evento.getDestino().getNaves()*evento.getDestino().getPorcentajeMuertes();
               naves1=evento.getOrigen().getPorcentajeMuertes()*evento.getNaves()-naves1;
               naves1=naves1/evento.getDestino().getPorcentajeMuertes();
                if (naves1<1) {
                    naves1=(double)2;
                }
               evento.getDestino().setNaves(naves1.intValue()-1);
                if (evento.getJugadorAnterior()!=null) {
                    evento.getDestino().setOwner(evento.getJugadorAnterior());
                    evento.getJugadorAnterior().agregarPlaneta(evento.getDestino());
                }else{
                    evento.getDestino().setOwner(null);
                    evento.getDestino().setNeutral(true);
                }
               evento.getDestino().setpr(evento.getProduccionAnterior());
                break;
            case EventoEnvio.TIPO_DEFENSA:
               Double naves = evento.getDestino().getNaves()*evento.getDestino().getPorcentajeMuertes();
               naves+=evento.getOrigen().getPorcentajeMuertes()*evento.getNaves();
               naves=naves/evento.getDestino().getPorcentajeMuertes();
               evento.getDestino().setNaves(naves.intValue());
                break;
            case EventoEnvio.TIPO_RENFUERZOS:
                evento.getDestino().restarNaves(evento.getNaves());
                break;    
        }
        
        
    }

    public EventoEnvio obtenerEventoDeEnvio(EnvioDeFlota envio){
        for (int i = 0; i < eventos.size(); i++) {
            if (eventos.get(i).getEnvioFlota().equals(envio)) {
                return eventos.get(i);
            }
        }
        return null;
    }
    
    private ArrayList<EnvioDeFlota> ordenarEnvios(ArrayList<EnvioDeFlota> envios) {
        EnvioDeFlota aux;
        boolean cambios;
        while (true) {
            cambios = false;
            for (int i = 1; i < envios.size(); i++) {
                if (envios.get(i).getTurnoDestino() < envios.get(i - 1).getTurnoDestino()) {
                    aux = envios.get(i);
                    envios.set(i, envios.get(i - 1));
                    envios.set(i - 1, aux);
                    cambios = true;
                }
            }
            if (cambios == false) {
                break;
            }
        }
        return envios;
    }
    
    public static ArrayList<EnvioDeFlota> ordenarEnviosRealizados(ArrayList<EnvioDeFlota> envios,boolean descendiente) {
        EnvioDeFlota aux;
        boolean cambios;
        while (true) {
            cambios = false;
            for (int i = 1; i < envios.size(); i++) {
                if (descendiente) {
                    if (envios.get(i).getRonda().getNumero() > envios.get(i - 1).getRonda().getNumero()) {
                    aux = envios.get(i);
                    envios.set(i, envios.get(i - 1));
                    envios.set(i - 1, aux);
                    cambios = true;
                }
                }else{
                    if (envios.get(i).getRonda().getNumero() < envios.get(i - 1).getRonda().getNumero()) {
                    aux = envios.get(i);
                    envios.set(i, envios.get(i - 1));
                    envios.set(i - 1, aux);
                    cambios = true;
                }
                }
                
            }
            if (cambios == false) {
                break;
            }
        }
        
        for (int i = 0; i < envios.size(); i++) {
            
        }
        return envios;
    }
}
