package producto;

import alertas.principal.ErrorAlert;
import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class OpcionesVentas {

    static ConexionBD cc = new ConexionBD();
    static Connection cn = cc.conexion();
    static PreparedStatement ps;

public static int registrar(senteciasVentas uc) {
    int rsu = 0;
    String sql = senteciasVentas.REGISTRAR;
    try {
        ps = cn.prepareStatement(sql);
        ps.setString(1, uc.getNombre());
        ps.setString(2, uc.getDescripcion());
        ps.setDouble(3, uc.getPrecio());

        rsu = ps.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    System.out.println(sql);
    return rsu;
}

public static int actualizar(senteciasVentas uc) {
    int rsu = 0;
    String sql = senteciasVentas.ACTUALIZAR;
    try {
        ps = cn.prepareStatement(sql);
        ps.setString(1, uc.getNombre());
        ps.setString(2, uc.getDescripcion());
        ps.setDouble(3, uc.getPrecio());
        ps.setInt(4, uc.getId());
        rsu = ps.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    System.out.println(sql);
    return rsu;
}

public static int eliminar(int id) {
    int rsu = 0;
    String sql = senteciasVentas.ELIMINAR;

    try {
        ps = cn.prepareStatement(sql);
        ps.setInt(1, id);
        rsu = ps.executeUpdate();
    } catch (SQLException ex) {
        ErrorAlert er = new ErrorAlert(new JFrame(), true);
            er.titulo.setText("OOPS...");
            er.msj.setText("NO SE PUEDE BORRAR ESTE REGISTRO");
            er.msj1.setText("EXISTEN REGISTROS ACOCIADOS");
            er.setVisible(true);
    }
    System.out.println(sql);
    return rsu;
}

public static int eliminarTodo() {
    int rsu = 0;
    String sql = senteciasVentas.ELIMINAR_TODO;

    try {
        ps = cn.prepareStatement(sql);
        rsu = ps.executeUpdate();
        rsu++;
    } catch (SQLException ex) {
        ErrorAlert er = new ErrorAlert(new JFrame(), true);
            er.titulo.setText("OOPS...");
            er.msj.setText("NO SE PUEDE BORRAR TODO");
            er.msj1.setText("EXISTEN REGISTROS ACOCIADOS");
            er.setVisible(true);
    }
    System.out.println(sql);
    return rsu;
}

public static void listar(String busca) {
    DefaultTableModel modelo = (DefaultTableModel) producto.ProductosVentas.tablaventas.getModel();

    while (modelo.getRowCount() > 0) {
        modelo.removeRow(0);
    }

    String sql = "";
    if (busca.equals("")) {
        sql = senteciasVentas.LISTAR;
    } else {
        sql = "SELECT p.idproducto, p.nombre, p.descripcion, p.precio "
            + "FROM productos_Ventas p "
            + "WHERE p.idproducto LIKE '" + busca + "%' OR "
            + "p.nombre LIKE '" + busca + "%' OR p.descripcion LIKE '" + busca + "%' OR "
            + "CAST(p.precio AS CHAR) LIKE '" + busca + "%' "
            + "ORDER BY p.idproducto";
    }

    String datos[] = new String[4];

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            datos[0] = rs.getString("idproducto");
            datos[1] = rs.getString("nombre");
            datos[2] = rs.getString("descripcion");
            datos[3] = rs.getString("precio");
            modelo.addRow(datos);
        }
    } catch (SQLException ex) {
        Logger.getLogger(OpcionesVentas.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public static int extraerID() {
    int c = 0;
    String SQL = "SELECT MAX(idproducto) FROM productos_Ventas";

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(SQL);
        while (rs.next()) {
            c = rs.getInt(1);
        }
    } catch (SQLException ex) {
        Logger.getLogger(OpcionesVentas.class.getName()).log(Level.SEVERE, null, ex);
    }
    return c;
}


    /*public static void iniciarTransaccion() {
        try {
            cn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

 /*  public static void finalizarTransaccion() {
        try {
            cn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    public static void cancelarTransaccion() {
        try {
            cn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
