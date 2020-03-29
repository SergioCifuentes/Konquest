/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.ui;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import konquest.Escritores.CreadorArchivoReplay;
import konquest.Escritores.Guardador;
import konquest.Manejadores.Json.ArchivoDeEntradaJson;
import konquest.Manejadores.Juego.ControlDeTurnos;
import konquest.Manejadores.Juego.Objetos.EnvioDeFlota;
import konquest.Manejadores.Juego.Objetos.Ronda;
import konquest.Replay.ManejadorDeEntrada;
import konquest.Replay.RondasReplay;
import konquest.contrladoresUI.TextoDeAcciones;
import konquest.mapa.Casilla;
import konquest.mapa.Jugador;

/**
 *
 * @author sergio
 */
public class FramePrincipal extends javax.swing.JFrame {

    public final static ImageIcon FONDO = new ImageIcon("src/konquest/imagenes/Fondo.jpg");
    private File file;
    private Casilla primerCasilla;
    private Casilla segundaCasilla;
    private boolean calcularDistancia;
    private int naves;
    private final static String TEXTO_TERMINAR_TURNO = "Fin Turno";
    private final static String TEXTO_ENVIAR = "Enviar";
    private ControlDeTurnos cdt;
    private RondasReplay rr;
    private JPanel pane;
    private boolean replay;
    private boolean ganador;

    /**
     * Creates new form FramePrincipal
     */
    public FramePrincipal() {
        initComponents();
        agregarFondo();
        replay=false;
        ganador=false;
        btnRegresar.setVisible(false);
        btnSiguiente.setVisible(false);
        btnCancel.setVisible(false);
        pane = new JPanel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        barTurnos = new javax.swing.JToolBar();
        lblTurno = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblInstruccion = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnCalcularDistancia = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnEstadisticas = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnFlotas = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        numeroTopas = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        btnEnvio = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        lblRonda = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        paneAcciones = new javax.swing.JTextPane();
        btnRegresar = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        menu = new javax.swing.JMenuBar();
        menuOpen = new javax.swing.JMenu();
        menuItemOpen = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        menuItemOnline = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        memuItemNew = new javax.swing.JMenuItem();
        menuItemEdit = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        itemGuardar = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu4.setText("Edit");
        jMenuBar1.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setBackground(new java.awt.Color(31, 30, 28));

        panel.setBackground(new java.awt.Color(15, 16, 49));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 980, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panel);

        barTurnos.setBackground(new java.awt.Color(254, 254, 254));
        barTurnos.setFloatable(false);
        barTurnos.setOpaque(false);

        lblTurno.setFont(new java.awt.Font("URW Gothic L", 1, 15)); // NOI18N
        lblTurno.setForeground(new java.awt.Color(254, 254, 254));
        lblTurno.setText("Bienvenido");
        barTurnos.add(lblTurno);

        jLabel1.setFont(new java.awt.Font("URW Gothic L", 0, 15)); // NOI18N
        jLabel1.setForeground(java.awt.Color.blue);
        jLabel1.setText("                ");
        barTurnos.add(jLabel1);

        lblInstruccion.setForeground(new java.awt.Color(119, 119, 119));
        lblInstruccion.setText("                                  ");
        barTurnos.add(lblInstruccion);

        jLabel4.setText("                       ");
        barTurnos.add(jLabel4);

