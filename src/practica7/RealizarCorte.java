/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica7;

import Clases.Conexion;
import Clases.Empleado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import reportesfarmacia.CrearReporte;

/**
 *
 * @author DELL
 */
public class RealizarCorte extends javax.swing.JFrame {
    private int columnas=7;
    private Conexion cliente;
    private Empleado empleado;
    private DefaultTableModel model_corte; // modelo de la tabla
    private String[] columna; // columnas de la tabla
    private String[][] sucursales;
    public RealizarCorte(Conexion cli,Empleado emp) {
        cliente=cli;
        empleado = emp;
        model_corte = new DefaultTableModel(null,getColumnas()){ // inicializar el modelo de la tabla
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        initComponents();
        this.setLocationRelativeTo(null);
        CBCorte.addItem("");
        this.setTitle("CORTE DE CAJA");
        tabla_corte.getTableHeader().setReorderingAllowed(false); //evitar que las columnas se editen
        this.setLocationRelativeTo(null); // centrar frame
        //llenar la tabla con todos los registros de la sucursal
        LlenarTable("select (ventas.idVenta,usuarios.nombreUsuario,ventas.numCaja,ventas.fechaVenta,ventas.totalVenta,"
                + "ventas.totalGanancia,ventas.cantidadProductos) from ventas"
                + " inner join usuarios on ventas.idUsuarioFK=usuarios.idUsuario");
        LlenarComboBox();
        
    }

    
    //obtener columnas de la cabecera de la tabla
    private String[] getColumnas(){ // columnas para la tabla
        return columna =  new String[]{"Num. venta","Usuario","Caja","Fecha","Total venta","Ganancia","Productos"};
    }
    //llenar la tabla segun sus registros
    private void LlenarTable(String query){
        for (int i = (model_corte.getRowCount() - 1); i >= 0; i--) // limpiamos tabla
            model_corte.removeRow(i);
//        28ae5f6449
        TableRowSorter<TableModel> orden = new TableRowSorter<TableModel>((DefaultTableModel)tabla_corte.getModel()); // modelo para la tabla
        tabla_corte.setRowSorter(orden); // add el ordenamiento
        String [][] res=cliente.consultaSelect(query);
        // recorremos el resultado de la ocnsulta
        for(int j=1;j<res.length;j++){
            model_corte.addRow(res[j]); // add a el modelo de l atabla
        }
    }
    
    //llenar los combobox
    private void LlenarComboBox(){
        //removemos los items de los combobox
        CBEmpleado.removeAllItems();
        CBCorte.removeAllItems();
        LlenarComboBoxSucursal();
        
        String [][] respuesta= cliente.consultaSelect("select (nombreUsuario) from usuarios where (idRolFK=2 || idRolFK=3)");
        
        for(int i=1;i<respuesta.length;i++){
            CBEmpleado.addItem(respuesta[i][0]);
        }
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
                CBCorte.addItem(sucTemp[i][0]);
            }
            sucursales=sucTemp;
            //cerramos el archivo
            br.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"No se pudo leer el archivo 'servers.txt'","Error",ERROR_MESSAGE );
            ex.printStackTrace();
        }
    }
    
    //imprimir el recibo en pdf
    private void imprimirRecibo(){
        String[][] mat = new String[tabla_corte.getRowCount()][tabla_corte.getColumnCount()];
        for(int i=0;i<tabla_corte.getRowCount();i++){
            for(int y=0;y<tabla_corte.getColumnCount();y++){
                mat[i][y] = tabla_corte.getValueAt(i,y).toString();
                System.out.print(mat[i][y]+" - ");
            }
            System.out.println("");
        }

        CrearReporte reporte = new CrearReporte();
        try {
            reporte.Reporte1(mat,CBCorte.getSelectedItem().toString());
        } catch (JRException ex) {
            Logger.getLogger(RealizarCorte.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        DateInicio = new com.toedter.calendar.JDateChooser();
        DateFin = new com.toedter.calendar.JDateChooser();
        BtnCorte = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_corte = new javax.swing.JTable();
        TxtTotalVenta = new javax.swing.JLabel();
        totalGananciaTxt = new javax.swing.JLabel();
        BtnGuardar = new javax.swing.JButton();
        BtnSalirCorte = new javax.swing.JButton();
        CBCorte = new javax.swing.JComboBox<>();
        CBEmpleado = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        BtnHome = new javax.swing.JButton();
        BtnBack = new javax.swing.JButton();
        totalVentaTxt = new javax.swing.JLabel();
        TxtTotalGanancia = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Corte de Caja");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 50, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Corte de:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 98, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Empleado:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 129, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Fecha Inicio:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(369, 98, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Fecha Fin:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(378, 129, -1, -1));
        jPanel1.add(DateInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(448, 96, 114, -1));
        jPanel1.add(DateFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(447, 127, 114, -1));

        BtnCorte.setText("Corte");
        BtnCorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCorteActionPerformed(evt);
            }
        });
        jPanel1.add(BtnCorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(632, 175, -1, -1));

        tabla_corte.setModel(model_corte);
        jScrollPane1.setViewportView(tabla_corte);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 209, 681, 125));

        TxtTotalVenta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(TxtTotalVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 121, 22));

        totalGananciaTxt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        totalGananciaTxt.setText("Total de Ganancia:");
        jPanel1.add(totalGananciaTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, -1, -1));

        BtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        BtnGuardar.setText("Guardar Recibo");
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(BtnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 410, -1, -1));

        BtnSalirCorte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/error.png"))); // NOI18N
        BtnSalirCorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirCorteActionPerformed(evt);
            }
        });
        jPanel1.add(BtnSalirCorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(658, 11, 33, -1));

        CBCorte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sucursal 1", "Sucursal 2", "Sucursal 3", "Todas" }));
        CBCorte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CBCorteMouseClicked(evt);
            }
        });
        jPanel1.add(CBCorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 96, 114, -1));

        jPanel1.add(CBEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 127, 114, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cash-register (1).png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 50, -1, -1));

        BtnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/house (3).png"))); // NOI18N
        BtnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHomeActionPerformed(evt);
            }
        });
        jPanel1.add(BtnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 11, 33, -1));

        BtnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/back (1).png"))); // NOI18N
        BtnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBackActionPerformed(evt);
            }
        });
        jPanel1.add(BtnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 33, -1));

        totalVentaTxt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        totalVentaTxt.setText("Total de Venta:");
        jPanel1.add(totalVentaTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 368, -1, -1));

        TxtTotalGanancia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(TxtTotalGanancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, 138, 22));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        if(!(DateInicio.getDate()==null || DateFin.getDate()==null)){
            imprimirRecibo();
        }else{
            JOptionPane.showMessageDialog(null, "Ingrese un rango de fechas","ERROR",ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnSalirCorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirCorteActionPerformed
            System.exit(0);       // TODO add your handling code here:
    }//GEN-LAST:event_BtnSalirCorteActionPerformed

    private void BtnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBackActionPerformed
        MenuPrincipal cc = new MenuPrincipal(cliente,empleado);
        cc.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_BtnBackActionPerformed

    private void BtnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHomeActionPerformed
        MenuPrincipal cc2 = new MenuPrincipal(cliente,empleado);
        cc2.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_BtnHomeActionPerformed

    private void BtnCorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCorteActionPerformed
        if(DateInicio.getDate()==null || DateFin.getDate()==null){
            JOptionPane.showMessageDialog(null, "Ingrese un rango de fechas","ERROR",ERROR_MESSAGE);
        }else{
            if(CBCorte.getSelectedItem().equals(sucursales[0][0])){
                Corte();
            }else{
                CorteSucExt();
            }
            //comprobamos si el corte de caja se quiere hacer local.
        }
    }//GEN-LAST:event_BtnCorteActionPerformed
