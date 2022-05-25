/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class Transaccion {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    private int id;

    private int usuario;

    private Date fecha;

    private double importe;

    public Transaccion(int id, int usuario, Date fecha, double importe) {
        this.id = id;
        this.usuario = usuario;
        this.fecha = fecha;
        this.importe = importe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        return "{\"id\":" + id + ",\"usuario\":\"" + usuario + "\",\"fecha\":\"" + sdf.format(fecha) + "\",\"pass\":" + importe + "}";
    }

}
