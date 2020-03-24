/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego.Objetos;

import java.util.ArrayList;
import konquest.Manejadores.Juego.ControlDeTurnos;

/**
 *
 * @author sergio
 */
public class ControlDeFlotas {
    
    private ArrayList<EnvioDeFlota> enviosRealizados;
    private ArrayList<EnvioDeFlota> enviosEnProceso;
    private ArrayList<EventoEnvio> eventos;
    
    public ControlDeFlotas() {
        enviosEnProceso = new ArrayList<>();
        enviosRealizados = new ArrayList<>();
        eventos = new ArrayList<>();
        
    }
    
    public void GuardarEnvioPorTurno(ArrayList<EnvioDeFlota> enviosNuevos) {
        for (int i = 0; i < enviosNuevos.size(); i++) {
            enviosEnProceso.add(enviosNuevos.get(i));
        }
        ordenarEnvios();
    }
    
    private void ordenarEnvios() {
        EnvioDeFlota aux;
        boolean cambios;
        while (true) {
            cambios = false;
            for (int i = 1; i < enviosEnProceso.size(); i++) {
                if (enviosEnProceso.get(i).getTurnoDestino() < enviosEnProceso.get(i - 1).getTurnoDestino()) {
                    
                    aux = enviosEnProceso.get(i);
                    enviosEnProceso.set(i, enviosEnProceso.get(i - 1));
                    enviosEnProceso.set(i - 1, aux);
                    cambios = true;
                }
            }
            if (cambios == false) {
                break;
            }
        }
    }
    
    public void realizarEnviosDeRonda(ControlDeTurnos cdt) {
        ordenarEnvios();
        for (int i = 0; i < enviosEnProceso.size(); i++) {
            if (cdt.getCdr().getRondaActual().getNumero() == enviosEnProceso.get(i).getTurnoDestino()) {
                eventos.add(realizarEnvio(enviosEnProceso.get(i)));
                enviosRealizados.add(enviosEnProceso.get(i));
                
            } else {
                for (int j = 0; j < i; j++) {
                    enviosEnProceso.remove(0);
                }
                cdt.redibujarTablero();
                break;
            }
            
        }
    }
    
    private EventoEnvio realizarEnvio(EnvioDeFlota envio) {
        if (envio.getDestino().getPlaneta().getOwner() != null) {
            if (envio.getDestino().getPlaneta().getOwner() == envio.getOrdenador()) {
                envio.getDestino().getPlaneta().recibirNavesAleadas(envio.getNaves());
                return new EventoEnvio(EventoEnvio.TIPO_RENFUERZOS, envio.getOrigen().getPlaneta(), envio.getDestino().getPlaneta(), envio.getNaves());
                
            }
        }
        double ataqueOrigen = envio.getOrigen().getPlaneta().getPorcentajeMuertes() * envio.getNaves();
        double defensaDestino = envio.getDestino().getPlaneta().getPorcentajeMuertes() * envio.getDestino().getPlaneta().getNaves();
        System.out.println(ataqueOrigen + " vs " + defensaDestino);
        if (ataqueOrigen > defensaDestino) {
            Double navesRestantes = (ataqueOrigen - defensaDestino) / envio.getOrigen().getPlaneta().getPorcentajeMuertes();
            
            envio.getDestino().getPlaneta().serConquistado(envio.getOrdenador(), navesRestantes.intValue());
            return new EventoEnvio(EventoEnvio.TIPO_CONQUISTA, envio.getOrigen().getPlaneta(), envio.getDestino().getPlaneta(), envio.getNaves());
        } else {
            Double navesRestantes = (defensaDestino - ataqueOrigen) / envio.getDestino().getPlaneta().getPorcentajeMuertes();
            envio.getDestino().getPlaneta().setNaves(navesRestantes.intValue());
            
            return new EventoEnvio(EventoEnvio.TIPO_DEFENSA, envio.getOrigen().getPlaneta(), envio.getDestino().getPlaneta(), envio.getNaves());
        }
        
    }
    
}
