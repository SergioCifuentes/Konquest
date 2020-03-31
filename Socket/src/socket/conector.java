/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;

/**
 *
 * @author sergio
 */
public class conector {
    ServerSocket server;
    java.net.Socket socket;
    int puerto=9000;
    DataOutputStream salida;
    BufferedReader entrada;
    
    
    public void inicialr(){
        try {
            server=new ServerSocket(puerto);
            socket= new java.net.Socket();
            socket=server.accept();
            entrada= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mensaje = entrada.readLine();
            System.out.println(mensaje);
            salida= new DataOutputStream(socket.getOutputStream());
            salida.writeUTF("Adios");
            socket.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}
