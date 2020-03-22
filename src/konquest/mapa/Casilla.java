/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.mapa;

import java.awt.Color;
import javafx.scene.control.Tooltip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import konquest.Manejadores.Tablero.ControladorDeColores;
import sun.awt.X11.InfoWindow;

/**
 *
 * @author sergio
 */
public class Casilla extends JPanel{
    public final static String PATH_IMAGEN="src/konquest/imagenes/";
    public final static String EXTENSION=".png";
    public static final int ANCHO=105;
    public static final int ALTO=64;
    private Planeta planeta=null;
    private JLabel imagen=new JLabel();
    private JLabel nombre=new JLabel();
    private JLabel naves=new JLabel();
    public Casilla(){
        this.setBackground(Color.CYAN);
        
        this.setOpaque(true);
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(this);
        this.setLayout(jPanel1Layout);
        
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
    public Planeta getPlaneta() {
        return planeta;
    }

    public void setPlaneta(Planeta planeta,Mapa mapa) {
        generarImagenPlaneta();
        nombre.setText(planeta.getNombre());
        this.planeta = planeta;
        try {
            PlanetaNeutral pn =(PlanetaNeutral) planeta;
            if (mapa.getConfiNeutrales().isMostrarNaves()) {
                naves.setText(String.valueOf(planeta.getNaves()));
            }
            this.setBackground(ControladorDeColores.NEUTRALES);
        } catch (Exception e) {
            naves.setText(String.valueOf(planeta.getNaves()));
            if (planeta.getOwner().getColor()!=null) {
                this.setBackground(planeta.getOwner().getColor());
            }
        }
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PanelMouseExited(evt);
            }
        });
        
        
        
        
    }
    
    
    private void PanelMouseEntered(java.awt.event.MouseEvent evt) { 
        
        this.setBackground(ControladorDeColores.obtenerColorAlSeleccionar(this.getBackground()));
    } 
    private void PanelMouseExited(java.awt.event.MouseEvent evt) {
        if (getPlaneta().getOwner()==null) {
            this.setBackground(ControladorDeColores.NEUTRALES);
        }else{
            this.setBackground(getPlaneta().getOwner().getColor());
        }
        
    }
    
    
    private void generarImagenPlaneta(){
        int numero = (int) (Math.random()*5+1);
        
        imagen.setIcon(new ImageIcon(PATH_IMAGEN+numero+EXTENSION));
        
        
    }
    
}
