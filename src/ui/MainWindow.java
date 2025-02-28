/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.nio.file.*;
import org.json.JSONException;

/**
 *
 * @author Aless
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        this.setLocationRelativeTo(null); 
        jTree1.setCellRenderer(new CustomTreeRenderer());

    }

    private void cargarLogDesdeArchivo() {
    File archivo = new File("log.txt");
    if (!archivo.exists()) {
        return; // Si el archivo no existe, no se carga nada
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
        StringBuilder contenido = new StringBuilder();
        String linea;
        while ((linea = reader.readLine()) != null) {
            contenido.append(linea).append("\n");
        }
        taLog.setText(contenido.toString()); // Cargar el contenido en el JTextArea
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar el log: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void guardarLogEnArchivo() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"))) {
        writer.write(taLog.getText()); // Guarda todo el contenido del JTextArea
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al guardar el log: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void registrarLog(String accion, String nombre) {
    // Obtener el usuario actual
    String usuario = lbUsuact.getText();

    // Obtener la fecha y hora actual
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    String fechaHora = sdf.format(new Date());

    // Construir el mensaje del log
    String mensaje = "[" + fechaHora + "] " + usuario + " " + accion + ": " + nombre + "\n";

    // Agregar el mensaje al JTextArea (taLog)
    taLog.append(mensaje);
}

    private void cargarUsuarioDesdeArchivo() {
    File archivo = new File("usuario.txt");
    if (!archivo.exists()) {
        return; // Si el archivo no existe, no hace nada
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
        String usuarioGuardado = reader.readLine();
        if (usuarioGuardado != null && !usuarioGuardado.trim().isEmpty()) {
            lbUsuact.setText(usuarioGuardado);
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void guardarUsuarioEnArchivo(String usuario) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuario.txt"))) {
        writer.write(usuario);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al guardar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    private void guardarArbolEnJSON(DefaultMutableTreeNode root) {
    JSONObject jsonTree = guardarNodosEnJSON(root);
    try (FileWriter file = new FileWriter("treeData.json")) {
        file.write(jsonTree.toString(4)); // Indentación para hacer el JSON más legible
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al guardar el árbol: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private JSONObject guardarNodosEnJSON(DefaultMutableTreeNode node) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("nombre", node.getUserObject().toString());
    
    // **Solo guardar hijos si el nodo lo permite (para evitar errores con archivos)**
    JSONArray hijosArray = new JSONArray();
    if (node.getAllowsChildren()) {
        for (int i = 0; i < node.getChildCount(); i++) {
            hijosArray.put(guardarNodosEnJSON((DefaultMutableTreeNode) node.getChildAt(i)));
        }
    }
    
    jsonObject.put("hijos", hijosArray);
    return jsonObject;
}

private void cargarArbolDesdeJSON() {
    File archivo = new File("treeData.json");
    if (!archivo.exists()) {
        return; // Si el archivo no existe, no se carga nada
    }

    try {
        String contenido = new String(Files.readAllBytes(Paths.get("treeData.json")));
        JSONObject jsonTree = new JSONObject(contenido);
        
        DefaultMutableTreeNode root = cargarNodosDesdeJSON(jsonTree);
        jTree1.setModel(new DefaultTreeModel(root));
        
        // **Aplicar el renderizador después de cargar el árbol**
        jTree1.setCellRenderer(new CustomTreeRenderer());

    } catch (IOException | JSONException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar el árbol: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private DefaultMutableTreeNode cargarNodosDesdeJSON(JSONObject jsonObject) {
    String nombreNodo = jsonObject.getString("nombre");
    DefaultMutableTreeNode node = new DefaultMutableTreeNode(nombreNodo);

    // **Si el nombre tiene "[Tamaño:", es un archivo y NO debe tener hijos**
    if (nombreNodo.contains("[Tamaño:")) {
        node.setAllowsChildren(false);
    }

    JSONArray hijosArray = jsonObject.getJSONArray("hijos");
    for (int i = 0; i < hijosArray.length(); i++) {
        node.add(cargarNodosDesdeJSON(hijosArray.getJSONObject(i)));
    }
    
    return node;
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbModoact = new javax.swing.JLabel();
        btCambiarmodo = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lbUsuact = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btCambiarusu = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        tfNombre = new javax.swing.JTextField();
        tfLongitud = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        cbSelectortipo = new javax.swing.JComboBox<>();
        btCrear = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        tfSelectednode = new javax.swing.JTextField();
        btEditar = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taLog = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(34, 40, 49));

        jPanel3.setBackground(new java.awt.Color(57, 62, 70));

        jLabel1.setForeground(new java.awt.Color(238, 238, 238));
        jLabel1.setText("Actualmente te encuentras en modo:");

        lbModoact.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbModoact.setForeground(new java.awt.Color(238, 238, 238));
        lbModoact.setText("Usuario");

        btCambiarmodo.setText("Cambiar a modo Admin");
        btCambiarmodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCambiarmodoActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(238, 238, 238));
        jLabel6.setText("Usuario actual:");

        lbUsuact.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbUsuact.setForeground(new java.awt.Color(238, 238, 238));
        lbUsuact.setText("lbUsuact");

        jPanel7.setBackground(new java.awt.Color(95, 101, 110));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(238, 238, 238));
        jLabel7.setText("Cambiar usuario");

        jLabel8.setForeground(new java.awt.Color(238, 238, 238));
        jLabel8.setText("Ingresa tu nombre:");

        btCambiarusu.setText("Cambiar");
        btCambiarusu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCambiarusuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btCambiarusu))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCambiarusu))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbUsuact, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator2)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbModoact, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btCambiarmodo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCambiarmodo)
                    .addComponent(lbModoact)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbUsuact))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(57, 62, 70));
        jPanel4.setForeground(new java.awt.Color(238, 238, 238));

        jLabel3.setForeground(new java.awt.Color(238, 238, 238));
        jLabel3.setText("Nombre:");

        jLabel4.setForeground(new java.awt.Color(238, 238, 238));
        jLabel4.setText("Longitud:");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel5.setForeground(new java.awt.Color(238, 238, 238));
        jLabel5.setText("Tipo:");

        cbSelectortipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Archivo", "Directorio" }));
        cbSelectortipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSelectortipoActionPerformed(evt);
            }
        });

        btCrear.setText("Crear");
        btCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrearActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(238, 238, 238));
        jLabel9.setText("Crear Directorios y Archivos");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(tfNombre)
                            .addComponent(jLabel4)
                            .addComponent(tfLongitud, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(cbSelectortipo, 0, 175, Short.MAX_VALUE)
                    .addComponent(btCrear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cbSelectortipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(btCrear))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(57, 62, 70));

        tfSelectednode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSelectednodeActionPerformed(evt);
            }
        });

        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

        btEliminar.setText("Eliminar");
        btEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(238, 238, 238));
        jLabel2.setText("Editar/Eliminar Directorios y Archivos");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tfSelectednode, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(tfSelectednode, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEliminar)))
                .addGap(17, 23, Short.MAX_VALUE))
        );

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("JTree");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("JTree", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 875, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 514, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("SD y Tabla", jPanel2);

        jPanel5.setBackground(new java.awt.Color(34, 40, 49));

        taLog.setColumns(20);
        taLog.setRows(5);
        jScrollPane3.setViewportView(taLog);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(242, 242, 242));
        jLabel10.setText("Registro de Auditoria");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(195, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Log", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCambiarmodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCambiarmodoActionPerformed
       if (lbModoact.getText().equals("Usuario")) {
        lbModoact.setText("Admin");
        btCambiarmodo.setText("Cambiar a Usuario");
    } else {
        lbModoact.setText("Usuario");
        btCambiarmodo.setText("Cambiar a Admin");
    }
        
    }//GEN-LAST:event_btCambiarmodoActionPerformed

    private void tfSelectednodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSelectednodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSelectednodeActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
 if (lbModoact.getText().equals("Usuario")) {
        JOptionPane.showMessageDialog(this, "No tienes permisos para editar en modo Usuario.", "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
        return;
    }
         
    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree1.getSelectionPath().getLastPathComponent();
    
    if (selectedNode == null) {
        JOptionPane.showMessageDialog(this, "Selecciona un nodo para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String nuevoNombre = tfSelectednode.getText().trim();
    if (nuevoNombre.isEmpty()) {
        JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Obtener la información adicional del nodo sin modificarla
    String nodoTexto = selectedNode.getUserObject().toString();
    int index = nodoTexto.indexOf("[");
    String detalles = (index != -1) ? nodoTexto.substring(index) : ""; // Mantener todo lo demás

    // Actualizar el nodo con el nuevo nombre sin perder datos
    selectedNode.setUserObject(nuevoNombre + " " + detalles);

    DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();
    model.reload();
    
    registrarLog("modificó", nuevoNombre);
        
    }//GEN-LAST:event_btEditarActionPerformed

    private void btEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminarActionPerformed
    if (lbModoact.getText().equals("Usuario")) {
        JOptionPane.showMessageDialog(this, "No tienes permisos para eliminar en modo Usuario.", "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
        return;
    }

    DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();
    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree1.getSelectionPath().getLastPathComponent();

    if (selectedNode == null) {
        JOptionPane.showMessageDialog(this, "Por favor, selecciona un nodo para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Evitar eliminar la raíz
    DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();
    if (parentNode == null) {
        JOptionPane.showMessageDialog(this, "No se puede eliminar la raíz del árbol.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Confirmación antes de eliminar
    int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar '" + selectedNode.toString() + "'?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }
    
    String nombreEliminado = selectedNode.toString();
    // Si el nodo tiene hijos, eliminamos todos sus subnodos
    selectedNode.removeAllChildren(); 

    // Eliminar el nodo padre
    parentNode.remove(selectedNode);
    model.reload();
    
    
    registrarLog("eliminó", nombreEliminado);
    }//GEN-LAST:event_btEliminarActionPerformed

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
         // Mostrar solo el nombre del nodo seleccionado en el JTextField
    TreeSelectionModel smd = jTree1.getSelectionModel();
    if (smd.getSelectionCount() > 0) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree1.getSelectionPath().getLastPathComponent();
        String nodoTexto = selectedNode.getUserObject().toString();
        
        // Extraer solo el nombre antes del primer "["
        int index = nodoTexto.indexOf("[");
        if (index != -1) {
            tfSelectednode.setText(nodoTexto.substring(0, index).trim()); 
        } else {
            tfSelectednode.setText(nodoTexto.trim()); // Si no hay "[", es solo el nombre
        }
    }
    }//GEN-LAST:event_jTree1MouseClicked

    private void cbSelectortipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSelectortipoActionPerformed
                                                 
    if (cbSelectortipo.getSelectedItem().equals("Directorio")) {
        tfLongitud.setEnabled(false); // Desactivar campo de longitud
        tfLongitud.setText(""); // Limpiar el campo
    } else {
        tfLongitud.setEnabled(true); // Activar campo si es Archivo
    }

    }//GEN-LAST:event_cbSelectortipoActionPerformed

    private void btCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrearActionPerformed
                                     
                                   
    // Verificar si el usuario está en modo "Usuario"
    if (lbModoact.getText().equals("Usuario")) {
        JOptionPane.showMessageDialog(this, "No tienes permisos para crear elementos en modo Usuario.", "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String nombre = tfNombre.getText().trim();
    String tipo = cbSelectortipo.getSelectedItem().toString(); 
    String longitudStr = tfLongitud.getText().trim();

    if (nombre.isEmpty()) {
        JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    if (jTree1.getSelectionPath() == null) {
    JOptionPane.showMessageDialog(this, "Selecciona un nodo donde agregar el nuevo elemento.", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}


    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree1.getSelectionPath().getLastPathComponent();

    if (selectedNode == null) {
        JOptionPane.showMessageDialog(this, "Selecciona un nodo donde agregar el nuevo elemento.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // **🚨 Nueva verificación: No se pueden agregar hijos a un archivo 🚨**
    if (!selectedNode.getAllowsChildren()) {
        JOptionPane.showMessageDialog(this, "No puedes agregar hijos a un archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Obtener la fecha de creación
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    String fechaCreacion = sdf.format(new Date());

    // Definir permisos básicos
    String permisos = "rwx";  // (lectura, escritura, ejecución)

    DefaultMutableTreeNode newNode;
    if (tipo.equals("Archivo")) {
        if (longitudStr.isEmpty() || !longitudStr.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "La longitud debe ser un número entero mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
//
        int longitud = Integer.parseInt(longitudStr);
        if (longitud <= 0) {
            JOptionPane.showMessageDialog(this, "La longitud debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Formato del archivo con su información
        String infoArchivo = nombre + " [Tamaño: " + longitud + " | Permisos: " + permisos + " | Creado: " + fechaCreacion + "]";
        newNode = new DefaultMutableTreeNode(infoArchivo);

        //  **Evitar que el archivo tenga hijos**
        newNode.setAllowsChildren(false);
    } else {
        // Formato del directorio con su información
        String infoDirectorio = nombre + " [Directorio | Permisos: " + permisos + " | Creado: " + fechaCreacion + "]";
        newNode = new DefaultMutableTreeNode(infoDirectorio);
    }

    selectedNode.add(newNode);
    DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();
    model.reload();

    tfNombre.setText("");
    tfLongitud.setText("");
    
    registrarLog("creó", nombre);

    }//GEN-LAST:event_btCrearActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
         cargarArbolDesdeJSON();
         cargarUsuarioDesdeArchivo();
         cargarLogDesdeArchivo();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) jTree1.getModel().getRoot();
    guardarArbolEnJSON(root);
     guardarUsuarioEnArchivo(lbUsuact.getText());
     guardarLogEnArchivo();
    }//GEN-LAST:event_formWindowClosing

    private void btCambiarusuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCambiarusuActionPerformed
        String nuevoUsuario = jTextField1.getText().trim();

    if (nuevoUsuario.isEmpty()) {
        JOptionPane.showMessageDialog(this, "El nombre de usuario no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Cambiar el usuario en la etiqueta
    lbUsuact.setText(nuevoUsuario);

    // Guardar usuario en el archivo
    guardarUsuarioEnArchivo(nuevoUsuario);
    }//GEN-LAST:event_btCambiarusuActionPerformed

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCambiarmodo;
    private javax.swing.JButton btCambiarusu;
    private javax.swing.JButton btCrear;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btEliminar;
    private javax.swing.JComboBox<String> cbSelectortipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel lbModoact;
    private javax.swing.JLabel lbUsuact;
    private javax.swing.JTextArea taLog;
    private javax.swing.JTextField tfLongitud;
    private javax.swing.JTextField tfNombre;
    private javax.swing.JTextField tfSelectednode;
    // End of variables declaration//GEN-END:variables
}
