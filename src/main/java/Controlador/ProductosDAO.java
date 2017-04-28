/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import edu.co.sergio.mundo.vo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Controlador.Conexion;
import java.net.URISyntaxException;

/**
 *
 * @author crist
 */
public class ProductosDAO {

    private Connection conexion;

    public ProductosDAO() throws URISyntaxException {
        this.conexion = Conexion.getConnection();
    }

    public void Insetar(int idP, String Nombre, int apellido, int tel) throws SQLException {

        String query = " insert into Producto (idproducto,descripcion,cantidad,valor)"
                + " values (?, ?, ?, ?)";

        PreparedStatement statement
                = this.conexion.prepareStatement(query);

        try {
            statement.setInt(1, idP);
            statement.setString(2, Nombre);
            statement.setInt(3, apellido);
            statement.setInt(4, tel);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Producto> Listar() {

        LinkedList<Producto> a = new LinkedList<Producto>();

        String query = "SELECT * FROM Producto";

        try {
           Statement statement =
                    this.conexion.createStatement();

            ResultSet rs = 
                    statement.executeQuery(query);

            while (rs.next()) {
                int codigoProducto = rs.getInt("idproducto");
                String descripcion = rs.getString("descripcion");
                int cantidad = rs.getInt("cantidad");
                int tel = rs.getInt("valor");

                Producto pro = new Producto(codigoProducto, descripcion, cantidad, tel);
                a.add(pro);
            }
            System.out.println(a);

        } catch (SQLException e) {
            System.out.println("Failed to make update!");
            e.printStackTrace();
        }

        return a;
    }

    public void Borrar(int id) {
        try {
            String query = "delete from Producto where idproducto = ?";
            
            PreparedStatement statement
                = this.conexion.prepareStatement(query);
            
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Failed to make update!");
            e.printStackTrace();
        }
    }

    public Producto Buscar(int id) {
        Producto pro = null;

        try {
            String query = "SELECT * FROM Producto where idproducto = ?";
            PreparedStatement statement
                = this.conexion.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                int codigoProveedor = rs.getInt("idproducto");
                String Nombre = rs.getString("descripcion");
                int Apellido = rs.getInt("cantidad");
                int tel = rs.getInt("valor");
                pro = new Producto(codigoProveedor, Nombre, Apellido, tel);
            }

        } catch (Exception ex) {
             Logger.getLogger(ProductosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pro;
    }

}
