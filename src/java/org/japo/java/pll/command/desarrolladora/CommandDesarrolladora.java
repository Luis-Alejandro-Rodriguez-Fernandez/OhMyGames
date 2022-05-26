/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.command.desarrolladora;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import org.japo.java.entities.Desarrolladora;
import org.japo.java.entities.Usuario;
import org.japo.java.pll.command.Command;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class CommandDesarrolladora extends Command{

    @Override
    public void process() throws ServletException, IOException {
        String page = "page/categoiras";
        Usuario u = (Usuario) request.getSession(false).getAttribute("usuario");
        if (u != null) {
            if (u.getGrupo() == 2) {
                try {
                    List<Desarrolladora> c = bll.listarDesarrolladoras();

                    request.setAttribute("desarrolladoras", c);
                } catch (Exception e) {
                }
            }
        } else {
            page = "admin/login";
        }
        forward(page);
    }
    
}
