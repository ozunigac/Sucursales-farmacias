/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportesfarmacia;

/**
 *
 * @author Victor manuel
 */
public class TablaProductos {

    private String idVenta;
    private String nombreUsuario;
    private String numCaja;
    private String fechaVenta;
    private String TotalVenta;
    private String TotalGanancia;
    private String CantidadProductos;
    private String misucursal;
    public TablaProductos(String a1, String a2, String a3, String a4,String a5,String a6, String a7,String a8) {
        this.idVenta=a1;
        this.nombreUsuario=a2;
        this.numCaja=a3;
        this.fechaVenta=a4;
        this.TotalVenta=a5;
        this.TotalGanancia=a6;
        this.CantidadProductos=a7;
        this.misucursal=a8;
    }

    public String getmisucursal(){
        return misucursal;
    }
    public String getidventa() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public String getnombreusuario() {
        return nombreUsuario;
    }

    public void setnombreusuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getnumcaja() {
        return numCaja;
    }

    public void setnumcaja(String numCaja) {
        this.numCaja = numCaja;
    }

    public String getfechaventa() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String gettotalventa() {
        return TotalVenta;
    }

    public void setTotalVenta(String TotalVenta) {
        this.TotalVenta = TotalVenta;
    }

    public String getganancia() {
        return TotalGanancia;
    }

    public void setTotalGanancia(String TotalGanancia) {
        this.TotalGanancia = TotalGanancia;
    }

    public String getcantidadproductos() {
        return CantidadProductos;
    }

    public void setCantidadProductos(String CantidadProductos) {
        this.CantidadProductos = CantidadProductos;
    }

}
