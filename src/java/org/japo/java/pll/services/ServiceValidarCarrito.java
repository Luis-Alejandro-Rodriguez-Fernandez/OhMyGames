/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.services;

import java.io.IOException;
import javax.servlet.ServletException;

import org.japo.java.entities.Carrito;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class ServiceValidarCarrito extends Service {

    @Override
    public void process() throws ServletException, IOException {
        if (request.getSession(false).getAttribute("usuario") != null) {

            String json = "";

            try {

                int prod = Integer.parseInt(request.getParameter("prod"));
                int user = Integer.parseInt(request.getParameter("user"));

                Carrito car = bll.obtenerCarrito(user, prod);
                if (car != null) {
                    json = "{\"res\":" + false + "}";
                } else {
                    json = "{\"res\":" + true + "}";
                }

            } catch (Exception e) {
                json = "{\"res\":" + null + "}";

            }

            forward(json);
        } else {
            forwardCmd("admin/login");
        }
    }

}
