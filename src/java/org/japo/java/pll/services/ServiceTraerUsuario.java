/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.services;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import org.japo.java.entities.Favorito;
import org.japo.java.entities.Usuario;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class ServiceTraerUsuario extends Service {

    @Override
    public void process() throws ServletException, IOException {
        if (request.getSession(false).getAttribute("usuario") != null) {

            String json = "";
            Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
            try {
                json = u.toString();
            } catch (Exception e) {
            }
            forward(json.equals("") ? "{\"value\":null}" : json);
        } else {
            forwardCmd("admin/login");
        }
    }

}
