package informes;

import conexion.ConexionBD;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class Opciones {

    public static ArrayList<Integer> listaIDProductos = new ArrayList<>();
   

   public static void obtenerInformeSemanal(String busca, JTable table) {
    ConexionBD conexionBD = new ConexionBD();
    try (Connection conn = conexionBD.conexion()) {
        String query = "SELECT * FROM informe_semanal WHERE idinforme LIKE ? OR fecha_inicio LIKE ? OR fecha_fin LIKE ? OR total_ventas LIKE ? OR total_compras LIKE ? OR ganancia LIKE ? OR perdida LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        String searchTerm = "%" + busca + "%";
        for (int i = 1; i <= 7; i++) {
            stmt.setString(i, searchTerm);
        }

        ResultSet rs = stmt.executeQuery();

        // Configurar el modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpiar la tabla

        // Obtener los datos de la consulta y agregarlos a la tabla
        while (rs.next()) {
            int idinforme = rs.getInt("idinforme");
            Date inicio = rs.getDate("fecha_inicio");
            Date fin = rs.getDate("fecha_fin");
            double totalVentas = rs.getDouble("total_ventas");
            double totalCompras = rs.getDouble("total_compras");
            double ganancias = rs.getDouble("ganancia");
            double perdidas = rs.getDouble("perdida");

            model.addRow(new Object[]{idinforme, inicio, fin, totalVentas, totalCompras, perdidas, ganancias});
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public static void obtenerInformeSemanalGrafico() {
        ConexionBD conexionBD = new ConexionBD();
        try (Connection conn = conexionBD.conexion()) {
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM informe_semanal";
            ResultSet rs = stmt.executeQuery(query);

            // Crear el conjunto de datos para el gráfico de barras separadas
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            // Obtener los datos de la consulta y agregarlos al conjunto de datos
            while (rs.next()) {
                Date inicio = rs.getDate("fecha_inicio");
                Date fin = rs.getDate("fecha_fin");
                double ganancias = rs.getDouble("ganancia");
                double perdidas = rs.getDouble("perdida");

                dataset.addValue(ganancias, "Ganancias", String.valueOf(inicio + " - " + fin));
                dataset.addValue(perdidas, "Pérdidas", String.valueOf(inicio + " - " + fin));
            }

            rs.close();
            stmt.close();

            // Crear el gráfico de barras separadas
            JFreeChart chart = ChartFactory.createBarChart("Informe Semanal", "Informe", "Valor",
                    dataset);

            // Obtener el plot del gráfico
            CategoryPlot plot = chart.getCategoryPlot();

            // Obtener el renderizador de barras
            BarRenderer renderer = (BarRenderer) plot.getRenderer();

            // Configurar colores de las barras
            renderer.setSeriesPaint(0, Color.BLUE); // Ganancias (azul)
            renderer.setSeriesPaint(1, Color.RED); // Pérdidas (rojo)

            // Crear un ChartPanel y agregarlo a un JFrame
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(500, 400));
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(chartPanel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void obtenerInformeMensual(String busca, JTable table) {
    ConexionBD conexionBD = new ConexionBD();
    try (Connection conn = conexionBD.conexion()) {
        String query = "SELECT * FROM informe_mensual WHERE idinforme LIKE ? OR mes LIKE ? OR anio LIKE ? OR total_ventas LIKE ? OR total_compras LIKE ? OR ganancia LIKE ? OR perdida LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        String searchTerm = "%" + busca + "%";
        for (int i = 1; i <= 7; i++) {
            stmt.setString(i, searchTerm);
        }

        ResultSet rs = stmt.executeQuery();

        // Configurar el modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpiar la tabla

        // Obtener los datos de la consulta y agregarlos a la tabla
        while (rs.next()) {
            int idinforme = rs.getInt("idinforme");
            int mes = rs.getInt("mes");
            int anio = rs.getInt("anio");
            double totalVentas = rs.getDouble("total_ventas");
            double totalCompras = rs.getDouble("total_compras");
            double ganancias = rs.getDouble("ganancia");
            double perdidas = rs.getDouble("perdida");

            model.addRow(new Object[]{idinforme, mes, anio, totalVentas, totalCompras, perdidas, ganancias});
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public static void obtenerInformeMensualGrafico() {
        ConexionBD conexionBD = new ConexionBD();
        try (Connection conn = conexionBD.conexion()) {
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM informe_mensual";
            ResultSet rs = stmt.executeQuery(query);

            // Crear el conjunto de datos para el gráfico de barras separadas
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            // Obtener los datos de la consulta y agregarlos al conjunto de datos
            while (rs.next()) {
                int idinforme = rs.getInt("idinforme");
                int mes = rs.getInt("mes");
                int anio = rs.getInt("anio");
                double ganancias = rs.getDouble("ganancia");
                double perdidas = rs.getDouble("perdida");

                dataset.addValue(ganancias, "Ganancias", String.valueOf(mes + "-" + anio));
                dataset.addValue(perdidas, "Pérdidas", String.valueOf(mes + "-" + anio));
            }

            rs.close();
            stmt.close();

            // Crear el gráfico de barras separadas
            JFreeChart chart = ChartFactory.createBarChart("Informe Mensual", "Mes-Año", "Valor",
                    dataset);

            // Obtener el plot del gráfico
            CategoryPlot plot = chart.getCategoryPlot();

            // Obtener el renderizador de barras
            BarRenderer renderer = (BarRenderer) plot.getRenderer();

            // Configurar colores de las barras
            renderer.setSeriesPaint(0, Color.BLUE); // Ganancias (azul)
            renderer.setSeriesPaint(1, Color.RED); // Pérdidas (rojo)

            // Crear un ChartPanel y agregarlo a un JFrame
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(500, 400));
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(chartPanel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void buscarPerdidas(String busca, JTable table) {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0); // Limpiar la tabla antes de llenarla con los resultados de la búsqueda
    ConexionBD conexionBD = new ConexionBD();
    try (Connection conn = conexionBD.conexion()) {
        String sql = "SELECT pdd.id, pdd.fecha, pdt.nombre, pdd.cantidad, pdd.motivo "
                + "FROM perdidas AS pdd "
                + "LEFT JOIN productos AS pdt ON pdd.producto = pdt.idproducto "
                + "WHERE pdd.id LIKE ? OR pdd.fecha LIKE ? OR pdt.nombre LIKE ? OR pdd.cantidad LIKE ? OR pdd.motivo LIKE ? "
                + "ORDER BY pdd.id";

        PreparedStatement statement = conn.prepareStatement(sql);

        String searchTerm = "%" + busca + "%";
        for (int i = 1; i <= 5; i++) {
            statement.setString(i, searchTerm);
        }

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // Obtener los datos del resultado y agregarlos a la tabla
            int id = resultSet.getInt("id");
            String fecha = resultSet.getString("fecha");
            String producto = resultSet.getString("nombre");
            int cantidad = resultSet.getInt("cantidad");
            String motivo = resultSet.getString("motivo");

            model.addRow(new Object[]{id, fecha, producto, cantidad, motivo});
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public static boolean registrarPerdida(int producto, int cantidad, String motivo) {
        LocalDate fechaActual = LocalDate.now();
        Date fecha = Date.valueOf(fechaActual);

        ConexionBD conexionBD = new ConexionBD();
        try (Connection conn = conexionBD.conexion()) {
            String sql = "INSERT INTO perdidas (fecha, producto, cantidad, motivo) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDate(1, fecha);
            statement.setInt(2, producto);
            statement.setInt(3, cantidad);
            statement.setString(4, motivo);

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0; // Retorna true si se insertaron filas, o false en caso contrario

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
