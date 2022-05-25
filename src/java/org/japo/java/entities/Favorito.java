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
public class Favorito {

    private int id;

    private Producto producto;

    private int usuario;

    public Favorito(int id, Producto producto, int usuario) {
        this.id = id;
        this.producto = producto;
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
        return "{\"id\":" + id + ",\"producto\":" + producto.toString() + ",\"usuario\":" + usuario + "}";
    }
}
