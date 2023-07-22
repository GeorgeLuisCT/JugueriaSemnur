package salidas;

import alertas.principal.ErrorAlert;
import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class Opciones {

    static ConexionBD cc = new ConexionBD();
    static Connection cn = cc.conexion();
    static PreparedStatement ps;

    public static ArrayList<Integer> listaIDProductos = new ArrayList<>();

    public static int registrar(Sentencias uc) {
        int rsu = 0;
        String sql = Sentencias.REGISTRAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, uc.getIdproducto());
            ps.setFloat(2, uc.getCantidad());
            ps.setDouble(3, uc.getPrecioUnitario());
            ps.setDate(4, new java.sql.Date(uc.getFecha().getTime()));
            ps.setString(5, uc.getDescripcion());
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }

    public static int actualizar(Sentencias uc) {
        int rsu = 0;
        String sql = Sentencias.ACTUALIZAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, uc.getIdproducto());
            ps.setFloat(2, uc.getCantidad());
            ps.setDouble(3, uc.getPrecioUnitario());
            ps.setDate(4, new java.sql.Date(uc.getFecha().getTime()));
            ps.setString(5, uc.getDescripcion());
            ps.setInt(6, uc.getIdcompra());
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }

    public static int eliminar(int id) {
        int rsu = 0;
        String sql = Sentencias.ELIMINAR;

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
        String sql = Sentencias.ELIMINAR_TODO;

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

    public static void totalGastos() {
        int filas = salidas.Salidas.tabla.getRowCount();
        double totalE = 0.0;
        for (int i = 0; i < filas; i++) {
            totalE = totalE + Double.parseDouble(salidas.Salidas.tabla.getValueAt(i, 3).toString());
        }
        salidas.Salidas.lblTotal1.setText(String.valueOf(totalE));
    }

    public static void listar(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) salidas.Salidas.tabla.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (busca.equals("")) {
            sql = Sentencias.LISTAR;
            salidas.Salidas.descripcion.setText("");
        } else {
            sql = "SELECT c.idcompra, p.nombre AS nombre_producto, c.cantidad, c.precio_unitario, c.fecha, c.descripcion "
                    + "FROM compras c "
                    + "JOIN productos p ON c.idproducto = p.idproducto "
                    + "WHERE (c.idcompra LIKE '" + busca + "%' OR "
                    + "p.nombre LIKE '" + busca + "%' OR c.cantidad LIKE '" + busca + "%' OR "
                    + "c.precio_unitario LIKE '" + busca + "%' OR c.fecha LIKE '" + busca + "%') "
                    + "ORDER BY c.fecha";
            salidas.Salidas.descripcion.setText("");
        }
        String datos[] = new String[6];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("idcompra");
                datos[1] = rs.getString("nombre_producto");
                datos[2] = rs.getString("cantidad");
                datos[3] = rs.getString("precio_unitario");
                datos[4] = rs.getString("fecha");
                datos[5] = rs.getString("descripcion");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void extraerDescripcion(String id) {
        String c = null;
        String SQL = "SELECT * FROM compras WHERE idcompra = " + id;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                c = rs.getString("descripcion");
            }
            System.out.println(c);
            salidas.Salidas.descripcion.setText(c);

        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void llenarComboBox(JComboBox<String> comboBox, String sentecia) {
        listaIDProductos.clear();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            ConexionBD conect = new ConexionBD();
            connection = conect.conexion();
            statement = connection.prepareStatement(sentecia);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int idProducto = resultSet.getInt("idproducto");
                comboBox.addItem(nombre);
                listaIDProductos.add(idProducto);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }
}
