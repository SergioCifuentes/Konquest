/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Juego;

import javax.swing.JOptionPane;
import konquest.Manejadores.Tablero.Distancias;
import konquest.mapa.Casilla;
import konquest.mapa.Planeta;
import konquest.ui.FramePrincipal;

/**
 *
 * @author sergio
 */
public class EleccionDePlaneta {
    private FramePrincipal fp;
    private ControlDeTurnos controlDeTurnos;

    public EleccionDePlaneta(FramePrincipal fp, ControlDeTurnos controlDeTurnos) {
        this.fp = fp;
        this.controlDeTurnos = controlDeTurnos;
    }
    public void obtenerEleccionPlaneta(Casilla casilla){
        if (fp.isCalcularDistancia()) {
            if (fp.getPrimerCasilla()==null) {
                fp.setPrimerPlaneta(casilla);
            }else{
                if (!casilla.getPlaneta().getNombre().equals(fp.getPrimerCasilla().getPlaneta().getNombre())) {
                    int rondas=Distancias.calcularDistancia( fp.getPrimerCasilla(),casilla);
                    String año;
                    if (rondas==1) {
                        año="Año";
                    }else{
                        año="Años";
                    }
                    JOptionPane.showMessageDialog(fp, 
                            "La distancia es de "+rondas+" "+año+" Luz\n Llegar en la Ronda "+(controlDeTurnos.getCdr().getRondaActual().getNumero()+rondas)
                            ,"Distancia "+fp.getPrimerCasilla().getPlaneta().getNombre()+" -> "+casilla.getPlaneta().getNombre()
                            ,JOptionPane.INFORMATION_MESSAGE);
                    fp.pedirPrimerPlaneta();
                }
            }
            
        }else{
            if (fp.getPrimerCasilla()!=null) {
                if (!fp.getPrimerCasilla().getPlaneta().getNombre().equals(casilla.getPlaneta().getNombre())) {
                     fp.setSegundaCasilla(casilla);
                }
               
            }else{
                if (casilla.getPlaneta().getOwner()==controlDeTurnos.getJugadorEnTurnoActual()) {
                    fp.setPrimerPlaneta(casilla);
                }else{
                    JOptionPane.showMessageDialog(fp, "El planeta Origen Debe Ser Tuyo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
}
