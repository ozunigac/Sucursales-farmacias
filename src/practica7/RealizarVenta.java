/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica7;

import Clases.Conexion;
import Clases.Empleado;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author DELL
 */
public class RealizarVenta extends javax.swing.JFrame {
    Conexion cliente;
    Empleado empleado;
    
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
    
    public RealizarVenta(Conexion cli,Empleado em) {
        cliente=cli;
        empleado=em;
        initComponents();
        this.setLocationRelativeTo(null);
        inicializaVariables();
        idUsuario=em.getIdUsuario();
        jLabel2.setText(em.getNombre()+" "+em.getApellidoP()+" "+em.getApellidoM());
        tabla = this.TVentas1;
        tabla.setModel(model);
        model.setColumnIdentifiers(getColumnasVentas1());
        tabla = this.TVentas2;
        tabla.setModel(model1);
        model1.setColumnIdentifiers(getColumnasVentas2());
        llenarTabla(); //metodo para llenar la tabla desde la BD
    }

    public String[] getColumnasVentas1(){  //metodo que me devolvera los nombres de las columnas de la tabla TVentas1
        String col[];           
        return col=new String[]{"Clave","Nombre","En Existencia","Descripción","Precio Unitario"};
    } 
    
    public String[] getColumnasVentas2(){  //metodo que me devolvera los nombres de las columnas de la tabla TVentas2
        String col[];           
        return col=new String[]{"Clave","Nombre","Cantidad","Precio Unitario","Total Producto"};
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
    //llenamos la tabla con los productos
    public void llenarTabla(){  //metodo para poner las cabeceras a las tablas y llenar la tabla TVentas1        
        for(int i=TVentas1.getRowCount()-1;i>-1;i--)
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
                model.addRow(new Object[]{idProductos[i][0],datosQuery[i][1].toLowerCase(),existenciaProductos[i],datosQuery[i][5],datosQuery[i][3]});
            //model.addRow(new Object[]{idProductos[i][0],rs.getString("nombre"),existenciaProductos[i],rs.getString("descripcion"),rs.getString("precioUnidad")});
            //i++;
        }
    }
    
    public void buscarProducto(String d){  //metodo para asignar el filtro a ciertas columnas de la tabla TVentas1
        filtro.setRowFilter(RowFilter.regexFilter(d,1));
    }
    
    
    public boolean isdouble(String n){        //metodo para saber si una cadena es un numero decimal
        try{
            Double.parseDouble(n);
            return true;
        }catch(NumberFormatException e){
            return false;
        }        
    }
    
    public boolean verificaProductoControlado(int id){    //metodo para verificar si el producto es controlado    
        for(int i=0;i<idProductos.length;i++){
            if(idProductos[i][0]==id&&idProductos[i][1]==2)
                return true;
        }
        return false;
    }
    
    
    
    public void mandaQuerys(String codReceta){ //metodo para generar los querys para los inserts y updates
        String resultado;
        String query="select (idVenta) from ventas";
        datosQuery = cliente.consultaSelect(query);  
        if(datosQuery!=null){
            nVenta=Integer.parseInt(datosQuery[datosQuery.length-1][0]);
            Date fechaActual = new Date();
            String f=new SimpleDateFormat("yyyy-MM-dd").format(fechaActual);
            query="insert into ventas values(null,"+idUsuario+","+numCaja+","+f+","+totalVenta+","+gananciaTotal+","+totalProductos+")";
            resultado = cliente.consultaInsert(query);
            if(resultado.equals("Exito")){
                for(int i=0;i<totalProducto.length;i++){           
                    query="insert into ventaproductos values("+nVenta+","+totalProducto[i][0]+","+totalProducto[i][1]+","+codReceta+")";
                    resultado = cliente.consultaInsert(query);
                    for(int j=0;j<idProductos.length;j++){
                        if(idProductos[j][0]==totalProducto[i][0]){
                            query="update productos set (existencia="+(existenciaProductos[j]-totalProducto[i][1])+") where (idProducto="+totalProducto[i][0]+")";
                            resultado = cliente.consultaInsert(query);
                            if(!resultado.equals("Exito"))
                                JOptionPane.showMessageDialog(null, "Error al hacer conección a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }else
                JOptionPane.showMessageDialog(null, "Error al hacer conección a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void generaReporte() throws JRException{
        datos=new String[TVentas2.getRowCount()][TVentas2.getColumnCount()];
        for(int i=0;i<TVentas2.getRowCount();i++)
            for(int j=0;j<TVentas2.getColumnCount();j++)
                datos[i][j]=TVentas2.getValueAt(i,j).toString();   
        for(int i=TVentas2.getRowCount()-1;i>-1;i--)
            model1.removeRow(i);
        //CrearReporte cr = new CrearReporte();
        //cr.Reporte2(datos, jLabel2.getText(), nVenta+"", totalVenta+"");       
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TVentas2 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        TVentas1 = new javax.swing.JTable();
        CantidadVenta = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        BtnAdd = new javax.swing.JButton();
        BtnClear = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        BtnSalir = new javax.swing.JButton();
        BtnHome = new javax.swing.JButton();
        BtnBack = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        totalVentaTxt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Realizar Venta");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 51, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nombre del Cajero");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 96, -1, -1));

        TVentas2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TVentas2);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 339, 674, 137));

        TVentas1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(TVentas1);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 125, 674, 140));

