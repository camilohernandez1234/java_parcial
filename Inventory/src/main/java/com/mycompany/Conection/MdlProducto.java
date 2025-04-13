package com.mycompany.Conection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MdlProducto {
    private int id;
    private String code;
    private String produc;
    private double price;
    private int stock;  

    public MdlProducto(int id, String code, String produc, double price, int stock) {
        this.id = id;
        this.code = code;
        this.produc = produc;
        this.price = price;
        this.stock = stock;
    }

   
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getProduc() { return produc; }
    public void setProduc(String produc) { this.produc = produc; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

 
    public static ArrayList<MdlProducto> obtenerProductos() {
        ArrayList<MdlProducto> productos = new ArrayList<>();
        String sql = "SELECT id, code, produc, price, stock FROM products"; 

        Conection conexion = new Conection();
        Connection con = conexion.obtenerConexion();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String produc = rs.getString("produc");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");  

                MdlProducto producto = new MdlProducto(id, code, produc, price, stock);
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }
}

