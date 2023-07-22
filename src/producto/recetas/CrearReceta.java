package producto.recetas;

import alertas.principal.ErrorAlert;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import producto.ModalProductoVentas;
import producto.OpcionesVentas;
import producto.ProductosVentas;
import producto.senteciasVentas;
import tabla.EstiloTablaHeader;
import tabla.EstiloTablaRenderer;
import tabla.MyScrollbarUI;

/**
 *
 * @author georg
 */
public class CrearReceta extends javax.swing.JDialog {

    /**
     * Creates new form Categorias
     *
     * @param parent
     * @param modal
     */
    public CrearReceta(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        CrearReceta.tablaProductos.getTableHeader().setDefaultRenderer(new EstiloTablaHeader());
        CrearReceta.tablaProductos.setDefaultRenderer(Object.class, new EstiloTablaRenderer());
        CrearReceta.tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.getViewport().setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.getVerticalScrollBar().setUI(new MyScrollbarUI());
        jScrollPane1.getHorizontalScrollBar().setUI(new MyScrollbarUI());

        CrearReceta.tablaIngredientes.getTableHeader().setDefaultRenderer(new EstiloTablaHeader());
        CrearReceta.tablaIngredientes.setDefaultRenderer(Object.class, new EstiloTablaRenderer());
        CrearReceta.tablaIngredientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.getViewport().setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.getVerticalScrollBar().setUI(new MyScrollbarUI());
        jScrollPane2.getHorizontalScrollBar().setUI(new MyScrollbarUI());
        //    ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);

        Opciones.listarProductosReceta("");
        
    }

    public static void seleccionaFila(String id) {
        for (int i = 0; i < tablaProductos.getRowCount(); i++) {
            if (id.equals(tablaProductos.getValueAt(i, 0).toString())) {
                tablaProductos.setRowSelectionInterval(i, i);
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cerrar = new principal.MaterialButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaIngredientes = new javax.swing.JTable();
        btnVentas = new principal.MaterialButtomRectangle();
        btnCancelar = new principal.MaterialButtomRectangle();
        btnMenos = new principal.MaterialButtonCircle();
        txtCantidad = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        nombreReceta = new app.bolivia.swing.JCTextField();
        jLabel7 = new javax.swing.JLabel();
        descripcionReceta = new app.bolivia.swing.JCTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        cantidadAlmacen = new javax.swing.JLabel();
        cantidadAlmacen1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocation(new java.awt.Point(300, 150));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(58, 159, 171));

        cerrar.setBackground(new java.awt.Color(255, 255, 255));
        cerrar.setForeground(new java.awt.Color(58, 159, 171));
        cerrar.setText("X");
        cerrar.setToolTipText("<html> <head> <style> #contenedor{background:white;color:black; padding-left:10px;padding-right:10px;margin:0; padding-top:5px;padding-bottom:5px;} </style> </head> <body> <h4 id=\"contenedor\">Cerrar</h4> </body> </html>");
        cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cerrar.setFont(new java.awt.Font("Roboto Medium", 1, 20)); // NOI18N
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarActionPerformed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/productos/productos.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CREACIÓN DE RECETA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(89, 89, 89)
                    .addComponent(jLabel4)
                    .addContainerGap(747, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel7.setBackground(new java.awt.Color(58, 159, 171));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaProductos.setBackground(new java.awt.Color(204, 204, 204));
        tablaProductos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tablaProductos.setForeground(new java.awt.Color(255, 255, 255));
        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "NOMBRE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaProductos.setDoubleBuffered(true);
        tablaProductos.setRowHeight(20);
        tablaProductos.setSelectionBackground(new java.awt.Color(0, 153, 255));
        tablaProductos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaProductos);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 407, 327));

        tablaIngredientes.setBackground(new java.awt.Color(204, 204, 204));
        tablaIngredientes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tablaIngredientes.setForeground(new java.awt.Color(255, 255, 255));
        tablaIngredientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "NOMBRE", " CANTIDAD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaIngredientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaIngredientes.setDoubleBuffered(true);
        tablaIngredientes.setRowHeight(20);
        tablaIngredientes.setSelectionBackground(new java.awt.Color(0, 153, 255));
        tablaIngredientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tablaIngredientes);

        jPanel7.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 546, 327));

        btnVentas.setBackground(new java.awt.Color(168, 207, 67));
        btnVentas.setForeground(new java.awt.Color(255, 255, 255));
        btnVentas.setText("GUARDAR");
        btnVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVentas.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });
        jPanel7.add(btnVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(663, 516, 141, 55));

        btnCancelar.setBackground(new java.awt.Color(204, 0, 0));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("CANCELAR");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCancelar.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel7.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(822, 516, 161, 55));

        btnMenos.setBackground(new java.awt.Color(168, 207, 67));
        btnMenos.setForeground(new java.awt.Color(255, 255, 255));
        btnMenos.setText("+");
        btnMenos.setToolTipText("<html> <head> <style> #contenedor{background:#3A9FAB;color:white; padding-left:10px;padding-right:10px;margin:0; padding-top:5px;padding-bottom:5px;} </style> </head> <body> <h4 id=\"contenedor\">Añadir</h4> </body> </html>");
        btnMenos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnMenos.setFont(new java.awt.Font("Roboto Medium", 1, 24)); // NOI18N
        btnMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenosActionPerformed(evt);
            }
        });
        jPanel7.add(btnMenos, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 60, 60));

        txtCantidad.setBorder(null);
        txtCantidad.setForeground(new java.awt.Color(58, 159, 171));
        txtCantidad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCantidad.setPlaceholder("CANTIDAD");
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });
        jPanel7.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 110, 30));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ventas/campo-cantidad.png"))); // NOI18N
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        nombreReceta.setEditable(false);
        nombreReceta.setBorder(null);
        nombreReceta.setForeground(new java.awt.Color(58, 159, 171));
        nombreReceta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nombreReceta.setPlaceholder("NOMBRE");
        nombreReceta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreRecetaKeyTyped(evt);
            }
        });
        jPanel7.add(nombreReceta, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 240, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/productos/campo-nombre.png"))); // NOI18N
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, -1, -1));

        descripcionReceta.setBorder(null);
        descripcionReceta.setForeground(new java.awt.Color(58, 159, 171));
        descripcionReceta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        descripcionReceta.setPlaceholder("DESCRIPCION");
        descripcionReceta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descripcionRecetaKeyTyped(evt);
            }
        });
        jPanel7.add(descripcionReceta, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, 240, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/productos/campo-nombre.png"))); // NOI18N
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel7.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 6, 10, 565));

        cantidadAlmacen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cantidadAlmacen.setForeground(new java.awt.Color(255, 255, 255));
        cantidadAlmacen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantidadAlmacen.setText("DETALLES DE LA RECETA");
        jPanel7.add(cantidadAlmacen, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, 29));

        cantidadAlmacen1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cantidadAlmacen1.setForeground(new java.awt.Color(255, 255, 255));
        cantidadAlmacen1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantidadAlmacen1.setText("PRODUCTOS DISPONIBLES");
        jPanel7.add(cantidadAlmacen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 29));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_cerrarActionPerformed

    private void nombreRecetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreRecetaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreRecetaKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
