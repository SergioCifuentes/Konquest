/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Sockets;

import java.io.File;
import java.util.ArrayList;
import konquest.Escritores.Guardador;
import konquest.Manejadores.Json.ArchivoDeEntradaJson;
import konquest.Manejadores.Juego.ControlDeTurnos;
import konquest.Manejadores.Juego.ControladorDeRondas;
import konquest.Manejadores.Juego.ControladorJuego;
import static konquest.Manejadores.Juego.ControladorJuego.agregarNavesProducidasIniciales;
import konquest.Manejadores.Juego.EleccionDePlaneta;
import konquest.Manejadores.Juego.Objetos.EnvioDeFlota;
import konquest.Manejadores.Tablero.ControladorDeColores;
import konquest.Manejadores.Tablero.DibujadorDeTablero;
import konquest.Manejadores.Tablero.ManejadorDeCasillas;
import konquest.Online.Guest;
import konquest.Online.Host;
import konquest.Replay.ManejadorDeEntrada;
import konquest.mapa.Jugador;
import konquest.mapa.Mapa;
import konquest.ui.FramePrincipal;

/**
 *
 * @author sergio
 */
public class ManejadorDeMensajes {
    Servidor ser;
    Cliente cliente;
    FramePrincipal fp;
    public void iniciarJuegoHost(Mapa mapa,FramePrincipal fp,File fileMapa){
        this.fp=fp;
        fp.setOnline(true);
        ManejadorDeCasillas mc = new ManejadorDeCasillas();
        ControladorDeRondas cdr= new ControladorDeRondas();
        Host host = new Host(mapa, fp,null);
        Jugador nombreGuest=null;
        for (int i = 0; i <mapa.getJugadores().size(); i++) {
            if (mapa.getJugadores().get(i).getTipo()==Jugador.TIPO_HUMANO) {
                if (mapa.getJugadores().get(i).isHost()) {
                    host.setJugador(mapa.getJugadores().get(i));
                }else{
                    nombreGuest=mapa.getJugadores().get(i);
                }
            }
        }
        ControlDeTurnos controlDeTurnos = new ControlDeTurnos(mapa, cdr, fp, host, null);
        host.setCdt(controlDeTurnos);
        fp.setCdt(controlDeTurnos);
        fp.setReplay(false);
        EleccionDePlaneta edp =new EleccionDePlaneta(fp, controlDeTurnos);
        mapa.setCasillas(mc.generarCasillas(mapa.getDimension().width, mapa.getDimension().height,controlDeTurnos,edp));
        ControladorDeColores cdc = new ControladorDeColores();
        cdc.generarColores(mapa.getJugadores());
        ControladorJuego.iniciarPosiciones(mapa);
        agregarNavesProducidasIniciales(mapa.getJugadores());
        
        ObservableHost oh = new ObservableHost();
        host.setObservableHost(oh);
        oh.setHost(host);
        oh.setMapa(Servidor.obtenerTextoDeFile(fileMapa));
        
        oh.setJugadorGuest(nombreGuest.getNombre());
        oh.setInicial(Guardador.obtenerTexto(mapa.getTodosLosPlanetas(), new ArrayList<EnvioDeFlota>(), 0));
        
        
        
        
       
        
        
        
    }
    
    private ObservableGuess ob;
    public void iniciarJuegoCliente(FramePrincipal fp){
        this.fp=fp;
        ObservableGuess ob = new ObservableGuess();
        this.ob=ob;
        ob.setMdm(this);
        ob.mandarTexto("Intentar Conectar");
        
        
    }
    
    public void dibujarMapa(String mapaText,String inicioText,String jugador){
       ArchivoDeEntradaJson adej = new  ArchivoDeEntradaJson();
        adej.abrirtexto(mapaText, fp);
        ManejadorDeEntrada mde = new ManejadorDeEntrada();
        mde.abrirTexto(inicioText,fp, adej.getMapa(),false);
        
        
        Jugador jugadorGuest=null;
        for (int i = 0; i < adej.getMapa().getJugadores().size(); i++) {
            if (adej.getMapa().getJugadores().get(i).getNombre().equals(jugador)) {
                jugadorGuest=adej.getMapa().getJugadores().get(i);
                break;
            }
        }
        
        Guest guest = new Guest(jugadorGuest, adej.getMapa(),fp,fp.getCdt());
        guest.setObservableGuess(ob);
        ob.setGuest(guest);
        fp.getCdt().setGuest(guest);
        fp.setOnline(true);
        guest.empezarJuego();
        
    }
    
 
    
    
}
