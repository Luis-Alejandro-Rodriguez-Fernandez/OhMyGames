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
public class ServiceConsultarUsuarios extends Service {

    @Override
    public void process() throws ServletException, IOException {

            String json = "";

            try {

                String user = request.getParameter("user");
                String email = request.getParameter("email");

                System.out.println("algo");
                Usuario u = bll.obtenerUsuarioUs(user);
                Usuario ue = bll.obtenerUsuarioEmail(email);
                if (u == null && ue == null) {
                    json = "{\"result\":true}";
                } else if (u != null) {
                    json = "{\"result\":false,\"error\":\"El nombre de usuario ya está en uso\"}";
                } else if (ue != null) {
                    json = "{\"result\":false,\"error\":\"El correo electrónico ya está en uso\"}";
                } else {
                    json = "{\"result\":false,\"error\":null}";
                }

            } catch (Exception e) {
            }
            forward(json.equals("") ? "{\"result\":false,\"error\":null}" : json);
    }
}