//metodo para calcular el total de las ventas y el total de las ganancias
    private void LlenarCamposTotal(String _query){
        TxtTotalVenta.setText(" ");
        TxtTotalGanancia.setText(" ");
        int totalventa=0;
        int totalganancia=0;
        //enviamos el query para recibir el total de la venta y las ganancias que se hicieron con esa venta
        String [][] res=cliente.consultaSelect(_query);
        //recorremos la matriz
        for(int i=1;i<res.length;i++){
            for(int j=0;j<res[i].length;j++){
                //sumamos el total de cada venta
                if(j==0)
                   totalventa+=Integer.parseInt(res[i][j]);
                //sumamos el total de las ganancias de cada venta 
                else if(j==1)
                   totalganancia+=Integer.parseInt(res[i][j]);
            }
        }
        //al final colocamos los valores en los cuadros de texto
        TxtTotalVenta.setText(String.valueOf(totalventa));
        TxtTotalGanancia.setText(String.valueOf(totalganancia));
    }
    
    //obtenemos el id del empleado
    private int getIdEmpleado(String _nombre){
        int id = 0;
        String [][] usuarios=cliente.consultaSelect("select (idUsuario) from usuarios where (nombreUsuario="+_nombre+")");
        id = Integer.parseInt(usuarios[1][0]);
        return id;
    }
    
    public void Corte(){
        //llenamos la tabla con los registros, segun el usuario y sucursal que elija
            LlenarTable("select (ventas.idVenta,usuarios.nombreUsuario,ventas.numCaja,"
                + "ventas.fechaVenta,ventas.totalVenta,ventas.totalGanancia,ventas.CantidadProductos)"
                + " from ventas inner join usuarios on ventas.idUsuarioFK=usuarios.idUsuario"
                + " where usuarios.idUsuario="+getIdEmpleado((String)CBEmpleado.getSelectedItem())+
                    " && ventas.fechaVenta>"+new SimpleDateFormat("yyyy-MM-dd").format(DateInicio.getDate())+
                    " && ventas.fechaVenta<"+new SimpleDateFormat("yyyy-MM-dd").format(DateFin.getDate()));
            
            LlenarCamposTotal("select (ventas.totalVenta,ventas.totalGanancia)"
                + " from ventas inner join usuarios on ventas.idUsuarioFK=usuarios.idUsuario"
                + " where usuarios.idUsuario="+getIdEmpleado((String)CBEmpleado.getSelectedItem())+
                    " && ventas.fechaVenta>"+new SimpleDateFormat("yyyy-MM-dd").format(DateInicio.getDate())+
                    " && ventas.fechaVenta<"+new SimpleDateFormat("yyyy-MM-dd").format(DateFin.getDate()));
            /*LlenarTable("select (ventas.idVenta,usuarios.nombreUsuario,ventas.numCaja,ventas.fechaVenta,ventas.totalVenta,"
                + "ventas.totalGanancia,ventas.cantidadProductos) from ventas"
                + " inner join usuarios on ventas.idUsuarioFK=usuarios.idUsuario");
                /*ReaCorte((String) CBCorte.getSelectedItem(),
                getIdEmpleado((String) CBEmpleado.getSelectedItem()),
                new SimpleDateFormat("yyyy-MM-dd").format(DateInicio.getDate()),
                new SimpleDateFormat("yyyy-MM-dd").format(DateFin.getDate()));*/
    }
    
    
    private void CBCorteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBCorteMouseClicked
        
    }//GEN-LAST:event_CBCorteMouseClicked

    //tendremos este metodo para cuando el select de sucursales cambie haga la 
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        CBCorte.addActionListener ((ActionEvent ae) -> {
            String consulta="select (ventas.idVenta,usuarios.nombreUsuario,ventas.numCaja,ventas.fechaVenta,ventas.totalVenta,"
                + "ventas.totalGanancia,ventas.cantidadProductos) from ventas"
                + " inner join usuarios on ventas.idUsuarioFK=usuarios.idUsuario";
            for(int i=0;i<sucursales.length;i++){
                if(sucursales[i][0].equals(CBCorte.getSelectedItem())){
                    String [][] respuestaTemp=cliente.ConexionTemp(sucursales[i][1],Integer.parseInt(sucursales[i][2]),consulta);
                    if(respuestaTemp==null){
                        limpiarCamposTabla();
                        CBEmpleado.removeAllItems();
                        return;
                    }
                    LlenarTablaSucursal(respuestaTemp);
                    consulta="select (nombreUsuario,idRolFK) from usuarios";
                    String [][] empleadosTemp=cliente.ConexionTemp(sucursales[i][1],Integer.parseInt(sucursales[i][2]),consulta);
                    LLenarEmpleadosSucursal(empleadosTemp);
                }
            }
        });
    }//GEN-LAST:event_formWindowActivated
    
    private void limpiarCamposTabla(){
        for (int j = (model_corte.getRowCount() - 1); j >= 0; j--) // limpiamos tabla
            model_corte.removeRow(j);
    }
    
    
    //llenar la tabla segun sus registros
    private void LlenarTablaSucursal(String[][] res){
        limpiarCamposTabla();
        // recorremos el resultado de la ocnsulta
        for(int j=1;j<res.length;j++){
            model_corte.addRow(res[j]); // add a el modelo de latabla
        }
    }
    //llenar los combobox
    private void LLenarEmpleadosSucursal(String[][]respuesta){
        //removemos los items de los combobox
        CBEmpleado.removeAllItems();
        
        for(int i=1;i<respuesta.length;i++){
            if(!respuesta[i][1].equals("1"))
                CBEmpleado.addItem(respuesta[i][0]);
        }
    }
    
    public void CorteSucExt(){
        //llenamos la tabla con los registros, segun el usuario y sucursal que elija
        String consulta=("select (ventas.idVenta,usuarios.nombreUsuario,ventas.numCaja,"
            + "ventas.fechaVenta,ventas.totalVenta,ventas.totalGanancia,ventas.CantidadProductos)"
            + " from ventas inner join usuarios on ventas.idUsuarioFK=usuarios.idUsuario"
            + " where usuarios.idUsuario="+getIdEmpleado((String)CBEmpleado.getSelectedItem())+
                " && ventas.fechaVenta>"+new SimpleDateFormat("yyyy-MM-dd").format(DateInicio.getDate())+
                " && ventas.fechaVenta<"+new SimpleDateFormat("yyyy-MM-dd").format(DateFin.getDate()));


        for(int i=0;i<sucursales.length;i++){
                if(sucursales[i][0].equals(CBCorte.getSelectedItem())){
                    String [][] respuestaTemp=cliente.ConexionTemp(sucursales[i][1],Integer.parseInt(sucursales[i][2]),consulta);
                    if(respuestaTemp==null){
                        CBEmpleado.removeAllItems();
                        limpiarCamposTabla();
                        return;
                    }
                    LlenarTablaSucursal(respuestaTemp);
                    consulta=("select (ventas.totalVenta,ventas.totalGanancia)"
                        + " from ventas inner join usuarios on ventas.idUsuarioFK=usuarios.idUsuario"
                        + " where usuarios.idUsuario="+getIdEmpleado((String)CBEmpleado.getSelectedItem())
                        + " && ventas.fechaVenta>"+new SimpleDateFormat("yyyy-MM-dd").format(DateInicio.getDate())
                        + " && ventas.fechaVenta<"+new SimpleDateFormat("yyyy-MM-dd").format(DateFin.getDate()));
                    String [][] empleadosTemp=cliente.ConexionTemp(sucursales[i][1],Integer.parseInt(sucursales[i][2]),consulta);
                    LlenarTotalSuc(empleadosTemp);
                }
            }
    }
   
    //metodo para calcular el total de las ventas y el total de las ganancias
    private void LlenarTotalSuc(String[][] res){
        TxtTotalVenta.setText(" ");
        TxtTotalGanancia.setText(" ");
        int totalventa=0;
        int totalganancia=0;
        //recorremos la matriz
        for(int i=1;i<res.length;i++){
            for(int j=0;j<res[i].length;j++){
                //sumamos el total de cada venta
                if(j==0)
                   totalventa+=Integer.parseInt(res[i][j]);
                //sumamos el total de las ganancias de cada venta 
                else if(j==1)
                   totalganancia+=Integer.parseInt(res[i][j]);
            }
        }
        //al final colocamos los valores en los cuadros de texto
        TxtTotalVenta.setText(String.valueOf(totalventa));
        TxtTotalGanancia.setText(String.valueOf(totalganancia));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBack;
    private javax.swing.JButton BtnCorte;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnHome;
    private javax.swing.JButton BtnSalirCorte;
    private javax.swing.JComboBox<String> CBCorte;
    private javax.swing.JComboBox<String> CBEmpleado;
    private com.toedter.calendar.JDateChooser DateFin;
    private com.toedter.calendar.JDateChooser DateInicio;
    private javax.swing.JLabel TxtTotalGanancia;
    private javax.swing.JLabel TxtTotalVenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_corte;
    private javax.swing.JLabel totalGananciaTxt;
    private javax.swing.JLabel totalVentaTxt;
    // End of variables declaration//GEN-END:variables
}
