package ventas;

import alertas.principal.ErrorAlert;
import alertas.principal.SuccessAlert;
import conexion.ConexionBD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import static ventas.Ventas.tablaVentas;

public class Opciones {

    static ConexionBD cc = new ConexionBD();
    public static Connection cn = cc.conexion();
    static PreparedStatement ps;

    public static void realizarVenta(List<Integer> productos, List<Float> cantidades, int idUsuario, float total, Date fecha, String nombreCliente, String direccionCliente, String telefonoCliente) throws SQLException {
        // Insertar el cliente en la tabla 'clientes'

        boolean suficienteStock = verificarSuficienteStock(productos, cantidades);
        if (!suficienteStock) {
            System.out.println("No hay suficiente stock disponible para realizar la venta.");
            ErrorAlert err = new ErrorAlert(new JFrame(), true);
            err.titulo.setText("STOCK VACIO");
            err.msj.setText("agunos productos para elaborar");
            err.msj1.setText("Este Producto no sin suficientes");
            err.setVisible(true);

            return; // Salir del método sin registrar la venta
        }

        String clienteQuery = "INSERT INTO clientes (nombre, direccion, telefono) VALUES (?, ?, ?)";
        int idCliente;
        try (PreparedStatement clienteStmt = cn.prepareStatement(clienteQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            clienteStmt.setString(1, nombreCliente);
            clienteStmt.setString(2, direccionCliente);
            clienteStmt.setString(3, telefonoCliente);
            clienteStmt.executeUpdate();

            // Obtener el ID del cliente generado
            try (ResultSet generatedKeys = clienteStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idCliente = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID del cliente.");
                }
            }
        }

        // Insertar la venta en la tabla 'ventas'
        String ventaQuery = "INSERT INTO ventas (idusuario, total, fecha) VALUES (?, ?, ?)";
        try (PreparedStatement ventaStmt = cn.prepareStatement(ventaQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ventaStmt.setInt(1, idUsuario);
            ventaStmt.setFloat(2, total);
            ventaStmt.setDate(3, fecha);
            ventaStmt.executeUpdate();

            // Obtener el ID de la venta generada
            int idVenta;
            try (ResultSet generatedKeys = ventaStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idVenta = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID de la venta.");
                }
            }

            // Insertar los detalles de la venta en la tabla 'detalle_venta'
            String detalleVentaQuery = "INSERT INTO detalle_venta (idventa, idproducto, cantidad) VALUES (?, ?, ?)";
            try (PreparedStatement detalleVentaStmt = cn.prepareStatement(detalleVentaQuery)) {
                for (int i = 0; i < productos.size(); i++) {
                    detalleVentaStmt.setInt(1, idVenta);
                    detalleVentaStmt.setInt(2, productos.get(i));
                    detalleVentaStmt.setFloat(3, cantidades.get(i));
                    detalleVentaStmt.executeUpdate();
                }
            }

            // Insertar la factura en la tabla 'facturas'
            String facturaQuery = "INSERT INTO facturas (idventa, idcliente, fecha) VALUES (?, ?, ?)";
            try (PreparedStatement facturaStmt = cn.prepareStatement(facturaQuery)) {
                facturaStmt.setInt(1, idVenta);
                facturaStmt.setInt(2, idCliente);
                facturaStmt.setDate(3, fecha);
                int filasAfectadas = facturaStmt.executeUpdate();

                if (filasAfectadas > 0) {
                    SuccessAlert sa = new SuccessAlert(new JFrame(), true);
                    sa.titulo.setText("¡HECHO!");
                    sa.msj.setText("SE HA REALIZADO ");
                    sa.msj1.setText("LA VENTA");
                    sa.setVisible(true);
                } else {
                    System.out.println("No se ha podido insertar la factura.");
                }
            }
        }
    }

