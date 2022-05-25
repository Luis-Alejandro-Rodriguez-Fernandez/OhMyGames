package org.japo.java.pll.command.producto;

import java.io.IOException;
import java.util.List;
import org.japo.java.entities.Carrito;
import org.japo.java.entities.Usuario;
import java.util.List;
import javax.servlet.ServletException;
import org.japo.java.entities.Categoria;
import org.japo.java.entities.Desarrolladora;
import org.japo.java.entities.Producto;
import org.japo.java.pll.command.Command;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class CommandProductoModificar extends Command {

    @Override
    public void process() throws ServletException, IOException {
        String page = "page/modificar-producto";
        if (request.getSession(false).getAttribute("usuario") != null) {
            Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
            if (u.getGrupo() == 2) {

                int id = Integer.parseInt(request.getParameter("id"));

                Producto p = bll.obtenerProducto(id);

                List<Categoria> cs = bll.listarCategorias();
                List<Desarrolladora> ds = bll.listarDesarrolladoras();

                request.setAttribute("lista-categorias", cs);
                request.setAttribute("lista-desarrolladoras", ds);
                request.setAttribute("producto", p);
            }
        } else {
            page = "admin/login";
        }
        System.out.println(page);
        forward(page);

    }
}
