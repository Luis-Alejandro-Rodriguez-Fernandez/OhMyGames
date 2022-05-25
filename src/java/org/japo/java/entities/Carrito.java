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
public class Carrito {

    private int id;
    private Producto producto;
    private int productoI;
    private int usuario;

    public Carrito(int id, Producto producto, int usuario) {
        this.id = id;
        this.producto = producto;
        this.usuario = usuario;
    }

    public Carrito(int id, int producto, int usuario) {
        this.id = id;
        this.productoI = producto;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "{\"id\":" + id + ",\"usuario\":" + usuario + ",\"producto\":" + producto.toString() + "}";
    }
}
