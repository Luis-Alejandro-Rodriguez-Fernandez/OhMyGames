/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.services;

import java.io.IOException;
import javax.servlet.ServletException;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class ServiceContarProductosNombre extends Service {

    @Override
    public void process() throws ServletException, IOException {
        String json = "";
        String nombre = request.getParameter("name");

        try {
            int cant = bll.contarProductosNombre(nombre);

            if (cant >= 0) {
                json = "{\"paginas\":" + cant + "}";
            }
        } catch (Exception e) {
            json = "{\"ok\":false,\"msg\":\"Error General1\"}";
        }
        forward(json.equals("") ? "{\"value\":null}" : json);
    }
}
