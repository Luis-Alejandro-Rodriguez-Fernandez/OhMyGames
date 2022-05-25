/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.services;

import java.io.IOException;
import javax.servlet.ServletException;
import org.japo.java.entities.Producto;
import org.japo.java.entities.Usuario;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class ServiceEleminarProductoCarrito extends Service {

    @Override
    public void process() throws ServletException, IOException {
        if (request.getSession(false).getAttribute("usuario") != null) {

            String json = "";

            Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
            String nombre = request.getParameter("producto");
            try {
                Producto p = bll.obtenerProductoNombre(nombre);

                boolean result = bll.eliminarProductoCarrito(u.getId(), p.getId());
            } catch (Exception e) {
            }

            forward(json.equals("") ? "{\"value\":null}" : json);
        } else {
            forwardCmd("admin/login");
        }
    }
}
