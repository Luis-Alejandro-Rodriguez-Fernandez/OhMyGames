/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.command.categoria;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import org.japo.java.entities.Categoria;
import org.japo.java.entities.Usuario;
import org.japo.java.pll.command.Command;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class CommandCategoria extends Command {

    @Override
    public void process() throws ServletException, IOException {
        String page = "page/categorias";
        Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
        if (u != null) {
            if (u.getGrupo() == 2) {
                try {
                    List<Categoria> c = bll.listarCategorias();

                    request.setAttribute("categorias", c);
                } catch (Exception e) {
                }
            }
        } else {
            page = "admin/login";
        }
        forward(page);
    }

}
