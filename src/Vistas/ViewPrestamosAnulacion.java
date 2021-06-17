/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import entidades.Lector;
import entidades.Prestamo;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import modelo.PrestamoData;

/**
 *
 * @author Chony
 */
public class ViewPrestamosAnulacion extends javax.swing.JInternalFrame {

    private PrestamoData prestamo;
    private DefaultTableModel modelo;
    public ViewPrestamosAnulacion() {
        initComponents();
        Conexion c=new Conexion();
        prestamo=new PrestamoData(c);
        modelo=new DefaultTableModel(); 
        jbAnular.setEnabled(false);
        jdcFechas.setEnabled(false);
        jbBuscar.setEnabled(false);
    }
     public void armarCabeceraActivos(){
        ArrayList<Object> columnas= new ArrayList<>();
        columnas.add("IDprestamo");
        columnas.add("IDejemplar");
        columnas.add("IDlector");
        columnas.add("Fecha prestamo");
        
        
        
        for(Object it: columnas){
            modelo.addColumn(it);
        }
        jtPrestamos.setModel(modelo);
        
    }
      public void armarCabeceraVencidos(){
        ArrayList<Object> columnas= new ArrayList<>();
        columnas.add("IDlector");
        columnas.add("Apellido");
        columnas.add("Nombre");
        columnas.add("DNI");
        columnas.add("Direccion");
        
        
        
        for(Object it: columnas){
            modelo.addColumn(it);
        }
        jtPrestamos.setModel(modelo);
        
    }
     public void llenarActivos(){
         List<Prestamo> list=prestamo.prestamosVigentes();
         for(Prestamo p:list){
             modelo.addRow(new Object[]{p.getIdPrestamo(),p.getEjemplar().getId_ejemplar(),p.getLector().getId_lector(),p.getFecha_prestamo()});
             
         }
     }
     public void llenarVencidos(){
         List<Lector> list=prestamo.prestamosVencidos();
         for(Lector l:list){
             modelo.addRow(new Object[]{l.getId_lector(),l.getApellidoLector(),l.getNombreLector(),l.getDniLector(),l.getDireLector()});
         }
     }
     public void llenarFecha(){
         try{
             LocalDate fecha=jdcFechas.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
              List<Prestamo> list=prestamo.prestamoXFecha(fecha);
              for(Prestamo p:list){
                    modelo.addRow(new Object[]{p.getIdPrestamo(),p.getEjemplar().getId_ejemplar(),p.getLector().getId_lector(),p.getFecha_prestamo()});
                }
         }catch(NullPointerException nfe){
             JOptionPane.showMessageDialog(this,"Error en la fecha");
         }

     }
     public void eliminarCabecera(){
         
     }
     public void limpiarTabla(){
        int a=modelo.getRowCount()-1;
        
        for(int i=a;i>=0;i--){
            modelo.removeRow(i);
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
        jtPrestamos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jrActivos = new javax.swing.JRadioButton();
        jrbVencidos = new javax.swing.JRadioButton();
        jbAnular = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jrcPrestFecha = new javax.swing.JRadioButton();
        jdcFechas = new com.toedter.calendar.JDateChooser();
        jbBuscar = new javax.swing.JButton();

        jtPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtPrestamos);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Consultas de Prestamos");

        jrActivos.setText("Prestamos Activos");
        jrActivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrActivosActionPerformed(evt);
            }
        });

        jrbVencidos.setText("Prestamos Vencidos");
        jrbVencidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbVencidosActionPerformed(evt);
            }
        });

        jbAnular.setText("Anular");
        jbAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAnularActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jrcPrestFecha.setText("Prestamos por Fecha");
        jrcPrestFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrcPrestFechaActionPerformed(evt);
            }
        });

        jbBuscar.setText("Buscar");
        jbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(191, 191, 191))
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jbAnular)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jrActivos)
                        .addGap(18, 18, 18)
                        .addComponent(jrbVencidos)
                        .addGap(42, 42, 42)
                        .addComponent(jrcPrestFecha)
                        .addGap(18, 18, 18)
                        .addComponent(jdcFechas, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbBuscar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addComponent(jdcFechas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jrActivos)
                            .addComponent(jrbVencidos)
                            .addComponent(jrcPrestFecha))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbBuscar)
                        .addGap(21, 21, 21)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbAnular)
                    .addComponent(jButton2))
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jrActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrActivosActionPerformed
        jrActivos.setSelected(true);
        jrbVencidos.setSelected(false);
        jrcPrestFecha.setSelected(false);
        limpiarTabla();
        armarCabeceraActivos();
        llenarActivos();
        jbAnular.setEnabled(true);
    }//GEN-LAST:event_jrActivosActionPerformed

    private void jrbVencidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbVencidosActionPerformed
        jrActivos.setSelected(false);
        jrbVencidos.setSelected(true);
        jrcPrestFecha.setSelected(false);
        limpiarTabla();
        armarCabeceraVencidos();
        llenarVencidos();
    }//GEN-LAST:event_jrbVencidosActionPerformed

    private void jbAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAnularActionPerformed
        int fe=jtPrestamos.getSelectedRow();
        if(fe!=-1){
            if(jrActivos.isSelected()){
                int id=(Integer)modelo.getValueAt(fe, 0);
                Prestamo presta=prestamo.buscarPrestamo(id);
                prestamo.anularPrestamo(presta);
                JOptionPane.showMessageDialog(this, "Se anulo el prestamo seleccionado");
            }else{
                 JOptionPane.showMessageDialog(this, "Regularice la situacion");
            }
            
        }
    }//GEN-LAST:event_jbAnularActionPerformed

    private void jrcPrestFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrcPrestFechaActionPerformed
        jdcFechas.setEnabled(true);
        jrActivos.setSelected(false);
        jrbVencidos.setSelected(false);
        jrcPrestFecha.setSelected(true);
        jbBuscar.setEnabled(true);
        limpiarTabla();
         
    }//GEN-LAST:event_jrcPrestFechaActionPerformed

    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
        limpiarTabla();
        armarCabeceraActivos();
        llenarFecha();
    }//GEN-LAST:event_jbBuscarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAnular;
    private javax.swing.JButton jbBuscar;
    private com.toedter.calendar.JDateChooser jdcFechas;
    private javax.swing.JRadioButton jrActivos;
    private javax.swing.JRadioButton jrbVencidos;
    private javax.swing.JRadioButton jrcPrestFecha;
    private javax.swing.JTable jtPrestamos;
    // End of variables declaration//GEN-END:variables
}
