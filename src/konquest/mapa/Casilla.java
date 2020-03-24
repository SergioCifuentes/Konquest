/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.mapa;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import konquest.Manejadores.Juego.ControlDeTurnos;
import konquest.Manejadores.Juego.EleccionDePlaneta;
import konquest.Manejadores.Tablero.ControladorDeColores;

/**
 *
 * @author sergio
 */
public class Casilla extends JPanel {

    public final static String PATH_IMAGEN = "src/konquest/imagenes/";
    public final static String EXTENSION = ".png";
    public static final int ANCHO = 105;
    public static final int ALTO = 64;
    private int columna;
    private int fila;
    private Planeta planeta = null;
    private EleccionDePlaneta edp;
    private JLabel imagen = new JLabel();
    private JLabel nombre = new JLabel();
    private JLabel naves = new JLabel();
    private ControlDeTurnos cdt;
    private boolean mapaCiego;
    //PlanetasNeutrales
    private boolean mostrarNaves;
    private boolean mostrarEstadisticas;

    public Casilla(ControlDeTurnos cdt, EleccionDePlaneta edp) {
        this.cdt = cdt;
        this.setBackground(new Color(15, 16, 49));
        this.edp = edp;
        this.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(this);
        this.setLayout(jPanel1Layout);
        nombre.setForeground(Color.BLACK);
        naves.setForeground(Color.BLACK);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(nombre)
                                        .addComponent(naves))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(naves))
        );

    }

    public void setCdt(ControlDeTurnos cdt) {
        this.cdt = cdt;
    }

    public Planeta getPlaneta() {
        return planeta;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void reDibujar() {

        if (planeta.isNeutral()) {
            if (mostrarNaves) {
                naves.setText(String.valueOf(planeta.getNaves()));
            }
            this.setBackground(ControladorDeColores.NEUTRALES);
        } else {
            naves.setText(String.valueOf(planeta.getNaves()));
            if (planeta.getOwner().getColor() != null) {
                this.setBackground(planeta.getOwner().getColor());
            }

        }
        this.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PanelMouseExited(evt);
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelMouseClicked(evt);
            }
        });
    }

    public void setPlaneta(Planeta planeta, Mapa mapa) {
        generarImagenPlaneta();
        nombre.setText(planeta.getNombre());
        this.planeta = planeta;
        this.mapaCiego = mapa.isMapaCiego();
        this.mostrarEstadisticas = mapa.getConfiNeutrales().isMostrarEstadisticas();
        this.mostrarNaves = mapa.getConfiNeutrales().isMostrarNaves();

        if (planeta.isNeutral()) {
            if (mapa.getConfiNeutrales().isMostrarNaves()) {
                naves.setText(String.valueOf(planeta.getNaves()));
            }
            this.setBackground(ControladorDeColores.NEUTRALES);
        } else {
            naves.setText(String.valueOf(planeta.getNaves()));
            if (planeta.getOwner().getColor() != null) {
                this.setBackground(planeta.getOwner().getColor());
            }
        }

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PanelMouseEntered(evt);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PanelMouseExited(evt);
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelMouseClicked(evt);
            }
        });

    }

    private void PanelMouseClicked(java.awt.event.MouseEvent evt) {
        if (planeta != null) {
            edp.obtenerEleccionPlaneta(this);
        }

    }

    private void PanelMouseEntered(java.awt.event.MouseEvent evt) {

        this.setBackground(ControladorDeColores.obtenerColorAlSeleccionar(this.getBackground()));

        if (getPlaneta().getOwner() != null) {
            if (cdt.getJugadorEnTurnoActual().equals(this.planeta.getOwner())) {
                this.setToolTipText("<html>Planeta: " + getPlaneta().getNombre() + "<br>"
                        + "Dueño: " + getPlaneta().getOwner().getNombre() + "<br>"
                        + "Naves: " + getPlaneta().getNaves() + "<br>"
                        + "Produccion: " + getPlaneta().getProduccion() + "<br>"
                        + "PorcentajeMuertes: " + getPlaneta().getPorcentajeMuertes() + "</html>");
            } else {
                if (mapaCiego) {
                    this.setToolTipText(null);
                } else {
                    this.setToolTipText("<html> Planeta: " + getPlaneta().getNombre() + "<br>"
                            + "Dueño: " + getPlaneta().getOwner().getNombre() + "<br>"
                            + "Naves: " + getPlaneta().getNaves() + "<br>"
                            + "Produccion: " + getPlaneta().getProduccion() + "<br>"
                            + "PorcentajeMuertes: " + getPlaneta().getPorcentajeMuertes() + "</html>");
                }
            }
        } else {
            if (mostrarEstadisticas) {
                String textToolTip = "";
                textToolTip += "<html> Planeta: " + getPlaneta().getNombre() + "<br>";
                if (mostrarNaves) {
                    textToolTip += "Naves: " + getPlaneta().getNaves() + "<br>";
                }
                textToolTip += "Produccion: " + getPlaneta().getProduccion() + "<br>"
                        + "PorcentajeMuertes: " + getPlaneta().getPorcentajeMuertes() + "</html>";
                this.setToolTipText(textToolTip);
            } else {
                if (mostrarNaves) {
                    this.setToolTipText("<html>Planeta: " + getPlaneta().getNombre() + "<br>"
                            + "Naves: " + getPlaneta().getNaves() + "</html>");
                }
            }
        }

    }

    private void PanelMouseExited(java.awt.event.MouseEvent evt) {
        if (getPlaneta().getOwner() == null) {
            this.setBackground(ControladorDeColores.NEUTRALES);
        } else {
            this.setBackground(getPlaneta().getOwner().getColor());
        }

    }

    private void generarImagenPlaneta() {
        int numero = (int) (Math.random() * 6 + 1);

        imagen.setIcon(new ImageIcon(PATH_IMAGEN + numero + EXTENSION));

    }

}
