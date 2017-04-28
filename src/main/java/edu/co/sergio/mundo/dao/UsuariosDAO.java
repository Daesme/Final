/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;

import edu.co.sergio.mundo.vo.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.co.sergio.mundo.dao.Conexion;
import java.net.URISyntaxException;

/**
 *
 * @author crist
 */
public class UsuariosDAO {
        
    private final Connection conexion;

    public UsuariosDAO() throws URISyntaxException {
        this.conexion = Conexion.getConnection();
    }

    public void Insetar(String Nombre, int Pass) throws SQLException{

        String query = " insert into Usuarios (Nombre,Pass)"
                + " values (?, ?)";

        PreparedStatement statement= this.conexion.prepareStatement(query);

        try {
            statement.setString(1, Nombre);
            statement.setInt(2, Pass);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public Usuarios Buscar(String nombre) {
        Usuarios usu = null;

        try {
            String query = "SELECT * FROM Usuarios where Nombre = ?";
            PreparedStatement statement
                = this.conexion.prepareStatement(query);
            statement.setString(1, nombre);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                String Nombre = rs.getString("Nombre");
                int contra = rs.getInt("Pass");
                usu = new Usuarios(Nombre,contra);
                System.out.println(usu);
            }

        } catch (Exception ex) {
             Logger.getLogger(ProductosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usu;
    }
    
        public void ActualizarU(String Nombre, int NuevaContra) {

        try {
            String query = "update Usuarios set Pass = ? where Nombre = ?";
            PreparedStatement statement
                = this.conexion.prepareStatement(query);
            statement.setInt(1, NuevaContra);
            statement.setString(2, Nombre);
            statement.execute();

        } catch (SQLException e) {
            System.out.println("Failed to make update!");
            e.printStackTrace();
        }
    }
}
