
package producto;

import java.util.Date;



public class Sentencias {

   
public static String LISTAR = "SELECT p.idproducto, p.nombre, p.descripcion, c.unidadMedida, p.precio, p.stock, c.nombre AS nombre_categoria "
    + "FROM productos p "
    + "JOIN categorias c ON p.idcategoria = c.idcategoria "
    + "ORDER BY p.idproducto";

    public static String LISTAR_AL = "SELECT p.*, c.unidadMedida FROM productos p JOIN categorias c ON p.idcategoria = c.idcategoria WHERE p.stock != 0 ORDER BY p.idproducto";

    public static String REGISTRAR = "INSERT INTO productos(nombre, descripcion, idcategoria, precio, stock) "
            + "VALUES(?, ?, ?, ?, ?)";

    public static String ACTUALIZAR = "UPDATE productos SET "
            + "nombre = ?, "
            + "descripcion = ?, "
            + "idcategoria = ?, "
            + "precio = ?, "
            + "stock = ? "
            + "WHERE idproducto = ?";

    public static String ACTUALIZAR_STOCK = "UPDATE productos SET "
            + "stock = ? "
            + "WHERE idproducto = ?";

    public static String ELIMINAR = "DELETE FROM productos WHERE idproducto = ?";

    public static String ELIMINAR_TODO = "TRUNCATE TABLE productos";

    public static String sqlCategorias = "SELECT nombre, unidadMedida FROM categorias";


    private int id;
    private String nombre;
    private String descripcion;
    private int tipo;
    private double precio;
    private double stock;
    private Date fechaCompra;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

}
