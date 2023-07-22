package producto.recetas;

import alertas.principal.SuccessAlert;
import producto.*;
import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class Opciones {

    static ConexionBD cc = new ConexionBD();
    static Connection cn = cc.conexion();
    static PreparedStatement ps;

    public static void listarProductosReceta(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) CrearReceta.tablaProductos.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        String sql = "";
        if (busca.equals("")) {
            sql = Sentencias.LISTAR;
        } else {
            sql = "SELECT p.idproducto, p.nombre, p.descripcion, c.nombre AS nombre_categoria, p.precio, p.stock, c.unidadMedida "
                    + "FROM productos p "
                    + "JOIN categorias c ON p.idcategoria = c.idcategoria "
                    + "WHERE p.idproducto LIKE '" + busca + "%' OR "
                    + "p.nombre LIKE '" + busca + "%' OR p.descripcion LIKE '" + busca + "%' OR "
                    + "c.nombre LIKE '" + busca + "%' OR CAST(p.precio AS CHAR) LIKE '" + busca + "%' OR "
                    + "CAST(p.stock AS CHAR) LIKE '" + busca + "%' "
                    + "ORDER BY p.idproducto";
        }

        String datos[] = new String[6];

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("idproducto");
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("descripcion");
                datos[3] = rs.getString("nombre_categoria");
                datos[4] = rs.getString("precio");
                datos[5] = rs.getString("stock") + " " + rs.getString("unidadMedida");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void listarSeleccionarProducto(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) SeleccionProducto.tablaProductos.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        String sql = "";
        if (busca.equals("")) {
            sql = Sentencias.LISTAR;
        } else {
            sql = "SELECT p.idproducto, p.nombre, p.descripcion, c.nombre AS nombre_categoria, p.precio, p.stock, c.unidadMedida "
                    + "FROM productos p "
                    + "JOIN categorias c ON p.idcategoria = c.idcategoria "
                    + "WHERE p.idproducto LIKE '" + busca + "%' OR "
                    + "p.nombre LIKE '" + busca + "%' OR p.descripcion LIKE '" + busca + "%' OR "
                    + "c.nombre LIKE '" + busca + "%' OR CAST(p.precio AS CHAR) LIKE '" + busca + "%' OR "
                    + "CAST(p.stock AS CHAR) LIKE '" + busca + "%' "
                    + "ORDER BY p.idproducto";
        }

        String datos[] = new String[6];

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("idproducto");
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("descripcion");
                datos[3] = rs.getString("nombre_categoria");
                datos[4] = rs.getString("precio");
                datos[5] = rs.getString("stock") + " " + rs.getString("unidadMedida");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static int extraerID() {
        int c = 0;
        String SQL = "SELECT MAX(idproducto) FROM productos";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                c = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public static void cancelarTransaccion() {
        try {
            cn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void registrarReceta(String nombre, String descripcion, int codigoProductoVenta) {
    try {
        // Insertar la receta en la tabla 'receta'
        String insertRecetaQuery = "INSERT INTO receta (nombre, descripcion, idproductoventa) VALUES (?, ?, ?)";
        PreparedStatement insertRecetaStmt = cn.prepareStatement(insertRecetaQuery, Statement.RETURN_GENERATED_KEYS);
        insertRecetaStmt.setString(1, nombre);
        insertRecetaStmt.setString(2, descripcion);
        insertRecetaStmt.setInt(3, codigoProductoVenta);
        insertRecetaStmt.executeUpdate();

        // Obtener el ID de la receta insertada
        ResultSet generatedKeys = insertRecetaStmt.getGeneratedKeys();
        int idReceta = -1;
        if (generatedKeys.next()) {
            idReceta = generatedKeys.getInt(1);
        }

        // Insertar cada producto de la tablaIngredientes en la tabla 'receta_ingredientes'
        DefaultTableModel modeloTablaIngredientes = (DefaultTableModel) CrearReceta.tablaIngredientes.getModel();
        for (int i = 0; i < modeloTablaIngredientes.getRowCount(); i++) {
            int codigoProducto = Integer.parseInt(modeloTablaIngredientes.getValueAt(i, 0).toString());
            float cantidad = Float.parseFloat(modeloTablaIngredientes.getValueAt(i, 2).toString());

            // Insertar el producto en la tabla 'receta_ingredientes'
            String insertRecetaIngredientesQuery = "INSERT INTO receta_ingredientes (idreceta, idproducto, cantidad) VALUES (?, ?, ?)";
            PreparedStatement insertRecetaIngredientesStmt = cn.prepareStatement(insertRecetaIngredientesQuery);
            insertRecetaIngredientesStmt.setInt(1, idReceta);
            insertRecetaIngredientesStmt.setInt(2, codigoProducto);
            insertRecetaIngredientesStmt.setFloat(3, cantidad);
            insertRecetaIngredientesStmt.executeUpdate();
        }

        SuccessAlert sa = new SuccessAlert(new JFrame(), true);
        sa.titulo.setText("¡HECHO!");
        sa.msj.setText("SE HA REGISTRADO LA RECETA");
        sa.msj1.setText("PARA VENTA");
        sa.setVisible(true);
        OpcionesVentas.listar("");
        ProductosVentas.seleccionaFila(String.valueOf(codigoProductoVenta));
        System.out.println("Código de la nueva receta registrada: " + idReceta);
    } catch (SQLException e) {
        // Error al registrar la receta
        // Manejar el error de acuerdo a tus necesidades
    }
}
    
   public static void registrarRecetaUnicoProducto(String nombre, String descripcion,float cantidad, int codigoProductoVenta) {
    try {
        // Insertar la receta en la tabla 'receta'
        String insertRecetaQuery = "INSERT INTO receta (nombre, descripcion, idproductoventa) VALUES (?, ?, ?)";
        PreparedStatement insertRecetaStmt = cn.prepareStatement(insertRecetaQuery, Statement.RETURN_GENERATED_KEYS);
        insertRecetaStmt.setString(1, nombre);
        insertRecetaStmt.setString(2, descripcion);
        insertRecetaStmt.setInt(3, codigoProductoVenta);
        insertRecetaStmt.executeUpdate();

        // Obtener el ID de la receta insertada
        ResultSet generatedKeys = insertRecetaStmt.getGeneratedKeys();
        int idReceta = -1;
        if (generatedKeys.next()) {
            idReceta = generatedKeys.getInt(1);
        }

        // Insertar el producto seleccionado de la tablaIngredientes en la tabla 'receta_ingredientes'
        DefaultTableModel modeloTablaIngredientes = (DefaultTableModel) SeleccionProducto.tablaProductos.getModel();
        int filaSeleccionada = SeleccionProducto.tablaProductos.getSelectedRow();
        if (filaSeleccionada != -1) {
            int codigoProducto = Integer.parseInt(modeloTablaIngredientes.getValueAt(filaSeleccionada, 0).toString());

            // Insertar el producto en la tabla 'receta_ingredientes'
            String insertRecetaIngredientesQuery = "INSERT INTO receta_ingredientes (idreceta, idproducto, cantidad) VALUES (?, ?, ?)";
            PreparedStatement insertRecetaIngredientesStmt = cn.prepareStatement(insertRecetaIngredientesQuery);
            insertRecetaIngredientesStmt.setInt(1, idReceta);
            insertRecetaIngredientesStmt.setInt(2, codigoProducto);
            insertRecetaIngredientesStmt.setFloat(3, cantidad);
            insertRecetaIngredientesStmt.executeUpdate();
        }

        SuccessAlert sa = new SuccessAlert(new JFrame(), true);
        sa.titulo.setText("¡HECHO!");
        sa.msj.setText("SE HA REGISTRADO LA RECETA");
        sa.msj1.setText("PARA VENTA");
        sa.setVisible(true);
        OpcionesVentas.listar("");
        ProductosVentas.seleccionaFila(String.valueOf(codigoProductoVenta));
        System.out.println("Código de la nueva receta registrada: " + idReceta);
    } catch (SQLException e) {
        // Error al registrar la receta
        // Manejar el error de acuerdo a tus necesidades
    }
}



   public static void obtenerRecetas(String parametroBusqueda) {
    DefaultTableModel modeloTablaRecetas = (DefaultTableModel) Recetas.tabla.getModel();

    try {
        // Limpiar la tabla
        modeloTablaRecetas.setRowCount(0);

        // Consultar las recetas de la base de datos
        String query = "SELECT r.idreceta, r.nombre AS nombre_receta, "
                + "r.descripcion AS descripcion_receta,  GROUP_CONCAT(CONCAT(p.nombre, ' (', ri.cantidad, ')')) AS ingredientes, "
                + " pv.nombre AS nombre_producto_venta   FROM receta r  JOIN receta_ingredientes ri ON r.idreceta = ri.idreceta"
                + " JOIN productos p ON ri.idproducto = p.idproducto  JOIN productos_Ventas pv ON r.idproductoventa = pv.idproducto"
                + " WHERE r.nombre LIKE ? OR r.descripcion LIKE ? OR p.nombre LIKE ? OR pv.nombre LIKE ?"
                + " GROUP BY r.idreceta";
        PreparedStatement stmt = cn.prepareStatement(query);
        stmt.setString(1, "%" + parametroBusqueda + "%");
        stmt.setString(2, "%" + parametroBusqueda + "%");
        stmt.setString(3, "%" + parametroBusqueda + "%");
        stmt.setString(4, "%" + parametroBusqueda + "%");
        ResultSet rs = stmt.executeQuery();

        // Agregar los resultados a la tabla
        while (rs.next()) {
            int idReceta = rs.getInt("idreceta");
            String nombreReceta = rs.getString("nombre_receta");
            String descripcionReceta = rs.getString("descripcion_receta");
            String ingredientes = rs.getString("ingredientes");
            String nombreProductoVenta = rs.getString("nombre_producto_venta");

            Object[] fila = {idReceta, nombreReceta, descripcionReceta, ingredientes, nombreProductoVenta};
            modeloTablaRecetas.addRow(fila);
        }

        // Cerrar el ResultSet y el Statement
        rs.close();
        stmt.close();
    } catch (SQLException e) {
        System.out.println(e);
    }
}


}
