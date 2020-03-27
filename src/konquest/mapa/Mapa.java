/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.mapa;

import java.awt.Dimension;
import java.util.ArrayList;
import konquest.ui.FramePrincipal;

/**
 *
 * @author sergio
 */
public class Mapa {

    private String id;
    private Casilla[][] casillas;
    private boolean alAzar = false;
    private int numeroPlanetasNeutrales;
    private boolean mapaCiego = false;
    private boolean acumular = false;
    private Dimension dimension;
    private ConfiguracionNeutrales confiNeutrales;
    private ArrayList<Planeta> planetas;
    private ArrayList<PlanetaNeutral> planetasNeutrales;
    private ArrayList<Jugador> jugadores;
    private Integer finalizado;
    
    public Mapa(Object[] atributos) {
        id = ((String)atributos[0]).substring(1,((String)atributos[0]).length()-1);
        dimension = (Dimension) atributos[1];
        if (atributos[2] == null) {
            alAzar = false;
        } else {
            alAzar = (Boolean) atributos[2];
        }

        if (atributos[3] != null) {
            numeroPlanetasNeutrales = (Integer) atributos[3];
        }

        if (atributos[4] == null) {
            mapaCiego = false;
        } else {
            mapaCiego = (Boolean) atributos[4];
        }

        if (atributos[5] == null) {
            acumular = false;
        } else {
            acumular = (Boolean) atributos[5];
        }

        confiNeutrales = (ConfiguracionNeutrales) atributos[6];
        finalizado = (Integer) atributos[7];
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public ArrayList<Planeta> getPlanetas() {
        return planetas;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<PlanetaNeutral> getPlanetasNeutrales() {
        return planetasNeutrales;
    }

    
    
    public String getId() {
        return id;

    }
    

    public void setAlAzar(boolean alAzar) {
        this.alAzar = alAzar;
    }

    
    public void setCasillas(Casilla[][] casillas) {
        this.casillas = casillas;
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }

    public boolean isAlAzar() {
        return alAzar;
    }

    public void setMapaCiego(boolean mapaCiego) {
        this.mapaCiego = mapaCiego;
    }

    public void setAcumular(boolean acumular) {
        this.acumular = acumular;
    }
    

    public int getNumeroPlanetasNeutrales() {
        return numeroPlanetasNeutrales;
    }

    public boolean isMapaCiego() {
        return mapaCiego;
    }

    public boolean isAcumular() {
        return acumular;
    }

    public ConfiguracionNeutrales getConfiNeutrales() {
        return confiNeutrales;
    }

    public Integer getFinalizado() {
        return finalizado;
    }
    

    public static boolean verificarObligatorios(Object[] atributos, FramePrincipal fp) {
        if ((atributos[0] == null || atributos[0] == "") || (atributos[1] == null || atributos[1] == "") || (atributos[6] == null || atributos[6] == "")) {
            fp.agregarTextoAcciones("Atributo(s) Obligatorio Faltante:\n");
            if ((atributos[0] == null || atributos[0] == "")) {
                fp.agregarTextoAcciones("\t - ID\n");
            }
            if ((atributos[1] == null || atributos[1] == "")) {
                fp.agregarTextoAcciones("\t - Tamaño\n");
            }
            if ((atributos[6] == null || atributos[6] == "")) {
                fp.agregarTextoAcciones("\t - Neutrales\n");
            }
            return false;
        }

        if (!(atributos[2] == null || atributos[2] == "") && (atributos[3] == null || atributos[3] == "")) {
            if ((Boolean) atributos[2] && (atributos[3] == null || atributos[3] == "")) {
                fp.agregarTextoAcciones("planetasNuetrales es Obligatorio si azar es 'true'\n");
                return false;
            }
        } else {

        }

        return true;
    }

    public static boolean verificacionesFinales(Mapa mapa,FramePrincipal fp) {
        boolean aux=false;
        ArrayList<Planeta> planetasAux=new ArrayList<>();
        for (int i = 0; i < mapa.getPlanetas().size(); i++) {
            planetasAux.add(mapa.getPlanetas().get(i));
        }
        
        for (int i = 0; i < mapa.getPlanetasNeutrales().size(); i++) {
            planetasAux.add(mapa.getPlanetasNeutrales().get(i));
        }
        if (Planeta.verificarNombresIguales(planetasAux, fp)) {
           aux=true;
        }
        if ((mapa.getDimension().height*mapa.getDimension().width)<(mapa.planetas.size()+mapa.planetasNeutrales.size())) {
            fp.agregarTextoAcciones("El numero De Planetas Supera El De Espacios \n");
            aux=true;
        }
        if (Jugador.verifiacarPlanetasJugador(mapa.getJugadores(),mapa.getPlanetas(), fp)) {
            aux=true;
        }
        return aux;
    }

    public ArrayList<Planeta> getTodosLosPlanetas(){
        ArrayList<Planeta> todos = new ArrayList<>();
        for (int i = 0; i < planetas.size(); i++) {
            todos.add(planetas.get(i));
        }
        for (int i = 0; i < planetasNeutrales.size(); i++) {
            todos.add(planetasNeutrales.get(i));
        }
        return todos;
    }
    
    public static boolean comprobarComponetesDeConstruicion(ArrayList<Object> componentes, FramePrincipal fp) {
        Mapa mapa = null;
        ArrayList<Planeta> plane = null;
        ArrayList<PlanetaNeutral> planeNeu = null;
        ArrayList<Jugador> jugadores = null;
        for (int i = 0; i < componentes.size(); i++) {
            try {
                mapa = (Mapa) componentes.get(i);
                
            } catch (Exception e0) {
                try {
                    PlanetaNeutral planeNeu2 =(PlanetaNeutral) ((ArrayList<PlanetaNeutral>) componentes.get(i)).get(0);
                     planeNeu =(ArrayList<PlanetaNeutral>) componentes.get(i);
                } catch (Exception e) {
                    try {
                        Planeta plane2 =(Planeta) ((ArrayList<Planeta>) componentes.get(i)).get(0);
                        plane = (ArrayList<Planeta>) componentes.get(i);
                    } catch (Exception e2) {
                        try {
                            Jugador jugador2 =(Jugador) ((ArrayList<Jugador>) componentes.get(i)).get(0);
                            jugadores = (ArrayList<Jugador>) componentes.get(i);
                        } catch (Exception e3) {

                        }
                    }
                }
            }
        }
                if (mapa == null || jugadores == null) {
            if (mapa == null) {
                fp.agregarTextoAcciones("MAPA Es Obligatorio\n");
            }
            if (jugadores == null) {
                fp.agregarTextoAcciones("JUGADORES Es Obligatorio\n");
            }
            
            return true;
        }
        if (!mapa.alAzar) {
            if (plane == null || planeNeu == null) {

                if (plane == null) {
                    fp.agregarTextoAcciones("PLANETAS Es Obligatorio si Alazar Es false\n");
                }
                if (planeNeu == null) {
                    fp.agregarTextoAcciones("PLANETAS_NEUTRALES Es Obligatorio si Alazar Es false\n");
                }
                return true;
            }
        } else {
            if (plane != null) {
                    fp.agregarTextoAcciones("PLANETAS Sera Ignorado ya que Alazar Es true\n");
                }
                if (planeNeu != null) {
                    fp.agregarTextoAcciones("PLANETAS_NEUTRALES Sera Ignorado ya que Alazar Es true\n");
                }
        }

        return false;
    }

    public static Mapa construirMapa(ArrayList<Object> componentes) {
        Mapa mapa = null;
        int aux = 0;
        for (int i = 0; i < componentes.size(); i++) {
            try {
                mapa = (Mapa) componentes.get(i);
                aux = i;
                break;
            } catch (Exception e) {

            }
        }

        for (int i = 0; i < componentes.size(); i++) {
            if (i != aux) {
                try {
                    PlanetaNeutral planeNeu2 =(PlanetaNeutral) ((ArrayList<PlanetaNeutral>) componentes.get(i)).get(0);
                    ArrayList<PlanetaNeutral> pn = (ArrayList<PlanetaNeutral>) componentes.get(i);
                    mapa.planetasNeutrales = pn;
                } catch (Exception e) {
                    try {
                         Planeta plane2 =(Planeta) ((ArrayList<Planeta>) componentes.get(i)).get(0);
                        ArrayList<Planeta> p = (ArrayList<Planeta>) componentes.get(i);
                        mapa.planetas = p;
                    } catch (Exception e2) {
                        try {
                                Jugador jugador2 =(Jugador) ((ArrayList<Jugador>) componentes.get(i)).get(0);
                            ArrayList<Jugador> j = (ArrayList<Jugador>) componentes.get(i);
                            mapa.jugadores = j;
                        } catch (Exception e3) {

                        }
                    }
                }
            }
        }
        PlanetaNeutral.ingresarProduccion(mapa.getPlanetasNeutrales(),mapa.getConfiNeutrales().getProduccion());
        return mapa;
    }

    public Dimension getDimension() {
        return dimension;
    }

}
