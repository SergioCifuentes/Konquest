/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.ui;

import java.awt.Frame;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import konquest.Manejadores.Juego.ControlDeTurnos;
import konquest.Manejadores.Juego.EstadisticasJugador;
import konquest.Manejadores.Juego.Objetos.EnvioDeFlota;
import konquest.Escritores.CreadorArchivoReplay;
import konquest.Replay.RondasReplay;
import konquest.Escritores.EscritorDeMapas;
import konquest.contrladoresUI.Render;
import konquest.mapa.Jugador;

/**
 *
 * @author sergio
 */
public class Estadisticas extends javax.swing.JDialog {

    private ArrayList<Jugador> todos;   
    
    private  ArrayList<EnvioDeFlota> pendiente;
    private ArrayList<EnvioDeFlota> realizados;
    private Frame parent;
    private ControlDeTurnos cdt;

    public void setCdt(ControlDeTurnos cdt) {
        this.cdt = cdt;
    }
    /**
     * Creates new form EstadisticasJugador
     */
    public Estadisticas(java.awt.Frame parent, boolean modal, ArrayList<Jugador> todos, ArrayList<EnvioDeFlota> pendiente, ArrayList<EnvioDeFlota> realizados) {
        super(parent, modal);
        this.parent=parent;
        this.pendiente = pendiente;
        this.realizados = realizados;
        this.todos = todos;
        initComponents();
        btnGuardar.setVisible(false);
        agregarDatosATabla();
    }

    public Estadisticas(java.awt.Frame parent, boolean modal, ArrayList<Jugador> todos, ArrayList<Jugador> ganadores, ArrayList<EnvioDeFlota> pendiente, ArrayList<EnvioDeFlota> realizados) {
        super(parent, modal);
        this.todos = todos;
        this.pendiente = pendiente;
        this.realizados = realizados;
        initComponents();
        agregarDatosATabla();
        if (ganadores.size()>1) {
            String texto="";
            for (int i = 0; i < ganadores.size(); i++) {
                texto+=ganadores.get(i).getNombre()+" ";
            }
            this.setTitle("FELICIDADES "+texto+"Empate");
        }else{
            this.setTitle("FELICIDADES "+ganadores.get(0).getNombre()+" Ganaste!!");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEstadisticas = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaEstadisticas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Jugador", "Naves Producidas", "Planetas", "Envios", "Produccion ", "Flotas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaEstadisticas);
        if (tablaEstadisticas.getColumnModel().getColumnCount() > 0) {
            tablaEstadisticas.getColumnModel().getColumn(0).setMinWidth(14);
            tablaEstadisticas.getColumnModel().getColumn(0).setPreferredWidth(16);
            tablaEstadisticas.getColumnModel().getColumn(0).setMaxWidth(16);
        }

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnOk)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnOk))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.showOpenDialog(this);
        File file = jfc.getSelectedFile();
        if (file != null) {
            IngresoDeNombre i = new IngresoDeNombre(parent, true);
            i.setVisible(true);
            if (i.getNombre() != null) {
                ArrayList<EnvioDeFlota> envios= new ArrayList<>();
                for (int j = 0; j < cdt.getCdf().getEnviosEnProceso().size(); j++) {
                    envios.add(cdt.getCdf().getEnviosEnProceso().get(j));
                }
                for (int j = 0; j < cdt.getCdf().getEnviosRealizados().size(); j++) {
                    envios.add(cdt.getCdf().getEnviosRealizados().get(j));
                }
                CreadorArchivoReplay car =  new CreadorArchivoReplay();
                car.crearArchivo(car.crearFile(file, i.getNombre()),cdt.getMapa().getTodosLosPlanetas(),RondasReplay.ordenarEnviosRealizados(envios, false));
                

            }

        }
    }//GEN-LAST:event_btnGuardarActionPerformed
public void ocultarGuardar(){
    btnGuardar.setVisible(false);
}
    public void agregarDatosATabla() {
        tablaEstadisticas.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dtm = (DefaultTableModel) tablaEstadisticas.getModel();

        int rows = dtm.getRowCount();
        for (int i = 0; i < rows; i++) {
            dtm.removeRow(0);
        }

        for (int i = 0; i < todos.size(); i++) {
            JLabel jlabelcolor = new JLabel();
            jlabelcolor.setBackground(todos.get(i).getColor());

            jlabelcolor.setOpaque(true);

            dtm.addRow(new Object[]{jlabelcolor, todos.get(i).getNombre(), todos.get(i).getEstadisticas().getNavesProducidas(),
                todos.get(i).getPlanetas().size(),todos.get(i).getEstadisticas().obtenerEnvios(pendiente, realizados)
                    , todos.get(i).getEstadisticas().obtenerProducciones(), EstadisticasJugador.verificarNavesExistentes(todos.get(i), pendiente)});

        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnOk;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaEstadisticas;
    // End of variables declaration//GEN-END:variables
}
