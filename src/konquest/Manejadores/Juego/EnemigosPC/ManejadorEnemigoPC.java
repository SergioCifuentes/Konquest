/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego.EnemigosPC;

import java.util.ArrayList;
import konquest.Manejadores.Juego.Objetos.EnvioDeFlota;
import konquest.Manejadores.Juego.Objetos.Ronda;
import konquest.Manejadores.Tablero.Distancias;
import konquest.mapa.*;

/**
 *
 * @author sergio
 */
public class ManejadorEnemigoPC {

    private Jugador jugador;
    private Mapa mapa;
    private Ronda ronda;
    private final static int DIFERENCIA_NECESARIA_PARA_RENFUERZOS_FACIL = 4;
    private final static int DIFERENCIA_NECESARIA_PARA_RENFUERZOS_DIFICIL = 3;
    private final static Double PORCENTAJE_DE_NAVES_PARA_ATAQUE_DOBLE = 0.23;

    public ManejadorEnemigoPC(Mapa mapa, Jugador jugador, Ronda ronda) {
        this.jugador = jugador;
        this.mapa = mapa;
        this.ronda = ronda;
    }

    public ArrayList<EnvioDeFlota> realizarAtaques(ArrayList<EnvioDeFlota> pendientes) {
        if (jugador.getTipo() == Jugador.TIPO_FACIL) {
            return realizarAtaquesFacil();
        } else {
            return realizarAtaquesDificl(pendientes);
        }

    }

    public ArrayList<EnvioDeFlota> realizarAtaquesFacil() {
        ArrayList<Planeta> planetas = jugador.getPlanetas();
        ArrayList<EnvioDeFlota> envios = new ArrayList<>();
        EnvioDeFlota renfuerzos = verificarRenfuerzosNecesarios(DIFERENCIA_NECESARIA_PARA_RENFUERZOS_FACIL);
        if (renfuerzos != null) {
            envios.add(renfuerzos);
        }
        ArrayList<EnvioDeFlota> enviosaPlanetasCercanas = atacarPlanetasMasCercandos();
        for (int i = 0; i < enviosaPlanetasCercanas.size(); i++) {
            envios.add(enviosaPlanetasCercanas.get(i));
        }

        return envios;
    }

    public EnvioDeFlota verificarRenfuerzosNecesarios(int diferencia) {
        ArrayList<Planeta> planetas = jugador.getPlanetas();
    
        if (planetas.size() > 1) {
            Planeta menor = planetas.get(0);
            Planeta mayor = planetas.get(0);
            for (int i = 1; i < planetas.size(); i++) {
                if (planetas.get(i).getNaves() < menor.getNaves()) {
                    menor = planetas.get(i);
                }
                if (planetas.get(i).getNaves() > mayor.getNaves()) {
                    mayor = planetas.get(i);
                }
            }
            if (menor != mayor) {
                if (mayor.getNaves() / DIFERENCIA_NECESARIA_PARA_RENFUERZOS_FACIL > menor.getNaves()) {
                    mayor.restarNaves(mayor.getNaves() / 8);
                    return new EnvioDeFlota(obtenerCasilla(mayor), obtenerCasilla(menor),
                            jugador, mayor.getNaves() / 8, ronda,
                            Distancias.calcularDistancia(obtenerCasilla(mayor), obtenerCasilla(menor)) + ronda.getNumero());
                }
            }

        }
        return null;
    }

