/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;

import edu.co.sergio.mundo.vo.Empleado;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Labing
 */
public class empleados extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
            /* TODO output your page here. You may use following sample code. */

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            
            int codigo = Integer.valueOf(request.getParameter("Codigo"));
            String nombre2 = request.getParameter("NombreEmpleado");
            int dur = Integer.valueOf(request.getParameter("Duracion"));
            int pago = Integer.valueOf(request.getParameter("Pago"));
            
            /* TODO output your page here. You may use following sample code. */
            EmpleadosDAO d = new EmpleadosDAO();
            try {
                d.Insetar(codigo, nombre2, dur, pago);
                response.sendRedirect("EmpleadoNuevo.html");
                
            } catch (SQLException ex) {
                Logger.getLogger(empleados.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (URISyntaxException ex) {
            Logger.getLogger(empleados.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int codigo = Integer.valueOf(request.getParameter("idemp"));
            EmpleadosDAO c = new EmpleadosDAO();
            c.Borrar(codigo);
            response.sendRedirect("BorrarEmpleado.html");
        } catch (URISyntaxException ex) {
            Logger.getLogger(empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
