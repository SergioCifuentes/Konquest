/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Sockets;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.*;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergio
 */
public class Servidor extends Observable implements Runnable{
int puerto;

    public Servidor(int puerto) {
        this.puerto = puerto;
    }




    
    public static String obtenerTextoDeFile(File file) {
        String text = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (true) {
                String aux = br.readLine();
                if (aux == null) {
                    break;
                } else {
                    text += aux;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return text;
    }

    
    
    @Override
    public void run() {
     try {
         ServerSocket servidor=null;
         Socket sc=null;
         DataInputStream in;
         servidor=new ServerSocket(puerto);
         while (true) {
                
                sc = servidor.accept();
                in = new DataInputStream(sc.getInputStream());
                
                String mensaje = in.readUTF();
                System.out.println(mensaje);
                this.setChanged();
                this.notifyObservers(mensaje);
                this.clearChanged();
                sc.close();
                
            }

         
         
     } catch (IOException ex) {

            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
