/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego.Objetos;

import java.awt.Color;
import java.util.ArrayList;
import konquest.Manejadores.Juego.ControlDeTurnos;
import konquest.contrladoresUI.TextoDeAcciones;
import konquest.mapa.Jugador;

/**
 *
 * @author sergio
 */
public class ControlDeFlotas {

    private ArrayList<EnvioDeFlota> enviosRealizados;
    private ArrayList<EnvioDeFlota> enviosEnProceso;
    private ArrayList<EventoEnvio> eventos;
    private static final boolean MOSTRAR_TODOS_LOS_EVENTOS = false;

    public ControlDeFlotas() {
        enviosEnProceso = new ArrayList<>();
        enviosRealizados = new ArrayList<>();
        eventos = new ArrayList<>();

    }

    public ArrayList<EnvioDeFlota> getEnviosEnProceso() {
        return enviosEnProceso;
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
        if (cdt.getCdr().getRondaActual().getNumero() == 2) {
            cdt.getFramePrincipal().removerTextoAcciones();
        }
        int in = 0;
        for (int i = 0; i < enviosEnProceso.size(); i++) {

            if (cdt.getCdr().getRondaActual().getNumero() == enviosEnProceso.get(i).getTurnoDestino()) {
                eventos.add(realizarEnvio(enviosEnProceso.get(i)));
                enviosRealizados.add(enviosEnProceso.get(i));
                in++;
            } else {

                break;
            }

        }
        if (MOSTRAR_TODOS_LOS_EVENTOS) {
            this.informarEventos(eventos, cdt);
        } else {
            this.informarEventos(obtenerEventosDeHumanos(cdt.getCdr().getRondaActual().getNumero()), cdt);
        }
        for (int j = 0; j < in; j++) {
            enviosEnProceso.remove(0);
        }

        cdt.redibujarTablero();
        cdt.redibujarTablero();
    }

    public ArrayList<EnvioDeFlota> obtenerEnviosPorJugador(Jugador jugador, int ronda) {
        ordenarEnvios();
        ArrayList<EnvioDeFlota> envios = new ArrayList<>();
        for (int i = 0; i < enviosEnProceso.size(); i++) {
            if (enviosEnProceso.get(i).getRonda().getNumero() == ronda && enviosEnProceso.get(i).getOrdenador() == jugador) {
                envios.add(enviosEnProceso.get(i));
            } else if (enviosEnProceso.get(i).getRonda().getNumero() > ronda) {
                break;
            }
        }
        return envios;
    }

    public ArrayList<EnvioDeFlota> getEnviosRealizados() {
        return enviosRealizados;
    }

    private void informarEventos(ArrayList<EventoEnvio> edfs, ControlDeTurnos cdt) {
        if (edfs.size() > 0) {

            TextoDeAcciones.appendToPane(cdt.getFramePrincipal().getJTextPane(), "Ronda " + cdt.getCdr().getRondaActual().getNumero() + ": \n", Color.WHITE);

        }
        for (int i = 0; i < edfs.size(); i++) {
            edfs.get(i).appendTextConsola(cdt.getFramePrincipal().getJPanelAcciones(), cdt.getFramePrincipal().getJTextPane());
        }
    }

    private ArrayList<EventoEnvio> obtenerEventosDeHumanos(int ronda) {
        System.out.println(eventos.size() + "[];[SS");
        ArrayList<EventoEnvio> eventosDeHumano = new ArrayList<>();
        for (int i = 0; i < eventos.size(); i++) {
            if (eventos.get(i).getRonda() == ronda) {
                if (eventos.get(i).getTipo() == EventoEnvio.TIPO_CONQUISTA) {
                    eventosDeHumano.add(eventos.get(i));
                } else {
                    if (eventos.get(i).getOrigen().getOwner().getTipo() == Jugador.TIPO_HUMANO
                            || eventos.get(i).getDestino().getOwner().getTipo() == Jugador.TIPO_HUMANO) {
                        eventosDeHumano.add(eventos.get(i));
                    }
                }
            }
        }
        return eventosDeHumano;
    }

    public static EventoEnvio realizarEnvio(EnvioDeFlota envio) {
        if (envio.getDestino().getPlaneta().getOwner() != null) {
            if (envio.getDestino().getPlaneta().getOwner() == envio.getOrdenador()) {
                envio.getDestino().getPlaneta().recibirNavesAleadas(envio.getNaves());
                return new EventoEnvio(EventoEnvio.TIPO_RENFUERZOS, envio.getOrigen().getPlaneta(), envio.getDestino().getPlaneta(), envio.getNaves(), envio.getTurnoDestino());

            }
        }
        double ataqueOrigen = envio.getOrigen().getPlaneta().getPorcentajeMuertes() * envio.getNaves();
        double defensaDestino = envio.getDestino().getPlaneta().getPorcentajeMuertes() * envio.getDestino().getPlaneta().getNaves();

        if (ataqueOrigen > defensaDestino) {
            Double navesRestantes = (ataqueOrigen - defensaDestino) / envio.getOrigen().getPlaneta().getPorcentajeMuertes();
            if (!envio.getOrdenador().isVivo()) {
                envio.getOrdenador().setVivo(true);
            }
            if (envio.getDestino().getPlaneta().getOwner() != null) {
                
                envio.getDestino().getPlaneta().getOwner().getPlanetas().remove(envio.getDestino().getPlaneta());
            }
            envio.getOrdenador().agregarPlaneta(envio.getDestino().getPlaneta());
            if (navesRestantes.intValue()==0) {
                navesRestantes++;
            }
            envio.getDestino().getPlaneta().serConquistado(envio.getOrdenador(), navesRestantes.intValue());
            return new EventoEnvio(EventoEnvio.TIPO_CONQUISTA, envio.getOrigen().getPlaneta(), envio.getDestino().getPlaneta(), envio.getNaves(), envio.getTurnoDestino());
        } else {
            Double navesRestantes = (defensaDestino - ataqueOrigen) / envio.getDestino().getPlaneta().getPorcentajeMuertes();
            if (navesRestantes.intValue()==0) {
                navesRestantes++;
            }
            envio.getDestino().getPlaneta().setNaves(navesRestantes.intValue());

            return new EventoEnvio(EventoEnvio.TIPO_DEFENSA, envio.getOrigen().getPlaneta(), envio.getDestino().getPlaneta(), envio.getNaves(), envio.getTurnoDestino());
        }

    }

}