    public static void generarPDFDesdeJTable(String cliente, String direccion, String telefo, String Fecha, String totalPagar) {
        try {
            // Generar el PDF con los datos del JTable
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                PDFont font = PDType1Font.HELVETICA_BOLD;
                int fontSize = 12;
                float leading = 1.5f * fontSize;

                contentStream.setFont(font, fontSize);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);

                contentStream.showText("JUGUERIA SEMNUR:");
                contentStream.newLine();
                contentStream.moveTextPositionByAmount(0, -leading);
                
                contentStream.showText("Datos de la factura:");
                contentStream.newLine();
                contentStream.moveTextPositionByAmount(0, -leading);

                contentStream.showText("Fecha: " + Fecha);
                contentStream.newLine();
                contentStream.moveTextPositionByAmount(0, -leading);

                contentStream.showText("Cliente: " + cliente);
                contentStream.newLine();
                contentStream.moveTextPositionByAmount(0, -leading);

                contentStream.showText("Dirección: " + direccion);
                contentStream.newLine();
                contentStream.moveTextPositionByAmount(0, -leading);

                contentStream.showText("Teléfono: " + telefo);
                contentStream.newLine();
                contentStream.moveTextPositionByAmount(0, -leading);

                contentStream.newLine(); // Nueva línea antes de los detalles de los productos
                contentStream.showText("Productos vendidos:");
                contentStream.newLine();
                contentStream.moveTextPositionByAmount(0, -leading);

                contentStream.showText("NOMBRE DE PRODUCTO: " + "      CANTIDAD: " + "      PRECIO: " + "      TOTAL:");
                contentStream.newLine();
                contentStream.moveTextPositionByAmount(0, -leading);

                // Generar el contenido de los productos vendidos desde el JTable
                for (int row = 0; row < tablaVentas.getRowCount(); row++) {
                    String producto = tablaVentas.getValueAt(row, 1).toString();
                    String cantidad = tablaVentas.getValueAt(row, 2).toString();
                    String precio = tablaVentas.getValueAt(row, 3).toString();
                    String total = tablaVentas.getValueAt(row, 4).toString();

                    contentStream.showText(producto + "                           " + cantidad + "                  " + precio + "               " + total);
                    contentStream.newLine();
                    contentStream.moveTextPositionByAmount(0, -leading);
                }

                contentStream.newLine(); // Nueva línea antes de los detalles de los productos
                contentStream.showText("Total  a pagar: " + totalPagar);
                contentStream.newLine();
                contentStream.moveTextPositionByAmount(0, -leading);

                contentStream.endText();
            }

            document.save("informe.pdf");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean verificarSuficienteStock(List<Integer> productos, List<Float> cantidades) throws SQLException {
        for (int i = 0; i < productos.size(); i++) {
            int idProducto = productos.get(i);
            float cantidad = cantidades.get(i);

            // Consultar el stock actual del producto
            String stockQuery = "SELECT stock FROM productos WHERE idproducto = ?";
            try (PreparedStatement stockStmt = cn.prepareStatement(stockQuery)) {
                stockStmt.setInt(1, idProducto);
                try (ResultSet stockResult = stockStmt.executeQuery()) {
                    if (stockResult.next()) {
                        float stockActual = stockResult.getFloat("stock");
                        if (stockActual < cantidad) {
                            return false; // No hay suficiente stock disponible
                        }
                    } else {
                        throw new SQLException("No se encontró el producto con ID: " + idProducto);
                    }
                }
            }
        }
        return true; // Hay suficiente stock para todos los productos
    }

