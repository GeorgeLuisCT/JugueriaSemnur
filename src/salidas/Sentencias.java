
package salidas;

import java.util.Date;

public class Sentencias {

    public static String LISTAR = "SELECT c.idcompra, p.nombre AS nombre_producto, c.cantidad, c.precio_unitario, c.fecha, c.descripcion FROM compras c JOIN productos p ON c.idproducto = p.idproducto ORDER BY c.fecha";


    public static String REGISTRAR = "INSERT INTO compras(idproducto, cantidad, precio_unitario, fecha, descripcion) "
            + "VALUES(?, ?, ?, ?, ?)";

    public static String ACTUALIZAR = "UPDATE compras SET "
            + "idproducto=?, "
            + "cantidad=?, "
            + "precio_unitario=?, "
            + "fecha=?, "
            + "descripcion=? "
            + "WHERE idcompra=?";

    public static String ELIMINAR = "DELETE FROM compras WHERE idcompra = ?";

    public static String ELIMINAR_TODO = "TRUNCATE TABLE compras";
    
      public static  String SELECT_PRODUCTOS = "SELECT nombre, idproducto FROM productos";

    private int idcompra;
    private int idproducto;
    private float cantidad;
    private double precioUnitario;
    private Date fecha;
    private String descripcion;

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() { 
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
       
}
