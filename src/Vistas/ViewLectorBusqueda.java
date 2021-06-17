/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import entidades.Lector;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.LectorData;

/**
 *
 * @author Ezequiel Coronel
 */
public class ViewLectorBusqueda extends javax.swing.JInternalFrame {

    private LectorData ld;

    public ViewLectorBusqueda() {
        initComponents();
        Conexion c = new Conexion();
        ld = new LectorData(c);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        jtfApellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtfDni = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtfDireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtfCodigoLector = new javax.swing.JTextField();
        jbBuscar = new javax.swing.JButton();
        jbActualizar = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();
        jbLimpiar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Busqueda de Lector");

        jLabel2.setText("Nombre:");

        jtfNombre.setEnabled(false);

        jtfApellido.setEnabled(false);

        jLabel3.setText("Apellido:");

        jLabel4.setText("Dni:");

        jtfDni.setEnabled(false);

        jLabel5.setText("Dirección:");

        jtfDireccion.setEnabled(false);

        jLabel6.setText("Codigo de lector(id):");

        jbBuscar.setText("Buscar");
        jbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarActionPerformed(evt);
            }
        });

        jbActualizar.setText("Actualizar");
        jbActualizar.setEnabled(false);
        jbActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbActualizarActionPerformed(evt);
            }
        });

        jbSalir.setText("Salir");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        jbLimpiar.setText("Limpiar");
        jbLimpiar.setEnabled(false);
        jbLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jtfDni, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtfCodigoLector, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addComponent(jbBuscar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jtfDireccion, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jtfApellido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbLimpiar)
                .addGap(13, 13, 13)
                .addComponent(jbSalir)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtfCodigoLector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBuscar))
                .addGap(36, 36, 36)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbActualizar)
                    .addComponent(jbSalir)
                    .addComponent(jbLimpiar))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
        try {
            int codigoLector = Integer.parseInt(jtfCodigoLector.getText());
            Lector lectorBuscado = ld.buscarLector(codigoLector);
            
            if (codigoLector > 0) {
                if (lectorBuscado != null) {
                    JOptionPane.showMessageDialog(this, "Lector encontrado!");
                    jtfCodigoLector.setEnabled(false);
                    jtfNombre.setText(lectorBuscado.getNombreLector());
                    jtfApellido.setText(lectorBuscado.getApellidoLector());
                    jtfDni.setText(String.valueOf(lectorBuscado.getDniLector()));
                    jtfDireccion.setText(lectorBuscado.getDireLector());
                    textFieldsEnabled(true);
                    jbActualizar.setEnabled(true);
                    jbLimpiar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "No existe un lector con ese codigo!");
                    limpiarTextFields();
                    textFieldsEnabled(false);
                    jbActualizar.setEnabled(false);
                    jtfCodigoLector.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese un codigo de lector valido!");
                limpiarTextFields();
                textFieldsEnabled(false);
                jbActualizar.setEnabled(false);
                jtfCodigoLector.setText("");
                jtfCodigoLector.requestFocus();
            }

        } catch (NullPointerException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un codigo de lector valido!");
            jtfCodigoLector.requestFocus();
        }
    }//GEN-LAST:event_jbBuscarActionPerformed

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        dispose();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jbActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbActualizarActionPerformed
        try{
            int dniLector = Integer.parseInt(jtfDni.getText());
            
            String nombreLector = jtfNombre.getText();
            String apellidoLector = jtfApellido.getText();
            String direLector = jtfDireccion.getText();
            
            if(nombreLector.isEmpty() || apellidoLector.isEmpty() || direLector.isEmpty()){
                JOptionPane.showMessageDialog(this, "Para actualizar a un lector se necesitan todos los datos!");
            }else if(tieneNum(nombreLector)){
                JOptionPane.showMessageDialog(this, "Colocar solo letras en el nombre del lector!");
                jtfNombre.requestFocus();
            }else if(tieneNum(apellidoLector)){
                JOptionPane.showMessageDialog(this, "Colocar solo letras en el apellido del lector!");
                jtfApellido.requestFocus();
            }else{
                int confirmarActualizacion = JOptionPane.showConfirmDialog(this, "Se esta por actualizar a un lector ¿Está seguro?");
                
                if(JOptionPane.OK_OPTION == confirmarActualizacion){
                    Lector lector = ld.buscarLector(Integer.parseInt(jtfCodigoLector.getText()));
                    lector.setNombreLector(nombreLector);
                    lector.setApellidoLector(apellidoLector);
                    lector.setDniLector(dniLector);
                    lector.setDireLector(direLector);
                    
                    ld.actualizarLector(lector);
                    limpiarTextFields();
                    textFieldsEnabled(false);
                    jtfCodigoLector.setEnabled(true);
                    jtfCodigoLector.setText("");
                    jbActualizar.setEnabled(false);
                    jbLimpiar.setEnabled(false);
                }else{
                    JOptionPane.showMessageDialog(this, "Se cancelo la actualizacion del lector");
                }
            }
            
        }catch(NullPointerException | NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Para actualizar a un lector se necesitan todos los datos y que sean correctos!");
        }
    }//GEN-LAST:event_jbActualizarActionPerformed

    private void jbLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLimpiarActionPerformed
        limpiarTextFields();
        textFieldsEnabled(false);
        jbLimpiar.setEnabled(false);
        jbActualizar.setEnabled(false);
        jtfCodigoLector.setText("");
        jtfCodigoLector.setEnabled(true);
        jtfCodigoLector.requestFocus();
    }//GEN-LAST:event_jbLimpiarActionPerformed

    public void textFieldsEnabled(boolean estado) {
        jtfNombre.setEnabled(estado);
        jtfApellido.setEnabled(estado);
        jtfDni.setEnabled(estado);
        jtfDireccion.setEnabled(estado);
    }

    public void limpiarTextFields() {
        jtfNombre.setText("");
        jtfApellido.setText("");
        jtfDni.setText("");
        jtfDireccion.setText("");
    }
    
    public boolean tieneNum(String str) {
        char aux[];

        aux = str.toCharArray();

        for (char c : aux) {
            if (Character.isDigit(c)) {
                return true;
            }
        }

        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton jbActualizar;
    private javax.swing.JButton jbBuscar;
    private javax.swing.JButton jbLimpiar;
    private javax.swing.JButton jbSalir;
    private javax.swing.JTextField jtfApellido;
    private javax.swing.JTextField jtfCodigoLector;
    private javax.swing.JTextField jtfDireccion;
    private javax.swing.JTextField jtfDni;
    private javax.swing.JTextField jtfNombre;
    // End of variables declaration//GEN-END:variables
}
