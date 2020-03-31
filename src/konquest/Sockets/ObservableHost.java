/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Sockets;

import java.util.Observable;
import java.util.Observer;
import konquest.Online.Host;

/**
 *
 * @author sergio
 */
public class ObservableHost implements Observer{
public  static final int PUERTO=6000;
private Servidor servidorHost;
private String mapa;
private String inicial;
private ManejadorDeMensajes mdm;
private Host host;
private String jugadorGuest;

    public void setJugadorGuest(String jugadorGuest) {
        this.jugadorGuest = jugadorGuest;
    }

    public void setHost(Host host) {
        this.host = host;
    }

private boolean conectado=false;

    public void setMdm(ManejadorDeMensajes mdm) {
        this.mdm = mdm;
    }

    public ObservableHost() {
        servidorHost= new Servidor(PUERTO);
        servidorHost.addObserver(this);
        Thread thread = new Thread(servidorHost);
        thread.start();
    }

    public void setInicial(String inicial) {
        this.inicial = inicial;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    
  public void mandarTexto(String text){
      Cliente cliente = new Cliente("127.0.0.1",ObservableGuess.PUERTO, text);
      Thread t = new Thread(cliente);
      t.start();
  }  
    
    
    @Override
    public void update(Observable o, Object arg) {
        if (!conectado) {
            host.mostrarConectado();
            conectado=true;
            mandarTexto(mapa);
            
        }else if(((String)arg).equals("MapaRecibido")){
            mandarTexto(inicial);
        }else if(((String)arg).equals("Nombre Jugador")){
            mandarTexto(jugadorGuest);
        }else{
            host.recibirTextoFlotas((String)arg);
        }
        
    }
    
}