    public ArrayList<EnvioDeFlota> atacarPlanetasMasCercandos() {
        ArrayList<EnvioDeFlota> envios = new ArrayList<>();
        ArrayList<Planeta> planetasCercanos = new ArrayList<>();
        ArrayList<Planeta> origenesMasCercanos = new ArrayList<>();
        Planeta planetaCercano = null;
        Planeta origenMasCercano = null;
        int distancia = 10;

        for (int i = 0; i < mapa.getPlanetas().size(); i++) {
            if (!mapa.getPlanetas().get(i).getOwner().getNombre().equals(jugador.getNombre())) {
                for (int j = 0; j < jugador.getPlanetas().size(); j++) {
                    if (planetaCercano == null) {
                        planetaCercano = mapa.getPlanetas().get(i);
                        distancia = Distancias.calcularDistancia(obtenerCasilla(planetaCercano), obtenerCasilla(jugador.getPlanetas().get(j)));
                        origenMasCercano = jugador.getPlanetas().get(j);
                    } else {
                        if (Distancias.calcularDistancia(obtenerCasilla(mapa.getPlanetas().get(i)), obtenerCasilla(jugador.getPlanetas().get(j))) < distancia) {
                            planetaCercano = mapa.getPlanetas().get(i);
                            distancia = Distancias.calcularDistancia(obtenerCasilla(planetaCercano), obtenerCasilla(jugador.getPlanetas().get(j)));
                            origenMasCercano = jugador.getPlanetas().get(j);
                        }
                    }
                }

            }
        }
        
        if (planetaCercano != null) {
            planetasCercanos.add(planetaCercano);
            origenesMasCercanos.add(origenMasCercano);
        }

        origenMasCercano = null;
        planetaCercano = null;
        distancia = 10;
        for (int i = 0; i < mapa.getPlanetasNeutrales().size(); i++) {
            

            if (mapa.getPlanetasNeutrales().get(i).isNeutral() || !mapa.getPlanetasNeutrales().get(i).getOwner().getNombre().equals(jugador.getNombre())) {
                for (int j = 0; j < jugador.getPlanetas().size(); j++) {

                    if (planetaCercano == null) {
                        planetaCercano = mapa.getPlanetasNeutrales().get(i);
                        distancia = Distancias.calcularDistancia(obtenerCasilla(mapa.getPlanetasNeutrales().get(i)), obtenerCasilla(jugador.getPlanetas().get(j)));
                        origenMasCercano = jugador.getPlanetas().get(j);
                    } else {
                        if (Distancias.calcularDistancia(obtenerCasilla(mapa.getPlanetasNeutrales().get(i)), obtenerCasilla(jugador.getPlanetas().get(j))) < distancia) {
                            planetaCercano = mapa.getPlanetasNeutrales().get(i);
                            distancia = Distancias.calcularDistancia(obtenerCasilla(mapa.getPlanetasNeutrales().get(i)), obtenerCasilla(jugador.getPlanetas().get(j)));
                            origenMasCercano = jugador.getPlanetas().get(j);
                        }
                    }
                }
            }

        }
        
        if (planetaCercano != null) {
            planetasCercanos.add(planetaCercano);
            origenesMasCercanos.add(origenMasCercano);
        }

        double coeficienteMenorDeAtaque = 0;
        for (int i = 0; i < planetasCercanos.size(); i++) {
            
          

            int naves = planetasCercanos.get(i).getNaves();
            int distanciaDeOrigenACercano = Distancias.calcularDistancia(obtenerCasilla(planetasCercanos.get(i)), obtenerCasilla(origenesMasCercanos.get(i)));
            for (int j = 0; j < distanciaDeOrigenACercano; j++) {

                if (mapa.isAcumular()) {
                    naves += planetasCercanos.get(i).getProduccion();
                    naves += j + 1;
                } else {
                    naves += planetasCercanos.get(i).getProduccion();
                }
            }
            coeficienteMenorDeAtaque = naves * planetasCercanos.get(i).getPorcentajeMuertes();
            Double navesNecesariasDeOrigen = coeficienteMenorDeAtaque / origenesMasCercanos.get(i).getPorcentajeMuertes() + 1;
            int navesNes = navesNecesariasDeOrigen.intValue();
            if (ronda.getNumero() <= 3) {
                if (navesNes < origenesMasCercanos.get(i).getNaves()) {
                    Casilla casilla1 = obtenerCasilla(origenesMasCercanos.get(i));
                    Casilla casilla2 = obtenerCasilla(planetasCercanos.get(i));
                    envios.add(new EnvioDeFlota(casilla1, casilla2,
                            jugador, navesNes, ronda,
                            ronda.getNumero() + Distancias.calcularDistancia(casilla1, casilla2)));
                    origenesMasCercanos.get(i).restarNaves(navesNes);
                }
            } else {

                if (navesNes >= origenesMasCercanos.get(i).getNaves() * 0.75) {
                    Planeta segundoOrigen = null;
                    int distanciaSegundo = 0;
                    for (int j = 0; j < jugador.getPlanetas().size(); j++) {
                        if (jugador.getPlanetas().get(j) != origenesMasCercanos.get(i)) {
                            int dAux = Distancias.calcularDistancia(obtenerCasilla(jugador.getPlanetas().get(j)), obtenerCasilla(planetasCercanos.get(i)));
                            if (segundoOrigen == null) {
                                segundoOrigen = jugador.getPlanetas().get(j);
                                distanciaSegundo = dAux;
                            } else if (dAux < distanciaSegundo) {
                                segundoOrigen = jugador.getPlanetas().get(j);
                                distanciaSegundo = dAux;
                            }
                        }
                    }
                    if (segundoOrigen != null) {
                        
                        if (distanciaSegundo == distanciaDeOrigenACercano) {
                            if (navesNes < (origenesMasCercanos.get(i).getNaves() * 0.75 + (segundoOrigen.getNaves() * 0.75 * segundoOrigen.getPorcentajeMuertes()) / origenesMasCercanos.get(i).getPorcentajeMuertes())) {
                                int navesAmandar = Double.valueOf(origenesMasCercanos.get(i).getNaves() * 0.75).intValue();
                                Casilla casilla1 = obtenerCasilla(origenesMasCercanos.get(i));
                                Casilla casilla2 = obtenerCasilla(planetasCercanos.get(i));
                                envios.add(new EnvioDeFlota(casilla1, casilla2,
                                        jugador, navesAmandar, ronda,
                                        ronda.getNumero() + Distancias.calcularDistancia(casilla1, casilla2)));
                                origenesMasCercanos.get(i).restarNaves(navesAmandar);

                                navesAmandar = Double.valueOf((segundoOrigen.getNaves() * 0.75 * segundoOrigen.getPorcentajeMuertes()) / origenesMasCercanos.get(i).getPorcentajeMuertes()).intValue();
                                casilla1 = obtenerCasilla(segundoOrigen);
                                envios.add(new EnvioDeFlota(casilla1, casilla2,
                                        jugador, navesAmandar, ronda,
                                        ronda.getNumero() + Distancias.calcularDistancia(casilla1, casilla2)));
                                segundoOrigen.restarNaves(navesAmandar);
                            }
                        } else {
                            int navesAmandar = Double.valueOf((segundoOrigen.getNaves() * 0.35)).intValue() ;
                            
                            Casilla casilla1 = obtenerCasilla(segundoOrigen);
                            Casilla casilla2 = obtenerCasilla(planetasCercanos.get(i));
                            envios.add(new EnvioDeFlota(casilla1, casilla2,
                                    jugador, navesAmandar, ronda,
                                    ronda.getNumero() + Distancias.calcularDistancia(casilla1, casilla2)));
                            segundoOrigen.restarNaves(navesAmandar);
                        }
                    }

                } else {
                    Casilla casilla1 = obtenerCasilla(origenesMasCercanos.get(i));
                    Casilla casilla2 = obtenerCasilla(planetasCercanos.get(i));
                    envios.add(new EnvioDeFlota(casilla1, casilla2,
                            jugador, navesNes, ronda,
                            ronda.getNumero() + Distancias.calcularDistancia(casilla1, casilla2)));
                    origenesMasCercanos.get(i).restarNaves(navesNes);
                }
            }

        }

        return envios;
    }

