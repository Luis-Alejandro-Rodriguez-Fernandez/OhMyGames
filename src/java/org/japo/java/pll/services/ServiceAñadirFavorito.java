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
public class ServiceAñadirFavorito extends Service {

    @Override
    public void process() throws ServletException, IOException {
        if (request.getSession(false).getAttribute("usuario") != null) {

            String json = "";

            try {

                int prod = Integer.parseInt(request.getParameter("prod"));
                int user = Integer.parseInt(request.getParameter("user"));

                boolean fav = bll.añadirFavorito(prod, user);

                json = "{\"fav\":" + fav + "}";
            } catch (Exception e) {
                json = "{\"ok\":false,\"msg\":\"Error General\"}";
            }

            forward(json);
        } else {
            forwardCmd("admin/login");
        }
    }

}
