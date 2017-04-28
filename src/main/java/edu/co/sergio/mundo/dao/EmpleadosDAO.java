/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;

import Modelo.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URISyntaxException;

/**
 *
 * @author crist
 */
public class EmpleadosDAO {

    private Connection conexion;

    public EmpleadosDAO() throws URISyntaxException {
        this.conexion  = Conexion.getConnection();       
    }

    public void Insetar(int idP, String Nombre, int apellido, int tel) throws SQLException {

        try {
        String query = " insert into Empleado (idEmpleado,empleadoName,duracion,pago)"
                + " values (?, ?, ?, ?)";

        PreparedStatement statement= this.conexion.prepareStatement(query);

        
            statement.setInt(1, idP);
            statement.setString(2, Nombre);
            statement.setInt(3, apellido);
            statement.setInt(4, tel);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Empleado> Listar() {

        LinkedList<Empleado> a = new LinkedList<Empleado>();

        String query = "SELECT * FROM Empleado";

        try {
           Statement statement =
                    this.conexion.createStatement();

            ResultSet rs = 
                    statement.executeQuery(query);

            while (rs.next()) {
                int codigoProducto = rs.getInt("idEmpleado");
                String descripcion = rs.getString("empleadoName");
                int cantidad = rs.getInt("duracion");
                int tel = rs.getInt("pago");

                Empleado pro = new Empleado(codigoProducto, descripcion, cantidad, tel);
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
            String query = "delete from Empleado where idEmpleado = ?";
            
            PreparedStatement statement
                = this.conexion.prepareStatement(query);
            
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Failed to make update!");
            e.printStackTrace();
        }
    }

    public Empleado Buscar(int id) {
        Empleado pro = null;

        try {
            String query = "SELECT * FROM Empleado where idEmpleado = ?";
            PreparedStatement statement
                = this.conexion.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int codigoProveedor = rs.getInt("idEmpleado");
                String Nombre = rs.getString("empleadoName");
                int Apellido = rs.getInt("duracion");
                int tel = rs.getInt("pago");
                pro = new Empleado(codigoProveedor, Nombre, Apellido, tel);
            }

        } catch (Exception ex) {
             Logger.getLogger(EmpleadosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pro;
    }

}

