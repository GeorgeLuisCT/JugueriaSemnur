package ventas;

public class Sentencias {

public static String LISTAR = "SELECT * FROM productos_Ventas ORDER BY idproducto";

    public static String LISTAR1 = "SELECT * FROM ventas " +
                               "JOIN productos ON ventas.idproducto = productos.idproducto";


    public static String REGISTRAR = "INSERT INTO ventas(idproducto,idusuario, cantidad,precio_unitario, total, fecha) "
            + "VALUES(?,?,?,?,?,?)";

    public static String ACTUALIZAR = "UPDATE productos SET "
            + "nombre=?, "
            + "descripcion=?, "
            + "idcategoria=?, "
            + "precio=? "
            + "WHERE idproducto=?";

    public static String ELIMINAR = "DELETE FROM ventas WHERE idventa = ?";

    public static String ELIMINAR_TODO = "TRUNCATE TABLE ventas";

    private int id_usuario;
    private int id_producto;
    private int cantidad;
    private double precio_unitario;

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
    private double total;
    private String fecha;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