    public ArrayList<EnvioDeFlota> realizarAtaquesDificl(ArrayList<EnvioDeFlota> pendientes) {

        ArrayList<Planeta> planetas = jugador.getPlanetas();
        ArrayList<EnvioDeFlota> envios = new ArrayList<>();
        EnvioDeFlota renfuerzos = verificarRenfuerzosNecesarios(DIFERENCIA_NECESARIA_PARA_RENFUERZOS_DIFICIL);
        if (renfuerzos != null) {
            envios.add(renfuerzos);
        }

        for (int i = 0; i < pendientes.size(); i++) {
            if (pendientes.get(i).getDestino().getPlaneta().getOwner() == null || pendientes.get(i).getDestino().getPlaneta().getOwner() == jugador) {
                for (int j = 0; j < planetas.size(); j++) {
                    Casilla casillaAux = obtenerCasilla(planetas.get(j));
                    int distancia = Distancias.calcularDistancia(casillaAux, pendientes.get(i).getDestino());
                    if (pendientes.get(i).getTurnoDestino() == ronda.getNumero() + distancia) {

                        Double naves = planetas.get(j).getNaves() * PORCENTAJE_DE_NAVES_PARA_ATAQUE_DOBLE;

                        planetas.get(j).recibirNavesAleadas(naves.intValue());
                        envios.add(new EnvioDeFlota(casillaAux, pendientes.get(i).getDestino(), jugador, naves.intValue(), ronda, distancia + ronda.getNumero()));
                    }
                }

            }
        }

        ArrayList<EnvioDeFlota> enviosaPlanetasCercanas = atacarPlanetasMasCercandos();
        for (int i = 0; i < enviosaPlanetasCercanas.size(); i++) {
            envios.add(enviosaPlanetasCercanas.get(i));
        }

        return envios;

    }

    public Casilla obtenerCasilla(Planeta p) {
        for (int i = 0; i < mapa.getCasillas().length; i++) {
            for (int j = 0; j < mapa.getCasillas()[0].length; j++) {
                if (mapa.getCasillas()[i][j].getPlaneta() != null) {

                    if (mapa.getCasillas()[i][j].getPlaneta().getNombre().equals(p.getNombre())) {
                        return mapa.getCasillas()[i][j];
                    }
                }
            }
        }
        return null;
    }

}
