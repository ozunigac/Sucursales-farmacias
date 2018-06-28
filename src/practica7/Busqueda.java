/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica7;

import Clases.Conexion;
import Clases.Empleado;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author DELL
 */
public class Busqueda extends javax.swing.JFrame {
    Conexion cliente;
    Empleado empleado;
    private String[][] sucursales;
    
    //Variables para manipular las tablas
    JTable tabla;
    DefaultTableModel model = new DefaultTableModel(),model1 = new DefaultTableModel();
    //Variable para hacer el filtro a la tabla TVentas1
    TableRowSorter filtro;
    //Variables para hacer los calculos necesarios
    int totalVenta,gananciaProducto[],gananciaTotal;
    int totalProducto[][],totalProductos,idProductos[][],idUsuario,existenciaProductos[];
    boolean productoControlado;
    //Variable para saber el numero de caja en el que se encuentra
    int numCaja;  //SE DEBERA DE CAMBIAR MANUALMENTE ESTE NUMERO EN CADA COMPUTADORA
    //variables para llenar el reporte de la venta    
    String datos[][],datosQuery[][];
    int nVenta;
    
    /**
     * Creates new form Busqueda
     */
    public Busqueda(Conexion cli,Empleado emp) {
        cliente = cli;
        empleado=emp;
        initComponents();
        this.setLocationRelativeTo(null);
        tabla = this.productosTable;
        tabla.setModel(model);
        model.setColumnIdentifiers(getColumnasVentas1());
        LlenarComboBoxSucursal();
        llenarTabla();
    }
    
    public String[] getColumnasVentas1(){  //metodo que me devolvera los nombres de las columnas de la tabla TVentas1
        String col[];           
        return col=new String[]{"Clave","Nombre","En Existencia","Precio Unitario","Descripcion"};
    } 
    
    public void llenarTabla(){  //metodo para poner las cabeceras a las tablas y llenar la tabla TVentas1        
        for(int i=productosTable.getRowCount()-1;i>-1;i--)
            model.removeRow(i);        
        String query="select * from productos";
        //st = conexion.conectar();
        datosQuery = cliente.consultaSelect(query);
        int n=datosQuery.length;
        idProductos=new int[n][2];
        gananciaProducto=new int[n];
        existenciaProductos=new int[n];
        for(int i=1;i<n;i++){
            idProductos[i][0]=Integer.parseInt(datosQuery[i][0]);//rs.getInt("idProducto");
            idProductos[i][1]=Integer.parseInt(datosQuery[i][6]);//rs.getInt("idCategoria");
            existenciaProductos[i]=Integer.parseInt(datosQuery[i][2]);//rs.getInt("existencia");
            gananciaProducto[i]=Integer.parseInt(datosQuery[i][3])-Integer.parseInt(datosQuery[i][4]);//rs.getDouble("precioUnidad")-rs.getDouble("precioProveedor");
            if(existenciaProductos[i]>0)
                model.addRow(new Object[]{idProductos[i][0],datosQuery[i][1].toLowerCase(),existenciaProductos[i],datosQuery[i][3],datosQuery[i][5]});
            //model.addRow(new Object[]{idProductos[i][0],rs.getString("nombre"),existenciaProductos[i],rs.getString("descripcion"),rs.getString("precioUnidad")});
            //i++;
        }
    }
    
    public void inicializaVariables(){
        totalVenta=0;
        gananciaProducto=null;
        gananciaTotal=0;
        totalProducto=null;
        totalProductos=0;
        idProductos=null;
        idUsuario=0;
        existenciaProductos=null;
        productoControlado=false;
        numCaja=1;
        datos=null;
        nVenta=0;
    }
    
    
    private void LlenarComboBoxSucursal(){
        //llenamos 
        try{
            //leemos los datos del archivo servers
            BufferedReader br = new BufferedReader(new FileReader("servers.txt"));
            int cont=0;
            //en este caso solo necesitamos el primer registro
            String[]datosArchivo = new String [10];
            //leemos la primera linea del archivo
            while((datosArchivo[cont]=br.readLine())!=null){
                cont++;
            }
            String [][] sucTemp=new String[cont][3];
            //empezamos a colocar las sucursales
            for(int i=0;i<cont;i++){
                String [] temp = datosArchivo[i].split("=");
                sucTemp[i][0]=temp[0];
                temp=temp[1].split(":");
                sucTemp[i][1]=temp[0];
                sucTemp[i][2]=temp[1];
                CBSucursal.addItem(sucTemp[i][0]);
            }
            sucursales=sucTemp;
            //cerramos el archivo
            br.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"No se pudo leer el archivo 'servers.txt'","Error",ERROR_MESSAGE );
            ex.printStackTrace();
        }
    }
    
    public void buscarProducto(String d){  //metodo para asignar el filtro a ciertas columnas de la tabla TVentas1
        filtro.setRowFilter(RowFilter.regexFilter(d,1));
    }
    
    
    private void limpiarCamposTabla(){
        for (int j = (model.getRowCount() - 1); j >= 0; j--) // limpiamos tabla
            model.removeRow(j);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        CBSucursal = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        productosTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Busqueda de Productos");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Producto:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, -1, -1));

        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchKeyTyped(evt);
            }
        });
        jPanel1.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 200, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Sucursal:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, -1, -1));

        jPanel1.add(CBSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 190, -1));

        productosTable.setModel(model);
        jScrollPane1.setViewportView(productosTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 211, 680, 160));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/error.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 33, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/house (3).png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 33, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/back (1).png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 33, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pills (2).png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MenuPrincipal nn = new MenuPrincipal(cliente,empleado);
         nn.setVisible(true);        // TODO add your handling code here:
         this.setVisible(false);
         this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        MenuPrincipal qq = new MenuPrincipal(cliente,empleado);
         qq.setVisible(true);        // TODO add your handling code here:
         this.setVisible(false);
         this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyTyped
                //metodo para ir capturando el valor del search cada que se ingresa o borra una letra y hacer el filtro
        search.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                String dato = (search.getText().toLowerCase());
                search.setText(dato);
                //repaint();
                buscarProducto(dato);
            }
        });
        filtro=new TableRowSorter(productosTable.getModel());
        productosTable.setRowSorter(filtro);
    }//GEN-LAST:event_searchKeyTyped

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        CBSucursal.addActionListener ((ActionEvent ae) -> {
            String consulta="select (idProducto,nombreProd,existencia,precioUnidad,descripcionProd) from productos";
            for(int i=0;i<sucursales.length;i++){
                if(sucursales[i][0].equals(CBSucursal.getSelectedItem())){
                    String [][] respuestaTemp=cliente.ConexionTemp(sucursales[i][1],Integer.parseInt(sucursales[i][2]),consulta);
                    if(respuestaTemp==null){
                        limpiarCamposTabla();
                        return;
                    }
                    LlenarTablaSucursal(respuestaTemp);
                }
            }
        });
    }//GEN-LAST:event_formWindowActivated

    //llenar la tabla segun sus registros
    private void LlenarTablaSucursal(String[][] res){
        limpiarCamposTabla();
        // recorremos el resultado de la ocnsulta
        for(int j=1;j<res.length;j++){
            model.addRow(res[j]); // add a el modelo de latabla
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBSucursal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable productosTable;
    private javax.swing.JTextField search;
    // End of variables declaration//GEN-END:variables
}
