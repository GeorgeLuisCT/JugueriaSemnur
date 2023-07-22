
package producto;


public class senteciasVentas {
    
public static String LISTAR = "SELECT p.idproducto, p.nombre, p.descripcion, p.precio "
    + "FROM productos_Ventas p "
    + "ORDER BY p.idproducto";

public static String LISTAR_AL = "SELECT p.*, c.unidadMedida FROM productos_Ventas p JOIN categorias c ON p.idcategoria = c.idcategoria WHERE p.stock != 0 ORDER BY p.idproducto";

public static String REGISTRAR = "INSERT INTO productos_Ventas(nombre, descripcion, precio) "
    + "VALUES(?, ?, ?)";

public static String ACTUALIZAR = "UPDATE productos_Ventas SET "
    + "nombre = ?, "
    + "descripcion = ?, "
    + "precio = ? "
    + "WHERE idproducto = ?";

public static String ELIMINAR = "DELETE FROM productos_Ventas WHERE idproducto = ?";

public static String ELIMINAR_TODO = "TRUNCATE TABLE productos_Ventas";



    private int id;
    private String nombre;
    private String descripcion;
    private double precio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