/*        char num = evt.getKeyChar();
        if ((num < '0' || num > '9')) {
            evt.consume();
        }*/
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void btnMenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenosActionPerformed
        if (this.tablaProductos.getRowCount() < 1) {
            ErrorAlert er = new ErrorAlert(new JFrame(), true);
            er.setAlwaysOnTop(true);
            er.titulo.setText("OOPS...");
            er.msj.setText("LA TABLA ESTA VACÍA");
            er.msj1.setText("");
            er.setVisible(true);
        } else {
            if (this.tablaProductos.getSelectedRowCount() < 1) {
                ErrorAlert er = new ErrorAlert(new JFrame(), true);
                er.setAlwaysOnTop(true);
                er.titulo.setText("OOPS...");
                er.msj.setText("SELECCIONA UN");
                er.msj1.setText("REGISTRO");
                er.setVisible(true);
            } else {
                if (this.txtCantidad.getText().equals("")) {
                    ErrorAlert er = new ErrorAlert(new JFrame(), true);
                    er.setAlwaysOnTop(true);
                    er.titulo.setText("OOPS...");
                    er.msj.setText("DEBES INGRESAR UNA");
                    er.msj1.setText("CANTIDAD");
                    er.setVisible(true);
                } else {

                    int indiceFilaSeleccionada = tablaProductos.getSelectedRow();
                    String codigo = tablaProductos.getValueAt(indiceFilaSeleccionada, 0).toString();
                    String nombre = tablaProductos.getValueAt(indiceFilaSeleccionada, 1).toString();
                    String cantidad = txtCantidad.getText();

                    DefaultTableModel modeloTabla2 = (DefaultTableModel) tablaIngredientes.getModel();

// Verificar si el registro ya existe en la tabla
                    int filaExistente = -1;
                    for (int i = 0; i < modeloTabla2.getRowCount(); i++) {
                        String codigoExistente = modeloTabla2.getValueAt(i, 0).toString();
                        if (codigoExistente.equals(codigo)) {
                            filaExistente = i;
                            break;
                        }
                    }

                    if (filaExistente != -1) {
                        // Actualizar el registro existente
                        modeloTabla2.setValueAt(nombre, filaExistente, 1);
                        modeloTabla2.setValueAt(cantidad, filaExistente, 2);
                    } else {
                        // Agregar un nuevo registro si no existe
                        modeloTabla2.addRow(new Object[]{codigo, nombre, cantidad});
                    }

                }
            }

        }
    }//GEN-LAST:event_btnMenosActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        this.dispose();
        producto.ProductosVentas.mp.dispose();

        RegistrarProductoVenta();

    }//GEN-LAST:event_btnVentasActionPerformed

    private void descripcionRecetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descripcionRecetaKeyTyped

    }//GEN-LAST:event_descripcionRecetaKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearReceta.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearReceta(new javax.swing.JDialog(), true).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private principal.MaterialButtomRectangle btnCancelar;
    private principal.MaterialButtonCircle btnMenos;
    private principal.MaterialButtomRectangle btnVentas;
    private javax.swing.JLabel cantidadAlmacen;
    private javax.swing.JLabel cantidadAlmacen1;
    private principal.MaterialButton cerrar;
    public static app.bolivia.swing.JCTextField descripcionReceta;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    public static app.bolivia.swing.JCTextField nombreReceta;
    public static javax.swing.JTable tablaIngredientes;
    public static javax.swing.JTable tablaProductos;
    private app.bolivia.swing.JCTextField txtCantidad;
    // End of variables declaration//GEN-END:variables

    private void RegistrarProductoVenta() {
        producto.senteciasVentas s = new senteciasVentas();
        s.setNombre(ModalProductoVentas.nombre.getText());
        s.setDescripcion(ModalProductoVentas.descripcion.getText());
        s.setPrecio(Double.parseDouble(ModalProductoVentas.precio.getText()));

        int opcion = producto.OpcionesVentas.registrar(s);
        if (opcion != 0) {
            int fila = OpcionesVentas.extraerID();

            // Registrar la receta
            Opciones.registrarReceta(nombreReceta.getText(), descripcionReceta.getText(), fila);
          
        } else {
            // Error al registrar el producto para venta
            // Manejar el error de acuerdo a tus necesidades
        }
    }

}
