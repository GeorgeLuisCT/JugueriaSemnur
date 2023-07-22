package salidas;

import alertas.principal.AWTUtilities;
import alertas.principal.ErrorAlert;
import alertas.principal.SuccessAlert;
import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static salidas.Opciones.listaIDProductos;

public class ModalSalidas extends javax.swing.JDialog {

    Timer timer = null;
    TimerTask task;
    int i = 32;

    public ModalSalidas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        AWTUtilities.setOpaque(this, false);
        this.descripcion.setLineWrap(true);
        this.fecha.setText(fechaactual());
        // this.id.setVisible(false);
        Ubicar(0);
        this.descripcion.requestFocus();

        cargarComboBoxs();
         this.descripcion.requestFocus();
    }

    public static String fechaactual() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd-MM-YYYY");
        return formatofecha.format(fecha);
    }

    private void limpiarCampos() {

        this.descripcion.requestFocus();
        this.cantidad.setText("");
        this.descripcion.setText("");
        this.precioUnit.setText("");
        this.fecha.setText(fechaactual());

        Opciones.listar("");
        Opciones.totalGastos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel2 = new org.edisoncor.gui.panel.Panel();
        jPanel4 = new javax.swing.JPanel();
        cantidad = new app.bolivia.swing.JCTextField();
        jLabel6 = new javax.swing.JLabel();
        producto = new componentes.org1.SComboBox();
        jLabel10 = new javax.swing.JLabel();
        precioUnit = new app.bolivia.swing.JCTextField();
        jLabel11 = new javax.swing.JLabel();
        fecha = new app.bolivia.swing.JCTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        descripcion = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        cerrar = new principal.MaterialButton();
        titulo = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        limpiar = new principal.MaterialButton();
        id = new javax.swing.JLabel();
        registrar = new principal.MaterialButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alertas/img/fondo.png"))); // NOI18N
        panel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        jPanel4.setBorder(dropShadowBorder1);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cantidad.setBorder(null);
        cantidad.setForeground(new java.awt.Color(58, 159, 171));
        cantidad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cantidad.setPlaceholder("CANTIDAD");
        cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantidadKeyTyped(evt);
            }
        });
        jPanel4.add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 240, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OtrasImagenes/cantidad.png"))); // NOI18N
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        producto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        producto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                productoItemStateChanged(evt);
            }
        });
        jPanel4.add(producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 240, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/productos/campo-tipo.png"))); // NOI18N
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        precioUnit.setBorder(null);
        precioUnit.setForeground(new java.awt.Color(58, 159, 171));
        precioUnit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        precioUnit.setPlaceholder("PRECIO UNITARIO");
        precioUnit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                precioUnitKeyTyped(evt);
            }
        });
        jPanel4.add(precioUnit, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 240, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ventas/campo-precio.png"))); // NOI18N
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        fecha.setEditable(false);
        fecha.setBorder(null);
        fecha.setForeground(new java.awt.Color(58, 159, 171));
        fecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fecha.setPlaceholder("FECHA DE COMPRA");
        fecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fechaKeyTyped(evt);
            }
        });
        jPanel4.add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 240, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ventas/campo-calendario.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("DESCRIPCIÓN DEL GASTO");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 220, -1));

        descripcion.setColumns(20);
        descripcion.setRows(5);
        jPanel4.add(descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 230, 150));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salidas/icono-gasto.png"))); // NOI18N
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        panel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 400, 440));

        jPanel5.setBackground(new java.awt.Color(168, 207, 67));

        cerrar.setBackground(new java.awt.Color(255, 255, 255));
        cerrar.setForeground(new java.awt.Color(58, 159, 171));
        cerrar.setText("X");
        cerrar.setToolTipText("<html> <head> <style> #contenedor{background:white;color:black; padding-left:10px;padding-right:10px;margin:0; padding-top:5px;padding-bottom:5px;} </style> </head> <body> <h4 id=\"contenedor\">Cerrar</h4> </body> </html>");
        cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cerrar.setFont(new java.awt.Font("Roboto Medium", 1, 20)); // NOI18N
        cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cerrarMouseExited(evt);
            }
        });
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarActionPerformed(evt);
            }
        });

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("TITULO");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 10, 458, -1));

        jPanel7.setBackground(new java.awt.Color(168, 207, 67));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        limpiar.setBackground(new java.awt.Color(255, 255, 255));
        limpiar.setForeground(new java.awt.Color(58, 159, 171));
        limpiar.setText("LIMPIAR CAMPOS");
        limpiar.setToolTipText("<html> <head> <style> #contenedor{background:#3A9FAB;color:white; padding-left:10px;padding-right:10px;margin:0; padding-top:5px;padding-bottom:5px;} </style> </head> <body> <h4 id=\"contenedor\">Limpiar campos</h4> </body> </html>");
        limpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        limpiar.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });
        jPanel7.add(limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 170, 50));

        id.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        id.setForeground(new java.awt.Color(255, 255, 255));
        id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        id.setText("id");
        jPanel7.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 30, -1));

        registrar.setBackground(new java.awt.Color(255, 255, 255));
        registrar.setForeground(new java.awt.Color(58, 159, 171));
        registrar.setText("REGISTRAR");
        registrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        registrar.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        registrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                registrarKeyTyped(evt);
            }
        });
        jPanel7.add(registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 180, 50));

        panel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 340, 459, 250));

        getContentPane().add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 476, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarActionPerformed
        task = new TimerTask() {
            @Override
            public void run() {
                if (i == 0) {
                    Cerrar();
                } else {
                    Ubicar(i);
                    i -= 32;
                    Trasparencia((float) i / 352);
                }
            }
        };
        timer = new Timer();
        timer.schedule(task, 0, 2);
    }//GEN-LAST:event_cerrarActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_limpiarActionPerformed

    private void cantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyTyped
        char num = evt.getKeyChar();
        if ((num < '0' || num > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_cantidadKeyTyped

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        registrar();
        limpiarCampos();
    }//GEN-LAST:event_registrarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        task = new TimerTask() {
            @Override
            public void run() {
                if (i == 352) {
                    timer.cancel();
                } else {
                    Ubicar(i);
                    i += 32;
                    Trasparencia((float) i / 352);
                }
            }
        };
        timer = new Timer();
        timer.schedule(task, 0, 2);
    }//GEN-LAST:event_formWindowOpened

    private void registrarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_registrarKeyTyped
        registrar();
    }//GEN-LAST:event_registrarKeyTyped

    private void cerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarMouseEntered

        cerrar.setBackground(Color.red);
    }//GEN-LAST:event_cerrarMouseEntered

    private void cerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarMouseExited
        cerrar.setBackground(Color.white);
    }//GEN-LAST:event_cerrarMouseExited

    private void productoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_productoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_productoItemStateChanged

    private void precioUnitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioUnitKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_precioUnitKeyTyped

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        if (dialog == null) { // Verificar si el JDialog ya está creado
            dialog = new JDialog();
            dialog.setTitle("Calendario");
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setModal(true);
            dialog.setAlwaysOnTop(true);

            JPanel panel = new JPanel(new BorderLayout());

            JCalendar calendar = new JCalendar();
            panel.add(calendar, BorderLayout.CENTER);

            JButton selectButton = new JButton("Seleccionar");
            selectButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    java.util.Date selectedDateUtil = calendar.getDate();
                    long selectedTime = selectedDateUtil.getTime();
                    java.sql.Date selectedDateSql = new java.sql.Date(selectedTime);

                    System.out.println("Fecha seleccionada: " + selectedDateSql);

                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    String formattedDate = sdf.format(selectedDateSql);
                    fecha.setText(formattedDate);

                    dialog.dispose();
                    dialog = null;

                }
            });

            panel.add(selectButton, BorderLayout.SOUTH);

            dialog.getContentPane().add(panel);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
        }

        dialog.setVisible(true);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void fechaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fechaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModalSalidas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModalSalidas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModalSalidas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModalSalidas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModalSalidas dialog = new ModalSalidas(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static app.bolivia.swing.JCTextField cantidad;
    private principal.MaterialButton cerrar;
    public static javax.swing.JTextArea descripcion;
    public static app.bolivia.swing.JCTextField fecha;
    public static javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private principal.MaterialButton limpiar;
    private org.edisoncor.gui.panel.Panel panel2;
    public static app.bolivia.swing.JCTextField precioUnit;
    public static componentes.org1.SComboBox producto;
    public static principal.MaterialButton registrar;
    public static javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables

    // Declarar un arreglo para almacenar los ID de los productos
    private JDialog dialog;

    private void Cerrar() {
        this.dispose();
        timer = null;
        task = null;
    }

    private void Trasparencia(float trasp) {
        AWTUtilities.setOpacity(this, trasp);
    }

    private void Ubicar(int y) {
        this.setLocation(603, y - 200);
    }

    private void cargarComboBoxs() {

        String sqlProduc = Sentencias.SELECT_PRODUCTOS;
        Opciones.llenarComboBox(producto, sqlProduc);
    }

    private void registrar() {

        if (this.descripcion.getText().equals("") || this.cantidad.getText().equals("")
                || this.fecha.getText().equals("")) {

            ErrorAlert er = new ErrorAlert(new JFrame(), true);
            er.titulo.setText("OOPS...");
            er.msj.setText("FALTAN CAMPOS DE LLENAR");
            er.msj1.setText("");
            er.setVisible(true);

        } else {

            if (this.registrar.getText().equals("GUARDAR")) {

                Sentencias s = new Sentencias();

                int indiceSeleccionado = producto.getSelectedIndex();
                int idProductoSeleccionado = listaIDProductos.get(indiceSeleccionado);
                s.setIdproducto(idProductoSeleccionado);
                s.setCantidad(Float.parseFloat(this.cantidad.getText()));
                s.setPrecioUnitario(Double.parseDouble(precioUnit.getText()));
                Date fechaActual = new Date(System.currentTimeMillis());
                s.setFecha(fechaActual);
                s.setDescripcion(this.descripcion.getText());
                s.setIdcompra(Integer.parseInt(this.id.getText()));

                int opcion = Opciones.actualizar(s);
                if (opcion != 0) {
                    Opciones.listar("");
                    SuccessAlert sa = new SuccessAlert(new JFrame(), true);
                    sa.titulo.setText("¡HECHO!");
                    sa.msj.setText("SE HAN GUARDADO LOS CAMBIOS");
                    sa.msj1.setText("");
                    sa.setVisible(true);
                }

            } else {

                Sentencias s = new Sentencias();

                 int indiceSeleccionado = producto.getSelectedIndex();
                int idProductoSeleccionado = listaIDProductos.get(indiceSeleccionado);
                s.setIdproducto(idProductoSeleccionado);
                s.setCantidad(Integer.parseInt(this.cantidad.getText()));
                s.setPrecioUnitario(Double.parseDouble(precioUnit.getText()));
                Date fechaActual = new Date(System.currentTimeMillis());
                s.setFecha(fechaActual);
                s.setDescripcion(this.descripcion.getText());

                int opcion = Opciones.registrar(s);
                if (opcion != 0) {
                    limpiarCampos();
                    SuccessAlert sa = new SuccessAlert(new JFrame(), true);
                    sa.titulo.setText("¡HECHO!");
                    sa.msj.setText("SE HA REGISTRADO UNA");
                    sa.msj1.setText("NUEVA COMPRA");
                    sa.setVisible(true);
                }
            }
        }

    }
}
