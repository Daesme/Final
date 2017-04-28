/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;

import edu.co.sergio.mundo.vo.Producto;
import edu.co.sergio.mundo.vo.Provedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.co.sergio.mundo.dao.Conexion;
import java.net.URISyntaxException;

/**
 *
 * @author crist
 */
public class ProvedorDAO {

    private final Connection conexion;

    public ProvedorDAO() throws URISyntaxException {
        this.conexion = Conexion.getConnection();
    }

    public void Insetar(int idP, String Nombre, String apellido, int tel) throws SQLException {

        String query = " insert into Proveedor (idProvedor,ProvedorName,ProveedorAp,tel)"
                + " values (?, ?, ?, ?)";

        PreparedStatement statement= this.conexion.prepareStatement(query);

        try {
            statement.setInt(1, idP);
            statement.setString(2, Nombre);
            statement.setString(3, apellido);
            statement.setInt(4, tel);
            statement.execute();
             statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public LinkedList<Provedor> Listar() {

        LinkedList<Provedor> a = new LinkedList<Provedor>();

        String query = "SELECT * FROM Proveedor";

        try {
           Statement statement =
                    this.conexion.createStatement();

            ResultSet rs = 
                    statement.executeQuery(query);

            while (rs.next()) {
                int codigoProveedor = rs.getInt("idProvedor");
                String Nombre = rs.getString("ProvedorName");
                String Apellido = rs.getString("ProveedorAp");
                int tel = rs.getInt("tel");

                Provedor pro = new Provedor(codigoProveedor, Nombre, Apellido, tel);
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
            String query = "delete from Proveedor where idProvedor = ?";
            
            PreparedStatement statement
                = this.conexion.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Failed to make update!");
            e.printStackTrace();
        }
    }

    public Provedor Buscar(int id) {
        Provedor pro = null;
        try {
            String query = "SELECT * FROM Proveedor where idProvedor = ?";
            PreparedStatement statement
                = this.conexion.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int codigoProveedor = rs.getInt("idProvedor");
                String Nombre = rs.getString("ProvedorName");
                String Apellido = rs.getString("ProveedorAp");
                int tel = rs.getInt("tel");
                pro = new Provedor(codigoProveedor, Nombre, Apellido, tel);
            }

        } catch (Exception ex) {
             Logger.getLogger(ProvedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pro;
    }
}
