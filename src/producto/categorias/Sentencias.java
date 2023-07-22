
package producto.categorias;



public class Sentencias {

    // Consulta para listar categorías
    public static String LISTAR_CATEGORIAS = "SELECT idcategoria, nombre, unidadMedida, duracionDias FROM categorias ORDER BY idcategoria";

    // Resto de consultas para categorías (con las opciones que tenías para productos)
    public static String REGISTRAR_CATEGORIA = "INSERT INTO categorias (nombre, unidadMedida, duracionDias) VALUES (?, ?, ?)";

    public static String ACTUALIZAR_CATEGORIA = "UPDATE categorias SET nombre = ?, unidadMedida =?, duracionDias =? WHERE idcategoria = ?";

    public static String ELIMINAR_CATEGORIA = "DELETE FROM categorias WHERE idcategoria = ?";

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

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public int getPeridoDuracion() {
        return PeridoDuracion;
    }

    public void setPeridoDuracion(int PeridoDuracion) {
        this.PeridoDuracion = PeridoDuracion;
    }
    
    
    
     private int id;
    private String nombre;
    private String unidadMedida;
    private int PeridoDuracion;
}