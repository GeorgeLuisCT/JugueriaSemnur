package ventas;

import alertas.principal.SuccessAlert;
import conexion.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CorteCajaDAO {

    static ConexionBD cc = new ConexionBD();
    public static Connection cn = cc.conexion();
    static PreparedStatement ps;

    public static void mostrarCortesCaja(String busca) {
    DefaultTableModel model = (DefaultTableModel) ListaVentas.tabla.getModel();
    model.setRowCount(0); // Limpiar la tabla antes de llenarla nuevamente

    try (Statement stmt = cn.createStatement()) {
        String query = "SELECT * FROM corte_caja WHERE "
                + "idcorte LIKE '%" + busca + "%' OR "
                + "fecha LIKE '%" + busca + "%' OR "
                + "hora LIKE '%" + busca + "%' OR "
                + "total_ventas LIKE '%" + busca + "%' OR "
                + "total_compras LIKE '%" + busca + "%' OR "
                + "total_efectivo LIKE '%" + busca + "%' OR "
                + "diferencia LIKE '%" + busca + "%'";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int idCorte = rs.getInt("idcorte");
            String fecha = rs.getString("fecha");
            String hora = rs.getString("hora");
            double totalVentas = rs.getDouble("total_ventas");
            double totalCompras = rs.getDouble("total_compras");
            double totalEfectivo = rs.getDouble("total_efectivo");
            double diferencia = rs.getDouble("diferencia");

            model.addRow(new Object[]{idCorte, fecha, hora, totalVentas, totalCompras, totalEfectivo, diferencia});
        }

        rs.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public static void generarCorteCaja(Date fechaCorte) {
        try (
                PreparedStatement stmtVentas = cn.prepareStatement(
                        "SELECT SUM(dv.cantidad * pv.precio) AS total_ventas "
                        + "FROM detalle_venta dv "
                        + "INNER JOIN productos_Ventas pv ON dv.idproducto = pv.idproducto "
                        + "INNER JOIN ventas v ON dv.idventa = v.idventa "
                        + "WHERE v.fecha = ?"); PreparedStatement stmtCompras = cn.prepareStatement(
                        "SELECT SUM(cantidad * precio_unitario) AS total_compras "
                        + "FROM compras "
                        + "WHERE fecha = ?"); PreparedStatement stmtEfectivo = cn.prepareStatement(
                        "SELECT SUM(total) AS total_efectivo "
                        + "FROM ventas "
                        + "WHERE fecha = ?"); PreparedStatement stmtInsertCorte = cn.prepareStatement(
                        "INSERT INTO corte_caja (fecha, hora, total_ventas, total_compras, total_efectivo, diferencia) "
                        + "VALUES (?, ?, ?, ?, ?, ?)")) {

            // Obtener el total de ventas
            stmtVentas.setDate(1, new java.sql.Date(fechaCorte.getTime()));
            ResultSet rsVentas = stmtVentas.executeQuery();
            double totalVentas = 0.0;
            if (rsVentas.next()) {
                totalVentas = rsVentas.getDouble("total_ventas");
            }

            // Obtener el total de compras
            stmtCompras.setDate(1, new java.sql.Date(fechaCorte.getTime()));
            ResultSet rsCompras = stmtCompras.executeQuery();
            double totalCompras = 0.0;
            if (rsCompras.next()) {
                totalCompras = rsCompras.getDouble("total_compras");
            }

            // Obtener el total de efectivo
            stmtEfectivo.setDate(1, new java.sql.Date(fechaCorte.getTime()));
            ResultSet rsEfectivo = stmtEfectivo.executeQuery();
            double totalEfectivo = 0.0;
            if (rsEfectivo.next()) {
                totalEfectivo = rsEfectivo.getDouble("total_efectivo");
            }

            // Calcular la diferencia
            double diferencia = totalVentas - totalCompras - totalEfectivo;
             // Obtener la hora actual
        LocalTime horaActual = LocalTime.now();

            // Insertar el corte de caja en la base de datos
            stmtInsertCorte.setDate(1, new java.sql.Date(fechaCorte.getTime()));
            stmtInsertCorte.setTime(2, java.sql.Time.valueOf(horaActual));
            stmtInsertCorte.setDouble(3, totalVentas);
            stmtInsertCorte.setDouble(4, totalCompras);
            stmtInsertCorte.setDouble(5, totalEfectivo);
            stmtInsertCorte.setDouble(6, diferencia);
            int rowsAffected = stmtInsertCorte.executeUpdate();

            if (rowsAffected > 0) {
                SuccessAlert sa = new SuccessAlert(new JFrame(), true);
                sa.titulo.setText("¡HECHO!");
                sa.msj.setText("SE HA REALIZADO ");
                sa.msj1.setText("EL CORTE DE CAJA");
                sa.setVisible(true);
            } else {
                System.out.println("No se pudo registrar el corte de caja.");
            }

        } catch (SQLException e) {

            System.out.println("error:" + e);
        }
    }
    
     public static void  consultarVentas(JTable table) {
        // Establecer la conexión
        ConexionBD conexionBD = new ConexionBD();
        Connection connection = conexionBD.conexion();

        // Realizar la consulta y obtener los resultados
        String query = "SELECT v.idventa, v.fecha, t.nombre AS nombre_trabajador, v.total, GROUP_CONCAT(p.nombre SEPARATOR ', ') AS productos "
                + "FROM ventas v "
                + "INNER JOIN usuarios u ON v.idusuario = u.idusuario "
                + "INNER JOIN trabajadores t ON u.idusuario = t.idtrabajador "
                + "INNER JOIN detalle_venta dv ON v.idventa = dv.idventa "
                + "INNER JOIN productos p ON dv.idproducto = p.idproducto "
                + "GROUP BY v.idventa";
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            // Crear el modelo de tabla
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("ID Venta");
            tableModel.addColumn("Fecha");
            tableModel.addColumn("Nombre Trabajador");
            tableModel.addColumn("Total");
            tableModel.addColumn("Productos");

            // Llenar el modelo con los resultados de la consulta
            while (resultSet.next()) {
                Object[] row = new Object[5];
                row[0] = resultSet.getInt("idventa");
                row[1] = resultSet.getDate("fecha");
                row[2] = resultSet.getString("nombre_trabajador");
                row[3] = resultSet.getDouble("total");
                row[4] = resultSet.getString("productos");
                tableModel.addRow(row);
            }

            // Asignar el modelo a la JTable
            table.setModel(tableModel);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta: " + e.getMessage());
        } finally {
            // Cerrar la conexión
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

}
