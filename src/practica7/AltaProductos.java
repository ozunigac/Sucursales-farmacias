/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica7;

import Clases.Conexion;
import Clases.Empleado;
import Clases.Producto;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 *
 * @author DELL
 */
public class AltaProductos extends javax.swing.JFrame {
    Conexion cliente;
    Producto producto;
    int tipo;
    Empleado empleado;
    public AltaProductos(Conexion cli,int tip,Producto pro,Empleado emp) {
        cliente=cli;
        tipo=tip;
        producto = pro;
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

        jPanel2 = new javax.swing.JPanel();
        altaProductos = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtNombreProducto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TxtExistencia = new javax.swing.JTextField();
        TxtPUnidad = new javax.swing.JTextField();
        TxtPProveedor = new javax.swing.JTextField();
        TxtDescripcion = new javax.swing.JTextField();
        BtnSalirP = new javax.swing.JButton();
        BtnAltaP = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        editarProductos = new javax.swing.JLabel();
        id_categoria = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        altaProductos.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        altaProductos.setText("Alta Productos");
        jPanel2.add(altaProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Nombre:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 156, -1, -1));
        jPanel2.add(TxtNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 154, 172, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Existencia:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 182, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Precio por Unidad:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 208, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Precio de Proveedor:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 239, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Descripción:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 265, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Categoria:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 296, -1, -1));
        jPanel2.add(TxtExistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 180, 172, -1));
        jPanel2.add(TxtPUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 206, 172, -1));

        TxtPProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPProveedorActionPerformed(evt);
            }
        });
        jPanel2.add(TxtPProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 237, 172, -1));
        jPanel2.add(TxtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 263, 172, -1));

        BtnSalirP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/error.png"))); // NOI18N
        BtnSalirP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirPActionPerformed(evt);
            }
        });
        jPanel2.add(BtnSalirP, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 33, -1));

        BtnAltaP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/success.png"))); // NOI18N
        BtnAltaP.setText("Aceptar");
        BtnAltaP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAltaPActionPerformed(evt);
            }
        });
        jPanel2.add(BtnAltaP, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, 110, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eraser.png"))); // NOI18N
        jButton1.setText("Borrar");
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 100, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pills (2).png"))); // NOI18N
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/house (3).png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 33, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/back (1).png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 33, -1));

        editarProductos.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        editarProductos.setText("Editar Productos");
        jPanel2.add(editarProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));

        jPanel2.add(id_categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 294, 172, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    
    
    //llenar comboBox de empleados
    private void LlenarComboBox(){
        id_categoria.removeAllItems();
        String [][] respuesta= cliente.consultaSelect("select (nombreCat) from categorias");
        for(int i=1;i<respuesta.length;i++){
            id_categoria.addItem(respuesta[i][0]);
        }
    }
     //limpiamos los campos
    public void limpiarCampos(){
        TxtNombreProducto.setText("");
        TxtExistencia.setText("");
        TxtPUnidad.setText("");
        TxtPProveedor.setText("");
        TxtDescripcion.setText("");
    }
    //metodo para validar si los campos estan vacios
    public boolean validarCampos(){
        if(TxtNombreProducto.getText().isEmpty()||TxtExistencia.getText().isEmpty()||TxtPUnidad.getText().isEmpty()||TxtPProveedor.getText().isEmpty()
                ||TxtDescripcion.getText().isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    //convertir de string a int
    public int convint(String ar){
        return Integer.parseInt(ar);
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        GestionProductos ob = new GestionProductos(cliente,empleado);
        ob.setVisible(true);// TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MenuPrincipal ob = new MenuPrincipal(cliente,empleado);
        ob.setVisible(true);// TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void BtnSalirPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirPActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSalirPActionPerformed

    private void TxtPProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPProveedorActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        altaProductos.setVisible(false);
        editarProductos.setVisible(false);
        LlenarComboBox();
        //1 para altas y 2 para updates
        if(tipo==1){
            altaProductos.setVisible(true);
        //si el usuario seleccionó editar usuario entonces llenará
        //los campos con los datos del usuario que quiere modificar
        }else if(tipo==2){
            //llenamos los campos
            editarProductos.setVisible(true);
            TxtNombreProducto.setText(producto.getNombre());
            TxtExistencia.setText(String.valueOf(producto.getExistencia()));
            TxtPUnidad.setText(String.valueOf(producto.getPrecioUnidad()));
            TxtPProveedor.setText(String.valueOf(producto.getPrecioProveedor()));
            TxtDescripcion.setText(producto.getDescripcion());
            //colocamos el comboBox con el nombre 
            String[][] nombrerol=cliente.consultaSelect("select (nombreCat) from categorias where (idCategoria="+producto.getIdCategoria()+")");
            id_categoria.setSelectedItem(nombrerol[1][0]);
        }
    }//GEN-LAST:event_formWindowOpened

    private void BtnAltaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAltaPActionPerformed
        //validar campos del formulario
        if(!validarCampos()){
            JOptionPane.showMessageDialog(null,"Favor de llenar los campos", "Error",ERROR_MESSAGE);
        }else if(!(isNumeric(TxtExistencia.getText())||isNumeric(TxtPUnidad.getText())||isNumeric(TxtPProveedor.getText()))){
            JOptionPane.showMessageDialog(null,"Favor de revisar que los campos sean numericos", "Error",ERROR_MESSAGE);
        }else{
            String nombre = TxtNombreProducto.getText();
            int existencia=convint(TxtExistencia.getText());
            int punidad=convint(TxtPUnidad.getText());
            int pproveedor=convint(TxtPProveedor.getText());
            String descripcion=TxtDescripcion.getText();
            String[][]con = cliente.consultaSelect("select (idCategoria) from categorias where (nombreCat="+id_categoria.getSelectedItem().toString()+")");
            int idCategoria = Integer.parseInt(con[1][0]);
            Producto productoInsert= new Producto(1,nombre,existencia,punidad,pproveedor,descripcion,idCategoria);

            if(tipo==1){
                insertarProducto(productoInsert);
            }else if(tipo==2){
                editarProducto(productoInsert);
            }

        }
    }//GEN-LAST:event_BtnAltaPActionPerformed
    
    //comprobamos si un camo es numerico
    public boolean isNumeric(String str){
        try{
            int i=Integer.parseInt(str);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    //metodo para insertar empleado
    public void insertarProducto(Producto productoins){
        //insertamos el empleado
        String respuesta =cliente.consultaInsert("insert into productos values"+"(null,"+productoins.getNombre()+","
                +productoins.getExistencia()+","+productoins.getPrecioUnidad()+","+ productoins.getPrecioProveedor()+
                ","+productoins.getDescripcion()+","+productoins.getIdCategoria()+")");
        if(respuesta.equals("Exito")){
            JOptionPane.showMessageDialog(null, "Registro exitoso");
            limpiarCampos();
        }else{
            JOptionPane.showMessageDialog(null, "Registro fallido", "Error", ERROR_MESSAGE);
        }
    }
    
    //update usuarios set(nombre=julio,apellidoPaterno=se,apellidoMaterno=la,direccion=come,idRol=3)
    //metodo para editar un empleado
    public void editarProducto(Producto productoupd){
        String respuesta=cliente.consultaInsert("update productos set(nombreProd="+productoupd.getNombre()+",existencia="+productoupd.getExistencia()+
                ",precioUnidad="+productoupd.getPrecioUnidad()+",precioProveedor="+productoupd.getPrecioProveedor()+",descripcionProd="+
                productoupd.getDescripcion()+",idCategoriaFK="+productoupd.getIdCategoria()+") where(idProducto="+producto.getIdProducto()+")");
        if(respuesta.equals("Exito")){
            JOptionPane.showMessageDialog(null, "Moficacion exitosa");
            GestionProductos gp = new GestionProductos(cliente,empleado);
            gp.setVisible(true);
            this.setVisible(false);
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Modificacion fallida", "Error", ERROR_MESSAGE);
        }
    }
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAltaP;
    private javax.swing.JButton BtnSalirP;
    private javax.swing.JTextField TxtDescripcion;
    private javax.swing.JTextField TxtExistencia;
    private javax.swing.JTextField TxtNombreProducto;
    private javax.swing.JTextField TxtPProveedor;
    private javax.swing.JTextField TxtPUnidad;
    private javax.swing.JLabel altaProductos;
    private javax.swing.JLabel editarProductos;
    private javax.swing.JComboBox<String> id_categoria;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
