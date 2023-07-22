package producto.categorias;

import alertas.principal.ErrorAlert;
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

     public static int registrarCategoria(Sentencias categoria) {
        int rsu = 0;
        String sql = Sentencias.REGISTRAR_CATEGORIA;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getUnidadMedida());
            ps.setInt(3, categoria.getPeridoDuracion());
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }

    public static int actualizarCategoria(Sentencias categoria) {
        int rsu = 0;
        String sql = Sentencias.ACTUALIZAR_CATEGORIA;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getUnidadMedida());
                ps.setInt(3, categoria.getPeridoDuracion());
            ps.setInt(4, categoria.getId());
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }

    public static int eliminarCategoria(int id) {
        int rsu = 0;
        String sql = Sentencias.ELIMINAR_CATEGORIA;

        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
              ErrorAlert er = new ErrorAlert(new JFrame(), true);
            er.titulo.setText("OOPS...");
            er.msj.setText("NO SE PUEDE BORRAR EL REGISTRO");
            er.msj1.setText("HAY PRODUCTOS ASOCIADOS");
            er.setVisible(true);
        }
        System.out.println(sql);
        return rsu;
    }

    public static void listarCategorias(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) Categorias.tabla.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        String sql = "";
        if (busca.equals("")) {
            sql = Sentencias.LISTAR_CATEGORIAS;
        } else {
            sql = "SELECT idcategoria, nombre, unidadMedida, duracionDias FROM categorias WHERE idcategoria LIKE '" + busca + "%' OR nombre LIKE '" + busca + "%' OR unidadMedida LIKE '" + busca + "%' ORDER BY idcategoria";
        }

        String datos[] = new String[4];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("idcategoria");
                datos[1] = rs.getString("nombre");
                 datos[2] = rs.getString("unidadMedida");
                 datos[3] = rs.getString("duracionDias");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int extraerIDCategoria() {
        int c = 0;
        String SQL = "SELECT MAX(idcategoria) FROM categorias";

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
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
