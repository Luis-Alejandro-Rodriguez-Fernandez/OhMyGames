/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.command.desarrolladora;

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
public class CommandDesarrolladoraBorrar extends Command {

    @Override
    public void process() throws ServletException, IOException {
        String page = "redirect/redirect-des-list";
        Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
        int id = Integer.parseInt(request.getParameter("id"));
        if (u != null) {
            if (u.getGrupo() == 2) {
                try {
                    
                    boolean del = bll.borrarDesarrolladora(id);

                    
                    request.getSession(false).setAttribute("delDes", del);
                } catch (Exception e) {
                }
            }
        } else {
            page = "admin/login";
        }
        forward(page);
    }

}
