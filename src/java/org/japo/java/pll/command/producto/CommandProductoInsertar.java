/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.command.producto;

import java.io.IOException;
import java.util.List;
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
public class CommandProductoInsertar extends Command {

    @Override
    public void process() throws ServletException, IOException {
        String page = "page/nuevo-producto";
        if (request.getSession(false).getAttribute("usuario") != null) {
            Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
            if (u.getGrupo() == 2) {

                List<Categoria> cs = bll.listarCategorias();
                List<Desarrolladora> ds = bll.listarDesarrolladoras();

                request.setAttribute("lista-categorias", cs);
                request.setAttribute("lista-desarrolladoras", ds);


            } else {
                page = "error/acceso-denegado";
            }

        } else {
            page = "admin/login";
        }
        forward(page);

    }

}