    public static int actualizar(Sentencias uc) {
        int rsu = 0;
        String sql = Sentencias.ACTUALIZAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, uc.getCantidad());
            ps.setDouble(2, uc.getTotal());
            ps.setString(3, uc.getFecha());
            ps.setInt(4, uc.getId_producto());
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
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
            er.msj.setText("YA SE GENERO");
            er.msj1.setText("LA FACTURA");
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
            er.msj.setText("NO ES POSIBLE ESTE SUCESO!");
            er.msj1.setText("CONTACTE AL INGE...");
            er.setVisible(true);
        }
        System.out.println(sql);
        return rsu;
    }

    public static void listar(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) ventas.Productos.tabla.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (busca.equals("")) {
            sql = Sentencias.LISTAR;
        } else {
            sql = "SELECT * FROM productos_Ventas WHERE (idproducto LIKE '" + busca + "%' OR "
                    + "nombre LIKE '" + busca + "%' OR descripcion LIKE '" + busca + "%' OR "
                    + "precio LIKE '" + busca + "%') "
                    + "ORDER BY idproducto";
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
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void listarVentas(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) ventas.ListaVentas.tabla.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        String sql = "";
        if (busca.equals("")) {
            sql = "SELECT * FROM ventas "
                    + "JOIN productos ON ventas.idproducto = productos.idproducto";
        } else {
            sql = "SELECT * FROM ventas "
                    + "JOIN productos ON ventas.idproducto = productos.idproducto "
                    + "WHERE (idventa LIKE '" + busca + "%' OR "
                    + "nombre LIKE '" + busca + "%' OR "
                    + "descripcion LIKE '" + busca + "%' OR "
                    + "total LIKE '" + busca + "%' OR "
                    + "fecha LIKE '" + busca + "%') "
                    + "AND ventas.idproducto = productos.idproducto";
        }

        String datos[] = new String[5];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("idventa");
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("idusuario");
                datos[3] = rs.getString("total");
                datos[4] = rs.getString("fecha");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void listarEntradas(String fecha) {
        DefaultTableModel modelo = (DefaultTableModel) ventas.ModalCorteDia.ListaEntradas.getModel();

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println(sqlDate);
        String sql = "SELECT * FROM ventas, productos WHERE fecha = '" + sqlDate + "' AND ventas.idproducto = productos.idproducto";

        String datos[] = new String[3];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("nombre");
                datos[1] = (rs.getString("idusuario") + " - " + rs.getString("cantidad"));
                datos[2] = rs.getString("total");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        corteEntradas();
    }

    public static void corteEntradas() {
        int filas = ventas.ModalCorteDia.ListaEntradas.getRowCount();
        double totalE = 0.0;
        for (int i = 0; i < filas; i++) {
            totalE = totalE + Double.parseDouble(ventas.ModalCorteDia.ListaEntradas.getValueAt(i, 2).toString());
        }
        ventas.ModalCorteDia.lblE.setText(String.valueOf(totalE));
    }

    public static void listarSalidas(String fecha) {
        /*  DefaultTableModel modelo = (DefaultTableModel) ventas.ModalCorteDia.ListaSalidas.getModel();

        String sql = "SELECT * FROM gastos WHERE fecha_gasto = '" + fecha + "'";
        String datos[] = new String[2];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("descripcion");
                datos[1] = rs.getString("gastado");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        corteSalidas();*/
    }

    public static void corteSalidas() {
        int filas = ventas.ModalCorteDia.ListaSalidas.getRowCount();
        double totalE = 0.0;
        for (int i = 0; i < filas; i++) {
            totalE = totalE + Double.parseDouble(ventas.ModalCorteDia.ListaSalidas.getValueAt(i, 1).toString());
        }
        ventas.ModalCorteDia.lblS.setText(String.valueOf(totalE));
    }

    public static void corteTotal() {
        double entradas = Double.parseDouble(ventas.ModalCorteDia.lblE.getText());
        double salidas = Double.parseDouble(ventas.ModalCorteDia.lblS.getText());
        ventas.ModalCorteDia.total.setText(String.valueOf(entradas - salidas));
    }

    public static void calcular() {
        String pre;
        String can;
        double total = 0;
        double precio;
        int cantidad;
        double imp = 0.0;

        for (int i = 0; i < ventas.Ventas.tablaVentas.getRowCount(); i++) {
            pre = ventas.Ventas.tablaVentas.getValueAt(i, 4).toString();
            can = ventas.Ventas.tablaVentas.getValueAt(i, 3).toString();
            precio = Double.parseDouble(pre);
            cantidad = Integer.parseInt(can);
            imp = precio * cantidad;
            total = total + imp;
            ventas.Ventas.tablaVentas.setValueAt(Math.rint(imp * 100) / 100, i, 5);

        }
        ventas.Ventas.lblTotal.setText("" + Math.rint(total * 100) / 100);
    }

    public static void corteCaja() {
        int filas = ventas.ListaVentas.tabla.getRowCount();
        double totalE = 0.0;
        for (int i = 0; i < filas; i++) {
            totalE = totalE + Double.parseDouble(ventas.ListaVentas.tabla.getValueAt(i, 3).toString());
        }
        ventas.ListaVentas.lblTotal1.setText(String.valueOf(totalE));
    }

    public static void enviar(int codigo, int cantidad) {
        DefaultTableModel tabladet = (DefaultTableModel) ventas.Ventas.tablaVentas.getModel();
        String[] dato = new String[6];
        int fila = ventas.Productos.tabla.getSelectedRow();

        String sql = "SELECT * FROM productos_Ventas WHERE idproducto = " + codigo;
        String cod = String.valueOf(codigo);
        String nombre = null;
        String descripcion = null;
        String precio = null;
        String cant = String.valueOf(cantidad);
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nombre = rs.getString("nombre");
                descripcion = rs.getString("descripcion");
                precio = rs.getString("precio");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        int c = 0;
        int j = 0;

        for (int i = 0; i < ventas.Ventas.tablaVentas.getRowCount(); i++) {
            Object com = ventas.Ventas.tablaVentas.getValueAt(i, 0);
            Object cant1 = ventas.Ventas.tablaVentas.getValueAt(i, 3);
            if (cod.equals(com)) {
                j = i;
                int cantT = Integer.parseInt(cant) + Integer.parseInt((String) cant1);
                ventas.Ventas.tablaVentas.setValueAt(String.valueOf(cantT), i, 3);
                c++;
                calcular();
                ventas.Ventas.txtImporte.setText("");
                ventas.Ventas.txtCambio.setText("");
            }
        }
        if (c == 0) {

            dato[0] = cod;
            dato[1] = nombre;
            dato[2] = descripcion;
            dato[3] = cant;
            dato[4] = precio;

            tabladet.addRow(dato);

            ventas.Ventas.tablaVentas.setModel(tabladet);
            calcular();

            ventas.Ventas.txtImporte.setText("");
            ventas.Ventas.txtCambio.setText("");
        }
    }

    public static void numerosVenta() {
        int c = 0;
        String SQL = "SELECT MAX(idventa) FROM ventas";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getInt(1);
            }

            if (c == 0) {
                ventas.Ventas.numVenta.setText("1");
            } else {
                ventas.Ventas.numVenta.setText(String.valueOf(c + 1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int obtenerIdUsuarioPorNombre(String nombreUsuario) {
        int idUsuario = -1;

        try {
            String sql = "SELECT idusuario FROM usuarios WHERE usuario = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, nombreUsuario);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                idUsuario = rs.getInt("idusuario");
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            // Manejar el error adecuadamente
        }

        return idUsuario;
    }

}
