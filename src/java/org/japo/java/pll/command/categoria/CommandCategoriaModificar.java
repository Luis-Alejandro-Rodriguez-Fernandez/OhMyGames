/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.command.categoria;

import java.io.IOException;
import javax.servlet.ServletException;
import org.japo.java.entities.Categoria;
import org.japo.java.entities.Usuario;
import org.japo.java.pll.command.Command;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class CommandCategoriaModificar extends Command {

    @Override
    public void process() throws ServletException, IOException {

        String page = "page/categoria-modificar";
        Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
        int id = Integer.parseInt(request.getParameter("id"));
        if (u != null) {
            if (u.getGrupo() == 2) {

                Categoria c = bll.obtenerCategoria(id);

                request.setAttribute("categoria", c);

            }
        } else {
            page = "admin/login";
        }

        forward(page);
    }

}
