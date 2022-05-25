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
public class ServiceConsultarGrupo extends Service {

    String json = "";

    @Override
    public void process() throws ServletException, IOException {
        Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
        if (u.getGrupo() == 2) {
            json = "{\"admin\":true}";
        } else {
            json = "{\"admin\":false}";
        }
        forward(json);
    }

}
