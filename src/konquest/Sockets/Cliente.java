/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Sockets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergio
 */
public class Cliente implements Runnable{

    private  String host ;
    
     private int puerto;
     private String mensaje;

      
    public Cliente(String host,int puerto,String mensaje) {
        this.host=host;
        this.puerto = puerto;
        this.mensaje=mensaje;
    }



    @Override
    public void run() {
        try {
            Socket sc = new Socket(host, puerto);
            DataOutputStream out=null;
            out = new DataOutputStream(sc.getOutputStream());
            
            out.writeUTF(mensaje);
            sc.close(); 
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
