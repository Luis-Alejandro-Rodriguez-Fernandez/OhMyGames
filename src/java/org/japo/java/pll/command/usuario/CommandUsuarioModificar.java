/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.command.usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import org.japo.java.pll.command.Command;
import org.japo.java.entities.Usuario;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class CommandUsuarioModificar extends Command {

    @Override
    public void process() throws ServletException, IOException {
        
        String page = "page/modificar-usuario";
        
        if (request.getSession(false).getAttribute("usuario") != null) {
            Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
        } else {
            page = "admin/login";
        }
            forward(page);
    }

}
