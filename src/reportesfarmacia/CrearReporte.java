package reportesfarmacia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class CrearReporte {

    public void Reporte1(String Tabla[][],String suc) throws JRException {
        Map parameters = new HashMap();
        parameters.put("idventa", "Num. Venta");
        parameters.put("nombreusuario", "nombre");//Usuario
        parameters.put("numcaja", "nombre");//Numero de caja
        parameters.put("fechaventa", "date");
        parameters.put("totalventa", "precio");
        parameters.put("ganancia", "precio");
        parameters.put("cantidadproductos", "fabricante");
        parameters.put("misucursal","misucursal");
        if (System.getProperty("user.dir").contains("/")) {
            parameters.put("MiPath", System.getProperty("user.dir") + "/src/reportes/");

        } else {
            parameters.put("MiPath", System.getProperty("user.dir") + "\\src\\reportes\\");
            System.out.println(""+System.getProperty("user.dir") + "\\src\\reportes\\");
        }

        ArrayList productos = new ArrayList();

        for (int i = 0; i < Tabla.length; i++) {
            TablaProductos p1 = new TablaProductos(Tabla[i][0], Tabla[i][1], Tabla[i][2], Tabla[i][3],Tabla[i][4],Tabla[i][5],Tabla[i][6],suc);
            productos.add(p1);
        }
        JasperPrint print = null;
        if (System.getProperty("user.dir").contains("/")) {
            print = JasperFillManager.fillReport(System.getProperty("user.dir") + "/src/reportes/ReporteFarmacia.jasper", parameters, new JRBeanCollectionDataSource(productos));
        } else {
            print = JasperFillManager.fillReport(System.getProperty("user.dir") + "\\src\\reportes\\ReporteFarmacia.jasper", parameters, new JRBeanCollectionDataSource(productos));
        }
        javax.swing.JFileChooser jF1 = new javax.swing.JFileChooser();

        String ruta = "";
        try {
            if (jF1.showSaveDialog(null) == jF1.APPROVE_OPTION) {
                ruta = jF1.getSelectedFile().getAbsolutePath();
                System.out.println("Mi ruta " + ruta);
//Aqui ya tiens la ruta,,,ahora puedes crear un fichero n esa rsaduta y escribir lo k kieras... 
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JasperExportManager.exportReportToPdfFile(print, ruta + ".pdf");
      JasperViewer.viewReport(print, false);

    }
}
