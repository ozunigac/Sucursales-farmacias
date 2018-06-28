/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica7;

import Clases.Conexion;
import Clases.Empleado;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 *
 * @author DELL
 */
public class AltaEmpleado extends javax.swing.JFrame {
    Conexion cliente;
    int tipo;
    Empleado empleado;
    /**
     * Creates new form g
     * @param cli
     * @param tipo
     */
    public AltaEmpleado(Conexion cli,int tip,Empleado emp) {
        tipo=tip;
        cliente = cli;
        empleado=emp;
        initComponents();
        this.setLocationRelativeTo(null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        altaEmpleado = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TxtNombre = new javax.swing.JTextField();
        TxtApellidoP = new javax.swing.JTextField();
        TxtApellidoM = new javax.swing.JTextField();
        BtnAceptarEmpleado = new javax.swing.JButton();
        BtnBorrar = new javax.swing.JButton();
        BtnSalir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        TxtDireccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TxtUsuario = new javax.swing.JTextField();
        TxtPass = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        editarEmpleado = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        id_rol = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        altaEmpleado.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        altaEmpleado.setText("Alta Empleado");
        altaEmpleado.setPreferredSize(new java.awt.Dimension(340, 29));
        jPanel1.add(altaEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 77, 173, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Nombre:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 138, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Apellido Paterno:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 167, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Apellido Materno:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));
        jPanel1.add(TxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 138, 172, -1));
        jPanel1.add(TxtApellidoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 167, 172, -1));
        jPanel1.add(TxtApellidoM, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 205, 172, -1));

        BtnAceptarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/success.png"))); // NOI18N
        BtnAceptarEmpleado.setText("Aceptar");
        BtnAceptarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAceptarEmpleadoActionPerformed(evt);
            }
        });
        jPanel1.add(BtnAceptarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 401, -1, -1));

        BtnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eraser.png"))); // NOI18N
        BtnBorrar.setText("Borrar");
        BtnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBorrarActionPerformed(evt);
            }
        });
        jPanel1.add(BtnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 396, 99, -1));

        BtnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/error.png"))); // NOI18N
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(BtnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 11, 33, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Dirección:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 238, -1, -1));
        jPanel1.add(TxtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 236, 172, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Usuario:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 279, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Contraseña:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 317, -1, -1));
        jPanel1.add(TxtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 277, 172, -1));
        jPanel1.add(TxtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 315, 172, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Rol:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 348, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/house (3).png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 11, 33, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/back (1).png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 33, -1));

        editarEmpleado.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        editarEmpleado.setText("Editar Empleado");
        jPanel1.add(editarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 77, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/employee.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 77, -1, -1));

        id_rol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_rolActionPerformed(evt);
            }
        });
        jPanel1.add(id_rol, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 346, 172, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
       System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSalirActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        GestionEmpleados men = new GestionEmpleados(cliente,empleado);
        men.setVisible(true);         // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MenuPrincipal ee = new MenuPrincipal(cliente,empleado);
        ee.setVisible(true);        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BtnAceptarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAceptarEmpleadoActionPerformed
        //validar campos del formulario
        if(!validarCampos()){
            JOptionPane.showMessageDialog(null,"Favor de llenar los campos", "Error",ERROR_MESSAGE);
        }else{
            String nombre = TxtNombre.getText();
            String apellidoP=TxtApellidoP.getText();
            String apellidoM=TxtApellidoM.getText();
            String direccion=TxtDireccion.getText();
            String usuario=TxtUsuario.getText();
            String password=TxtPass.getText();
            String[][]con = cliente.consultaSelect("select (idRol) from roles where (nombreRol="+id_rol.getSelectedItem().toString()+")");
            int idRol = Integer.parseInt(con[1][0]);
            Empleado empleadoinsert= new Empleado(1,nombre,apellidoP,apellidoM,direccion,usuario,password,idRol);
            
            if(tipo==1){
                insertarEmpleado(empleadoinsert);
            }else if(tipo==2){
                editarEmpleado(empleadoinsert);
            }
            
        }
    }//GEN-LAST:event_BtnAceptarEmpleadoActionPerformed
    //metodo para insertar empleado
    public void insertarEmpleado(Empleado empleado){
        //insertamos el empleado
        String respuesta =cliente.consultaInsert("insert into usuarios values"
                + "(null,"+empleado.getNombre()+","+empleado.getApellidoP()+","+empleado.getApellidoM()+","+empleado.getDireccion()+","
                    +empleado.getUsuario()+","+empleado.getPassword()+","+empleado.getIdRol()+")");
        if(respuesta.equals("Exito")){
            JOptionPane.showMessageDialog(null, "Registro exitoso");
            limpiarCampos();
        }else{
            JOptionPane.showMessageDialog(null, "Registro fallido", "Error", ERROR_MESSAGE);
        }
    }
    
    //update usuarios set(nombre=julio,apellidoPaterno=se,apellidoMaterno=la,direccion=come,idRol=3)
    //metodo para editar un empleado
    public void editarEmpleado(Empleado empleadosupdate){
        String respuesta=cliente.consultaInsert("update usuarios set(nombreUsuario="+empleadosupdate.getNombre()+","
                + "apellidoPaterno="+empleadosupdate.getApellidoP()+",apellidoMaterno="+empleadosupdate.getApellidoM()
                +",direccionUsuario="+empleadosupdate.getDireccion()+",idRolFK="+empleadosupdate.getIdRol()
                +") where(idUsuario="+empleado.getIdUsuario()+")");
        if(respuesta.equals("Exito")){
            JOptionPane.showMessageDialog(null, "Moficacion exitosa");
            GestionEmpleados ge = new GestionEmpleados(cliente,empleado);
            ge.setVisible(true);
            this.setVisible(false);
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Modificacion fallida", "Error", ERROR_MESSAGE);
        }
    }
    
    
    //cuando la ventana este abierta se ocultará el titulo segun lo que haya seleccionado el empleado
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        altaEmpleado.setVisible(false);
        editarEmpleado.setVisible(false);
        LlenarComboBox();
        //1 para altas y 2 para updates
        if(tipo==1){
            altaEmpleado.setVisible(true);
        //si el usuario seleccionó editar usuario entonces llenará
        //los campos con los datos del usuario que quiere modificar
        }else if(tipo==2){
            //llenamos los campos
            editarEmpleado.setVisible(true);
            TxtNombre.setText(empleado.getNombre());
            TxtApellidoP.setText(empleado.getApellidoP());
            TxtApellidoM.setText(empleado.getApellidoM());
            TxtDireccion.setText(empleado.getDireccion());
            TxtUsuario.setText(empleado.getUsuario());
            TxtPass.setText(empleado.getPassword());
            //colocamos el comboBox con el nombre 
            String[][] nombrerol=cliente.consultaSelect("select (nombreRol) from roles where (idRol="+empleado.getIdRol()+")");
            id_rol.setSelectedItem(nombrerol[1][0]);
        }
    }//GEN-LAST:event_formWindowOpened

   
    private void BtnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBorrarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_BtnBorrarActionPerformed

    private void id_rolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_rolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_rolActionPerformed

    
    
    //metodo para validar si los campos estan vacios
    public boolean validarCampos(){
        if(TxtNombre.getText().isEmpty()||TxtApellidoP.getText().isEmpty()||TxtApellidoM.getText().isEmpty()||TxtDireccion.getText().isEmpty()
                ||TxtUsuario.getText().isEmpty()||TxtPass.getText().isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    //limpiamos los campos
    public void limpiarCampos(){
        TxtNombre.setText("");
        TxtApellidoP.setText("");
        TxtApellidoM.setText("");
        TxtDireccion.setText("");
        TxtUsuario.setText("");
        TxtPass.setText("");
    }
    //llenar comboBox de empleados
    private void LlenarComboBox(){
        id_rol.removeAllItems();
        String [][] respuesta= cliente.consultaSelect("select (nombreRol) from roles");
        if(empleado.getIdRol()==1){
            for(int i=1;i<respuesta.length;i++)
                id_rol.addItem(respuesta[i][0]);
        }else if(empleado.getIdRol()==3)
            for(int i=1;i<respuesta.length;i++)
                if(!(respuesta[i][0].equals("Admin")||respuesta[i][0].equals("Gerente")))
                    id_rol.addItem(respuesta[i][0]);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAceptarEmpleado;
    private javax.swing.JButton BtnBorrar;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JTextField TxtApellidoM;
    private javax.swing.JTextField TxtApellidoP;
    private javax.swing.JTextField TxtDireccion;
    private javax.swing.JTextField TxtNombre;
    private javax.swing.JTextField TxtPass;
    private javax.swing.JTextField TxtUsuario;
    private javax.swing.JLabel altaEmpleado;
    private javax.swing.JLabel editarEmpleado;
    private javax.swing.JComboBox<String> id_rol;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}