        btnCalcularDistancia.setBackground(new java.awt.Color(15, 16, 49));
        btnCalcularDistancia.setFont(new java.awt.Font("URW Gothic L", 0, 15)); // NOI18N
        btnCalcularDistancia.setForeground(new java.awt.Color(254, 254, 254));
        btnCalcularDistancia.setText("Calcular Distancia");
        btnCalcularDistancia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(254, 254, 254), 1, true));
        btnCalcularDistancia.setEnabled(false);
        btnCalcularDistancia.setFocusable(false);
        btnCalcularDistancia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCalcularDistancia.setOpaque(true);
        btnCalcularDistancia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCalcularDistancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularDistanciaActionPerformed(evt);
            }
        });
        barTurnos.add(btnCalcularDistancia);

        jLabel5.setText(" ");
        barTurnos.add(jLabel5);

        btnEstadisticas.setBackground(new java.awt.Color(15, 16, 49));
        btnEstadisticas.setFont(new java.awt.Font("URW Gothic L", 0, 15)); // NOI18N
        btnEstadisticas.setForeground(new java.awt.Color(254, 254, 254));
        btnEstadisticas.setText("Estadisticas");
        btnEstadisticas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(254, 254, 254), 1, true));
        btnEstadisticas.setEnabled(false);
        btnEstadisticas.setFocusable(false);
        btnEstadisticas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEstadisticas.setOpaque(true);
        btnEstadisticas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadisticasActionPerformed(evt);
            }
        });
        barTurnos.add(btnEstadisticas);

        jLabel6.setText(" ");
        barTurnos.add(jLabel6);

        btnFlotas.setBackground(new java.awt.Color(15, 16, 49));
        btnFlotas.setFont(new java.awt.Font("URW Gothic L", 0, 15)); // NOI18N
        btnFlotas.setForeground(new java.awt.Color(254, 254, 254));
        btnFlotas.setText("Flotas");
        btnFlotas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(254, 254, 254), 1, true));
        btnFlotas.setEnabled(false);
        btnFlotas.setFocusable(false);
        btnFlotas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFlotas.setOpaque(true);
        btnFlotas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFlotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFlotasActionPerformed(evt);
            }
        });
        barTurnos.add(btnFlotas);

        jLabel2.setText("                                    ");
        jLabel2.setMaximumSize(new java.awt.Dimension(1000, 17));
        barTurnos.add(jLabel2);

        numeroTopas.setToolTipText("");
        numeroTopas.setEnabled(false);
        numeroTopas.setMaximumSize(new java.awt.Dimension(50, 2147483647));
        numeroTopas.setMinimumSize(new java.awt.Dimension(50, 27));
        numeroTopas.setPreferredSize(new java.awt.Dimension(50, 27));
        numeroTopas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroTopasActionPerformed(evt);
            }
        });
        barTurnos.add(numeroTopas);

        jLabel7.setText(" ");
        barTurnos.add(jLabel7);

        btnEnvio.setText("Enviar");
        btnEnvio.setEnabled(false);
        btnEnvio.setFocusable(false);
        btnEnvio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEnvio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnvioActionPerformed(evt);
            }
        });
        barTurnos.add(btnEnvio);

        jLabel3.setText("            ");
        barTurnos.add(jLabel3);

        btnCancel.setBackground(java.awt.Color.red);
        btnCancel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(254, 254, 254));
        btnCancel.setText("Cancelar");
        btnCancel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnCancel.setFocusable(false);
        btnCancel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        barTurnos.add(btnCancel);

        lblRonda.setText("Ronda: 0");

        paneAcciones.setBackground(new java.awt.Color(1, 1, 1));
        jScrollPane4.setViewportView(paneAcciones);

        jScrollPane3.setViewportView(jScrollPane4);

        btnRegresar.setBackground(new java.awt.Color(254, 254, 254));
        btnRegresar.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(23, 13, 99));
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnSiguiente.setBackground(new java.awt.Color(254, 254, 254));
        btnSiguiente.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btnSiguiente.setForeground(new java.awt.Color(23, 13, 99));
        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        menuOpen.setText("Game");

        menuItemOpen.setText("Jugar");
        menuItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOpenActionPerformed(evt);
            }
        });
        menuOpen.add(menuItemOpen);

        jMenuItem1.setText("Cargar Partida");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuOpen.add(jMenuItem1);

        jMenuItem2.setText("Replay");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuOpen.add(jMenuItem2);

        menuItemOnline.setText("Online");
        menuOpen.add(menuItemOnline);

        menu.add(menuOpen);

        jMenu2.setText("File");

        memuItemNew.setText("Nueva Mapa");
        memuItemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memuItemNewActionPerformed(evt);
            }
        });
        jMenu2.add(memuItemNew);

        menuItemEdit.setText("Editar");
        menuItemEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEditActionPerformed(evt);
            }
        });
        jMenu2.add(menuItemEdit);

        menu.add(jMenu2);

        jMenu5.setText("Partida");

        itemGuardar.setText("Guardar");
        itemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGuardarActionPerformed(evt);
            }
        });
        jMenu5.add(itemGuardar);

        menu.add(jMenu5);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barTurnos, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblRonda, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSiguiente)
                        .addGap(158, 158, 158))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(barTurnos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblRonda)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRegresar)
                        .addComponent(btnSiguiente))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemOpenActionPerformed

        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.showOpenDialog(this);
        file = jfc.getSelectedFile();
        if (file != null) {
            removerTextoAcciones();
            panel.removeAll();
            ArchivoDeEntradaJson adej = new ArchivoDeEntradaJson();
            adej.abrirAchivo(file, this, true);

        }
    }//GEN-LAST:event_menuItemOpenActionPerformed

    private void numeroTopasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroTopasActionPerformed
        enviar();
    }//GEN-LAST:event_numeroTopasActionPerformed

    private void btnCalcularDistanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularDistanciaActionPerformed
        calcularDistancia = true;
        ocultarCancel(false);
    }//GEN-LAST:event_btnCalcularDistanciaActionPerformed

    private void btnEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadisticasActionPerformed
        if (replay) {
            rr.mostrarEstadistics();
        }else{
            cdt.mostrarEstadistics();
        }
    }//GEN-LAST:event_btnEstadisticasActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        pedirPrimerPlaneta();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnvioActionPerformed
        if (btnEnvio.getText().equals(TEXTO_ENVIAR)) {
            enviar();
        } else {
            cdt.finalizarTurno();
        }
    }//GEN-LAST:event_btnEnvioActionPerformed

    private void btnFlotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFlotasActionPerformed
        FlotasPendientes flota = new FlotasPendientes(this, calcularDistancia, cdt);
        flota.setVisible(true);
    }//GEN-LAST:event_btnFlotasActionPerformed

    private void menuItemEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEditActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.showOpenDialog(this);
        file = jfc.getSelectedFile();
        if (file != null) {
            removerTextoAcciones();
            panel.removeAll();
            ArchivoDeEntradaJson adej = new ArchivoDeEntradaJson();
            adej.abrirAchivoParaEditar(file, this);

        }
    }//GEN-LAST:event_menuItemEditActionPerformed

    private void memuItemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memuItemNewActionPerformed
        EditadorMapas ed = new EditadorMapas(this, calcularDistancia, this);
        ed.setVisible(true);
    }//GEN-LAST:event_memuItemNewActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Seleccione El Archivo Mapa .JSON");
        
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.showOpenDialog(this);
        file = jfc.getSelectedFile();
        if (file != null) {
            removerTextoAcciones();
            panel.removeAll();
            ArchivoDeEntradaJson adej = new ArchivoDeEntradaJson();
            adej.abrirAchivo(file, this, false);
            if (adej.getMapa() != null) {
                JFileChooser jfc1 = new JFileChooser();
                jfc1.setDialogTitle("Seleccione El Archivo Replay .JSON");
                jfc1.setFileSelectionMode(JFileChooser.FILES_ONLY);
                jfc1.showOpenDialog(this);
                file = jfc1.getSelectedFile();
                if (file != null) {
                    ManejadorDeEntrada mde = new ManejadorDeEntrada();
                    mde.abrirAchivo(file, this, adej.getMapa(),true);
                }
            }
        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Seleccione El Archivo Mapa .JSON");
        
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.showOpenDialog(this);
        file = jfc.getSelectedFile();
        if (file != null) {
            removerTextoAcciones();
            panel.removeAll();
            ArchivoDeEntradaJson adej = new ArchivoDeEntradaJson();
            adej.abrirAchivo(file, this, false);
            if (adej.getMapa() != null) {
                JFileChooser jfc1 = new JFileChooser();
                jfc1.setDialogTitle("Seleccione El Archivo Guardado .JSON");
                jfc1.setFileSelectionMode(JFileChooser.FILES_ONLY);
                jfc1.showOpenDialog(this);
                file = jfc1.getSelectedFile();
                if (file != null) {
                    ManejadorDeEntrada mde = new ManejadorDeEntrada();
                    mde.abrirAchivo(file, this, adej.getMapa(),false);
                }
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        rr.nextRonda();
        btnRegresar.setEnabled(true);
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        rr.returnRonda();
        btnSiguiente.setEnabled(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void itemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGuardarActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.showOpenDialog(this);
        File file = jfc.getSelectedFile();
        if (file != null) {
            IngresoDeNombre i = new IngresoDeNombre(this, true);
            i.setVisible(true);
            if (i.getNombre() != null) {
                ArrayList<EnvioDeFlota> envios= new ArrayList<>();
                for (int j = 0; j < cdt.getCdf().getEnviosEnProceso().size(); j++) {
                    envios.add(cdt.getCdf().getEnviosEnProceso().get(j));
                }
                for (int j = 0; j < cdt.getCdf().getEnviosRealizados().size(); j++) {
                    envios.add(cdt.getCdf().getEnviosRealizados().get(j));
                }
                Guardador guardador= new Guardador();
                guardador.crearArchivo(CreadorArchivoReplay.crearFile(file,i.getNombre()), cdt.getMapa().getTodosLosPlanetas(), RondasReplay.ordenarEnviosRealizados(envios, false),cdt.getCdr().getRondaActual().getNumero()-1);
                JOptionPane.showMessageDialog(this, "Partida Guardada");
                panel.removeAll();
                panel.repaint();
            }

        }
    }//GEN-LAST:event_itemGuardarActionPerformed
    private void enviar() {
        if (numeroTopas.getText() != null) {
            try {
                naves = Integer.parseInt(numeroTopas.getText());
                if (naves <= primerCasilla.getPlaneta().getNaves()) {
                    cdt.terminarEnvio();
                } else {
                    JOptionPane.showMessageDialog(this, "No Cuentas Con Esa Cantidad De Naves \nEn El Planeta Origen", "Error", JOptionPane.ERROR_MESSAGE);
                    naves = 0;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Debes Ingresar Un Numero Entero", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debes Ingresar el Numero De Naves", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JPanel getJPanel() {
        return panel;
    }

    public int getNaves() {
        return naves;
    }

    private void ocultarCancel(boolean bo) {
        btnCancel.setVisible(!bo);
        if (bo) {
            btnEnvio.setEnabled(true);
            btnEnvio.setText(TEXTO_TERMINAR_TURNO);
        } else {
            btnEnvio.setEnabled(false);
            btnEnvio.setText(TEXTO_ENVIAR);
        }
    }

    private void agregarFondo() {
        JLabel fondo = new JLabel();
        fondo.setLocation(0, 0);
        fondo.setBounds(0, 0, 3840, 2400);
        fondo.setIcon(FONDO);
        this.add(fondo);
        fondo.setVisible(true);

    }

    public boolean isGanador() {
        itemGuardar.setEnabled(false);
        return ganador;
    }

    public void agregarTextoAcciones(String texto) {

        TextoDeAcciones.appendToPane(paneAcciones, texto, Color.WHITE);
    }

    public void removerTextoAcciones() {
        paneAcciones.setText("");
    }

    public void obtenerInfoDeTurno(Jugador jugador, Ronda ronda) {
        itemGuardar.setEnabled(true);
        ganador=false;
        lblTurno.setText("Turno: " + jugador.getNombre());
        lblRonda.setText("Ronda: " + ronda.getNumero());

        pedirPrimerPlaneta();
    }

    private void resetVariables() {
        
        primerCasilla = null;
        segundaCasilla = null;
        calcularDistancia = false;
        ocultarCancel(true);
        btnEstadisticas.setEnabled(true);
        btnCalcularDistancia.setEnabled(true);
        btnFlotas.setEnabled(true);
        numeroTopas.setText("");
        numeroTopas.setEnabled(false);

    }

    public void pedirPrimerPlaneta() {
        itemGuardar.setEnabled(true);
        resetVariables();

        lblInstruccion.setText("\tElije Su Planeta Origen");
    }

    public void setCdt(ControlDeTurnos cdt) {
        this.cdt = cdt;
    }

    public void setRr(RondasReplay rr) {
        this.rr = rr;
    }

    public void setPrimerPlaneta(Casilla prPlaneta) {
         System.out.println("seeetts");
        this.primerCasilla = prPlaneta;
        pedirSegundoPlaneta();
    }

    public void pedirSegundoPlaneta() {
        itemGuardar.setEnabled(false);
        ocultarCancel(false);
        btnCalcularDistancia.setEnabled(false);
        if (calcularDistancia) {
            lblInstruccion.setText("\t Distancia:" + primerCasilla.getPlaneta().getNombre() + " -> Elije Su Planeta Destino");
        } else {
            lblInstruccion.setText("\t" + primerCasilla.getPlaneta().getNombre() + " -> Elije Su Planeta Destino");
        }

    }

    public void setReplay(boolean replay) {
        this.replay = replay;
    }
    

    public void setSegundaCasilla(Casilla segunda) {
        this.segundaCasilla = segunda;
        pedirNumeroNaves();
    }

    private void pedirNumeroNaves() {
        lblInstruccion.setText(primerCasilla.getPlaneta().getNombre() + "->" + segundaCasilla.getPlaneta().getNombre() + " Ingrese La Cantidad De Naves");
        numeroTopas.setEnabled(true);
        btnEnvio.setEnabled(true);
    }

    /**
     * @param args the command line arguments
     */
    public Casilla getPrimerCasilla() {
        System.out.println("nsnsns "+primerCasilla);
        return primerCasilla;
    }

    public Casilla getSegundaCasilla() {
       
        return segundaCasilla;
    }

    public boolean isCalcularDistancia() {
        return calcularDistancia;
    }

    public JPanel getJPanelAcciones() {
        return pane;
    }

    public JTextPane getJTextPane() {
        return paneAcciones;
    }

    public void MarcarGanador(){
        if (replay) {
            btnSiguiente.setEnabled(false);
        }
        itemGuardar.setEnabled(false);
        panel.setEnabled(false);
        ganador=true;
    }
    public void empezarReplay(int ronda){
        itemGuardar.setEnabled(false);
        btnEstadisticas.setEnabled(true);
        ganador=false;
        if (!replay) {
         replay=true;
         btnRegresar.setVisible(true);
         btnSiguiente.setVisible(true);
        }
        if (ronda==1) {
            btnRegresar.setEnabled(false);
        }else{
            btnRegresar.setEnabled(true);
        }
        lblRonda.setText("Ronda: " + ronda);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barTurnos;
    private javax.swing.JButton btnCalcularDistancia;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEnvio;
    private javax.swing.JButton btnEstadisticas;
    private javax.swing.JButton btnFlotas;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JMenuItem itemGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblInstruccion;
    private javax.swing.JLabel lblRonda;
    private javax.swing.JLabel lblTurno;
    private javax.swing.JMenuItem memuItemNew;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem menuItemEdit;
    private javax.swing.JMenuItem menuItemOnline;
    private javax.swing.JMenuItem menuItemOpen;
    private javax.swing.JMenu menuOpen;
    private javax.swing.JPasswordField numeroTopas;
    private javax.swing.JTextPane paneAcciones;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
