/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.mapa;

import java.awt.Color;
import java.util.ArrayList;
import konquest.Manejadores.Juego.EstadisticasJugador;
import konquest.ui.FramePrincipal;

/**
 *
 * @author sergio
 */
public class Jugador {

    private String nombre;
    private ArrayList<Planeta> planetas;
    private ArrayList<String> nombresPlaneta;
    private int tipo;
    private Color color;
    private boolean vivo;
    private EstadisticasJugador estadisticas;

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
    /*1 HUMANO
    2 DIFICIL
    3 FACIL    
     */
    public static final int TIPO_HUMANO = 1;
    public static final int TIPO_DIFICIL = 2;
    public static final int TIPO_FACIL = 3;

    public Color getColor() {
        return color;
    }

    public void setTipo(int tipo){
        this.tipo=tipo;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    
    public Jugador(Object[] atributos) {
        String aux = ((String) atributos[0]).substring(1, ((String) atributos[0]).length() - 1);
        ArrayList<String> aux2= new ArrayList<>();
        if (atributos[1]!=null) {
            for (int i = 0; i < ((ArrayList<String>)atributos[1]).size(); i++) {
            String nombreSinComillas=((ArrayList<String>)atributos[1]).get(i).substring(1, ((ArrayList<String>)atributos[1]).get(i).length()-1);
            aux2.add(nombreSinComillas);
        }
        }
        
        this.nombre = aux;
        this.nombresPlaneta = aux2;
        this.tipo = (Integer) atributos[2];
        vivo=true;
        estadisticas=new EstadisticasJugador(this);
    }

    public EstadisticasJugador getEstadisticas() {
        return estadisticas;
    }

    public static boolean verificarObligatorios(Object[] atributos) {
        for (int i = 0; i < 3; i++) {
            if (atributos[i] == null || atributos[i] == "") {
                return false;
            }
        }
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Planeta> getPlanetas() {
        return planetas;
    }

    public void agregarPlaneta(Planeta p) {
        if (planetas == null) {
            planetas = new ArrayList<>();
        }
        planetas.add(p);
    }

    public ArrayList<String> getNombresPlaneta() {
        return nombresPlaneta;
    }

    public int getTipo() {
        return tipo;
    }

    public static boolean verifiacarPlanetasJugador(ArrayList<Jugador> jugadores, ArrayList<Planeta> planetas, FramePrincipal fp) {
        boolean aux = false;
        boolean encontrado = false;

        //Verificar que existan los planetas
        for (int i = 0; i < jugadores.size(); i++) {
            for (int j = 0; j < jugadores.get(i).nombresPlaneta.size(); j++) {
                encontrado = false;
                for (int k = 0; k < planetas.size(); k++) {
                    if (planetas.get(k).getNombre().equals(jugadores.get(i).getNombresPlaneta().get(j))) {
                        jugadores.get(i).agregarPlaneta(planetas.get(k));
                        
                        planetas.get(k).setOwner(jugadores.get(i));
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    fp.agregarTextoAcciones("No se Encuentra El Planeta Del Jugador " + jugadores.get(i).nombre
                            + ", Planeta:" + jugadores.get(i).getNombresPlaneta().get(j) + "\n");
                    aux=true;
                }
                jugadores.get(i).setVivo(true);
            }
        }
        for (int i = 0; i < jugadores.size(); i++) {
            for (int l = 0; l < jugadores.get(i).getNombresPlaneta().size(); l++) {
                for (int j = i; j < jugadores.size(); j++) {
                    if (i==jugadores.size()-1&&l==jugadores.get(i).getNombresPlaneta().size()-1) {
                        break;
                    }
                    if (j==i&&l==jugadores.get(i).getNombresPlaneta().size()-1) {
                        j++;
                    }
                    for (int k = 0; k < jugadores.get(j).getNombresPlaneta().size(); k++) {
                        if (j==i&&k==l) {
                            k++;
                        }
                        if (jugadores.get(i).getNombresPlaneta().get(l).equals(jugadores.get(j).getNombresPlaneta().get(k))) {
                            fp.agregarTextoAcciones("Nombres De Planetas Se Repiten Para Jugador");
                            if (i==j) {
                                fp.agregarTextoAcciones(" : \n -"+jugadores.get(i).getNombre()+"\n");
                            }else{
                                fp.agregarTextoAcciones("s : \n -"+jugadores.get(i).getNombre()+"\n -"+jugadores.get(j).getNombre()+"\n");
                            }
                            fp.agregarTextoAcciones("Nombre Del Planeta: "+jugadores.get(i).getNombresPlaneta().get(l)+"\n");
                            aux=true;
                        }
                    }

                }
            }
        }

        return aux;
    }
}
