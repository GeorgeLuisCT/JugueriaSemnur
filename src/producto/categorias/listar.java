
package producto.categorias;


public class listar {
    
    private int idCategoria;
    private String nombre;
    private String unidadMediga;

    public listar(int idCategoria, String nombre, String unidadMediga) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.unidadMediga = unidadMediga;
    }

   

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadMediga() {
        return unidadMediga;
    }

    public void setUnidadMediga(String unidadMediga) {
        this.unidadMediga = unidadMediga;
    }
    
    
}
