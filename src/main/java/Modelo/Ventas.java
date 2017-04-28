/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author crist
 */
public class Ventas {
    
    private int IdFactura;
    private int IdEmpleado;
    private int IdProducto;
    private int valor;

    public Ventas(int IdFactura, int IdEmpleado, int IdProducto, int valor) {
        this.IdFactura = IdFactura;
        this.IdEmpleado = IdEmpleado;
        this.IdProducto = IdProducto;
        this.valor = valor;
    }

    public int getIdFactura() {
        return IdFactura;
    }

    public void setIdFactura(int IdFactura) {
        this.IdFactura = IdFactura;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
    
}
