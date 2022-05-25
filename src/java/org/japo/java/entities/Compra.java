/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.entities;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class Compra {
    private int id;
    private int transaccion;
    private Producto producto;
    private double precio;

    public Compra(int id, int transaccion, Producto producto, double precio) {
        this.id = id;
        this.transaccion = transaccion;
        this.producto = producto;
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(int transaccion) {
        this.transaccion = transaccion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }


   
    
        @Override
    public String toString() {
        return "{\"id\":" + id + ",\"transaccion\":" + transaccion + ",\"producto\":" + producto.toString() + ",\"precio\":"+precio+"}";
    }
}
