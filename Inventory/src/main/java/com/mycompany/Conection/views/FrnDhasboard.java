package com.mycompany.Conection.views;



import com.mycompany.Conection.MdlProducto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
public class FrnDhasboard extends javax.swing.JFrame {

   
    public JTable tablaDatos;
    public DefaultTableModel modelo;
    public JMenuBar menuBar;
    public JMenu menuFile;
    public JMenuItem menuItemUsuarios, menuItemProductos;

    public FrnDhasboard() {
        setTitle("Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(240, 240, 240)); 
        setLayout(new BorderLayout());

        
        tablaDatos = new JTable();
        modelo = new DefaultTableModel();
        tablaDatos.setModel(modelo);
        tablaDatos.setRowHeight(30);
        tablaDatos.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaDatos.setForeground(Color.WHITE);
        tablaDatos.setBackground(new Color(40, 40, 40)); 
        tablaDatos.setSelectionBackground(new Color(100, 100, 255)); 
        tablaDatos.setSelectionForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tablaDatos);
        add(scrollPane, BorderLayout.CENTER);

        // Crear la barra de menú con colores personalizados
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(50, 50, 50)); // Fondo de la barra de menú
        menuFile = new JMenu("menus del inventario");
        menuFile.setFont(new Font("Arial", Font.BOLD, 14));
        menuFile.setForeground(Color.WHITE);

        menuItemUsuarios = new JMenuItem("Usuarios");
        menuItemUsuarios.setFont(new Font("Arial", Font.PLAIN, 14));
        menuItemUsuarios.setBackground(new Color(80, 80, 80));
        menuItemUsuarios.setForeground(Color.WHITE);

        menuItemProductos = new JMenuItem("Productos");
        menuItemProductos.setFont(new Font("Arial", Font.PLAIN, 14));
        menuItemProductos.setBackground(new Color(80, 80, 80));
        menuItemProductos.setForeground(Color.WHITE);

        menuFile.add(menuItemUsuarios);
        menuFile.add(menuItemProductos);
        menuBar.add(menuFile);
        setJMenuBar(menuBar);

        // Agregar eventos a los menús
        menuItemUsuarios.addActionListener(e -> cargarUsuarios());
        menuItemProductos.addActionListener(e -> cargarProductos());
    }

    // Método para obtener la conexión a la base de datos
    private Connection obtenerConexion() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory?serverTimezone=UTC", "root", "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }

    // Cargar usuarios
    private void cargarUsuarios() {
        modelo.setRowCount(0);
        modelo.setColumnIdentifiers(new String[]{"ID", "Username", "Fullname"});

        try (Connection con = obtenerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, username, fullname FROM usuario")) {

            while (rs.next()) {
                Object[] fila = {
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("fullname")
                };
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cargar productos
    private void cargarProductos() {
        modelo.setRowCount(0);
        modelo.setColumnIdentifiers(new String[]{"ID", "Código", "Producto", "Precio", "Stock"});

        ArrayList<MdlProducto> productos = MdlProducto.obtenerProductos();
        for (MdlProducto p : productos) {
            Object[] fila = {
                    p.getId(),
                    p.getCode(),
                    p.getProduc(),
                    p.getPrice(),
                    p.getStock()
            };
            modelo.addRow(fila);
        }
    }

    // Método main para probar el Dashboard
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrnDhasboard().setVisible(true));
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  


       

       
     
            
         


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