        CantidadVenta.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jPanel2.add(CantidadVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Cantidad:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, -1, -1));

        BtnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/plus (1).png"))); // NOI18N
        BtnAdd.setText("Agregar");
        BtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddActionPerformed(evt);
            }
        });
        jPanel2.add(BtnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 280, -1, -1));

        BtnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eraser.png"))); // NOI18N
        BtnClear.setText("Borrar");
        BtnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnClearActionPerformed(evt);
            }
        });
        jPanel2.add(BtnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 500, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/money.png"))); // NOI18N
        jButton1.setText("Pagar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 500, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/payment-method (1).png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 51, -1, -1));

        BtnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/error.png"))); // NOI18N
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(BtnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(651, 12, 33, -1));

        BtnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/house (3).png"))); // NOI18N
        BtnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHomeActionPerformed(evt);
            }
        });
        jPanel2.add(BtnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 12, 33, -1));

        BtnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/back (1).png"))); // NOI18N
        BtnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBackActionPerformed(evt);
            }
        });
        jPanel2.add(BtnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 12, 33, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Buscar:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 96, -1, -1));

        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchKeyTyped(evt);
            }
        });
        jPanel2.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(516, 94, 168, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Cantidad:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, -1, -1));

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jPanel2.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 510, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("TOTAL:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 510, -1, -1));

        totalVentaTxt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(totalVentaTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 510, 120, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnClearActionPerformed
        if(TVentas2.getSelectedRowCount()==1){      //condicion para que solo entre cuando hay una sola fila seleccionada     
            double a=Double.parseDouble(TVentas2.getValueAt(TVentas2.getSelectedRow(), 3).toString());//obtengo el precio del producto a eliminar de la venta
            int b=(int) jSpinner1.getValue(); //obtengo la cantidad de productos que quiere eliminar
            int c=Integer.parseInt(TVentas2.getValueAt(TVentas2.getSelectedRow(), 2).toString()); //obtego la cantidad que tengo en la venta            
            int aux=Integer.parseInt(TVentas2.getValueAt(TVentas2.getSelectedRow(), 0).toString()),aux1,aux2;//variables para eliminar el producto
            if((c-b)>-1){
                if(c-b==0)
                    model1.removeRow(TVentas2.getSelectedRow());                
                else
                    TVentas2.setValueAt(c-b, TVentas2.getSelectedRow(), 2);//hago la dismiucion de productos en la tabla TVentas1
                for(int i=0;i<TVentas1.getRowCount();i++){//ciclo para buscar el codigo del producto a vender en la tabla TVentas2
                    aux1=Integer.parseInt(TVentas1.getValueAt(i, 0).toString());//obtengo los codigos de los productos en la tabla TVentas2                    
                    if(aux==aux1){//condicion para saber si ya existe el producto en la tabla TVentas2
                        aux2=Integer.parseInt(TVentas1.getValueAt(i, 2).toString());//obtengo la catidad de cada producto a vender
                        TVentas1.setValueAt(aux2+b, i, 2);//actualizo la tabla TVentas2 con la cantidad de productos a vender
                        break;//rompo el ciclo
                    }
                }
                jSpinner1.setValue(1);
                totalVenta-=a*b;
                System.out.println("total Venta: "+totalVenta);
                totalProductos-=b;
                for(int i=0;i<idProductos.length;i++)
                    if(aux==idProductos[i][0])
                        gananciaTotal-=gananciaProducto[i]*b;
                System.out.println("Ganancia: "+gananciaTotal);
                if(TVentas2.getRowCount()==0){
                    totalProducto=null;
                    productoControlado=false; 
                }else{
                    for(int i=0;i<totalProducto.length;i++){
                        if(aux==totalProducto[i][0]){
                            totalProducto[i][1]-=b;
                            if(totalProducto[i][1]==0){
                                int totp[][]=new int[totalProducto.length][2];
                                totp=totalProducto;
                                totalProducto=null;
                                totalProducto=new int[totp.length-1][2];
                                int k=0;
                                for(int j=0;j<totp.length;j++){                       
                                    if(totp[j][1]!=0){
                                        totalProducto[k]=totp[j];
                                        k++;
                                    }
                                }
                            }
                            break;
                        }
                    }
                    productoControlado=false;
                    for(int i=0;i<TVentas2.getRowCount();i++){
                        aux=Integer.parseInt(TVentas2.getValueAt(i, 0).toString());
                        if(verificaProductoControlado(aux)){
                            productoControlado=true;
                            break;
                        }
                    }
                    
                }                
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione un número menor o igual a :"+c, "Error", JOptionPane.ERROR_MESSAGE);
                jSpinner1.setValue(c);
            }
        }else
            JOptionPane.showMessageDialog(null, "Seleccione un solo registro de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_BtnClearActionPerformed

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
        System.exit(0);      // TODO add your handling code here:
    }//GEN-LAST:event_BtnSalirActionPerformed

    private void BtnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBackActionPerformed
        MenuPrincipal men = new MenuPrincipal(cliente,empleado);
        men.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_BtnBackActionPerformed

    private void BtnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHomeActionPerformed
        MenuPrincipal men1 = new MenuPrincipal(cliente,empleado);
        men1.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_BtnHomeActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

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
        filtro=new TableRowSorter(TVentas1.getModel());
        TVentas1.setRowSorter(filtro);
    }//GEN-LAST:event_searchKeyTyped

    private void BtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddActionPerformed
        if(TVentas1.getSelectedRowCount()==1){      //condicion para que solo entre cuando hay una sola fila seleccionada     
            double a=Double.parseDouble(TVentas1.getValueAt(TVentas1.getSelectedRow(), 4).toString()),aux3;//obtengo el precio del producto a vender
            int b=(int) CantidadVenta.getValue(); //obtengo la cantidad de productos que quiere
            int c=Integer.parseInt(TVentas1.getValueAt(TVentas1.getSelectedRow(), 2).toString()); //obtego la cantidad que tengo en existencia            
            int aux=Integer.parseInt(TVentas1.getValueAt(TVentas1.getSelectedRow(), 0).toString()),aux1,aux2;//variables para verificar que si quiere agregar el mismo producto solo lo muestre una ves y aumente la cantidad de productos a vender
            boolean ban=true;//bandera
            if((c-b)>-1){
                TVentas1.setValueAt(c-b, TVentas1.getSelectedRow(), 2);//hago la dismiucion de productos en la tabla TVentas1
                for(int i=0;i<TVentas2.getRowCount();i++){//ciclo para buscar el codigo del producto a vender en la tabla TVentas2
                    aux1=Integer.parseInt(TVentas2.getValueAt(i, 0).toString());//obtengo los codigos de los productos en la tabla TVentas2                    
                    if(aux==aux1){//condicion para saber si ya existe el producto en la tabla TVentas2
                        aux2=Integer.parseInt(TVentas2.getValueAt(i, 2).toString());//obtengo la catidad de cada producto a vender                        
                        ban=false;//cambio el valor de la bandera
                        TVentas2.setValueAt(aux2+b, i, 2);//actualizo la tabla TVentas2 con la cantidad de productos a vender
                        aux3=Double.parseDouble(TVentas2.getValueAt(i, 4).toString());//obtengo la catidad de cada producto a vender
                        System.out.println("aux3: "+aux3+"\na*b: "+(a*b));
                        TVentas2.setValueAt(aux3+(a*b), i, 4);//actualizo la tabla TVentas2 con la cantidad de productos a vender
                        break;//rompo el ciclo
                    }
                }
                if(ban)//condicion con la bandera
                    model1.addRow(new Object[]{TVentas1.getValueAt(TVentas1.getSelectedRow(), 0),TVentas1.getValueAt(TVentas1.getSelectedRow(), 1),b,TVentas1.getValueAt(TVentas1.getSelectedRow(), 4),a*b});
                CantidadVenta.setValue(1);
                totalVenta+=a*b;     
                totalVentaTxt.setText(totalVenta+"");
                System.out.println("total Venta: "+totalVenta);
                totalProductos+=b;
                for(int i=0;i<idProductos.length;i++)
                    if(aux==idProductos[i][0])
                        gananciaTotal+=gananciaProducto[i]*b;
                System.out.println("Ganancia: "+gananciaTotal);
                if(totalProducto==null){
                    totalProducto=new int[1][2];
                    totalProducto[0][0]=aux;                    
                    totalProducto[0][1]=b;                      
                }else{
                    boolean band=true;
                    for(int i=0;i<totalProducto.length;i++){
                        if(totalProducto[i][0]==aux){
                            totalProducto[i][1]+=b;
                            band=false;
                        }
                    }
                    if(band){
                        int totp[][]=new int[totalProducto.length][2];
                        totp=totalProducto;
                        totalProducto=null;
                        totalProducto=new int[totp.length+1][2];
                        for(int i=0;i<totp.length;i++)                       
                            totalProducto[i]=totp[i];
                        totalProducto[totp.length][0]=aux;
                        totalProducto[totp.length][1]=b;                        
                    }
                }
                if(!productoControlado)   
                    productoControlado=verificaProductoControlado(aux);                              
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione un número menor o igual a :"+c, "Error", JOptionPane.ERROR_MESSAGE);
                CantidadVenta.setValue(c);
            }
        }else
            JOptionPane.showMessageDialog(null, "Seleccione un solo registro de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_BtnAddActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(TVentas2.getRowCount()>0){
            boolean ban=true;
            String codReceta="-";
            if(productoControlado){
                while(ban){
                    codReceta = JOptionPane.showInputDialog(null, "Escriba el codigo de la receta.", "Receta", JOptionPane.QUESTION_MESSAGE); // el icono sera un iterrogante
                    if(!codReceta.equals(""))
                        ban=false;
                    else
                        JOptionPane.showMessageDialog(null, "Ingrese un código.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                ban=true;
            }
            while(ban){
                String seleccion =JOptionPane.showInputDialog(null, "El total de la venta es de $"+totalVenta+"\nEscriba la cantidad con la que pagara.", "Pago", JOptionPane.QUESTION_MESSAGE); // el icono sera un iterrogante
                seleccion=seleccion.trim(); 
                if(!seleccion.equals("")){
                    if(isdouble(seleccion)){
                        double cant=Double.parseDouble(seleccion);
                        if(cant-totalVenta>=0){
                            JOptionPane.showMessageDialog(null, "Cambio: $"+(cant-totalVenta)+".", "Cambio", JOptionPane.INFORMATION_MESSAGE);
                            try {
                                mandaQuerys(codReceta);
                                generaReporte();
                                this.setVisible(false);
                                RealizarVenta rv=new RealizarVenta(cliente,empleado);
                                rv.setVisible(true);
                                this.dispose();
                            } catch (JRException ex) {
                                Logger.getLogger(RealizarVenta.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            for(int i=0;i<TVentas2.getRowCount();i++)
                                model1.removeRow(i);                                                    
                            ban=false;
                        }else
                            JOptionPane.showMessageDialog(null, "Ingrese una cantidad mayor o igual al total de la venta.", "Error", JOptionPane.ERROR_MESSAGE);
                    }else
                        JOptionPane.showMessageDialog(null, "Ingrese solo números.", "Error", JOptionPane.ERROR_MESSAGE);
                }else
                    JOptionPane.showMessageDialog(null, "Ingrese una cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            productoControlado=false;
        }else
            JOptionPane.showMessageDialog(null, "Compre almenos un producto.", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAdd;
    private javax.swing.JButton BtnBack;
    private javax.swing.JButton BtnClear;
    private javax.swing.JButton BtnHome;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JSpinner CantidadVenta;
    private javax.swing.JTable TVentas1;
    private javax.swing.JTable TVentas2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField search;
    private javax.swing.JLabel totalVentaTxt;
    // End of variables declaration//GEN-END:variables
}
