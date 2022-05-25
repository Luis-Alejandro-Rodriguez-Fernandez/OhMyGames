/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.command.producto;

import java.io.IOException;
import javax.servlet.ServletException;
import org.japo.java.entities.Favorito;
import org.japo.java.entities.Producto;
import org.japo.java.entities.Usuario;
import org.japo.java.pll.command.Command;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class CommandProductoConsulta extends Command {

    @Override
    public void process() throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Usuario user = (Usuario) request.getSession(false).getAttribute("usuario");

        
        
        Producto p = bll.obtenerProducto(id);
        Favorito f = null;
        if(user != null ){
         f = bll.obtenerFavorito(user.getId(),p.getId());
         System.out.println(f);

        }        
        request.setAttribute("producto", p);
        request.setAttribute("favorito", f);

        forward("page/producto-consulta");

    }
}
