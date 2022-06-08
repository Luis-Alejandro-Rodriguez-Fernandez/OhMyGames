/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.services;

import java.io.IOException;
import javax.servlet.ServletException;
import org.japo.java.entities.Usuario;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class ServiceContarTransacciones extends Service{

    @Override
    public void process() throws ServletException, IOException {
        String json = "";
        Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
         try {
            long cant = bll.contarTransacciones(u.getId());

            if (cant > 0) {
                json = "{\"paginas\":" + cant + "}";
            }
        } catch (Exception e) {
            json = "{\"ok\":false,\"msg\":\"Error General1\"}";
        }
        forward(json.equals("") ? "{\"value\":null}" : json);
    }
    
}
