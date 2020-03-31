/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Sockets;

import java.util.Observable;
import java.util.Observer;
import konquest.Online.Guest;
import konquest.mapa.Jugador;

/**
 *
 * @author sergio
 */
public class ObservableGuess implements Observer {

    private String textoRecibido;
    private Servidor servidor;
    private String mapa = null;
    private String inicial = null;
    private String nombreJugador = null;
    public static final int PUERTO = 4000;
    private ManejadorDeMensajes mdm;
    private Guest guest;

    public ObservableGuess() {
        servidor = new Servidor(PUERTO);
        servidor.addObserver(this);
        Thread thread = new Thread(servidor);
        thread.start();

    }

    public void mandarTexto(String text) {
        Cliente cliente = new Cliente("127.0.0.1", ObservableHost.PUERTO, text);
        Thread t = new Thread(cliente);
        t.start();
    }

    public String getTextoRecibido() {
        return textoRecibido;
    }

    private void obtenerMapa(String s) {
        mapa = s;
        mandarTexto("MapaRecibido");
    }

    private void obtenerInical(String texto) {
        inicial = texto;
        mandarTexto("Nombre Jugador");
        
        

    }
    private void obtenerJugador(String texto) {
        nombreJugador = texto;
        mdm.dibujarMapa(mapa, inicial,nombreJugador);

    }

    public void setMdm(ManejadorDeMensajes mdm) {
        this.mdm = mdm;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("recibido::::"+(String)arg);
        if (mapa == null) {
            obtenerMapa((String) arg);
        } else if (inicial == null) {
            obtenerInical((String) arg);
        } else if(nombreJugador == null)        
        {
            obtenerJugador((String) arg);
        }else{
            
            guest.recibirTextoFlotas((String)arg);
            
            
            
            
            
        }

    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    

}
