/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;

import Modelo.Ventas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import edu.co.sergio.mundo.dao.Conexion;
import java.net.URISyntaxException;

/**
 *
 * @author user7
 */
public class VentasDAO {
    private  Connection conexion;

    public VentasDAO() throws URISyntaxException {
        this.conexion = Conexion.getConnection();
    }

    public void Insetar(int idF, int  idP, int idE, int VALOR) throws SQLException {

        String query = " insert into ventas (IdFactura,Idproducto,Idempledo,Cantidad)"
                + " values (?, ?, ?, ?)";

        PreparedStatement statement= this.conexion.prepareStatement(query);

        try {
            statement.setInt(1, idF);
            statement.setInt(2, idP);
            statement.setInt(3, idE);
            statement.setInt(4, VALOR);
            statement.execute();
             statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public LinkedList<Ventas> Listar() {

        LinkedList<Ventas> a = new LinkedList<Ventas>();

        String query = "SELECT * FROM ventas";

        try {
           Statement statement =
                    this.conexion.createStatement();

            ResultSet rs = 
                    statement.executeQuery(query);

            while (rs.next()) {
                int idfa = rs.getInt("IdFactura");
                int idpr = rs.getInt("Idproducto");
                int idemp = rs.getInt("Idempledo");
                int valor = rs.getInt("Cantidad");

                Ventas ve = new Ventas(idfa, idemp, idpr, valor);
                a.add(ve);
            }
            System.out.println(a);

        } catch (SQLException e) {
            System.out.println("Failed to make update!");
            e.printStackTrace();
        }
        return a;
    }

}
