package Clases;

/**
 *
 * @author Isma
 */
public class Producto {
    private int idProducto;
    private String nombre;
    private int existencia;
    private int precioUnidad;
    private int precioProveedor;
    private String descripcion;
    private int idCategoria;

    
    
    public Producto(int id, String no, int ex, int pu, int pp, String des, int idca){
        this.idProducto=id;
        this.nombre=no;
        this.existencia=ex;
        this.precioUnidad=pu;
        this.precioProveedor=pp;
        this.descripcion=des;
        this.idCategoria=idca;
    }
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public int getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(int precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public int getPrecioProveedor() {
        return precioProveedor;
    }

    public void setPrecioProveedor(int precioProveedor) {
        this.precioProveedor = precioProveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    
}
