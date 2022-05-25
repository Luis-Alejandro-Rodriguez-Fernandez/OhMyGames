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
public class ServiceContarProductos extends Service {

    @Override
    public void process() throws ServletException, IOException {
        String json = "";
        int cat = Integer.parseInt(request.getParameter("cat"));
        int des = Integer.parseInt(request.getParameter("des"));
        int tipo = Integer.parseInt(request.getParameter("tipo"));
        double min = Integer.parseInt(request.getParameter("min"));
        double max = Integer.parseInt(request.getParameter("max"));

        try {
            int cant = bll.contarProductos(cat, des, tipo, min, max);

            if (cant > 0) {
                json = "{\"paginas\":" + cant + "}";
            }
        } catch (Exception e) {
            json = "{\"ok\":false,\"msg\":\"Error General1\"}";
        }
        forward(json.equals("") ? "{\"value\":null}" : json);
    }
}
