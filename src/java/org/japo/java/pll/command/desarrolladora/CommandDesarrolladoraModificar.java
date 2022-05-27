/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.command.desarrolladora;

import java.io.IOException;
import javax.servlet.ServletException;
import org.japo.java.entities.Categoria;
import org.japo.java.entities.Desarrolladora;
import org.japo.java.entities.Usuario;
import org.japo.java.pll.command.Command;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class CommandDesarrolladoraModificar extends Command {

    @Override
    public void process() throws ServletException, IOException {

        String page = "page/desarrolladora-modificar";
        Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
        int id = Integer.parseInt(request.getParameter("id"));
        if (u != null) {
            if (u.getGrupo() == 2) {

                Desarrolladora c = bll.obtenerDesarrolladora(id);

                request.setAttribute("desarrolladora", c);

            }
        } else {
            page = "admin/login";
        }

        forward(page);
    }

}
