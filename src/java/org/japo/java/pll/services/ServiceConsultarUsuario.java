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
public class ServiceConsultarUsuario extends Service {

    @Override
    public void process() throws ServletException, IOException {

            String json = "";

            try {

                String user = request.getParameter("user");
                String pass = request.getParameter("pass");

                System.out.println(user);
                System.out.println(pass);
                Usuario u = bll.obtenerUsuarioUs(user);
                if (u != null && u.getPassword().trim().equals(pass)) {
                    json = "{\"exist\":true}";
                } else {
                    json = "{\"exist\":false}";
                }

            } catch (Exception e) {
            }
            forward(json.equals("") ? "{\"exist\":false}" : json);
        
    }
}
