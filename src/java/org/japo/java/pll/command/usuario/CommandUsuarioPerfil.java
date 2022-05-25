/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.pll.command.usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import org.japo.java.entities.Usuario;
import org.japo.java.pll.command.Command;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class CommandUsuarioPerfil extends Command {

    @Override
    public void process() throws ServletException, IOException {
        System.out.println(request.getSession(false).getAttribute("usuario") != null);

        if (request.getSession(false).getAttribute("usuario") != null) {
            
            forward("page/perfil");
        } else {
            forward("admin/login");
        }
    }

}
