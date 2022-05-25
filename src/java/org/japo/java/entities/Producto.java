/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.japo.java.libraries.IdDescription;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class Producto {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private int id;

    private String nombre;

    private double precio;

    private boolean tipo;

    private IdDescription categoria;

    private IdDescription desarrolladora;

    private String descripcion;

    private Date lanzamiento;

    private String imagen;

    private int descuento;

    public Producto(int id, String nombre, double precio, boolean tipo, IdDescription categoria, IdDescription desarrolladora, String descripcion, Date lanzamiento, String imagen, int descuento) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.categoria = categoria;
        this.desarrolladora = desarrolladora;
        this.descripcion = descripcion;
        this.lanzamiento = lanzamiento;
        this.imagen = imagen;
        this.descuento = descuento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public IdDescription getCategoria() {
        return categoria;
    }

    public void setCategoria(IdDescription categoria) {
        this.categoria = categoria;
    }

    public IdDescription getDesarrolladora() {
        return desarrolladora;
    }

    public void setDesarrolladora(IdDescription desarrolladora) {
        this.desarrolladora = desarrolladora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getLanzamiento() {
        return lanzamiento;
    }

    public void setLanzamiento(Date lanzamiento) {
        this.lanzamiento = lanzamiento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public String toStringFull() {
        return "{\"id\":" + id + ",\"nombre\":\"" + nombre + "\",\"precio\":" + precio + ",\"tipo\":"
                + tipo + ",\"categoria\":{ \"id\":" + categoria.getId() + ",\"description\":\"" + categoria.getDescription() + "\"},"
                + "\"desarrolladora\":{ \"id\":" + desarrolladora.getId() + ",\"description\":\"" + desarrolladora.getDescription() + "\"},"
                + "\"descripcion\":" + descripcion + ",\"fecha\":\"" + sdf.format(lanzamiento)
                + "\",\"imagen\":\"" + imagen + "\",\"descuento\":\"" + descuento + "\"}";
    }

    @Override
    public String toString() {
        return "{\"id\":" + id + ",\"nombre\":\"" + nombre + "\",\"precio\":" + precio + ",\"tipo\":"
                + tipo + ",\"imagen\":\"" + imagen + "\",\"descuento\":\"" + descuento + "\"}";
    }
}
